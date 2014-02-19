package com.kutuphane;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPasswordField;

public class KullaniciGiris extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textKullaniciAd;
	Connection conn;
	private JPasswordField textSifre;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciGiris frame = new KullaniciGiris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KullaniciGiris() {
		
		setTitle("KULLANICI G\u0130R\u0130S\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.yellow);
		JLabel lblKullaniciAdi = new JLabel("Kullanici Adi : ");
		lblKullaniciAdi.setBounds(41, 31, 88, 14);
		contentPane.add(lblKullaniciAdi);
		
		JLabel lblSifre = new JLabel("Sifre : ");
		lblSifre.setBounds(41, 60, 64, 14);
		contentPane.add(lblSifre);
		
		textKullaniciAd = new JTextField();
		textKullaniciAd.setBounds(115, 28, 165, 20);
		contentPane.add(textKullaniciAd);
		textKullaniciAd.setColumns(10);
		
		final AnaEkran anaekran = new AnaEkran();
		JButton btnNewButton = new JButton("Giris");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(kullaniciSorgula()){
						anaekran.setVisible(true);    // ana ekrana yonlendirme yapýyorum. 
						setVisible(false);
					}
				} catch (ClassNotFoundException e) {
				
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(191, 116, 89, 47);
		contentPane.add(btnNewButton);
		
		textSifre = new JPasswordField();
		textSifre.setBounds(115, 56, 165, 20);
		contentPane.add(textSifre);
	
		final KayýtOl kayýtol = new KayýtOl();
		JButton btnKaytOl = new JButton("Kay\u0131t Ol");
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kayýtol.setVisible(true);
			}
		});
		kayýtol.addWindowListener(new WindowAdapter() {
		
			public void windowClosing(WindowEvent e)
			{
				kayýtol.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
			
		btnKaytOl.setBackground(Color.GREEN);
		btnKaytOl.setBounds(80, 116, 89, 47);
		contentPane.add(btnKaytOl);
		
		
		
	}
	
	public boolean kullaniciSorgula() throws ClassNotFoundException
	{
		Boolean kullaniciVarmi = false;
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

			String kullaniciAd = textKullaniciAd.getText().trim();
			@SuppressWarnings("deprecation")
			String sifre = textSifre.getText();
			
			cstmt = conn.prepareCall("{? = call kullaniciSorgula(?,?)}");     // SAKLI YORDAMI KULLANDIGIM YER
			cstmt.setString("kullaniciAd", kullaniciAd);					// SAKLI YORDAM KULLANICI SORGULAYIP DEGER DONDURUYOR.
			cstmt.setString("sifre", sifre);								// DONEN DEGERE GORE ÝSLEM YAPIYORUM. 
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.execute();
			
			int sonuc = cstmt.getInt(1);
			
			if(sonuc > 0)
			{
				kullaniciVarmi = true;
			}
			
			else if (sonuc == 0) {
				JOptionPane.showMessageDialog(null, "Kayýt Bulunamadý",
						"Error", JOptionPane.ERROR_MESSAGE);
				textKullaniciAd.setText("");
				textSifre.setText("");
			}

			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kullaniciVarmi;

	}
}
