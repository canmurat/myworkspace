package com.kutuphane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextPane;



public class YazarGuncelle extends JFrame {

	public static JPanel contentPane;
	public JTextField textYazarId;
	public JTextField textAd;
	public JTextField textSoyad;
	
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
	public YazarGuncelle() {
		setBackground(Color.WHITE);
		setTitle("YAZAR GUNCELLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHastaTcNo = new JLabel("Yazar Id : ");
		lblHastaTcNo.setBounds(10, 26, 78, 14);
		contentPane.add(lblHastaTcNo);
		
		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(10, 53, 46, 14);
		contentPane.add(lblAd);
		
		JLabel lblSoyadl = new JLabel("Soyad:");
		lblSoyadl.setBounds(10, 78, 46, 14);
		contentPane.add(lblSoyadl);
		
		textYazarId = new JTextField();
		textYazarId.setBounds(121, 23, 150, 20);
		contentPane.add(textYazarId);
		textYazarId.setColumns(10);
		
		textAd = new JTextField();
		textAd.setBounds(121, 50, 150, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textSoyad = new JTextField();
		textSoyad.setBounds(121, 75, 150, 20);
		contentPane.add(textSoyad);
		textSoyad.setColumns(10);
		
		JButton btnEkle = new JButton("Guncelle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					HastayýGuncelle();
					JOptionPane.showMessageDialog(null, "Hasta Basarý ile Guncellenmistir!");
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		btnEkle.setBounds(167, 120, 104, 39);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		
	
	}
	public void HastayýGuncelle() throws ClassNotFoundException
	{
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String yazar_id = textYazarId.getText();
		String yazar_ad = textAd.getText();
		String yazar_soyad = textSoyad.getText();
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");

	
		try {

			PreparedStatement ps = conn
					.prepareStatement("update t_yazar set yazar_id = '"+yazar_id+"',='"+yazar_ad+"',HastaSoyad='"+yazar_soyad+"'");
	
			ps.executeUpdate();
				
			JOptionPane.showMessageDialog(null, "Bilgileri girilen hasta baþarý ile eklendi!");
			
			textYazarId.setText("");
			textAd.setText("");
			textSoyad.setText("");
		
		
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}