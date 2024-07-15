import java.sql.*;
import java.util.Scanner;

public class SalesManager {
    public static void salesMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("Sales Management Menu:");
            System.out.println("1. Record Sale");
            System.out.println("2. View Sales Details");
            System.out.println("3. Update Sale");
            System.out.println("4. Delete Sale");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    recordSale(scanner);
                    break;
                case 2:
                    viewSales(scanner);
                    break;
                case 3:
                    updateSale(scanner);
                    break;
                case 4:
                    deleteSale(scanner);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void recordSale(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        double totalAmount = 0;

        String saleQuery = "INSERT INTO Sales (product_id, customer_id, sale_date, quantity, total_amount) VALUES (?, ?, NOW(), ?, ?)";
        String priceQuery = "SELECT price FROM Product WHERE product_id = ?";

        try (Connection connection = JDBCdemo.getConnection()) {
            // Get the product price
            PreparedStatement priceStatement = connection.prepareStatement(priceQuery);
            priceStatement.setInt(1, productId);
            ResultSet priceResult = priceStatement.executeQuery();
            if (priceResult.next()) {
                double price = priceResult.getDouble("price");
                totalAmount = price * quantity;

                // Insert sale record
                PreparedStatement saleStatement = connection.prepareStatement(saleQuery);
                saleStatement.setInt(1, productId);
                saleStatement.setInt(2, customerId);
                saleStatement.setInt(3, quantity);
                saleStatement.setDouble(4, totalAmount);
                saleStatement.executeUpdate();

                // Update product stock
                updateProductStock(connection, productId, quantity);
                System.out.println("Sale recorded successfully. Total Amount: " + totalAmount);
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateProductStock(Connection connection, int productId, int quantity) throws SQLException {
        String updateQuery = "UPDATE Product SET stock_quantity = stock_quantity - ? WHERE product_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, quantity);
            updateStatement.setInt(2, productId);
            updateStatement.executeUpdate();
        }
    }

    private static void viewSales(Scanner scanner) {
        System.out.print("Enter sale ID: ");
        int salesId = scanner.nextInt();
        String query = "SELECT * FROM Sales WHERE sales_id = ?";
        
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, salesId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Sale ID: " + resultSet.getInt("sales_id"));
                System.out.println("Product ID: " + resultSet.getInt("product_id"));
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println("Sale Date: " + resultSet.getTimestamp("sale_date"));
                System.out.println("Quantity: " + resultSet.getInt("quantity"));
                System.out.println("Total Amount: " + resultSet.getDouble("total_amount"));
            } else {
                System.out.println("Sale not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateSale(Scanner scanner) {
        System.out.print("Enter sale ID to update: ");
        int salesId = scanner.nextInt();

        System.out.print("Enter new product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        double totalAmount = 0;

        String saleQuery = "UPDATE Sales SET product_id = ?, customer_id = ?, quantity = ?, total_amount = ? WHERE sales_id = ?";
        String priceQuery = "SELECT price FROM Product WHERE product_id = ?";

        try (Connection connection = JDBCdemo.getConnection()) {
            // Get the product price
            PreparedStatement priceStatement = connection.prepareStatement(priceQuery);
            priceStatement.setInt(1, productId);
            ResultSet priceResult = priceStatement.executeQuery();
            if (priceResult.next()) {
                double price = priceResult.getDouble("price");
                totalAmount = price * quantity;

                // Update sale record
                PreparedStatement saleStatement = connection.prepareStatement(saleQuery);
                saleStatement.setInt(1, productId);
                saleStatement.setInt(2, customerId);
                saleStatement.setInt(3, quantity);
                saleStatement.setDouble(4, totalAmount);
                saleStatement.setInt(5, salesId);
                int rowsUpdated = saleStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Sale updated successfully. Total Amount: " + totalAmount);
                } else {
                    System.out.println("Sale not found.");
                }
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteSale(Scanner scanner) {
        System.out.print("Enter sale ID to delete: ");
        int salesId = scanner.nextInt();
        String query = "DELETE FROM Sales WHERE sales_id = ?";
        
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, salesId);
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Sale deleted successfully.");
            } else {
                System.out.println("Sale not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

