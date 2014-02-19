package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class UyeEkle extends JFrame {

	private static JPanel contentPane;
	private JTextField textUyeId;
	private JTextField textAd;
	private JTextField textSoyad;
	private JTextField textCep;
	public JTextArea textAdres;
	private JTextField textDurum;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeEkle frame = new UyeEkle();
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
	public UyeEkle() {
		setTitle("Uye Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		JLabel labelUyeId = new JLabel("Uye Id :");
		labelUyeId.setBackground(Color.WHITE);
		labelUyeId.setBounds(10, 26, 78, 14);
		contentPane.add(labelUyeId);
		
		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(10, 53, 46, 14);
		contentPane.add(lblAd);
		
		JLabel lblSoyadl = new JLabel("Soyad:");
		lblSoyadl.setBounds(10, 78, 46, 14);
		contentPane.add(lblSoyadl);
		
		JLabel labelCep = new JLabel("Cep : ");
		labelCep.setBounds(10, 105, 46, 14);
		contentPane.add(labelCep);
		
		JLabel lblAdres = new JLabel("Adres : ");
		lblAdres.setBounds(10, 168, 67, 14);
		contentPane.add(lblAdres);
		
		textUyeId = new JTextField();
		textUyeId.setBounds(121, 23, 150, 20);
		contentPane.add(textUyeId);
		textUyeId.setColumns(10);
		
		textAd = new JTextField();
		textAd.setBounds(121, 50, 150, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textSoyad = new JTextField();
		textSoyad.setBounds(121, 75, 150, 20);
		contentPane.add(textSoyad);
		textSoyad.setColumns(10);
		
		textCep = new JTextField();
		textCep.setBounds(121, 102, 150, 20);
		contentPane.add(textCep);
		textCep.setColumns(10);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					yeniUyeEkle();
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		btnEkle.setBounds(191, 237, 89, 39);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		textAdres = new JTextArea();
		textAdres.setBackground(Color.WHITE);
		textAdres.setBounds(121, 155, 159, 70);
		contentPane.add(textAdres);
		
		JLabel lblNewLabel = new JLabel("Aktivlik Durumu");
		lblNewLabel.setBounds(10, 130, 107, 14);
		contentPane.add(lblNewLabel);
		
		textDurum = new JTextField();
		textDurum.setBounds(121, 127, 150, 20);
		contentPane.add(textDurum);
		textDurum.setColumns(10);

	
	}
	public void yeniUyeEkle() throws ClassNotFoundException
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
		String uyeid = textUyeId.getText();
		String ad = textAd.getText();
		String soyad = textSoyad.getText();
		String cep = textCep.getText();
		String adres = textAdres.getText();
		String durum = textDurum.getText();

		try {

			PreparedStatement ps = conn
					.prepareStatement("insert into t_uye (uye_id,uye_ad,uye_soyad,uye_cep,uye_adres,uye_durum) values (?,?,?,?,?,?)");
			ps.setString(1,uyeid.trim());
			ps.setString(2, ad.trim());
			ps.setString(3, soyad.trim());
		
			ps.setString(4, cep.trim());
			ps.setString(5, adres.trim());
			ps.setString(6, durum.trim());
	
			ps.executeUpdate();
				
			JOptionPane.showMessageDialog(null,"Bilgileri girilen yazar baþarý ile eklendi!");
			
			textUyeId.setText("");
			textAd.setText("");
			textSoyad.setText("");
			
			textCep.setText("");
			textAdres.setText("");
			textDurum.setText("");
			
		
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}