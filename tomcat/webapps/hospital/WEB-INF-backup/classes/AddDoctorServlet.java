import java.sql.*;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/addDoctor")
public class AddDoctorServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		// setting up to writer to browser
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		// 
		try {
			// get a connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "myuser", "xxxx");
			
			// get binary data from picture file
			Part pictureFile = request.getPart("doctor-photo");
			InputStream in = pictureFile.getInputStream();
			
			
			// set up statement 
			String sqlStr = "INSERT INTO doctors VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sqlStr);
			statement.setString(1, request.getParameter("doctor-name"));
			statement.setBinaryStream(2, in);
			statement.setString(3, request.getParameter("doctor-department"));
			
			statement.execute();
			
		} catch (SQLException e) {
			out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		doGet(request, response);
	}
}