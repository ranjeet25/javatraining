package jdbcdb;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectJDBC {

	  public static void main(String[] args) {

	        // 1. Database URL, Username, Password
	        String url = "jdbc:mysql://localhost:3306/sbi";
	        String username = "root";
	        String password = "root";

	        Connection con = null;

	        try {
	        	
	            // 2. Load MySQL JDBC Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // 3. Establish Connection
	            con = DriverManager.getConnection(url, username, password);

	            // 4. Check Connection
	            if (con != null) {
	                System.out.println("Database connected successfully!");
	            }
	            
	          

	        } catch (ClassNotFoundException e) {
	            System.out.println("MySQL Driver not found");
	            e.printStackTrace();

	        } catch (SQLException e) {
	            System.out.println("Connection failed");
	            e.printStackTrace();

	        } finally {
	            // 5. Close Connection
	            try {
	                if (con != null) {
	                    con.close();
	                    System.out.println("Connection closed");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	  }

}
