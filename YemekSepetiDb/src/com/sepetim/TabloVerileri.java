package com.sepetim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabloVerileri {

	public String yemek, tatli, icecek = "";
	public ArrayList<String> YiyeceklerListesi;
	public ArrayList<String> IceceklerListesi;
	public ArrayList<String> SalatalarListesi;
	public ArrayList<String> TatlilarListesi;
	public List<String> yemekListesi = new ArrayList<String>();
	public List<Integer> yemekFiyatListesi = new ArrayList<Integer>();
	public HashMap<String, Integer> yemekMap;
	public HashMap<String, Integer> icecekMap;
	public HashMap<String, Integer> salataMap;
	public HashMap<String, Integer> tatliMap;

	public static Connection conn;
	String url = "jdbc:sqlserver://localhost:1433;databaseName=YemekSepeti;user=mehmet;password=1234567";

	String yemekAd;
	String icecekAd;
	String salataAd;
	String tatliAd;
	int yemekFiyatý;
	int icecekFiyatý;
	int salataFiyatý;
	int tatliFiyatý;

	public TabloVerileri() throws ClassNotFoundException {
		Yiyecekler();
		Icecekler();
		Salatalar();
		Tatlilar();

	}

	public void Yiyecekler() throws ClassNotFoundException {
		YiyeceklerListesi = new ArrayList<String>();
		yemekMap = new HashMap<String, Integer>();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from yiyecekler");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				yemekAd = rs.getString("Ad").trim();
				yemekFiyatý = rs.getInt("Fiyat");
				yemekAd = (yemekAd == null) ? "" : yemekAd;
				YiyeceklerListesi.add(yemekAd);
				yemekMap.put(yemekAd, yemekFiyatý);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Icecekler() throws ClassNotFoundException {
		IceceklerListesi = new ArrayList<String>();
		icecekMap = new HashMap<String, Integer>();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from icecekler");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				icecekAd = rs.getString("Ad").trim();
				icecekFiyatý = rs.getInt("Fiyat");
				// Database'de ki null degerleri "" olarak al . Boylece hatalar
				// onlendi.
				icecekAd = (icecekAd == null) ? "" : icecekAd;
				IceceklerListesi.add(icecekAd);
				icecekMap.put(icecekAd, icecekFiyatý);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Salatalar() throws ClassNotFoundException {
		salataMap = new HashMap<String, Integer>();
		SalatalarListesi = new ArrayList<String>();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from salatalar");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				salataAd = rs.getString("Ad").trim();
				salataFiyatý = rs.getInt("Fiyat");
				salataAd = (salataAd == null) ? "" : salataAd;
				SalatalarListesi.add(salataAd);
				salataMap.put(salataAd, salataFiyatý);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Tatlilar() throws ClassNotFoundException {
		tatliMap = new HashMap<String, Integer>();
		TatlilarListesi = new ArrayList<String>();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from tatlilar");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				tatliAd = rs.getString("Ad").trim();
				tatliFiyatý = rs.getInt("Fiyat");
				tatliAd = (tatliAd == null) ? "" : tatliAd;
				TatlilarListesi.add(tatliAd);
				tatliMap.put(tatliAd, tatliFiyatý);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
