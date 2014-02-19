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

public class KitapIslemleri extends JFrame {

	static DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textAd;
	static Connection conn;
	static String[] basliklar = { "kitap_id", "kitap_ad", "yazar_id", "yayin_evi_id",
			"kitap_sayfa","kitap_kayit_tarih" };
	static String kitap_id;   
	static String kitap_ad;			
	static String yazar_id;   
	static String kitap_kayit_tarih;  
	static String kitap_sayfa;	
	static String yayin_evi_id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapIslemleri frame = new KitapIslemleri();
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
	public KitapIslemleri() {
		
		setTitle("Kitap Islemleri");
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
		scroll.setSize(651, 162);
		scroll.setLocation(10, 199);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JButton btnAra = new JButton("Ara");

		btnAra.setBounds(556, 137, 89, 23);
		getContentPane().add(btnAra);
		btnAra.setBackground(Color.WHITE);
		JButton btnKitapEkle = new JButton("EKLE");
		final JFrame uyeekle = new KitapEkle();
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

		btnKitapEkle.setBounds(371, 89, 89, 71);
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

				int secim = JOptionPane
						.showConfirmDialog(
								// Silmek istediginizden Emin misiniz ?
								null,
								"Secilen kitabý silmek istediginizden emin misiniz ? ",
								"Kitabý Sil", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						new KitapSil(silinecekNo);
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
		btnKitapSl.setBounds(253, 89, 89, 71);
		getContentPane().add(btnKitapSl);
		btnKitapSl.setBackground(Color.WHITE);
		
		

		textAd = new JTextField();
		textAd.setBounds(556, 106, 86, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JLabel labelKitapAd = new JLabel("Kitap Ad\u0131 : ");
		labelKitapAd.setBounds(556, 84, 72, 14);
		getContentPane().add(labelKitapAd);

		JButton btnKitaplar = new JButton("G\u00D6R\u00DCNT\u00DCLE");
		btnKitaplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TabloyuDoldur("select * from t_kitap",table);
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
					// JOptionPane.showMessageDialog(null,
					// "Tabloyu doldurmada hata!");
				}
			}
		});
		btnKitaplar.setBounds(130, 89, 105, 71);
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
		btnGuncelle.setBounds(10, 89, 110, 71);
		getContentPane().add(btnGuncelle);
		btnGuncelle.setBackground(Color.WHITE);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

			
				uyeGuncelle.textUyeId.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
				
				int secilensatir = table.getSelectedRow();
				kitap_id = (String) table.getValueAt(secilensatir, 0);
			}
		});
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("textAd.getText()= " + textAd.getText());
		

				try {
					if (KitapAra(textAd.getText())) {

						TabloyuDoldur("select * from t_kitap where (kitap_ad='"
								+ textAd.getText()+"'",table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});

	}

	public Boolean KitapAra(String kitapAd)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Boolean kitapvarmi = false;

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
					.prepareStatement("select * from t_kitap");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				kitap_ad = rs.getString("kitap_ad");
			

				if (kitapAd.trim().equals(kitap_ad.trim())) {
					kitapvarmi = true;

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
		return kitapvarmi;

	}
     // TabloyuDoldur yordamýna JTable turunde referans vermemizdeki amaç, bu yordamý baska sýnýflarda da kullanýlabilir kýlmak
	public void TabloyuDoldur(String sorgu,JTable table)
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

				kitap_id = rs.getString("kitap_id").trim();
				kitap_ad = rs.getString("kitap_ad").trim();
				yazar_id = rs.getString("yazar_id").trim();
				kitap_kayit_tarih = rs.getString("kitap_kayit_tarih");
				yayin_evi_id = rs.getString("yayin_evi_id");
				kitap_sayfa = rs.getString("kitap_sayfa");
			

				
				
				kitap_id = (kitap_id == null) ? "" : kitap_id;    //Database'de ki null degerleri "" olarak al . Boylece hatalar onlendi.
				kitap_ad= (kitap_ad == null )? "" : kitap_ad;
				yazar_id = (yazar_id == null)? "" : yazar_id;
				kitap_kayit_tarih = (kitap_kayit_tarih== null) ? "" : kitap_kayit_tarih;
				kitap_sayfa = (kitap_sayfa ==null)? "": kitap_sayfa;
				yayin_evi_id = (yayin_evi_id == null) ? "" : yayin_evi_id;
				

				model.addRow(new Object[] { kitap_id, kitap_ad, yazar_id, kitap_kayit_tarih,
						kitap_sayfa, yayin_evi_id });
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
