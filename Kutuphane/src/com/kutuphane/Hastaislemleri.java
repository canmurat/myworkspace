package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

public class Hastaislemleri extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	static Connection conn;
	
	static String[] basliklar = { "Tc Numarasý", "Ad", "Soyad", "Dogum Tarihi",
			"Cinsiyet", "Tel", "Kan Gurubu", "Adres","Hasta Kayýt T." };
	static String[] comboboxitemleri = { "TcNo", "Adý Soyadý", "TelNo",
			"SicilNo" };
	static String HastaTcNo;
	static String Ad;
	static String Soyad;
	static Date DogumTarih;
	static String Cinsiyet;
	static String Adres;
	static String Telefon;
	static String KanGurubu;
	static Date HastaKayýtTarih;
	private JTextField textAd;
	private JTextField textSoyad;
	DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hastaislemleri frame = new Hastaislemleri();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

				HastaTcNo = rs.getString("HastaTcNo").trim();
				Ad = rs.getString("HastaAd").trim();
				Soyad = rs.getString("HastaSoyad").trim();
				DogumTarih = rs.getDate("HastaDogumTarih");
				Cinsiyet = rs.getString("HastaCinsiyet").trim();
				Adres = rs.getString("HastaAdres").trim();
				Telefon = rs.getString("HastaTelefon").trim();
				KanGurubu = rs.getString("HastaKanGurubu").trim();
				HastaKayýtTarih = rs.getDate("HastaKayýtTarih");
				
				HastaTcNo = (HastaTcNo == null) ? "" : HastaTcNo;    // DB 'den alýnan degerler null ise "" ile degistir.
				Ad = (Ad == null) ? "" : Ad ;                        // bunu yapmamým amacý , null degerlerde verilen hayatý gidermek
				Soyad = (Soyad == null) ? "" : Soyad ;
				DogumTarih = (Date) ((DogumTarih == null) ? "" : DogumTarih);
				Cinsiyet = (Cinsiyet == null) ? "" : Cinsiyet;
				Adres = (Adres == null) ? "" : Adres ; 
				Telefon = (Telefon == null) ? "" : Telefon;
				KanGurubu = (KanGurubu == null) ? "" : KanGurubu;

				
				model.addRow(new Object[] { HastaTcNo, Ad, Soyad, DogumTarih,
						Cinsiyet,Telefon, KanGurubu, Adres, HastaKayýtTarih });
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

	public Hastaislemleri() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 409);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.YELLOW);
		setTitle("Hasta Islemleri");

		JButton btnHastaAra = new JButton("HASTA ARA");
		btnHastaAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (HastaAra(textAd.getText(), textSoyad.getText())) {

						TabloyuDoldur("select * from hastalar where (HastaAd='"
								+ textAd.getText() + "'" + "and HastaSoyad='"
								+ textSoyad.getText() + "') or HastaAd ='"
								+ textAd.getText() + "'" + "or HastaSoyad='"
								+ textSoyad.getText() + "';",table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});
		btnHastaAra.setBounds(428, 28, 103, 23);
		getContentPane().add(btnHastaAra);
		btnHastaAra.setBackground(Color.GREEN);
		table = new JTable();
		table.setBounds(40, 83, 511, 210);
		getContentPane().add(table);
		
		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		final DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		JButton btnGoruntule = new JButton("GORUNTULE");
		btnGoruntule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TabloyuDoldur("select * from hastalar",table);
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});
		btnGoruntule.setBounds(153, 304, 118, 55);
		getContentPane().add(btnGoruntule);
		btnGoruntule.setBackground(Color.green);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(551, 236);
		scroll.setLocation(20, 57);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JButton btnEkle = new JButton("EKLE");
		final JFrame hastaekle = new HastaEkle();
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				hastaekle.setVisible(true);
			}
		});
		hastaekle.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				hastaekle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}

		});

		btnEkle.setBounds(281, 304, 89, 55);
		getContentPane().add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(40, 32, 28, 14);
		getContentPane().add(lblAd);

		textAd = new JTextField();
		textAd.setBounds(78, 29, 103, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JLabel lblSoyad = new JLabel("Soyad:");
		lblSoyad.setBounds(203, 32, 46, 14);
		getContentPane().add(lblSoyad);

		textSoyad = new JTextField();
		textSoyad.setBounds(259, 29, 111, 20);
		getContentPane().add(textSoyad);
		textSoyad.setColumns(10);

		JButton btnSil = new JButton("Sil");
		btnSil.setBackground(Color.green);
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int secilenSatir = table.getSelectedRow();

				String TcKimlikNo = (String) table.getValueAt(secilenSatir, 0);

				String silinecekNo = TcKimlikNo;

				int secim = JOptionPane
						.showConfirmDialog(
								// Silmek istediginizden Emin misiniz ?
								null,
								"Secilen Hastayý silmek istediginizden emin misiniz ? ",
								"Doktoru Sil", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						System.out.println("secilen 'in hastanýn no su = "
								+ silinecekNo);
						new HastaSil(silinecekNo);
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
		btnSil.setBounds(380, 304, 89, 55);
		getContentPane().add(btnSil);

		JButton btnKapat = new JButton("C\u0131k\u0131s");
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
		btnKapat.setBounds(482, 304, 89, 55);
		getContentPane().add(btnKapat);
		btnKapat.setBackground(Color.GREEN);
		final HastaGuncelle hastaguncelle = new HastaGuncelle();
		JButton btnGuncelle = new JButton("GUNCELLE");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				hastaguncelle.setVisible(true);
			}
		});
		btnGuncelle.setBounds(40, 304, 103, 55);
		getContentPane().add(btnGuncelle);
		btnGuncelle.setBackground(Color.GREEN);
		hastaguncelle.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				hastaguncelle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}

		});

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				hastaguncelle.textTcNo.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
				hastaguncelle.textAd.setText(table.getValueAt(table.getSelectedRow(),
						1).toString());
				hastaguncelle.textSoyad.setText(table.getValueAt(table.getSelectedRow(),
						2).toString());
				hastaguncelle.textDTarih.setDate((java.util.Date)table.getValueAt(table.getSelectedRow(),
						3));
				hastaguncelle.textCinsiyet.setText(table.getValueAt(table.getSelectedRow(),
						4).toString());
				hastaguncelle.textTel.setText(table.getValueAt(table.getSelectedRow(),
						5).toString());
				hastaguncelle.textKGrup.setText(table.getValueAt(table.getSelectedRow(),
						6).toString());
				hastaguncelle.textAdres.setText(table.getValueAt(table.getSelectedRow(),
						7).toString());
				hastaguncelle.textKTarih.setDate((java.util.Date)table.getValueAt(table.getSelectedRow(),
						8));
				int secilensatir = table.getSelectedRow();
				HastaTcNo = (String) table.getValueAt(secilensatir, 0);
			}
		});
	}

	public Boolean HastaAra(String HastaAd, String HastaSoyad)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Boolean hastavarmi = false;

		DefaultTableModel model = new DefaultTableModel();
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
					.prepareStatement("select * from hastalar");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				String hastaad = rs.getString("HastaAd");
				String hastasoyad = rs.getString("HastaSoyad");

				if (HastaAd.trim().equals(hastaad.trim())
						|| (HastaSoyad.trim().equals(hastasoyad.trim()))) {
					hastavarmi = true;

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
		return hastavarmi;

	}
}
