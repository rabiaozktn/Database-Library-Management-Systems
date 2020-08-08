package com.company.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class YayinEviRepositoryPostgreSQL {

    private Connection conn;

    public YayinEviRepositoryPostgreSQL() {
        conn = Dao.getInstance().getConnection();

    }

    public void getAll() {
        try {
            String sql = "SELECT \"PublisherNo\", \"PublisherName\"  FROM \"Yayınevleri\"";

            // Sorgu çalıştırma //
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Kayda ait alan değerlerini değişkene ata //
                String v1 = rs.getString("PublisherNo");
                String v2 = rs.getString("PublisherName");

                // Ekrana yazdır //
                System.out.print("Yayın evi no : " + v1);
                System.out.println(", Yayın evi Name :  " + v2);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String isExist(int kullan) {
        String sql = "SELECT * FROM \"Yayınevleri\" WHERE \"PublisherNo\"=" + kullan;
        String value = null;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                value = rs.getString("PublisherName");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

}
