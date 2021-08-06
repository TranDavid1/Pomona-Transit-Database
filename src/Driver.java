import javax.xml.transform.Result;
import java.sql.*;

public class Driver {
    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();

            String name = "William Barnes";
            String number = "9096787632";

//            ResultSet resultSet =
            statement.execute("INSERT INTO Driver VALUES ('"+ name +"', '" + number +"')");

//            ResultSetMetaData rsMeta = resultSet.getMetaData();

//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("TripNumber"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
