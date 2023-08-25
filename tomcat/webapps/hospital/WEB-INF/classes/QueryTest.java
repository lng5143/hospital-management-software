import java.sql.*;

public class QueryTest {
    public static void main(String args[]) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","myuser","xxxx");

            Statement statement = connection.createStatement();

            String sqlStr = "SELECT " +
                    "p.patient_id, " +
                    "p.patient_name, " +
                    "p.date_of_hospitalization, " +
                    "p.date_of_discharge, " +
                    "d.doctor_name, " +
                    "di.diagnosis_value, " +
                    "di.symptoms, " +
                    "tc.treatment_course_value " +
                    "FROM patients p " +
                    "LEFT JOIN doctors d " +
                    "ON p.assigned_doctor_id = d.doctor_id " +
                    "LEFT JOIN diagnosis di " +
                    "ON p.patient_id = di.diagnosis_id " +
                    "LEFT JOIN treatment_courses tc " +
                    "ON tc.treatment_course_id = p.patient_id";

            ResultSet resultSet = statement.executeQuery(sqlStr);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int colsCount = metaData.getColumnCount();

            String[] colsNameArray = new String[colsCount];
            for (int i = 0; i < colsCount; i++) {
                colsNameArray[i] = metaData.getColumnName(i + 1);
            }

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

            System.out.println(jsonString);

        } catch(Exception ex) {
//            out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
//            out.close();
        }
    }
}