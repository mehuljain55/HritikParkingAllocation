package com.ParkingAllocation.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {

    private final String url="jdbc:mysql://localhost:3306/parking";
    private final String username="root";
    private final String password="1234@modi";

    public Connection establishConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Driver name
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("JDBC Connection Establish");
            return con;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }



}
