import java.sql.*;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		// setting up to writer to browser
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
    	out.println("<head><title>Insert Result</title></head>");
		
		// 
		try {
			// get a connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "myuser", "xxxx");
			
			// set up statement 
			String insertPatientsStr = "INSERT INTO patients VALUES("
										+ "DEFAULT, "
										+ request.getParameter("patient-name") + ", "
										+ request.getParameter("date-of-hospitalization") + ", "
										+ request.getParameter("date-of-discharge") + ", "
										+ request.getParameter("assigned-doctor") + ")";
										
			
			String insertDiagnosisStr = "INSERT INTO diagnosis VALUES("
										+ "DEFAULT, "
										+ request.getParameter("symptoms") + ", "
										+ request.getParameter("diagnosis") + ")";
			
			String insertTreatmentStr = "INSERT INTO treatment_courses VALUES("
										+ "DEFAULT, "
										+ request.getParameter("treatment-course") + ")";
			
			Statement statement = connection.createStatement();
			
			int patientsInserted = statement.executeUpdate(insertPatientsStr);
			int diagnosisInserted = statement.executeUpdate(insertDiagnosisStr);
			int treatmentsInserted = statement.executeUpdate(insertTreatmentStr);
			
			out.println("<body>");
			out.println("<p>Number of Patient inserted: " + patientsInserted + "</p>");
			out.println("</body></html>");
			
		} catch (SQLException e) {
			out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		doGet(request, response);
	}
	
	private String validateNullString(String string) {
		if (string.length() > 3) return string;
		return "NULL";
	}
}