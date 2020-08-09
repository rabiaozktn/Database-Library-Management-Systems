package com.company.dao;

import com.company.Main;
import com.company.entity.Kitap;
import com.company.enums.Kitaplar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class KitaplarRepositoryPostgreSQL {

    Connection conn;

    public KitaplarRepositoryPostgreSQL() {
        conn = Dao.getInstance().getConnection();
    }

    public String isExist(int kullan) {
        String sql = "SELECT * FROM \"Kitaplar\" WHERE \"KitapNo\"=" + kullan;
        String value = null;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                value = rs.getString("KitapAdi");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public void tumUrunler(Kitaplar kitaplar) {

        System.out.println("ürünleri getiriyor...");

        try {
            String sql = "SELECT \"KitapNo\", \"ISBN\", \"KitapAdi\",\"YayinEviNo\",\"KategoriNo\",\"SayfaSayisi\",\"DilNo\",\"OwnerId\"  FROM \"Kitaplar\"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //***** Bağlantı sonlandırma *****
//            conn.close();

            int KitapNo;
            int Isbn;
            String adi;
            int sayfa;
            int yayinevino;
            int kategorino;
            int dilno;
            String value = null;


            while (rs.next()) {
                KitapNo = rs.getInt("KitapNo");
                Isbn = rs.getInt("ISBN");
                adi = rs.getString("KitapAdi");
                yayinevino = rs.getInt("YayinEviNo");
                kategorino = rs.getInt("KategoriNo");
                sayfa = rs.getInt("SayfaSayisi");
                dilno = rs.getInt("DilNo");
                value = rs.getString("OwnerId");

                switch (kitaplar) {
                    case ALL:

                        printKitaplar(KitapNo, Isbn, adi, yayinevino, kategorino, sayfa, dilno);

                        break;

                    case ALINAN:

                        if (value != null && Integer.parseInt(value) == Main.uye.getno()) {
                            printKitaplar(KitapNo, Isbn, adi, yayinevino, kategorino, sayfa, dilno);
                        }

                        break;

                    case ALINMAYAN:

                        if (value == null) {
                            printKitaplar(KitapNo, Isbn, adi, yayinevino, kategorino, sayfa, dilno);
                        }
                        break;
                }

            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printKitaplar(int kitapNo, int isbn, String adi, int yayinevino, int kategorino, int sayfa, int dilno) {
        System.out.print("KitapNo:" + kitapNo);
        System.out.print(" ISBN: " + isbn);
        System.out.print(" Kitap Adı: " + adi);
        System.out.print(" Yayın Evi No: " + yayinevino);
        System.out.print(" Kategori No: " + kategorino);
        System.out.print(" Sayfa Sayısı: " + sayfa);
        System.out.println(" Dil Kodu: " + dilno);
    }

    public void kaydet(Kitap Kitap) {
        String sql = "INSERT INTO  \"Kitaplar\" (\"KitapNo\",\"ISBN\",\"KitapAdi\",\"YayinEviNo\",\"KategoriNo\",\"SayfaSayisi\",\"DilNo\") VALUES(\'" + Kitap.getKitapNo() + "\',\'" + Kitap.getISBN() + "\',\'" + Kitap.getKitapAdi() + "\',\'" + Kitap.getYayinEviNo() + "\',\'" + Kitap.getKategoriNo() + "\',\'" + Kitap.getSayfaSayisi() + "\',\'" + Kitap.getDilNo() + "\')";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Kütüphanemiz bağınız için size teşekkür ediyor!");
            //***** Bağlantı sonlandırma *****
//            conn.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
//            e.printStackTrace();
        }
    }

    public void update(int kitapNo, Integer userId) {

        System.out.println("ürün güncelleniyor...");

        String sql = "UPDATE \"Kitaplar\" SET \"OwnerId\"=" + userId + " WHERE \"KitapNo\"=" + kitapNo;

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
