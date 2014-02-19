package com.kutuphane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class OduncSil {
	static DefaultTableModel model;
	static Connection conn;

	public OduncSil(String silinecek1, String silinecek2, String silinecek3, String silinecek4, String silinecek5) throws ClassNotFoundException {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			PreparedStatement ps = conn
					.prepareStatement("delete from t_odunc inner join t_kitap on t_kitap.kitap_id=t_odunc.kitap_id where kitap_id='"+silinecek1+"' AND t_kitap.kitap_ad='"+silinecek2+"' AND"
							+ " uye_id='"+silinecek3+"'AND uye_ad='"+silinecek4+"' AND odunc_tarih='"+silinecek5+"'");

			ps.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
