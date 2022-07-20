package jdbc_tests.Day5_SQL;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@52.91.45.47:1521:XE";             // connection string (should be given to us)
        String dbUsername = "hr";                                           // connection string (should be given to us)
        String dbPassword = "hr";                                           // connection string (should be given to us)

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);       // connection
        Statement statement = connection.createStatement();                                     // statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");              // execution

        // next() --> move pointer to first row
        // resultSet.next();

        // getting info with column name
        // System.out.println(resultSet.getString("region_name"));                     // we get the label of the column (adjust get method according to data type that it will return)

        // getting info with column index
        // System.out.println(resultSet.getString(2));                                 // we get column according to their no. (adjust get method according to data type that it will return)



        // print: 1 - Europe
        //        2 - America
        //        3 - Asia

        /*
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        // move to second row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        // move to third row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
         */

        while (resultSet.next()){               // next() returns a boolean, if there is another row, it continues with statement
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }


        // CLOSE CONNECTIONS IN REVERSE ORDER
        resultSet.close();
        statement.close();
        connection.close();

    }
}
