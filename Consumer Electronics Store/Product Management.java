import java.sql.*;
import java.util.Scanner;

public class productmanager {
    public static void productMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("Product Management Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. View Product Details");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    AddProduct(scanner);
                    break;
                case 2:
                    ViewProduct(scanner);
                    break;
                case 3:
                    UpdateProduct(scanner);
                    break;
                case 4:
                    DeleteProduct(scanner);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void AddProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter category: ");
        String category = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();

        String query = "INSERT INTO Product (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (Connection con = JDBCdemo.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setInt(4, stockQuantity);
            statement.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ViewProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        String query = "SELECT * FROM Product WHERE product_id = ?";
        
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Product ID: " + resultSet.getInt("product_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Category: " + resultSet.getString("category"));
                System.out.println("Price: " + resultSet.getDouble("price"));
                System.out.println("Stock Quantity: " + resultSet.getInt("stock_quantity"));
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void UpdateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();

        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new category: ");
        String category = scanner.next();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new stock quantity: ");
        int stockQuantity = scanner.nextInt();

        String query = "UPDATE Product SET name = ?, category = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setInt(4, stockQuantity);
            statement.setInt(5, productId);
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void DeleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();
        String query = "DELETE FROM Product WHERE product_id = ?";
        
        try (Connection connection = JDBCdemo.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

