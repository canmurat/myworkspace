package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class YazarEkle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private JTextField textYazarID;
	private JTextField textYazarAd;
	private JTextField textYazarSoyad;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YazarEkle frame = new YazarEkle();
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
	public YazarEkle() {
		setTitle("Yazar Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		JLabel labelYazarýD = new JLabel("Yazar ID:");
		labelYazarýD.setBounds(10, 26, 78, 14);
		contentPane.add(labelYazarýD);
		
		JLabel lblAd = new JLabel("Yazar Ad : ");
		lblAd.setBounds(10, 53, 101, 14);
		contentPane.add(lblAd);
		
		JLabel labelSoyad = new JLabel("Yazar Soyad : ");
		labelSoyad.setBounds(10, 78, 101, 14);
		contentPane.add(labelSoyad);
		
		textYazarID = new JTextField();
		textYazarID.setBounds(121, 23, 150, 20);
		contentPane.add(textYazarID);
		textYazarID.setColumns(10);
		
		textYazarAd = new JTextField();
		textYazarAd.setBounds(121, 50, 150, 20);
		contentPane.add(textYazarAd);
		textYazarAd.setColumns(10);
		
		textYazarSoyad = new JTextField();
		textYazarSoyad.setBounds(121, 75, 150, 20);
		contentPane.add(textYazarSoyad);
		textYazarSoyad.setColumns(10);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					yeniHastaEkle();
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		btnEkle.setBounds(182, 106, 89, 39);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		
	}
	public void yeniHastaEkle() throws ClassNotFoundException
	{
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String tcno = textYazarID.getText();
		String ad = textYazarAd.getText();
		String soyad = textYazarSoyad.getText();


		try {

			PreparedStatement ps = conn
					.prepareStatement("insert into t_yazar (yazar_id,yazar_ad,yazar_soyad) values (?,?,?)");
			ps.setString(1,tcno.trim());
			ps.setString(2, ad.trim());
			ps.setString(3, soyad.trim());
		
	
			ps.executeUpdate();
				
			JOptionPane.showMessageDialog(null, "Bilgileri girilen yazar baþarý ile eklendi!");
			
			textYazarID.setText("");
			textYazarAd.setText("");
			textYazarSoyad.setText("");
		
		
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}