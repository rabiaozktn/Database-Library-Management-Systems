package com.company.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class KategoriRepositoryPostgreSQL {

    private Connection conn;

    public KategoriRepositoryPostgreSQL() {
        conn = Dao.getInstance().getConnection();

    }

    public void getAll() {
        try {
            String sql = "SELECT \"KategoriNo\", \"KategoriAdi\"  FROM \"Kategoriler\"";

            // Sorgu çalıştırma //
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Kayda ait alan değerlerini değişkene ata //
                String v1 = rs.getString("KategoriNo");
                String v2 = rs.getString("KategoriAdi");

                // Ekrana yazdır //
                System.out.print("Kategori No : " + v1);
                System.out.println(", Kategori Adi : " + v2);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String isExist(int kullan) {
        String sql = "SELECT * FROM \"Kategoriler\" WHERE \"KategoriNo\"=" + kullan;
        String value = null;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                value = rs.getString("KategoriAdi");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


}
