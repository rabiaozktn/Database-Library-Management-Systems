package com.company.dao;

import com.company.entity.Uye;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UyeRepositoryPostgreSQL {

    Connection conn;

    public UyeRepositoryPostgreSQL() {
        conn = Dao.getInstance().getConnection();
    }

    public Uye ara(int kullan) {
        System.out.println("Uye aranıyor...");
        Uye uye = null;

        String sql = "SELECT * FROM \"Uyeler\" WHERE \"UyeNo\"=" + kullan;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

//            conn.close();
            String KullaniciAdi;
            String Adi;
            String Soyadi;
            String _sifre;
            String email;
            int no;
            while (rs.next()) {
                // Kayda ait alan değerlerini değişkene ata //
                Adi = rs.getString("Adi");
                Soyadi = rs.getString("Soyadi");
                KullaniciAdi = rs.getString("KullaniciAdi");
                _sifre = rs.getString("_sifre");
                email = rs.getString("email");
                no = rs.getInt("UyeNo");

                uye = new Uye(Adi, Soyadi, KullaniciAdi, _sifre, email, no);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uye;
    }

    public void kaydet(Uye uye) {
        String sql = "INSERT INTO  \"Uyeler\" (\"UyeNo\",\"KullaniciAdi\",\"Adi\",\"Soyadi\",\"_sifre\",\"email\") VALUES(\'" + uye.getno() + "\',\'" + uye.getKullaniciAdi() + "\',\'" + uye.getAdi() + "\',\'" + uye.getSoyadi() + "\',\'" + uye.getSifre() + "\',\'" + uye.getemail() + "\')";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** Bağlantı sonlandırma *****
//            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void degistir(Uye uye) {

        String sql = "UPDATE \"Uyeler\" SET \"Adi\"=\'" + uye.getAdi() + "\', \"Soyadi\"=\'" + uye.getSoyadi() + "\',\"KullaniciAdi\"=\'" + uye.getKullaniciAdi() + "\',\"_sifre\"=\'" + uye.getSifre() + "\',\"email\"=\'" + uye.getemail() + "\' WHERE \"UyeNo\"=\'" + uye.getno() + "\'";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** Bağlantı sonlandırma *****
//            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}