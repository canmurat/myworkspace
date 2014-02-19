package com.sepetim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UyeYaz {
	
	static String ad;
	static String soyad;
	static String dtarih;
	static long Tel;
	static String Eposta;
	static String Adres;
	static long sifre;
	static long kartNo;
	static int x;
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=YemekSepeti;user=mehmet;password=1234567";

	String uyeAd;

	public UyeYaz(String ad, String soyad, String dtarih, long Tel,
			String Eposta, String Adres, long sifre, long kartNo) throws ClassNotFoundException
	{
		
		UyeYaz.ad = ad;
		UyeYaz.soyad = soyad;
		UyeYaz.dtarih = dtarih;
		UyeYaz.Tel= Tel;
		UyeYaz.Eposta=Eposta;
		UyeYaz.Adres = Adres;
		UyeYaz.sifre = sifre;
		UyeYaz.kartNo = kartNo;
		
		System.out.println("x degeri = "+x);
		
		
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {

			PreparedStatement ps = conn
					.prepareStatement("Insert into kullanicilar(Ad,Soyad,DTarih,Adres,EPosta,Tel,Sifre,Admin,KrediKartNo) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, ad);
			ps.setString(2, soyad);
			ps.setString(3, dtarih);
			ps.setString(4, Adres);
			ps.setString(5, Eposta);
			ps.setLong(6, Tel);
			ps.setLong(7, sifre);
			ps.setInt(8, 0);
			ps.setLong(9, kartNo);

			x = ps.executeUpdate();
			
			System.out.println("x degeri = "+x);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

//	public static void uyeyiYaz() throws ClassNotFoundException
//	{
//		
//	}
//public static void main(String[] args) throws ClassNotFoundException {
//		
//		uyeyiYaz();
//		
//	}

}
