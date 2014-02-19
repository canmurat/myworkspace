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

public class UyeGuncelle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public JTextField textUyeId;
	public JTextField textAd;
	public JTextField textSoyad;
	public JTextField textCep;
	public JTextField textDurum;
	public JTextArea textAdres;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeGuncelle frame = new UyeGuncelle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UyeGuncelle() {
		setBackground(Color.YELLOW);
		setTitle("UYE GUNCELLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelUyeId = new JLabel("Uye Id : ");
		labelUyeId.setBounds(10, 26, 78, 14);
		contentPane.add(labelUyeId);

		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(10, 53, 46, 14);
		contentPane.add(lblAd);

		JLabel lblSoyadl = new JLabel("Soyad:");
		lblSoyadl.setBounds(10, 78, 46, 14);
		contentPane.add(lblSoyadl);

		JLabel labelCep = new JLabel("Cep  :");
		labelCep.setBounds(10, 103, 46, 14);
		contentPane.add(labelCep);

		JLabel lblKanGurubu = new JLabel("Durum : ");
		lblKanGurubu.setBounds(10, 128, 78, 14);
		contentPane.add(lblKanGurubu);

		JLabel lblAdres = new JLabel("Adres : ");
		lblAdres.setBounds(10, 156, 67, 14);
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
		textCep.setBounds(121, 100, 150, 20);
		contentPane.add(textCep);
		textCep.setColumns(10);

		textDurum = new JTextField();
		textDurum.setBounds(121, 125, 150, 20);
		contentPane.add(textDurum);
		textDurum.setColumns(10);

		JButton btnGuncelle = new JButton("Guncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					UyeyiGuncelle();
					JOptionPane.showMessageDialog(null,
							"Uye Güncellemesi Baþarýlý.");
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
			}

		});
		btnGuncelle.setBounds(183, 248, 104, 39);
		contentPane.add(btnGuncelle);
		btnGuncelle.setBackground(Color.GREEN);
		textAdres = new JTextArea();
		textAdres.setBounds(121, 156, 192, 70);
		contentPane.add(textAdres);

	}

	public void UyeyiGuncelle() throws ClassNotFoundException {
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String uyeId = textUyeId.getText().trim();
		String ad = textAd.getText().trim();
		String soyad = textSoyad.getText().trim();
		String cep = textCep.getText().trim();
		String adres = textAdres.getText().trim();
		String durum = textDurum.getText().trim();

		try {

			PreparedStatement ps = conn
					.prepareStatement("update t_uye set uye_id = '" + uyeId
							+ "',uye_ad='" + ad + "',uye_soyad='" + soyad
							+ "',uye_cep='" + cep + "',uye_adres='" + adres

							+ "',uye_durum='" + durum + "'where uye_id='"
							+ uyeId + "'");

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null,
					"Uye eklemesi baþarýlý.");

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