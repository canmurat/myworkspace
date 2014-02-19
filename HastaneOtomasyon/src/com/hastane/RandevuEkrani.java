package com.hastane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;

public class RandevuEkrani extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textHastaTcNo;
	private JTextField textDoktorNo;
	private JTable table;
	private JTextField textHastaAdAra;
	private JTextField textHastaSoyadAra;
	private JTextField textDoktorAdAra;
	private JTextField textDoktorSoyadAra;
	JDateChooser textTarih;
	private JTable table_1;
	static Connection conn;
	static String DoktorNo;
	static String HastaTcNo;
	static DefaultTableModel model;
	static DefaultTableModel model2;
	static String[] basliklar = { "Tc Numarasý", "Ad", "Soyad", "Dogum Tarihi",
			"Cinsiyet", "Adres", "Tel", "Kan Gurubu" };

	static String[] basliklar2 = { "DoktorNo", "Ad", "Soyad", "Dogum Tarihi",
			"Cinsiyet", "Adres", "Tel", "Uzmanlýk" };
	private JTextField textSaat;

	/**
	 * @return the textDoktorAd
	 */
	public JTextField getTextDoktorAd() {
		return textDoktorNo;
	}

	/**
	 * @param textDoktorAd
	 *            the textDoktorAd to set
	 */
	public void setTextDoktorAd(JTextField textDoktorAd) {
		this.textDoktorNo = textDoktorAd;
	}

	/**
	 * @return the textHastaSoyad
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandevuEkrani frame = new RandevuEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RandevuEkrani() {

		model = new DefaultTableModel();
		model2 = new DefaultTableModel();
		setTitle("RANDEVU VER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 510);
		getContentPane().setLayout(null);

		JLabel lblHastaAd = new JLabel("Tarih :");
		lblHastaAd.setBounds(37, 399, 63, 14);
		getContentPane().add(lblHastaAd);
		getContentPane().setBackground(Color.YELLOW);
		textHastaTcNo = new JTextField();
		textHastaTcNo.setBounds(129, 362, 179, 20);
		getContentPane().add(textHastaTcNo);
		textHastaTcNo.setColumns(10);

		JLabel lblDoktorAd = new JLabel("Doktor No :");
		lblDoktorAd.setBounds(37, 340, 82, 14);
		getContentPane().add(lblDoktorAd);

		textDoktorNo = new JTextField();
		textDoktorNo.setBounds(129, 331, 179, 20);
		getContentPane().add(textDoktorNo);
		textDoktorNo.setColumns(10);

		JLabel lblDoktorSoyad = new JLabel("Hasta TC No : ");
		lblDoktorSoyad.setBounds(37, 365, 82, 14);
		getContentPane().add(lblDoktorSoyad);

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

				textHastaTcNo.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
				int secilensatir = table.getSelectedRow();
				HastaTcNo = (String) table.getValueAt(secilensatir, 0);
			}
		});
		table_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				textDoktorNo.setText(table_1
						.getValueAt(table_1.getSelectedRow(), 0).toString());
				int secilensatir = table_1.getSelectedRow();
				DoktorNo = (String) table_1.getValueAt(secilensatir, 0);
			}
		});

		JLabel lblNewLabel = new JLabel("Hasta Ad:");
		lblNewLabel.setBounds(21, 17, 79, 14);
		getContentPane().add(lblNewLabel);

		textHastaAdAra = new JTextField();
		textHastaAdAra.setBounds(93, 14, 130, 20);
		getContentPane().add(textHastaAdAra);
		textHastaAdAra.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Hasta Soyad:");
		lblNewLabel_1.setBounds(263, 17, 82, 14);
		getContentPane().add(lblNewLabel_1);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(695, 110);
		scroll.setLocation(30, 40);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JScrollPane scroll_2 = new JScrollPane(table_1);
		scroll_2.setSize(695, 100);
		scroll_2.setLocation(30, 210);
		scroll_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll_2);

		textHastaSoyadAra = new JTextField();
		textHastaSoyadAra.setBounds(347, 14, 136, 20);
		getContentPane().add(textHastaSoyadAra);
		textHastaSoyadAra.setColumns(10);

		final DoktorIslemleri dk = new DoktorIslemleri();
		final Hastaislemleri hs = new Hastaislemleri();

		JButton btnHastaAra = new JButton("Hasta Ara");
		btnHastaAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (hs.HastaAra(textHastaAdAra.getText(),
							textHastaSoyadAra.getText())) {

						hs.TabloyuDoldur(
								"select * from hastalar where (HastaAd='"
										+ textHastaAdAra.getText() + "'"
										+ "and Hastasoyad='"
										+ textHastaSoyadAra.getText()
										+ "') or Hastaad ='"
										+ textHastaAdAra.getText() + "'"
										+ "or Hastasoyad='"
										+ textHastaSoyadAra.getText() + "';",
								table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}

			}
		});
		btnHastaAra.setBounds(550, 13, 116, 23);
		getContentPane().add(btnHastaAra);
		btnHastaAra.setBackground(Color.GREEN);
		JLabel lblDoktorAra = new JLabel("DoktorAd:");
		lblDoktorAra.setBounds(21, 182, 79, 14);
		getContentPane().add(lblDoktorAra);

		textDoktorAdAra = new JTextField();
		textDoktorAdAra.setBounds(98, 179, 143, 20);
		getContentPane().add(textDoktorAdAra);
		textDoktorAdAra.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Doktor Soyad :");
		lblNewLabel_2.setBounds(283, 182, 111, 14);
		getContentPane().add(lblNewLabel_2);

		textDoktorSoyadAra = new JTextField();
		textDoktorSoyadAra.setBounds(377, 179, 143, 20);
		getContentPane().add(textDoktorSoyadAra);
		textDoktorSoyadAra.setColumns(10);

		JButton btnDoktorAra = new JButton("Doktor Ara");

		btnDoktorAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("textAd.getText()= "
						+ textDoktorAdAra.getText());
				System.out.println("textSoyad.getText()= "
						+ textDoktorSoyadAra.getText());

				try {
					if (dk.DoktorAra(textDoktorAdAra.getText(),
							textDoktorSoyadAra.getText())) {

						dk.TabloyuDoldur(
								"select * from doktorlar where (ad='"
										+ textDoktorAdAra.getText() + "'"
										+ "and soyad='"
										+ textDoktorSoyadAra.getText()
										+ "') or ad ='"
										+ textDoktorAdAra.getText() + "'"
										+ "or soyad='"
										+ textDoktorSoyadAra.getText() + "';",
								table_1);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});

		btnDoktorAra.setBounds(577, 178, 116, 23);
		getContentPane().add(btnDoktorAra);
		btnDoktorAra.setBackground(Color.GREEN);
		final JFrame randevularigoster = new RandevulariGoster();
		JButton btnRandevuListesiniGoruntule = new JButton("Randevu Listesi");
		btnRandevuListesiniGoruntule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				randevularigoster.setVisible(true);
			}
		});
		randevularigoster.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				randevularigoster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnRandevuListesiniGoruntule.setBounds(589, 367, 136, 93);
		getContentPane().add(btnRandevuListesiniGoruntule);
		btnRandevuListesiniGoruntule.setBackground(Color.GREEN);
		JButton btnRandevuAl = new JButton("Randevu Al");
		btnRandevuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn = null;
				String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
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
				java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
				String DoktorNo = textDoktorNo.getText();
				String HastaTcNo = textHastaTcNo.getText();
				String tarih = fmt.format(textTarih.getDate());
				String saat = textSaat.getText();
				
				try {

					PreparedStatement ps = conn
							.prepareStatement("insert into randevular (DoktorNo, HastaTcNo, Tarih, saat) values (?,?,?,?)");
					ps.setString(1, DoktorNo);
					ps.setString(2, HastaTcNo);
					ps.setString(3, tarih);
					ps.setString(4, saat);
							
					ps.executeUpdate();
						
					JOptionPane.showMessageDialog(null, "Muayene bilgileri Kayýt edildi.");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnRandevuAl.setBounds(413, 367, 143, 93);
		getContentPane().add(btnRandevuAl);
		btnRandevuAl.setBackground(Color.GREEN);
		JLabel lblNewLabel_3 = new JLabel("Saat : ");
		lblNewLabel_3.setBounds(37, 424, 82, 14);
		getContentPane().add(lblNewLabel_3);
		
		textSaat = new JTextField();
		textSaat.setBounds(129, 421, 179, 20);
		getContentPane().add(textSaat);
		textSaat.setColumns(10);
		
		textTarih = new JDateChooser();
		textTarih.setDateFormatString("yyyy-MM-dd");
		textTarih.setBounds(129, 393, 179, 20);
		getContentPane().add(textTarih);

	}
}
