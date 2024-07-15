import java.sql.*;
import java.util.Scanner;

public class CustomerManager {
    public static void customerMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("Customer Management Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer Details");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    viewCustomer(scanner);
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter phone: ");
        String phone = scanner.next();
        System.out.print("Enter address: ");
        String address = scanner.next();

        String query = "INSERT INTO Customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        String query = "SELECT * FROM Customer WHERE customer_id = ?";
        
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone: " + resultSet.getString("phone"));
                System.out.println("Address: " + resultSet.getString("address"));
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new email: ");
        String email = scanner.next();
        System.out.print("Enter new phone: ");
        String phone = scanner.next();
        System.out.print("Enter new address: ");
        String address = scanner.next();

        String query = "UPDATE Customer SET name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setInt(5, customerId);
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        String query = "DELETE FROM Customer WHERE customer_id = ?";
        
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

