
import java.util.*;

public class Account_management {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AccountManager mgr = new AccountManager();

        mgr.createAccount("Alice", "Checking", 1000);
        mgr.createAccount("Bob", "Savings", 500);

        while (true) {
            printMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": createAccount(mgr); break;
                case "2": listAccounts(mgr); break;
                case "3": deposit(mgr); break;
                case "4": withdraw(mgr); break;
                case "5": transfer(mgr); break;
                case "6": search(mgr); break;
                case "7": closeAccount(mgr); break;
                case "8": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Account Management System ===");
        System.out.println("1. Create account");
        System.out.println("2. List accounts");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Search by holder name");
        System.out.println("7. Close account");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createAccount(AccountManager mgr) {
        System.out.print("Holder name: ");
        String name = sc.nextLine().trim();
        System.out.print("Type (Checking/Savings): ");
        String type = sc.nextLine().trim();
        System.out.print("Initial deposit: ");
        double init = parseDouble(sc.nextLine().trim(), 0);
        Account a = mgr.createAccount(name, type, init);
        System.out.println("Created: " + a);
    }

    private static void listAccounts(AccountManager mgr) {
        System.out.println("Accounts:");
        for (Account a : mgr.listAccounts()) System.out.println("  " + a);
    }

    private static void deposit(AccountManager mgr) {
        try {
            System.out.print("Account ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.deposit(id, amt);
            System.out.println(ok ? "Deposit successful." : "Deposit failed (check ID/amount/status).");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void withdraw(AccountManager mgr) {
        try {
            System.out.print("Account ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.withdraw(id, amt);
            System.out.println(ok ? "Withdrawal successful." : "Withdrawal failed (insufficient funds or invalid).");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void transfer(AccountManager mgr) {
        try {
            System.out.print("From account ID: ");
            int from = Integer.parseInt(sc.nextLine().trim());
            System.out.print("To account ID: ");
            int to = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.transfer(from, to, amt);
            System.out.println(ok ? "Transfer successful." : "Transfer failed (check IDs/funds/status).");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void search(AccountManager mgr) {
        System.out.print("Search query: ");
        String q = sc.nextLine().trim();
        List<Account> res = mgr.searchByHolder(q);
        if (res.isEmpty()) System.out.println("No accounts found.");
        else for (Account a : res) System.out.println("  " + a);
    }

    private static void closeAccount(AccountManager mgr) {
        try {
            System.out.print("Account ID to close: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean ok = mgr.closeAccount(id);
            System.out.println(ok ? "Account closed." : "Account not found.");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static double parseDouble(String s, double def) {
        if (s.isEmpty()) return def;
        try { return Double.parseDouble(s); } catch (NumberFormatException ex) { return def; }
    }
}

class Account {
    private final int id;
    private String holderName;
    private String type;
    private double balance;
    private boolean closed;

    public Account(int id, String holderName, String type, double balance) {
        this.id = id;
        this.holderName = holderName;
        this.type = type;
        this.balance = balance;
        this.closed = false;
    }

    public int getId() { return id; }
    public String getHolderName() { return holderName; }
    public String getType() { return type; }
    public double getBalance() { return balance; }
    public boolean isClosed() { return closed; }

    public void setBalance(double balance) { this.balance = balance; }
    public void setClosed(boolean closed) { this.closed = closed; }

    @Override
    public String toString() {
        return "Account{id=" + id + ", holder='" + holderName + "', type='" + type +
                "', balance=" + balance + (closed ? ", CLOSED" : "") + "}";
    }
}

class AccountManager {
    private final List<Account> accounts = new ArrayList<>();
    private int nextId = 1;

    public Account createAccount(String holderName, String type, double initial) {
        Account a = new Account(nextId++, holderName, type, initial);
        accounts.add(a);
        return a;
    }

    public List<Account> listAccounts() {
        return accounts;
    }

    private Account findById(int id) {
        for (Account a : accounts) if (a.getId() == id) return a;
        return null;
    }

    public boolean deposit(int id, double amt) {
        Account a = findById(id);
        if (a == null || a.isClosed() || amt <= 0) return false;
        a.setBalance(a.getBalance() + amt);
        return true;
    }

    public boolean withdraw(int id, double amt) {
        Account a = findById(id);
        if (a == null || a.isClosed() || amt <= 0 || a.getBalance() < amt) return false;
        a.setBalance(a.getBalance() - amt);
        return true;
    }

    public boolean transfer(int fromId, int toId, double amt) {
        Account from = findById(fromId);
        Account to = findById(toId);
        if (from == null || to == null || from.isClosed() || to.isClosed()) return false;
        if (amt <= 0 || from.getBalance() < amt) return false;
        from.setBalance(from.getBalance() - amt);
        to.setBalance(to.getBalance() + amt);
        return true;
    }

    public List<Account> searchByHolder(String query) {
        List<Account> res = new ArrayList<>();
        String q = query.toLowerCase();
        for (Account a : accounts) {
            if (a.getHolderName().toLowerCase().contains(q)) res.add(a);
        }
        return res;
    }

    public boolean closeAccount(int id) {
        Account a = findById(id);
        if (a == null) return false;
        a.setClosed(true);
        return true;
    }
}