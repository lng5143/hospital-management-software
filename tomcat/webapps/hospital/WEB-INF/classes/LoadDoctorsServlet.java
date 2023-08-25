import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/loadDoctors")
public class LoadDoctorsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		//response.setContentType("text/html,charset=UTF-8");
		// METHOD 1: Write a ResultSet into a String and send back
		response.setContentType("text/plain");
		// METHOD 2: Write to a .json file and send the file back

		// METHOD 3: Use Jackson's streaming API to serialize ResultSet object

		PrintWriter out = response.getWriter();
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","myuser","xxxx");

			Statement statement = connection.createStatement();
			
			String sqlStr = "SELECT do.doctor_id, do.doctor_name, de.department_name "
						  + "FROM doctors do "
						  + "LEFT JOIN departments de "
						  + "ON do.department_id = de.department_id";
			
			ResultSet resultSet = statement.executeQuery(sqlStr);

			ResultSetMetaData metaData = resultSet.getMetaData();
			int colsCount = metaData.getColumnCount();

			String[] colsNameArray = new String[colsCount];
			for (int i = 0; i < colsCount; i++) {
				colsNameArray[i] = metaData.getColumnName(i + 1);
			}

			// HANDLING RESULTSET TO SEND DATA TO JAVASCRIPT

			// ---------------
			// METHOD 1: Write a ResultSet into a String and send back
			String jsonString = "";

			jsonString += "[";
			while(resultSet.next()) {
				jsonString += "\n{";
				for (int i = 0; i < colsCount - 1; i++) {
					jsonString += "\n\"" + colsNameArray[i] + "\":\""
							+ resultSet.getString(colsNameArray[i]) + "\",";
				}
				jsonString += "\n\"" + colsNameArray[colsCount - 1] + "\":\""
						+ resultSet.getString(colsNameArray[colsCount - 1]) + "\"";
				jsonString += "\n},";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "\n]";

			out.write(jsonString);

			// ---------------
			// METHOD 2: Use org.json to parse to create JSONArray
//			JSONArray jsonArray = new JSONArray();
//			while (resultSet.next()) {
//				JSONObject jsonObject = new JSONObject();
//				for (int i = 0; i < colsCount; i++) {
//					jsonObject.put(colsNameArray[0], resultSet.getObject(i + 1));
//				}
//			}

			// ---------------
			// METHOD 3: Use Jackson's streaming API to write to .json file
//			JsonFactory jsonFactory = new JsonFactory();
//			JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File("doctors.json"), JsonEncoding.UTF8);
//			jsonGenerator.useDefaultPrettyPrinter();
//;
//			jsonGenerator.writeStartArray();
//			while (resultSet.next()) {
//				jsonGenerator.writeStartObject();
//				for (int i = 0; i < colsCount; i++) {
//					jsonGenerator.writeFieldName(colsNameArray[i]);
//					jsonGenerator.writeString(resultSet.getString(colsNameArray[i]));
//				}
//				jsonGenerator.writeEndObject();
//			}
//			jsonGenerator.writeEndArray();
//			jsonGenerator.close();


		} catch(Exception ex) {
			out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			out.close();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}