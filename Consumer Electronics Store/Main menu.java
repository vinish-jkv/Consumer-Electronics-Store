import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;

        while (exit) {
            System.out.println("Menu:");
            System.out.println("1. Product Management");
            System.out.println("2. Sales Management");
            System.out.println("3. Customer Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    productmanager.productMenu(scanner);
                    break;
                case 2:
                    SalesManager.salesMenu(scanner);
                    break;
                case 3:
                    CustomerManager.customerMenu(scanner);
                    break;
                case 4:
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}

