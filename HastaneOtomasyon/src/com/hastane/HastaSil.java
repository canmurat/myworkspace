package com.hastane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class HastaSil {
	static DefaultTableModel model;
	static Connection conn;

	public HastaSil(String silinecek) throws ClassNotFoundException {

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
					.prepareStatement("delete from hastalar where HastaTcNo='"+silinecek+"';");

			ps.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}