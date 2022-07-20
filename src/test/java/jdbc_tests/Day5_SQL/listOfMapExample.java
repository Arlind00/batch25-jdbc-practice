package jdbc_tests.Day5_SQL;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMapExample {


    String dbUrl = "jdbc:oracle:thin:@52.91.45.47:1521:XE";             // connection string (should be given to us)
    String dbUsername = "hr";                                           // connection string (should be given to us)
    String dbPassword = "hr";                                           // connection string (should be given to us)


    @Test
    public void test1() {

        // List for keeping all the rows of maps
        List<Map<String, Object>> queryData = new ArrayList<>();


        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");
        System.out.println("row1 = " + row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");
        System.out.println("row2 = " + row2);

        // adding rows one by one to list
        queryData.add(row1);
        queryData.add(row2);


        // get stevens lastname directly from the list
        //     first get specifies first map(row1), second get (element inside row1, with key last_name)
        System.out.println("queryData.get(0).get(2) = " + queryData.get(0).get("last_name"));


        // get Ninas salary
        System.out.println("queryData.get(1).get(\"salary\") = " + queryData.get(1).get("salary"));


    }


    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);       // connection
        Statement statement = connection.createStatement();                                       // statement
        ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, salary, job_id\n" +
                                                         " from employees\n" +
                                                         " where rownum < 6");              // execution


        // in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

        // move to first row
        resultSet.next();


        // List for keeping all the rows of maps
        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsmd.getColumnName(1), resultSet.getString(1));
        row1.put(rsmd.getColumnName(2), resultSet.getString(2));
        row1.put(rsmd.getColumnName(3), resultSet.getString(3));
        row1.put(rsmd.getColumnName(4), resultSet.getString(4));
        System.out.println("row1 = " + row1);



        // move to second row
        resultSet.next();

        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsmd.getColumnName(1), resultSet.getString(1));
        row2.put(rsmd.getColumnName(2), resultSet.getString(2));
        row2.put(rsmd.getColumnName(3), resultSet.getString(3));
        row2.put(rsmd.getColumnName(4), resultSet.getString(4));
        System.out.println("row2 = " + row2);

        // adding rows one by one to list
        queryData.add(row1);
        queryData.add(row2);



        // CLOSE CONNECTIONS IN REVERSE ORDER
        resultSet.close();
        statement.close();
        connection.close();
    }






}




