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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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


public class OduncIslemleri extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textKitapID;
	private JTextField textUyeID;
	private JTable table;
	private JTextField textKitapAdý;
	private JTextField textUyeAd;
	private JTextField textUyeSoyad;
	private JTable table_1;
	static Connection conn;
	static String UyeId;;
	static String KitapId;
	static DefaultTableModel model;
	static DefaultTableModel model2;
	static String[] basliklar = { "kitap_id", "kitap_ad", "yazar_id", "yayin_evi_id",
		"kitap_sayfa","kitap_kayit_tarih" };

	static String[] basliklar2 = { "uye_id", "uye_ad", "uye_soyad", "uye_cep",
		"uye_adres", "uye_durum" };
	private JTextField textOduncTarih;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OduncIslemleri frame = new OduncIslemleri();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OduncIslemleri() {

		model = new DefaultTableModel();
		model2 = new DefaultTableModel();
		setTitle("ODUNC ISLEMLER\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 482);
		getContentPane().setLayout(null);

		textKitapID = new JTextField();
		textKitapID.setBounds(129, 362, 179, 20);
		getContentPane().add(textKitapID);
		textKitapID.setColumns(10);

		JLabel labelUyeID = new JLabel("Uye No : ");
		labelUyeID.setBounds(37, 340, 82, 14);
		getContentPane().add(labelUyeID);

		textUyeID = new JTextField();
		textUyeID.setBounds(129, 337, 179, 20);
		getContentPane().add(textUyeID);
		textUyeID.setColumns(10);

		JLabel laalfasg = new JLabel("Kitap No : ");
		laalfasg.setBounds(37, 365, 82, 14);
		getContentPane().add(laalfasg);
		getContentPane().setBackground(Color.WHITE);
		table = new JTable();
		table.setBounds(21, 42, 707, 109);
		getContentPane().add(table);

		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table_1 = new JTable();
		table_1.setBounds(21, 207, 707, 122);
		getContentPane().add(table_1);

		table_1.setForeground(Color.blue);
		table_1.setRowHeight(24);
		table_1.setFont(new Font("Arial", Font.BOLD, 12));
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model.setColumnIdentifiers(basliklar);
		model2.setColumnIdentifiers(basliklar2);

		table.setModel(model);
		table_1.setModel(model2);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				textKitapID.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
				int secilensatir = table.getSelectedRow();
				KitapId = (String) table.getValueAt(secilensatir, 0);
			}
		});
		table_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				textUyeID.setText(table_1.getValueAt(
						table_1.getSelectedRow(), 0).toString());
				int secilensatir = table_1.getSelectedRow();
				UyeId = (String) table_1.getValueAt(secilensatir, 0);
			}
		});

		JLabel lblNewLabel = new JLabel("Kitap Ad\u0131 : ");
		lblNewLabel.setBounds(597, 18, 79, 14);
		getContentPane().add(lblNewLabel);

		textKitapAdý = new JTextField();
		textKitapAdý.setBounds(564, 43, 143, 20);
		getContentPane().add(textKitapAdý);
		textKitapAdý.setColumns(10);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(428, 110);
		scroll.setLocation(30, 24);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JScrollPane scroll_2 = new JScrollPane(table_1);
		scroll_2.setSize(428, 100);
		scroll_2.setLocation(30, 210);
		scroll_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll_2);

		final UyeIslemleri uye = new UyeIslemleri();
		final KitapIslemleri kitap = new KitapIslemleri();

		JButton btnKitapAra = new JButton("Kitap Ara");
		btnKitapAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (kitap.KitapAra(textKitapAdý.getText())) {

						kitap.TabloyuDoldur(
								"select * from t_kitap where kitap_ad='"
										+ textKitapAdý.getText() + "'",table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}

			}
		});
		btnKitapAra.setBounds(574, 73, 116, 23);
		getContentPane().add(btnKitapAra);
		btnKitapAra.setBackground(Color.WHITE);
		JLabel labelUyeAra = new JLabel("Uye Ad :");
		labelUyeAra.setBounds(485, 216, 79, 14);
		getContentPane().add(labelUyeAra);

		textUyeAd = new JTextField();
		textUyeAd.setBounds(564, 206, 143, 20);
		getContentPane().add(textUyeAd);
		textUyeAd.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Uye Soyad :");
		lblNewLabel_2.setBounds(485, 237, 111, 14);
		getContentPane().add(lblNewLabel_2);

		textUyeSoyad = new JTextField();
		textUyeSoyad.setBounds(564, 234, 143, 20);
		getContentPane().add(textUyeSoyad);
		textUyeSoyad.setColumns(10);

		JButton btnUyeAra = new JButton("Uye Ara");

		
		btnUyeAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("textAd.getText()= "
						+ textUyeAd.getText());
				System.out.println("textSoyad.getText()= "
						+ textUyeSoyad.getText());

				try {
					if (uye.UyeAra(textUyeAd.getText(),
							textUyeSoyad.getText())) {

						uye.TabloyuDoldur(
								"select * from t_uye where (uye_ad='"
										+ textUyeAd.getText() + "'"
										+ "and uye_soyad='"
										+ textUyeSoyad.getText()
										+ "') or uye_ad ='"
										+ textUyeAd.getText() + "'"
										+ "or uye_soyad='"
										+ textUyeSoyad.getText() + "';",
								table_1);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});

		btnUyeAra.setBounds(574, 267, 116, 23);
		getContentPane().add(btnUyeAra);
		btnUyeAra.setBackground(Color.WHITE);
		final JFrame oduncislemleri = new OduncleriGoster();
		JButton btnOduncListesi = new JButton("Odunc Listesi");
		btnOduncListesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				oduncislemleri.setVisible(true);
			}
		});
		oduncislemleri.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				oduncislemleri
						.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnOduncListesi.setBounds(533, 337, 143, 45);
		getContentPane().add(btnOduncListesi);
		btnOduncListesi.setBackground(Color.WHITE);
		JButton btnOdunc = new JButton("Odunc Ver ");
		btnOdunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn = null;
				String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				}

				try {
					conn = DriverManager.getConnection(url);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String uyeId = textUyeID.getText();
				String kitapId = textKitapID.getText();
				String tarih = textOduncTarih.getText(); 

				try {

					PreparedStatement ps = conn
							.prepareStatement("insert into t_odunc (kitap_id, uye_id, odunc_tarih, odunc_durum) values (?,?,?,?)");
					ps.setString(1, kitapId);
					ps.setString(2, uyeId);
					ps.setString(3, tarih);
					ps.setString(4, "Odunc Verildi.");					

					ps.executeUpdate();

					JOptionPane.showMessageDialog(null,
							"Odunc Verildi");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnOdunc.setBounds(352, 337, 143, 45);
		getContentPane().add(btnOdunc);
		btnOdunc.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("Odunc Tarih : ");
		lblNewLabel_1.setBounds(37, 394, 82, 14);
		getContentPane().add(lblNewLabel_1);
		
		textOduncTarih = new JTextField();
		textOduncTarih.setBounds(129, 388, 179, 20);
		getContentPane().add(textOduncTarih);
		textOduncTarih.setColumns(10);


	}

}
