package jdbc_tests.Day5_SQL;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@52.91.45.47:1521:XE";             // connection string (should be given to us)
    String dbUsername = "hr";                                           // connection string (should be given to us)
    String dbPassword = "hr";                                           // connection string (should be given to us)



    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);       // connection
        Statement statement = connection.createStatement();                                       // statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");               // execution


        // in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();


        // list of maps to keep all the information
        List<Map<String,Object>> queryData = new ArrayList<>();

        // number of columns
        int colCount = rsmd.getColumnCount();


        // loop through each row
        while(resultSet.next()){                                // iterates vertically, from top of list to bottom
            Map<String,Object> row = new LinkedHashMap<>();

            for (int i = 1; i <= colCount ; i++) {              // iterates horizontally, from right to left
                row.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }


            // add map row to List
            queryData.add(row);
        }


        // print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
        








        // CLOSE CONNECTIONS IN REVERSE ORDER
        resultSet.close();
        statement.close();
        connection.close();
    }





}
