package com.hastane;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class KayýtOl extends JFrame {

	private JPanel contentPane;
	private JTextField textKulAd;
	private JTextField textKulAdTekrar;
	private JTextField textBashekimlikSifre;
	JLabel labelKullaniciAdi;
	JLabel labelSifre;
	String sifreID;
	String RektorlukSifresi;
	private JPasswordField textSifre;
	private JPasswordField textSifreTekrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KayýtOl frame = new KayýtOl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KayýtOl() {
		setTitle("Yeni Kay\u0131t");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.YELLOW);
		JLabel lblKullaniciAd = new JLabel("Kullanici Ad\u0131 : ");
		lblKullaniciAd.setBounds(10, 41, 106, 14);
		contentPane.add(lblKullaniciAd);

		textKulAd = new JTextField();
		textKulAd.setBounds(126, 38, 135, 20);
		contentPane.add(textKulAd);
		textKulAd.setColumns(10);

		JLabel lblKullaniciAdiTekrar = new JLabel("Kullanici Adi Tekrar : ");
		lblKullaniciAdiTekrar.setBounds(10, 84, 121, 14);
		contentPane.add(lblKullaniciAdiTekrar);

		textKulAdTekrar = new JTextField();
		textKulAdTekrar.setBounds(126, 81, 135, 20);
		contentPane.add(textKulAdTekrar);
		textKulAdTekrar.setColumns(10);

		JLabel lblSifre = new JLabel("Sifre :");
		lblSifre.setBounds(10, 129, 106, 14);
		contentPane.add(lblSifre);

		JLabel lblSifreTekrar = new JLabel("Sifre Tekrar : ");
		lblSifreTekrar.setBounds(10, 178, 106, 14);
		contentPane.add(lblSifreTekrar);

		JLabel lblBasHekimlikSifresi = new JLabel("Bas Hekimlik Sifresi :");
		lblBasHekimlikSifresi.setBounds(10, 223, 121, 14);
		contentPane.add(lblBasHekimlikSifresi);

		textBashekimlikSifre = new JTextField();
		textBashekimlikSifre.setBounds(126, 220, 135, 20);
		contentPane.add(textBashekimlikSifre);
		textBashekimlikSifre.setColumns(10);

		JButton btnNewButton = new JButton("Kay\u0131t Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					yeniKayýt();
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(172, 298, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(Color.green);
		labelKullaniciAdi = new JLabel("");
		labelKullaniciAdi.setBounds(10, 298, 121, 14);
		contentPane.add(labelKullaniciAdi);

		labelSifre = new JLabel("");
		labelSifre.setBounds(10, 337, 135, 14);
		contentPane.add(labelSifre);
		
		textSifre = new JPasswordField();
		textSifre.setBounds(126, 126, 135, 20);
		contentPane.add(textSifre);
		
		textSifreTekrar = new JPasswordField();
		textSifreTekrar.setBounds(126, 175, 135, 20);
		contentPane.add(textSifreTekrar);
	}
	//Rektorlukten alýnan sifrenin daha once kullanýlýp kullanýlmadýgýný test ediyoruz . 
	public boolean sifreDahaOnceKullanildimi(String sifre) throws ClassNotFoundException
	{
		Connection conn=null;
		Boolean sifreKullanilmisMi = false;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		CallableStatement cstmt = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

		
			
			cstmt = conn.prepareCall("{? = call sifreDahaOnceKullanilmismi(?)}");     // SAKLI YORDAMI KULLANDIGIM YER
			cstmt.setString("sifre", sifreID);											// SAKLI YORDAM KULLANICI SORGULAYIP DEGER DONDURUYOR.								
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.execute();
			
			int sonuc = cstmt.getInt(1);
			
			if(sonuc > 0)
			{
				sifreKullanilmisMi = true;
				JOptionPane.showMessageDialog(null, "Bu Rektorluk Sifresi Daha once kullanýlmýs !",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		

			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sifreKullanilmisMi;
	}
	
	/*Her kullaniciya bir adet Hekimlik sifresi tanýmlanýyor.
	 * Daha once girilmis bir kullanicinin hekimlik sifresi bir daha kullanýlamaz.*/
	@SuppressWarnings("deprecation")
	public void yeniKayýt() throws ClassNotFoundException {

		Boolean devamEt = true;
		String hataMesajý = "";
		String kullaniciAdý = "";
		String sifre = "";
		String hekimlikSifresi = "";

		if (textKulAd.getText().equals(textKulAdTekrar.getText())) {
			kullaniciAdý = textKulAd.getText();
		} else {
			hataMesajý += "Kullanici Ýsimleri eslesmiyor\n";
			devamEt = false;
		}
		if (textSifre.getText().equals(textSifreTekrar.getText())) {
			sifre = textSifre.getText();
		} else {
			hataMesajý += "Sifreler Eslesmiyor\n";
			devamEt = false;
		}
		if (textBashekimlikSifre.getText() != null) {

			hekimlikSifresi = textBashekimlikSifre.getText().trim();

			if (!hekimlikSifresiSorgula(hekimlikSifresi)) {
				hataMesajý += "Hekimlik Sifresi gecerli degil !";
				devamEt = false;
			}
			else if (sifreDahaOnceKullanildimi(hekimlikSifresi)) {
				hataMesajý += "Hekimlik Sifresi Daha once kullanýlmýs !";
				devamEt = false;
			}
			

		}
		if (devamEt == false) {
			JOptionPane.showMessageDialog(null, hataMesajý);
		} else {
			kaydet(kullaniciAdý, sifre);
			labelKullaniciAdi.setText("kullanici Adý=" + kullaniciAdý);
			labelSifre.setText("Sifre=" + sifre);
			JOptionPane.showMessageDialog(null, "Uye  Oldun !");
		}

	}

	public void kaydet(String kullaniciAd, String sifre)
			throws ClassNotFoundException {

		Connection conn = null;

		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		try {

			PreparedStatement ps = conn
					.prepareStatement("insert into kullanicilar (KullaniciAd,Sifre,sifreID) values (?,?,?)");

			ps.setString(1, kullaniciAd);
			ps.setString(2, sifre);
			ps.setString(3, this.sifreID);

			ps.executeUpdate();

			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean hekimlikSifresiSorgula(String sifre)
			throws ClassNotFoundException {
		Connection conn = null;

		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Boolean sifreGecerli = false;

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from rektorlukSifre where RektorlukSifresi ='"
							+ sifre + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				this.RektorlukSifresi = rs.getString("RektorlukSifresi");
				this.sifreID = rs.getString("sifreID");
				System.out.println("this.RektorlukSifresi = "
						+ this.RektorlukSifresi);
				System.out.println("this.sifreID = " + this.sifreID);

				if (!this.RektorlukSifresi.isEmpty()) {
					sifreGecerli = true;

				}
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sifreGecerli;

	}

}
