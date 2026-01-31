import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
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
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		int cid = Integer.parseInt(req.getParameter("cid"));
		
		String query = "SELECT * FROM customers WHERE customer_id = ?";
		res.setContentType("text/html");
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			
			
			
			if(rs != null) {
				while(rs.next()) {
					String name = rs.getString("name");
					String city = rs.getString("city");
					//res.getWriter().println("<p>Name:"+ name +"|" + "City:"+ city+ "</p>");
					
					/*
					req.setAttribute("name", name);
					req.setAttribute("city", city);
					RequestDispatcher rd = req.getRequestDispatcher("welcome.jsp");
					rd.forward(req,res);
					*/
					
					HttpSession session = req.getSession();
					session.setAttribute("name", name);

					res.sendRedirect("welcome.jsp");
				}
				
				
				System.out.println("data found");
				
			}else {
				System.out.println("No data found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
