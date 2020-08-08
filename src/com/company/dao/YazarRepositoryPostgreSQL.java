package com.company.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class YazarRepositoryPostgreSQL {

    private Connection conn;

    public YazarRepositoryPostgreSQL() {
        conn = Dao.getInstance().getConnection();

    }

    public void getAll() {
        try {
            String sql = "SELECT \"YazarAdi\", \"Email\"  FROM \"Yazarlar\"";

            // Sorgu çalıştırma //
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Kayda ait alan değerlerini değişkene ata //
                String yazarAdi = rs.getString("YazarAdi");
                String Email = rs.getString("Email");

                // Ekrana yazdır //
                System.out.print("Yazar Adi: " + yazarAdi);
                System.out.println(", Email: " + Email);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
