package com.company.dao;

import com.company.entity.AlinanKitaplar;

import java.sql.Connection;
import java.sql.Statement;

public class AKRepositoryPostgreSQL {

    public void kaydet(AlinanKitaplar AK) {
        String sql = "INSERT INTO  \"AlinanKitaplar\" (\"AKNo\",\"KitapNo\",\"UyeNo\") VALUES(\'" + AK.getAKNo() + "\',\'" + AK.getKitapNo() + "\',\'" + AK.getUyeNo() + "\')";
        Connection conn = Dao.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** Bağlantı sonlandırma *****
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
