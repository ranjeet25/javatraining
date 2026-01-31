import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class HelloServlet extends HttpServlet {
	
	
	 private Connection con;

	public void init() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/sbi",
	                "root",
	                "root"
	            );
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
    
    	
    	String name = req.getParameter("name");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>Hello: "  + name + "</h1>");
        
        String query = "SELECT * FROM customers";
        try {
			PreparedStatement p1 = con.prepareStatement(query);
			ResultSet rs =  p1.executeQuery(query);
			
			while(rs.next()) {
				
				int id = rs.getInt("customer_id");
				String name1 = rs.getString("name");
				String city = rs.getString("city");
				
				out.println("<p>" + id +" "+ name1 +" "+  city +"</p>"  

						);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
	