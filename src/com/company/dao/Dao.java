package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

    private static Dao instance;
    private Connection conn = null;

    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }

        return instance;
    }

    public Dao() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rabia2",
                    "postgres", "hello123");
            if (conn != null) {
                System.out.println("Veritabanına bağlandı!");
            } else
                System.out.println("Bağlantı Grişimi başarısız.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        if (conn != null)
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
