package JDBC;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC_Connection {

    String oracleDbUrl = "jdbc:postgresql://room-reservation-qa.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/hr";
    String oracleDbUsername = "hr";
    String oracleDbPassword = "hr";

    @Test
    public void text() throws SQLException {
        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM countries");

        resultSet.beforeFirst();
        while(resultSet.next())
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));

        resultSet.last();
        System.out.println("Row Number  - " + resultSet.getRow());
    }


    @Test
    public void metaDataTest() throws SQLException {
        String sql = "SELECT employee_id, last_name, job_id FROM employees";
        Connection connection = DriverManager.getConnection(oracleDbUrl,oracleDbUsername,oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("Data Base Type : " + dbMetaData.getDatabaseProductName());

        ResultSetMetaData rsmd = resultSet.getMetaData();
        System.out.println("Number columns : " + rsmd.getColumnCount());
       for (int i = 1; i <= rsmd.getColumnCount(); i++){
           System.out.println(rsmd.getColumnName(i));
       }

       List<Map<String, Object>>  queryData = new ArrayList<>();

        resultSet.beforeFirst();
       while (resultSet.next()){
           Map<String, Object> map = new HashMap<>();

           for (int j=1; j <= rsmd.getColumnCount(); j++) {
               map.put(rsmd.getColumnName(j),resultSet.getString(j));
           }
           queryData.add(map);
       }

        for (Map<String, Object> map:queryData) {
            System.out.println(map.get("employee_id"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }



}
