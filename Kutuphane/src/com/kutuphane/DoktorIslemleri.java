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

public class DoktorIslemleri extends JFrame {

	static DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textAd;
	static Connection conn;
	private JTextField textSoyad;
	static String[] basliklar = { "kitap_id", "kitap_ad", "yazar_id", "yayin_evi_id",
			"kitap_sayfa","kitap_kayit_tarih" };
	static String kitap_id;   //DoktorNo
	static String kitap_ad;			//Ad
	static String yazar_id;    //Soyad
	static Date   kitap_kayit_tarih;  //Dogum_Tarihi
	static String kitap_sayfa;	
	static String yayin_evi_id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorIslemleri frame = new DoktorIslemleri();
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
	public DoktorIslemleri() {
		
		setTitle("Kitap Islemleri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 411);
		// getContentPane()..setBorder(new EmptyBorder(5, 5, 5, 5));
		// setgetContentPane().(getContentPane().);
		getContentPane().setLayout(null);

		table = new JTable();
		table.setBounds(26, 80, 632, 174);
		getContentPane().add(table);
		getContentPane().setBackground(Color.YELLOW);
		// table.setBackground(Color.orange);
		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(651, 206);
		scroll.setLocation(10, 45);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JButton btnAra = new JButton("Ara");

		btnAra.setBounds(481, 11, 89, 23);
		getContentPane().add(btnAra);
		btnAra.setBackground(Color.GREEN);
		JButton btnDoktorEkle = new JButton("EKLE");
		final JFrame doktorekle = new DoktorEkle();
		btnDoktorEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doktorekle.setVisible(true);

			}
		});
		doktorekle.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				doktorekle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}

		});

		btnDoktorEkle.setBounds(354, 276, 89, 71);
		getContentPane().add(btnDoktorEkle);
		btnDoktorEkle.setBackground(Color.GREEN);
		JButton btnDoktorSl = new JButton("S\u0130L");
		btnDoktorSl.addActionListener(new ActionListener() {
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
								"Secilen Doktor'u silmek istediginizden emin misiniz ? ",
								"Doktoru Sil", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						System.out.println("secilen 'in doktor no su = "
								+ silinecekNo);
						new DoktorSil(silinecekNo);
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
		btnDoktorSl.setBounds(255, 276, 89, 71);
		getContentPane().add(btnDoktorSl);
		btnDoktorSl.setBackground(Color.GREEN);
		
		

		textAd = new JTextField();
		textAd.setBounds(93, 14, 86, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JLabel lblDoktorAd = new JLabel("Doktor Ad\u0131 : ");
		lblDoktorAd.setBounds(10, 20, 72, 14);
		getContentPane().add(lblDoktorAd);

		JLabel lblNewLabel = new JLabel("Doktor Soyad\u0131:");
		lblNewLabel.setBounds(217, 20, 89, 14);
		getContentPane().add(lblNewLabel);

		textSoyad = new JTextField();
		textSoyad.setBounds(316, 14, 86, 20);
		getContentPane().add(textSoyad);
		textSoyad.setColumns(10);

		JButton btnDoktorlar = new JButton("G\u00D6R\u00DCNT\u00DCLE");
		btnDoktorlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TabloyuDoldur("select * from doktorlar",table);
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
					// JOptionPane.showMessageDialog(null,
					// "Tabloyu doldurmada hata!");
				}
			}
		});
		btnDoktorlar.setBounds(140, 277, 105, 68);
		getContentPane().add(btnDoktorlar);
		btnDoktorlar.setBackground(Color.GREEN);
		final DoktorGuncelle doktoruGuncelle = new DoktorGuncelle();
		JButton btnGuncelle = new JButton("G\u00DCNCELLE");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doktoruGuncelle.setVisible(true);
			}
		});
		doktoruGuncelle.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {

				doktoruGuncelle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}

			
		});
		btnGuncelle.setBounds(20, 276, 110, 71);
		getContentPane().add(btnGuncelle);
		btnGuncelle.setBackground(Color.GREEN);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

			
				doktoruGuncelle.textDoktorNo.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
				doktoruGuncelle.textAd.setText(table.getValueAt(table.getSelectedRow(),
						1).toString());
				doktoruGuncelle.textSoyad.setText(table.getValueAt(table.getSelectedRow(),
						2).toString());
				doktoruGuncelle.textDTarih.setDate((java.util.Date) table.getValueAt(table.getSelectedRow(),
						3));
				doktoruGuncelle.textCinsiyet.setText(table.getValueAt(table.getSelectedRow(),
						4).toString());
				doktoruGuncelle.textAdres.setText(table.getValueAt(table.getSelectedRow(),
						5).toString());
				doktoruGuncelle.textTel.setText(table.getValueAt(table.getSelectedRow(),
						6).toString());
				doktoruGuncelle.textUzmanlik.setText(table.getValueAt(table.getSelectedRow(),
						7).toString());
				int secilensatir = table.getSelectedRow();
				kitap_id = (String) table.getValueAt(secilensatir, 0);
			}
		});
		

		JButton btnKapat = new JButton("\u00C7IKI\u015E");
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int secim = JOptionPane.showConfirmDialog(null,
						"Cýkmak istediginizden emin misiniz ?,", "Cikis",
						JOptionPane.YES_NO_OPTION);

				if (secim == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}

			}
		});

		btnKapat.setBounds(588, 276, 89, 71);
		getContentPane().add(btnKapat);
		btnKapat.setBackground(Color.GREEN);
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("textAd.getText()= " + textAd.getText());
				System.out.println("textSoyad.getText()= "
						+ textSoyad.getText());

				try {
					if (DoktorAra(textAd.getText(), textSoyad.getText())) {

						TabloyuDoldur("select * from doktorlar where (ad='"
								+ textAd.getText() + "'" + "and soyad='"
								+ textSoyad.getText() + "') or ad ='"
								+ textAd.getText() + "'" + "or soyad='"
								+ textSoyad.getText() + "';",table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});

	}

	public Boolean DoktorAra(String DoktorAd, String DoktorSoyad)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Boolean doktorvarmi = false;

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
					.prepareStatement("select * from doktorlar");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				kitap_ad = rs.getString("Ad");
				yazar_id = rs.getString("Soyad");

				if (DoktorAd.trim().equals(kitap_ad.trim())
						|| DoktorSoyad.trim().equals(yazar_id.trim())) {
					doktorvarmi = true;

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
		return doktorvarmi;

	}
     // TabloyuDoldur yordamýna JTable turunde referans vermemizdeki amaç, bu yordamý baska sýnýflarda da kullanýlabilir kýlmak
	public void TabloyuDoldur(String sorgu,JTable table)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
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

				kitap_id = rs.getString("DoktorNo").trim();
				kitap_ad = rs.getString("Ad").trim();
				yazar_id = rs.getString("Soyad").trim();
				kitap_kayit_tarih = rs.getDate("DogumTarihi");
				yayin_evi_id = rs.getString("DogumTarihi");
				kitap_sayfa = rs.getString("fsgsgfgsfsfgsdfsd");
			

				
				
				kitap_id = (kitap_id == null) ? "" : kitap_id;    //Database'de ki null degerleri "" olarak al . Boylece hatalar onlendi.
				kitap_ad= (kitap_ad == null )? "" : kitap_ad;
				yazar_id = (yazar_id == null)? "" : yazar_id;
				kitap_kayit_tarih = (Date) ((kitap_kayit_tarih== null)? "" : kitap_kayit_tarih);
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
