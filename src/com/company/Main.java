package com.company;

import com.company.dao.*;
import com.company.entity.AlinanKitaplar;
import com.company.entity.Kitap;
import com.company.entity.Uye;
import com.company.enums.Database;
import com.company.enums.Kitaplar;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class Main {

    private static Scanner al;
    public static Uye uye = null;
    private static DilRepositoryPostgreSQL dilRepo;
    private static YayinEviRepositoryPostgreSQL yayinRepo;
    private static KategoriRepositoryPostgreSQL kategoryRepo;
    private static KitaplarRepositoryPostgreSQL kitaprepo;

    public static void main(String[] args) {
        al = new Scanner(System.in);
//        Dao.getInstance();

        //Gecici Degiskenler
        Kitap kitap = null;
        AlinanKitaplar ak = null;
        String KullaniciAdi;
        String Adi;
        String Soyadi;
        String _sifre;
        String email;
        String cevap = null;

        int secim;
        boolean devamMi = true;

        Random rand = new Random();

        UyeRepositoryPostgreSQL uyerepo = new UyeRepositoryPostgreSQL();
        AKRepositoryPostgreSQL akrepo = new AKRepositoryPostgreSQL();
        YazarRepositoryPostgreSQL yazarRepo = new YazarRepositoryPostgreSQL();
        kitaprepo = new KitaplarRepositoryPostgreSQL();
        yayinRepo = new YayinEviRepositoryPostgreSQL();
        kategoryRepo = new KategoriRepositoryPostgreSQL();
        dilRepo = new DilRepositoryPostgreSQL();

        System.out.println("Kütüphane Otomasyonuna Hoşgeldiniz");

        boolean exit = true;
        while (exit) {
            secim = getScannerInt("1- Giriş Yap \n2- Üyelik Aç\n");

            if (secim == 1) {

                while (uye == null) {
                    uye = userEnter(uyerepo);
                    if (uye != null)
                        devamMi = true;
                }

            } else if (secim == 2) {
                Adi = getScannerText("Adınızı Giriniz : ");
                Soyadi = getScannerText("Soyadinizi Giriniz : ");
                _sifre = getScannerText("Sadece Sayilardan olusan sifre giriniz : ");
                KullaniciAdi = getScannerText("Kullanici Adinizi giriniz : ");
                email = getScannerText("Email giriniz : ");
                int uyeno = getScannerInt("UyeNo Giriniz : ");
                uye = new Uye(Adi, Soyadi, KullaniciAdi, _sifre, email, uyeno);
                uyerepo.kaydet(uye);

                devamMi = true;
            } else {
                System.out.println("Geçerli bir seçenek giriniz.");
            }
            //devamMi = true;
            while (devamMi) {
                System.out.println("1- Alınan Kitapları Listele");
                System.out.println("2- Alınan Kitapları Teslim Et");
                System.out.println("3- Kitapları Listele");
                System.out.println("4- Kitap bağışla");
                System.out.println("5- Kitap Ödünç Al");
                System.out.println("6- Yazarları Listele");
                System.out.println("7- Üyelik Bilgilerimi Güncelle");
                System.out.println("8- Yayın Evi Listele");
                System.out.println("9- Dilleri Listele");
                System.out.println("10- Kategorileri Listele");
                System.out.println("11- Ana menüye dön");
                System.out.println("12- Çıkış Yap");

                secim = getScannerInt("");

                if (secim == -1) {
                    kitaprepo.tumUrunler(Kitaplar.ALINAN);
                } else if (secim == 0) {
                    kitaprepo.tumUrunler(Kitaplar.ALINAN);
                    int kitapno = getScannerInt("Teslim etmek istediğin kitap no giriniz : ");
                    kitaprepo.update(kitapno, null);
                } else if (secim == 1) {
                    kitaprepo.tumUrunler(Kitaplar.ALL);
                } else if (secim == 2) {
                    int kitapno = getScannerIntCheck("Kitap no giriniz : ");
                    int isbn = getScannerInt("ISBN Giriniz : ");
                    String adi = getScannerText("Kitap Adı Giriniz : ");
                    int sayfasayisi = getScannerInt("Sayfa Sayisi Giriniz : ");
                    yayinRepo.getAll();
                    int yayinEviNo = getScannerIntCheck("Yayın Evi No Giriniz : ", Database.YAYIEVI);
                    kategoryRepo.getAll();
                    int kategorino = getScannerIntCheck("Kategori No giriniz : ", Database.KATEGORI);
                    dilRepo.getAll();
                    int dilNo = getScannerIntCheck("Dil No giriniz : ", Database.DIL);

                    kitap = new Kitap(kitapno, isbn, adi, yayinEviNo, kategorino, sayfasayisi, dilNo);
                    kitaprepo.kaydet(kitap);
                } else if (secim == 3) {
                    kitaprepo.tumUrunler(Kitaplar.ALINMAYAN);
                    secim = getScannerInt("Ödünç almak istediğiniz Kitabın numarasını giriniz : ");

                    ak = new AlinanKitaplar(rand.nextInt(1000), secim, uye.getno());
                    akrepo.kaydet(ak);

                    kitaprepo.update(secim, uye.getno());
                    System.out.println("Ürün Alınan Kitaplar Tablosuna eklendi.");

                } else if (secim == 4) {

                    yazarRepo.getAll();

                } else if (secim == 5) {
                    Adi = getScannerText("Adınızı Giriniz : ");
                    Soyadi = getScannerText("Soyadinizi Giriniz : ");
                    _sifre = getScannerText("Sadece Sayilardan olusan sifre giriniz : ");
                    KullaniciAdi = getScannerText("Kullanici Adinizi giriniz : ");
                    email = getScannerText("Email giriniz : ");
                    uye = new Uye(Adi, Soyadi, KullaniciAdi, _sifre, email, uye.getno());
                    uyerepo.degistir(uye);
                    System.out.println("Bilgileriniz başarı ile değiştirilmiştir!");
                } else if (secim == 6) {
                    yayinRepo.getAll();
                } else if (secim == 7) {
                    dilRepo.getAll();
                } else if (secim == 8) {
                    kategoryRepo.getAll();
                } else if (secim == 9) {
                    devamMi = false;
                    uye = null;
                } else if (secim == 10) {
                    devamMi = false;
                    exit = false;
                    Dao.getInstance().closeConnection();
                } else {
                    System.out.print("Hatalı tuşladınız. Tekrar Deneyiniz");
                }
            }
        }
    }

    private static Uye userEnter(UyeRepositoryPostgreSQL uyerepo) {
        System.out.println("Giriş Yapmak İçin Üye Numaranızı ve Şifrenizi Giriniz.");
        int uyeno = getScannerInt("Üye Numaranız : ");
        int sifre = getScannerInt("Şifreniz : ");

        uye = uyerepo.ara(uyeno);

        if (uye != null && Integer.parseInt(uye.getSifre()) == sifre) {
            System.out.println("Hoşgeldiniz " + uye.getAdi() + " " + uye.getSoyadi());
            return uye;
        }
        System.out.println("Böyle bir üye bulunamadı.");

        return null;

    }

    private static String getScannerText(String text) {
        while (true) {
            String value = "";
            try {
                System.out.print("\n" + text);
                value = al.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("Girdiğiniz veri doğru formatta değil lütfen tekrar deneyin.");
            }
        }
    }

    private static int getScannerInt(String text) {
        while (true) {
            int value = 0;
            try {
                System.out.print("\n" + text);
                value = Integer.parseInt(al.nextLine());
                return value;
            } catch (Exception e) {
                System.out.println("Girdiğiniz veri doğru formatta değil lütfen tekrar deneyin.");
            }
        }
    }

    private static int getScannerIntCheck(String text, Database database) {
        while (true) {
            int value = 0;
            try {
                System.out.print("\n" + text);
                value = Integer.parseInt(al.nextLine());
                String isExist = null;
                switch (database) {
                    case DIL:
                        isExist = dilRepo.isExist(value);
                        break;
                    case YAYIEVI:
                        isExist = yayinRepo.isExist(value);
                        break;
                    case KATEGORI:
                        isExist = kategoryRepo.isExist(value);
                        break;
                    case KITAP:
                        isExist = kitaprepo.isExist(value);
                        break;
                }

                if (isExist != null) {
                    System.out.println(isExist + " seçildi.");
                    return value;
                } else {
                    System.out.println("Girmiş olduğunuz veri database de yok");
                }

            } catch (Exception e) {
                System.out.println("Girdiğiniz veri doğru formatta değil lütfen tekrar deneyin.");
            }
        }
    }

    private static int getScannerIntCheck(String text) {
        while (true) {
            int value = 0;
            try {
                System.out.print("\n" + text);
                value = Integer.parseInt(al.nextLine());
                String isExist = kitaprepo.isExist(value);
                if (isExist == null) {
                    return value;
                } else {
                    System.out.println("Girmiş olduğunuz veri database de zaten mevcut başka bir id giriniz.");
                }
            } catch (Exception e) {
                System.out.println("Girdiğiniz veri doğru formatta değil lütfen tekrar deneyin.");
            }
        }
    }
}

