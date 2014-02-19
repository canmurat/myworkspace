package com.sepetim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UyeAra {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=YemekSepeti;user=mehmet;password=1234567";
	public static boolean uyeVarmi = false;
	String uyeAd;

	public UyeAra(String isim) throws ClassNotFoundException, SQLException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(url);

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from kullanicilar");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				uyeAd = rs.getString("Ad");

				if (uyeAd.trim().equals(isim.trim())) {
					uyeVarmi = true;

				}
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public boolean uyeVarmi()
	{
		return uyeVarmi;
	}

}
