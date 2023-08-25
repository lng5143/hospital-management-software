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
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			// get a connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "myuser", "xxxx");
			Statement statement = connection.createStatement();

			String insertPatientsStr = "INSERT INTO patients VALUES ("
					+ "DEFAULT, "
					+ processParam(request.getParameter("patientName")) + "', "
					+ request.getParameter("dateOfHospitalization") + "', "
					+ request.getParameter("dateOfDischarge") + ", "
					+ request.getParameter("assignedDoctor") + ")";

			String insertDiagnosisStr = "INSERT INTO diagnosis VALUES ("
					+ "DEFAULT, "
					+ request.getParameter("symptoms") + ", "
					+ request.getParameter("diagnosis") + ")";

			String insertTreatmentStr = "INSERT INTO treatment_courses VALUES ("
					+ "DEFAULT, "
					+ request.getParameter("treatmentCourse") + ")";

//			String insertPatientsStr = "INSERT INTO patients VALUES (DEFAULT, ?, ?, ?, ?)";
//			PreparedStatement insertPatientStm = connection.prepareStatement(insertPatientsStr);
//			setParams(insertPatientStm, 1, request.getParameter("patientName"), "String");
//			setParams(insertPatientStm, 2, request.getParameter("dateOfHospitalization"), "Date");
//			setParams(insertPatientStm, 3, request.getParameter("dateOfDischarge"), "Date");
//			setParams(insertPatientStm, 4, request.getParameter("assignedDoctor"), "Int");
//			insertPatientStm.execute();
//
//			String insertDiagnosisStr = "INSERT INTO diagnosis VALUES (DEFAULT, ?, ?)";
//			PreparedStatement insertDiagnosisStm = connection.prepareStatement(insertDiagnosisStr);
//			setParams(insertDiagnosisStm, 1, request.getParameter("symptoms"), "String");
//			setParams(insertDiagnosisStm, 2, request.getParameter("diagnosis"), "String");
//			insertDiagnosisStm.execute();
//
//			String insertTreatmentStr = "INSERT INTO treatment_courses VALUES (DEFAULT, ?)";
//			PreparedStatement insertTreatmentStm = connection.prepareStatement(insertTreatmentStr);
//			setParams(insertTreatmentStm, 1, request.getParameter("treatmentCourse"), "String");
//			insertTreatmentStm.execute();
//
//			out.write(insertPatientsStr);
//			out.write(request.getParameter("patientName"));

		} catch (SQLException e) {
			e.printStackTrace();
			out.println("Error: " + e.getMessage());
//			out.write("Failed to add patient");
			out.write("Failed: \n" + request.getParameter("patientName"));
			out.println(request.getParameter("dateOfHospitalization"));
			out.println(request.getParameter("dateOfDischarge"));
			out.println(request.getParameter("assignedDoctor"));
			out.println(request.getParameter("symptoms"));
			out.println(request.getParameter("diagnosis"));
			out.println(request.getParameter("treatmentCourse"));

//			String insertPatientsStr = "INSERT INTO patients VALUES ("
//					+ "DEFAULT, "
//					+ request.getParameter("patientName") + ", "
//					+ request.getParameter("dateOfHospitalization") + ", "
//					+ request.getParameter("dateOfDischarge") + ", "
//					+ request.getParameter("assignedDoctor") + ")";
//
//			String insertDiagnosisStr = "INSERT INTO diagnosis VALUES ("
//					+ "DEFAULT, "
//					+ request.getParameter("symptoms") + ", "
//					+ request.getParameter("diagnosis") + ")";
//
//			String insertTreatmentStr = "INSERT INTO treatment_courses VALUES ("
//					+ "DEFAULT, "
//					+ request.getParameter("treatmentCourse") + ")";
//
//			out.println(insertPatientsStr);
//			out.println((insertDiagnosisStr));
//			out.println(insertTreatmentStr);
		} finally {
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

//		// setting up to writer to browser
//		response.setContentType("text/plain");
//		PrintWriter out = response.getWriter();
//
//		try {
//			// get a connection
//			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "myuser", "xxxx");
//
//			// set up statement
//			String insertPatientsStr = "INSERT INTO patients VALUES("
//					+ "DEFAULT, "
//					+ request.getParameter("patientName") + ", "
//					+ request.getParameter("dateOfHospitalization") + ", "
//					+ request.getParameter("dateOfDischarge") + ", "
//					+ request.getParameter("assignedDoctor") + ")";
//
//			String insertDiagnosisStr = "INSERT INTO diagnosis VALUES("
//					+ "DEFAULT, "
//					+ request.getParameter("symptoms") + ", "
//					+ request.getParameter("diagnosis") + ")";
//
//			String insertTreatmentStr = "INSERT INTO treatment_courses VALUES("
//					+ "DEFAULT, "
//					+ request.getParameter("treatmentCourse") + ")";
//
//			Statement statement = connection.createStatement();
//
//			statement.executeUpdate(insertPatientsStr);
//			statement.executeUpdate(insertDiagnosisStr);
//			statement.executeUpdate(insertTreatmentStr);
//
////			out.write("Added patient successfully!");
//			out.write(request.getParameter("patientName"));
//
//		} catch (SQLException e) {
//			e.printStackTrace();
////			out.write("Failed to add patient");
//			out.write(request.getParameter("patientName"));
//		} finally {
//			out.close();
//		}
	}

	private String setNullIfEmptyString(String string) {
		if (string == " ") string = "NULL";
		return string;
	}

	private void setParams(PreparedStatement stm, int paramIndex, String paramValue, String dataType) throws SQLException {
		if (paramValue == " ") stm.setNull(paramIndex, Types.NULL);
		else {
			switch (dataType) {
				case "String":
					stm.setString(paramIndex, paramValue);
					break;
				case "Int":
					stm.setInt(paramIndex, Integer.parseInt(paramValue));
				case "Date":
					stm.setDate(paramIndex, Date.valueOf(paramValue));
				default:
					throw new SQLException();
			}
		}
	}
}