

import java.util.*;

public class Employee_management {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeManager mgr = new EmployeeManager();


        mgr.addDepartment("Engineering");
        mgr.addDepartment("HR");
        mgr.addEmployee("Alice Johnson", "Engineer", 75000);
        mgr.addEmployee("Bob Smith", "HR Manager", 65000);

        while (true) {
            printMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": addEmployee(mgr); break;
                case "2": listEmployees(mgr); break;
                case "3": searchEmployees(mgr); break;
                case "4": updateEmployee(mgr); break;
                case "5": removeEmployee(mgr); break;
                case "6": addDepartment(mgr); break;
                case "7": listDepartments(mgr); break;
                case "8": assignDepartment(mgr); break;
                case "9": listByDepartment(mgr); break;
                case "10": raiseSalary(mgr); break;
                case "11": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Employee Management System ===");
        System.out.println("1. Add employee");
        System.out.println("2. List employees");
        System.out.println("3. Search employees by name");
        System.out.println("4. Update employee");
        System.out.println("5. Remove employee");
        System.out.println("6. Add department");
        System.out.println("7. List departments");
        System.out.println("8. Assign department to employee");
        System.out.println("9. List employees by department");
        System.out.println("10. Raise salary");
        System.out.println("11. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addEmployee(EmployeeManager mgr) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Position: ");
        String pos = sc.nextLine().trim();
        System.out.print("Salary: ");
        double sal = parseDoubleInput(sc.nextLine().trim(), 0);
        Employee e = mgr.addEmployee(name, pos, sal);
        System.out.println("Added: " + e);
    }

    private static void listEmployees(EmployeeManager mgr) {
        System.out.println("Employees:");
        for (Employee e : mgr.listEmployees()) System.out.println("  " + e + (e.getDepartmentId() != null ? (" [dept:" + e.getDepartmentId() + "]") : ""));
    }

    private static void searchEmployees(EmployeeManager mgr) {
        System.out.print("Query: ");
        String q = sc.nextLine().trim();
        List<Employee> res = mgr.searchByName(q);
        if (res.isEmpty()) System.out.println("No matches.");
        else for (Employee e : res) System.out.println("  " + e);
    }

    private static void updateEmployee(EmployeeManager mgr) {
        try {
            System.out.print("Employee ID to update: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("New name (leave blank to keep): ");
            String nameIn = sc.nextLine().trim();
            String name = nameIn.isEmpty() ? null : nameIn;
            System.out.print("New position (leave blank to keep): ");
            String posIn = sc.nextLine().trim();
            String pos = posIn.isEmpty() ? null : posIn;
            System.out.print("New salary (leave blank to keep): ");
            String salstr = sc.nextLine().trim();
            Double sal = salstr.isEmpty() ? null : Double.parseDouble(salstr);
            boolean ok = mgr.updateEmployee(id, name, pos, sal);
            System.out.println(ok ? "Updated." : "Employee not found.");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void removeEmployee(EmployeeManager mgr) {
        try {
            System.out.print("Employee ID to remove: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean ok = mgr.removeEmployee(id);
            System.out.println(ok ? "Removed." : "Employee not found.");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void addDepartment(EmployeeManager mgr) {
        System.out.print("Department name: ");
        String name = sc.nextLine().trim();
        Department d = mgr.addDepartment(name);
        System.out.println("Added: " + d);
    }

    private static void listDepartments(EmployeeManager mgr) {
        System.out.println("Departments:");
        for (Department d : mgr.listDepartments()) System.out.println("  " + d);
    }

    private static void assignDepartment(EmployeeManager mgr) {
        try {
            System.out.print("Employee ID: ");
            int eid = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Department ID: ");
            int did = Integer.parseInt(sc.nextLine().trim());
            boolean ok = mgr.assignDepartment(eid, did);
            System.out.println(ok ? "Assigned." : "Check IDs.");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void listByDepartment(EmployeeManager mgr) {
        try {
            System.out.print("Department ID: ");
            int did = Integer.parseInt(sc.nextLine().trim());
            List<Employee> list = mgr.listByDepartment(did);
            if (list.isEmpty()) System.out.println("No employees in this department.");
            else for (Employee e : list) System.out.println("  " + e);
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void raiseSalary(EmployeeManager mgr) {
        try {
            System.out.print("Employee ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount to raise (positive number): ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.raiseSalary(id, amt);
            System.out.println(ok ? "Salary updated." : "Employee not found.");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static double parseDoubleInput(String s, double def) {
        if (s.isEmpty()) return def;
        try { return Double.parseDouble(s); } catch (NumberFormatException ex) { return def; }
    }
}

class Employee {
    private final int id;
    private String name;
    private String position;
    private double salary;
    private Integer departmentId;

    public Employee(int id, String name, String position, double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.departmentId = null;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public Integer getDepartmentId() { return departmentId; }

    public void setName(String name) { this.name = name; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', position='" + position +
                "', salary=" + salary + "}";
    }
}

class Department {
    private final int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}

class EmployeeManager {
    private final List<Employee> employees = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();
    private int nextEmployeeId = 1;
    private int nextDepartmentId = 1;

    public Employee addEmployee(String name, String position, double salary) {
        Employee e = new Employee(nextEmployeeId++, name, position, salary);
        employees.add(e);
        return e;
    }

    public List<Employee> listEmployees() {
        return employees;
    }

    private Employee findEmployeeById(int id) {
        for (Employee e : employees) if (e.getId() == id) return e;
        return null;
    }

    public List<Employee> searchByName(String query) {
        List<Employee> res = new ArrayList<>();
        String q = query.toLowerCase();
        for (Employee e : employees) {
            if (e.getName().toLowerCase().contains(q)) res.add(e);
        }
        return res;
    }

    public boolean updateEmployee(int id, String name, String position, Double salary) {
        Employee e = findEmployeeById(id);
        if (e == null) return false;
        if (name != null) e.setName(name);
        if (position != null) e.setPosition(position);
        if (salary != null) e.setSalary(salary);
        return true;
    }

    public boolean removeEmployee(int id) {
        Employee e = findEmployeeById(id);
        if (e == null) return false;
        employees.remove(e);
        return true;
    }

    public Department addDepartment(String name) {
        Department d = new Department(nextDepartmentId++, name);
        departments.add(d);
        return d;
    }

    public List<Department> listDepartments() {
        return departments;
    }

    private Department findDepartmentById(int id) {
        for (Department d : departments) if (d.getId() == id) return d;
        return null;
    }

    public boolean assignDepartment(int employeeId, int departmentId) {
        Employee e = findEmployeeById(employeeId);
        Department d = findDepartmentById(departmentId);
        if (e == null || d == null) return false;
        e.setDepartmentId(departmentId);
        return true;
    }

    public List<Employee> listByDepartment(int departmentId) {
        List<Employee> res = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getDepartmentId() != null && e.getDepartmentId() == departmentId) res.add(e);
        }
        return res;
    }

    public boolean raiseSalary(int id, double amount) {
        Employee e = findEmployeeById(id);
        if (e == null || amount <= 0) return false;
        e.setSalary(e.getSalary() + amount);
        return true;
    }
}