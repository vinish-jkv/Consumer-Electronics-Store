import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class JDBCdemo {  
	    private static final String URL = "jdbc:mysql://localhost:3306/jdbcdemo";
	    private static  String USER = "*****";
	    private static  String PASSWORD = "******";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}
