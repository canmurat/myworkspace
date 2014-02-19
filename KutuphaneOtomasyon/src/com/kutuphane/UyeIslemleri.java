package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class UyeIslemleri extends JFrame {

	static DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textAd;
	static Connection conn;
	static String[] basliklar = { "uye_id", "uye_ad", "uye_soyad", "uye_cep",
			"uye_adres", "uye_durum" };
	static String uye_id; 
	static String uye_ad; 
	static String uye_soyad; 
	static String uye_cep; 
	static String uye_adres;
	static String uye_durum;
	private JTextField textSoyad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeIslemleri frame = new UyeIslemleri();
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
	public UyeIslemleri() {

		setTitle("Uye Islemleri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 411);
		// getContentPane()..setBorder(new EmptyBorder(5, 5, 5, 5));
		// setgetContentPane().(getContentPane().);
		getContentPane().setLayout(null);

		table = new JTable();
		table.setBounds(26, 80, 632, 174);
		getContentPane().add(table);
		getContentPane().setBackground(Color.WHITE);
		// table.setBackground(Color.orange);
		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(615, 206);
		scroll.setLocation(30, 155);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JButton btnAra = new JButton("Ara");

		btnAra.setBounds(552, 106, 125, 23);
		getContentPane().add(btnAra);
		btnAra.setBackground(Color.WHITE);
		JButton btnKitapEkle = new JButton("EKLE");
		final JFrame uyeekle = new UyeEkle();
		btnKitapEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uyeekle.setVisible(true);

			}
		});
		uyeekle.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				uyeekle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}

		});

		btnKitapEkle.setBounds(389, 45, 89, 71);
		getContentPane().add(btnKitapEkle);
		btnKitapEkle.setBackground(Color.WHITE);
		JButton btnKitapSl = new JButton("S\u0130L");
		btnKitapSl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// tablonun secilen satir degegerini alýyorum . amac, hic
				// secilmediyse uyarmak.

				int secilenSatir = table.getSelectedRow();

				String id = (String) model.getValueAt(secilenSatir, 0);
				// int selectedRowIndex = table.getSelectedRow();
				// int selectedColumnIndex = table.getSelectedColumn();

				String silinecekNo = id;

				int secim = JOptionPane.showConfirmDialog(
						// Silmek istediginizden Emin misiniz ?
						null,
						"Secilen uyeyi silmek istediginizden emin misiniz ? ",
						"Kitabý Sil", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						new UyeSil(silinecekNo);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}

					if (secilenSatir >= 0) { // tablodan birsey secilmis ise sil
												// yoksa uyar ..
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();
						model.removeRow(secilenSatir);
						return;
					} else {
						JOptionPane.showMessageDialog(null,
								"Hic bir sey secmediniz !");
					}
				} else if (secim == JOptionPane.CANCEL_OPTION) {
					return;
				} else {
					return;
				}

			}
		});
		btnKitapSl.setBounds(290, 45, 89, 71);
		getContentPane().add(btnKitapSl);
		btnKitapSl.setBackground(Color.WHITE);

		textAd = new JTextField();
		textAd.setBounds(552, 48, 125, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JLabel labelTextAd = new JLabel("Uye Ad :");
		labelTextAd.setBounds(503, 51, 72, 14);
		getContentPane().add(labelTextAd);

		JButton btnKitaplar = new JButton("G\u00D6R\u00DCNT\u00DCLE");
		btnKitaplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TabloyuDoldur("select * from t_uye", table);
				} catch (ClassNotFoundException e) {

					e.printStackTrace();

				}
			}
		});
		btnKitaplar.setBounds(163, 46, 105, 71);
		getContentPane().add(btnKitaplar);
		btnKitaplar.setBackground(Color.WHITE);
		final UyeGuncelle uyeGuncelle = new UyeGuncelle();
		JButton btnGuncelle = new JButton("G\u00DCNCELLE");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uyeGuncelle.setVisible(true);
			}
		});
		uyeGuncelle.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				uyeGuncelle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}

		});
		btnGuncelle.setBounds(30, 45, 110, 71);
		getContentPane().add(btnGuncelle);
		btnGuncelle.setBackground(Color.WHITE);
		
		JLabel labelUyesoyad = new JLabel("Uye Soyad :");
		labelUyesoyad.setBounds(488, 82, 73, 14);
		getContentPane().add(labelUyesoyad);
		
		textSoyad = new JTextField();
		textSoyad.setBounds(552, 79, 125, 20);
		getContentPane().add(textSoyad);
		textSoyad.setColumns(10);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				uyeGuncelle.textUyeId.setText(table.getValueAt(
						table.getSelectedRow(), 0).toString());
				uyeGuncelle.textAd.setText(table.getValueAt(
						table.getSelectedRow(), 1).toString());
				uyeGuncelle.textSoyad.setText(table.getValueAt(
						table.getSelectedRow(), 2).toString());

				uyeGuncelle.textCep.setText(table.getValueAt(
						table.getSelectedRow(), 3).toString());
				uyeGuncelle.textAdres.setText(table.getValueAt(
						table.getSelectedRow(), 4).toString());

				uyeGuncelle.textDurum.setText(table.getValueAt(
						table.getSelectedRow(), 5).toString());
				int secilensatir = table.getSelectedRow();
				uye_id = (String) table.getValueAt(secilensatir, 0);

			}
		});
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("textAd.getText()=" + textAd.getText());

				try {
					if (UyeAra(textAd.getText(),textSoyad.getText())) {

						TabloyuDoldur("select * from t_uye where uye_ad='"
								+textAd.getText().trim() + "'", table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});

	}

	public Boolean UyeAra(String uyeAd,String uyeSoyad) throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Boolean uyevarmi = false;

		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		table.setModel(model);

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			PreparedStatement ps = conn
					.prepareStatement("select * from t_uye");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				uye_ad = rs.getString("uye_ad");
				uye_soyad =rs.getString("uye_soyad");

				if (uyeAd.trim().equals(uye_ad.trim()) || (uyeSoyad.trim().equals(uye_soyad.trim()))) {
					uyevarmi = true;

				}
				i++;
			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "Kayýt Bulunamadý",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				System.out.println(i + " Record Found");
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uyevarmi;

	}

	// TabloyuDoldur yordamýna JTable turunde referans vermemizdeki amaç, bu
	// yordamý baska sýnýflarda da kullanýlabilir kýlmak
	public void TabloyuDoldur(String sorgu, JTable table)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		table.setModel(model);

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			PreparedStatement ps = conn.prepareStatement(sorgu);

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				uye_id = rs.getString("uye_id").trim();
				uye_ad = rs.getString("uye_ad").trim();
				uye_soyad = rs.getString("uye_soyad").trim();
				uye_cep = rs.getString("uye_cep");
				uye_adres = rs.getString("uye_adres");
				uye_durum = rs.getString("uye_durum");

				uye_id = (uye_id == null) ? "" : uye_id; // Database'de ki null
															// degerleri ""
															// olarak al .
															// Boylece hatalar
															// onlendi.
				uye_ad = (uye_ad == null) ? "" : uye_ad;
				uye_soyad = (uye_soyad == null) ? "" : uye_soyad;
				uye_cep =   (uye_cep == null) ? "" : uye_cep;
				uye_adres = (uye_adres == null) ? "" : uye_adres;
				uye_durum = (uye_durum == null) ? "" : uye_durum;

				model.addRow(new Object[] { uye_id, uye_ad, uye_soyad, uye_cep,
						uye_adres, uye_durum });
				i++;
			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				System.out.println(i + " Record Found");
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
