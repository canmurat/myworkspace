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

public class KitapGuncelle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textAd;
	public JTextField textYazar;
	public JTextField textSayfa;
	public JTextField textKayýtTarihi;
	public JTextField textKitapId;
	private JLabel labelKitapAd;
	private JButton btnGuncelle;

	public static void main(String[] args) throws ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapGuncelle frame = new KitapGuncelle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KitapGuncelle() {
		setBackground(Color.YELLOW);
		setTitle("Kitab\u0131 Guncelle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelKitapAd = new JLabel("Kitap Ad : ");
		labelKitapAd.setBounds(10, 36, 74, 14);
		contentPane.add(labelKitapAd);
		
		JLabel labelYazarAd = new JLabel("Yazar Ad :");
		labelYazarAd.setBounds(10, 61, 74, 14);
		contentPane.add(labelYazarAd);
		
		JLabel labelKitapSayfasý = new JLabel("Kitap Sayfas\u0131 :");
		labelKitapSayfasý.setBounds(10, 86, 96, 14);
		contentPane.add(labelKitapSayfasý);
		
		JLabel labelKitapKayýtTarih = new JLabel("KitapKay\u0131tTarihi :");
		labelKitapKayýtTarih.setBounds(10, 111, 96, 14);
		contentPane.add(labelKitapKayýtTarih);
		
		textAd = new JTextField();
		textAd.setBounds(116, 33, 132, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textYazar = new JTextField();
		textYazar.setBounds(116, 58, 132, 20);
		contentPane.add(textYazar);
		textYazar.setColumns(10);
		
		textSayfa = new JTextField();
		textSayfa.setBounds(116, 83, 132, 20);
		contentPane.add(textSayfa);
		textSayfa.setColumns(10);
		
		textKayýtTarihi = new JTextField();
		textKayýtTarihi.setBounds(116, 108, 132, 20);
		contentPane.add(textKayýtTarihi);
		textKayýtTarihi.setColumns(10);
		
		btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					KitabýGuncelle();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGuncelle.setBounds(149, 160, 99, 23);
		contentPane.add(btnGuncelle);
		btnGuncelle.setBackground(Color.GREEN);
		JLabel labelKitapId = new JLabel("Kitap Id : ");
		labelKitapId.setBounds(10, 11, 74, 14);
		contentPane.add(labelKitapId);
		
		textKitapId = new JTextField();
		textKitapId.setBounds(116, 8, 132, 20);
		contentPane.add(textKitapId);
		textKitapId.setColumns(10);
		

		
	}
	public void KitabýGuncelle() throws ClassNotFoundException
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
		String kitap_id = textKitapId.getText();
		String kitap_ad = textAd.getText();
		String yazar_ad = textYazar.getText();
		String kitap_sayfa = textSayfa.getText();
		String kayýt_tarih = textKayýtTarihi.getText();
		
		
		try {

			PreparedStatement ps = conn
					.prepareStatement("update t_kitap set kitap_id= '"+kitap_id+"',kitap_ad='"+kitap_ad+"',yazar_='"+yazar_ad+"',kitap_sayfa='"+kitap_sayfa+"',Adres='',kitap_tarih='"+kayýt_tarih+"'where DoktorNo='"+kitap_id+"'");
		
			
			ps.executeUpdate();
				
			JOptionPane.showMessageDialog(null, "Kitap Basarý ile guncellendi !");
			textKitapId.setText("");
			textAd.setText("");
			textYazar.setText("");
			textSayfa.setText("");
			textKayýtTarihi.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
