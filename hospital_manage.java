import java.util.Scanner;

public class hospital_manage {
    Scanner sc = new Scanner(System.in);

    int[] patientId = new int[100];
    String[] patientName = new String[100];
    int[] patientAge = new int[100];
    String[] patientGender = new String[100];
    String[] patientDisease = new String[100];
    String[] patientDoctor = new String[100];
    int[] patientPriority = new int[100];
    int patientCount = 0;

    void addPatient() {
        if (patientCount >= 100) {
            System.out.println("Patient list is full.");
            return;
        }

        System.out.print("Enter Patient ID: ");
        patientId[patientCount] = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Patient Name: ");
        patientName[patientCount] = sc.nextLine();

        System.out.print("Enter Patient Age: ");
        patientAge[patientCount] = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Patient Gender: ");
        patientGender[patientCount] = sc.nextLine();

        System.out.print("Enter Disease: ");
        patientDisease[patientCount] = sc.nextLine();

        System.out.print("Enter Doctor Name: ");
        patientDoctor[patientCount] = sc.nextLine();

        System.out.print("Enter Priority (1-Emergency, 2-Normal): ");
        patientPriority[patientCount] = sc.nextInt();

        patientCount++;
        System.out.println("\nPatient added successfully!\n");
    }

    void viewAllPatients() {
        if (patientCount == 0) {
            System.out.println("No patients registered yet.");
            return;
        }

        System.out.println("\nPATIENT LIST");

for (int i = 0; i < patientCount; i++) {

    System.out.println("ID: " + patientId[i]);
    System.out.println("Name: " + patientName[i]);
    System.out.println("Age: " + patientAge[i]);
    System.out.println("Gender: " + patientGender[i]);
    System.out.println("Disease: " + patientDisease[i]);
    System.out.println("Doctor: " + patientDoctor[i]);
    System.out.println("Priority: " + patientPriority[i]);

    System.out.println("--------------------------");
}

        System.out.println("======================================================\n");
    }

    void menu() {
        System.out.println("========== HOSPITAL QUEUE MANAGEMENT SYSTEM ========== ");
        System.out.println("1. Add Patient ");
        System.out.println("2. View All Patients");
        System.out.println("3. Search Patient");
        System.out.println("4. Call Next Patient ");
        System.out.println("5. View Waiting Queue ");
        System.out.println("6. Sort Patients ");
        System.out.println("7. Update Patient");
        System.out.println("8. Remove Patient ");
        System.out.println("9. Hospital Report ");
        System.out.println("10. Exit ");
    }

    public static void main(String[] args) {
        int choice;
        hospital_manage obj = new hospital_manage();

        do {
            obj.menu();
            System.out.print("Enter Choice: ");
            choice = obj.sc.nextInt();

            switch (choice) {
                case 1:
                    obj.addPatient();
                    break;
                case 2:
                    obj.viewAllPatients();
                    break;
                case 3:
                    System.out.println("Search Patient option is not implemented yet.");
                    break;
                case 4:
                    System.out.println("Call Next Patient option is not implemented yet.");
                    break;
                case 5:
                    System.out.println("View Waiting Queue option is not implemented yet.");
                    break;
                case 6:
                    System.out.println("Sort Patients option is not implemented yet.");
                    break;
                case 7:
                    System.out.println("Update Patient option is not implemented yet.");
                    break;
                case 8:
                    System.out.println("Remove Patient option is not implemented yet.");
                    break;
                case 9:
                    System.out.println("Hospital Report option is not implemented yet.");
                    break;
                case 10:
                    System.out.println("Exiting Hospital Management System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 10);
    }
}
