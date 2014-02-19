package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KitapEkle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTextField textKitapAd;
	JTextField textYazarId;
	JTextField textSayfa;
	JTextField textKayýtTarihi;
	JTextField textKitapId;

	public static void main(String[] args) throws ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapEkle frame = new KitapEkle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KitapEkle() {

		setTitle("Kitap Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		JLabel labelKitapAd = new JLabel("Kitap Ad :");
		labelKitapAd.setBounds(10, 58, 89, 14);
		contentPane.add(labelKitapAd);

		JLabel labelYazarAdý = new JLabel("Yazar Id :");
		labelYazarAdý.setBounds(10, 89, 89, 14);
		contentPane.add(labelYazarAdý);

		JLabel labelKitapSayfasý = new JLabel("Kitap Sayfas\u0131 :");
		labelKitapSayfasý.setBounds(10, 149, 89, 14);
		contentPane.add(labelKitapSayfasý);

		JLabel labelKitapKayýtTarih = new JLabel(
				"K\u0131tap Kay\u0131t Tarih :");
		labelKitapKayýtTarih.setBounds(10, 180, 111, 14);
		contentPane.add(labelKitapKayýtTarih);

		textKitapAd = new JTextField();
		textKitapAd.setBounds(116, 55, 132, 20);
		contentPane.add(textKitapAd);
		textKitapAd.setColumns(10);

		textYazarId = new JTextField();
		textYazarId.setBounds(116, 86, 132, 20);
		contentPane.add(textYazarId);
		textYazarId.setColumns(10);

		textSayfa = new JTextField();
		textSayfa.setBounds(116, 146, 132, 20);
		contentPane.add(textSayfa);
		textSayfa.setColumns(10);

		textKayýtTarihi = new JTextField();
		textKayýtTarihi.setBounds(116, 177, 132, 20);
		contentPane.add(textKayýtTarihi);
		textKayýtTarihi.setColumns(10);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					yeniKitapEkle();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEkle.setBounds(180, 224, 68, 23);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.WHITE);
		JLabel labeKitapId = new JLabel("Kitap Id:");
		labeKitapId.setBounds(10, 27, 89, 14);
		contentPane.add(labeKitapId);

		textKitapId = new JTextField();
		textKitapId.setBounds(116, 24, 132, 20);
		contentPane.add(textKitapId);
		textKitapId.setColumns(10);

	}

	public void yeniKitapEkle() throws ClassNotFoundException {
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String kitap_id = textKitapId.getText();
		String kitap_ad = textKitapAd.getText();
		String yazar_id = textYazarId.getText();
		String kitap_sayfa = textSayfa.getText();
		String kitap_kayit_tarih = textKayýtTarihi.getText();
		try {

			PreparedStatement ps = conn
					.prepareStatement("insert into t_kitap (kitap_id,kitap_ad,yazar_id,kitap_sayfa,kitap_kayit_tarih) values (?,?,?,?,?)");
			ps.setString(1, kitap_id);
			ps.setString(2, kitap_ad);
			ps.setString(3, yazar_id);
			ps.setString(4, kitap_sayfa);
			ps.setString(5, kitap_kayit_tarih);

			ps.executeUpdate();

			textKitapId.setText("");
			textKitapAd.setText("");
			textYazarId.setText("");
			textSayfa.setText("");
			textKayýtTarihi.setText("");

			JOptionPane.showMessageDialog(null,
					"Bilgileri girilen kitap baþarý ile eklendi!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
