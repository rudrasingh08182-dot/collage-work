import java.util.Scanner;

public class StudentGread {
    void grade() {
        String name;
        long roll;
        byte python, javaScore, c;
        short total;
        float per;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter name :");
        name = sc.nextLine();
        System.out.println("Enter roll :");
        roll = sc.nextLong();
        System.out.println("Enter java :");
        javaScore = sc.nextByte();
        System.out.println("Enter python :");
        python = sc.nextByte();
        System.out.println("Enter c :");
        c = sc.nextByte();

        total = (short) (javaScore + python + c);
        per = total / 3.0f;

        System.out.println("Total = " + total);
        System.out.println("Percentage = " + per);

        if (per > 90) {
            System.out.println("Grade A");
        } else if (per > 75) {
            System.out.println("Good");
        } else if (per > 50) {
            System.out.println("Avg");
        } else {
            System.out.println("Fail");
        }

        sc.close();
    }

    public static void main(String[] args) {
        new StudentGread().grade();
    }
}