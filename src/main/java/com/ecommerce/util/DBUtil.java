package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static Connection conn= null;

    public static Connection getConnection(){
        try {
            if(conn== null) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/ecommerce_db";

                conn = DriverManager.getConnection(url, "root", "root@123");
            }
            return conn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  conn;
    }

    public static void closeMyConnection(){

        try{
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
