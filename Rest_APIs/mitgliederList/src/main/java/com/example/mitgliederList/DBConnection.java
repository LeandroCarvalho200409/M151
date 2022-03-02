package com.example.mitgliederList;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DBConnection {
    public Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost/filmclub?characterEncoding=utf8";
            conn = DriverManager.getConnection(dbUrl, "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
