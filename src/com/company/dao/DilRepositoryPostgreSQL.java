package com.company.dao;

import com.company.entity.Uye;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DilRepositoryPostgreSQL {

    private Connection conn;

    public DilRepositoryPostgreSQL() {
        conn = Dao.getInstance().getConnection();

    }

    public void getAll() {
        try {
            String sql = "SELECT \"DilNo\", \"DilAdi\"  FROM \"Diller\"";

            // Sorgu çalıştırma //
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Kayda ait alan değerlerini değişkene ata //
                String v1 = rs.getString("DilNo");
                String v2 = rs.getString("DilAdi");

                // Ekrana yazdır //
                System.out.print("Dil No : " + v1);
                System.out.println(", Dil Adi : " + v2);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String isExist(int kullan) {
        String sql = "SELECT * FROM \"Diller\" WHERE \"DilNo\"=" + kullan;
        String value = null;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                value = rs.getString("DilAdi");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public void delete(int kitapNo){
        System.out.println("Dil siliniyor...");

        String sql= "DELETE FROM \"Diller\" WHERE \"DilNo\"="+kitapNo;

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** Bağlantı sonlandırma *****
            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
