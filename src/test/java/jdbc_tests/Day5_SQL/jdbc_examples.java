package jdbc_tests.Day5_SQL;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {


    String dbUrl = "jdbc:oracle:thin:@52.91.45.47:1521:XE";             // connection string (should be given to us)
    String dbUsername = "hr";                                           // connection string (should be given to us)
    String dbPassword = "hr";                                           // connection string (should be given to us)

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);       // connection
        Statement statement = connection.createStatement();                                       // statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");            // execution


        // display:  10 - Administration - 200 - 1700 format
        while (resultSet.next())

            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2)
                                + " - " + resultSet.getString(3) + " - " + resultSet.getInt(4));


        // CLOSE CONNECTIONS IN REVERSE ORDER
        resultSet.close();
        statement.close();
        connection.close();
    }



    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);       // connection

        // statement (allow us to navigate up and down in query result)
        // read only, don’t update the results 4 tools that we have to create dynamic code
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");           // execution



        // find how many rows we have for query
        // move to last row first
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("(rowCount) = " + (rowCount));

        // returns resultSet to first after we use last method
        resultSet.beforeFirst();


        // print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        // CLOSE CONNECTIONS IN REVERSE ORDER
        resultSet.close();
        statement.close();
        connection.close();
    }



    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);       // connection

        // statement (allow us to navigate up and down in query result)
        // read only, don’t update the results 4 tools that we have to create dynamic code
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");             // execution



        // get database related data inside the dbMetadata object
        DatabaseMetaData dbMetaData = connection.getMetaData();             // enables to get info about db and driver

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());




        // get the resultMetaData (RSMD)
        ResultSetMetaData rsMetaData = resultSet.getMetaData();         // enables to get specific data from db

        // how many columns we have?
        int colCount = rsMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);

        // getting column names
        System.out.println("rsMetaData.getColumnName(1) = " + rsMetaData.getColumnName(1));
        System.out.println("rsMetaData.getColumnName(2) = " + rsMetaData.getColumnName(2));

        // print all column names dynamically
        // we need two conditions, beginning point and ending point of rows, hence...
        for (int i = 1; i <= colCount; i++) {
            System.out.println("rsMetaData.getColumnName(i) = " + rsMetaData.getColumnName(i));
        }










        // CLOSE CONNECTIONS IN REVERSE ORDER
        resultSet.close();
        statement.close();
        connection.close();
    }

}
