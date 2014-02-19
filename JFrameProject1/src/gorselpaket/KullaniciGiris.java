package gorselpaket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class KullaniciGiris {

	static Connection conn;
	static boolean uyeOlabilir = false;

	public static boolean KullaniciSorgu(String kullaniciAdi, String sifre)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from kullanicilar");
			/*
			 * if UDF, and need to pass params, can do something like:
			 * ...prepareStatement("select * from UDF('" + UDFinputVal + "')"
			 */

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (kullaniciAdi.trim().equals(rs.getString("KullaniciAd"))
						&& sifre.trim().equals(rs.getString("Sifre")))
					uyeOlabilir = true;
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uyeOlabilir;
	}

	static public String[] DatabaseConnection(String sorgu)
			throws ClassNotFoundException {
		String[] colNames = null;
		ResultSet resultset = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn
					.prepareStatement(sorgu);
			
			ResultSet rs = ps.executeQuery();
			resultset = rs;
			
			colNames = new String[10];  
	        ResultSetMetaData rsMetaData = resultset.getMetaData();  
	        
	        for (int k = 0; k < rsMetaData.getColumnCount(); k++) {  
	               colNames[k] = rsMetaData.getColumnName(k);  
	        } 

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return colNames;
	}

}
