package org.tinker.big.data.hive.jdbc;

import java.sql.*;

public class HiveJDBC {

    private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    private static final String EC2_DNS = "ec2-44-210-243-237.compute-1.amazonaws.com";
    private static final String SCHEMA = "default";

    private static final String USERNAME = "hadoop";
    private static final String PASSWORD = "***********";
    private static final int PORT = 10000;  //must enable PORT 10000 in Amazon EC2!!!

    private static final String CONN_STRING = "jdbc:hive2://" + EC2_DNS + ":" + PORT + "/" + SCHEMA;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Register driver and create driver instance

        Class.forName(DRIVER_NAME);

        Connection con = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from pokes");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "---" + rs.getString(2));
        }
        System.out.println(con);

//        stmt.executeQuery("CREATE DATABASE userdb");
//        System.out.println("Database userdb created successfully.");
        con.close();

    }
}
