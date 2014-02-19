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
import com.toedter.calendar.JDateChooser;

public class MuayeneEkraný extends JFrame {
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
	private JTable table_1;
	static Connection conn;
	static String DoktorNo;
	static String HastaTcNo;
	static DefaultTableModel model;
	static DefaultTableModel model2;
	JDateChooser textTarih;
	static String[] basliklar = { "Tc Numarasý", "Ad", "Soyad", "Dogum Tarihi",
			"Cinsiyet", "Adres", "Tel", "Kan Gurubu" };

	static String[] basliklar2 = { "DoktorNo", "Ad", "Soyad", "Dogum Tarihi",
			"Cinsiyet", "Adres", "Tel", "Uzmanlýk" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuayeneEkraný frame = new MuayeneEkraný();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MuayeneEkraný() {

		model = new DefaultTableModel();
		model2 = new DefaultTableModel();
		setTitle("MUAYENE EKRANI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 482);
		getContentPane().setLayout(null);

		JLabel lblHastaAd = new JLabel("Tarih :");
		lblHastaAd.setBounds(37, 390, 63, 14);
		getContentPane().add(lblHastaAd);

		textHastaTcNo = new JTextField();
		textHastaTcNo.setBounds(129, 362, 179, 20);
		getContentPane().add(textHastaTcNo);
		textHastaTcNo.setColumns(10);

		JLabel lblDoktorAd = new JLabel("Doktor No :");
		lblDoktorAd.setBounds(37, 340, 82, 14);
		getContentPane().add(lblDoktorAd);

		textDoktorNo = new JTextField();
		textDoktorNo.setBounds(129, 337, 179, 20);
		getContentPane().add(textDoktorNo);
		textDoktorNo.setColumns(10);

		JLabel lblDoktorSoyad = new JLabel("Hasta TC No : ");
		lblDoktorSoyad.setBounds(37, 365, 82, 14);
		getContentPane().add(lblDoktorSoyad);
		getContentPane().setBackground(Color.YELLOW);
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

				textDoktorNo.setText(table_1.getValueAt(
						table_1.getSelectedRow(), 0).toString());
				int secilensatir = table_1.getSelectedRow();
				DoktorNo = (String) table_1.getValueAt(secilensatir, 0);
			}
		});

		JLabel lblNewLabel = new JLabel("Hasta Ad:");
		lblNewLabel.setBounds(21, 17, 79, 14);
		getContentPane().add(lblNewLabel);

		textHastaAdAra = new JTextField();
		textHastaAdAra.setBounds(98, 14, 143, 20);
		getContentPane().add(textHastaAdAra);
		textHastaAdAra.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Hasta Soyad:");
		lblNewLabel_1.setBounds(283, 17, 82, 14);
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
		textHastaSoyadAra.setBounds(375, 14, 145, 20);
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
		btnHastaAra.setBounds(577, 13, 116, 23);
		getContentPane().add(btnHastaAra);
		btnHastaAra.setBackground(Color.green);
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
		final JFrame muayenelerigoster = new MuayeneleriGoster();
		JButton btnMuayeneListesiniGoruntule = new JButton("Muayene Listesi");
		btnMuayeneListesiniGoruntule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				muayenelerigoster.setVisible(true);
			}
		});
		muayenelerigoster.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				muayenelerigoster
						.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnMuayeneListesiniGoruntule.setBounds(589, 340, 136, 93);
		getContentPane().add(btnMuayeneListesiniGoruntule);
		btnMuayeneListesiniGoruntule.setBackground(Color.green);
		JButton btnMuayeneAl = new JButton("Muayene Yaz");
		btnMuayeneAl.addActionListener(new ActionListener() {
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
				java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				String DoktorNo = textDoktorNo.getText();
				String HastaTcNo = textHastaTcNo.getText();
				String tarih = fmt.format(textTarih.getDate());

				try {

					PreparedStatement ps = conn
							.prepareStatement("insert into muayeneler (DoktorNo, HastaTcNo, Tarih, saat, MuayeneSýrasý) values (?,?,?,?,?)");
					ps.setString(1, DoktorNo);
					ps.setString(2, HastaTcNo);
					ps.setString(3, tarih);
					ps.setString(4, saatAl());
					ps.setInt(5, muayeneSýrasýAl() + 1);

					ps.executeUpdate();

					JOptionPane.showMessageDialog(null,
							"Muayene bilgileri Kayýt edildi.");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnMuayeneAl.setBounds(421, 340, 143, 93);
		getContentPane().add(btnMuayeneAl);
		btnMuayeneAl.setBackground(Color.GREEN);

		textTarih = new JDateChooser();
		textTarih.setDateFormatString("yyyy-MM-dd");
		textTarih.setBounds(129, 390, 179, 20);
		getContentPane().add(textTarih);

	}

	public String saatAl() throws ParseException {
		String saat = "09:00";
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

		try {

			PreparedStatement ps = conn
					.prepareStatement("select max(saat) as sonsaat from  muayeneler where DoktorNo='"
							+ DoktorNo + "'");

			ResultSet rs = ps.executeQuery();
			
			// Gunun ilk muayenesi ise ; DB'den aldýgýmýz deger null ise , saati mesai saati baslangýcý olan 09:00 yap.
			
				while (rs.next()) {
					
					saat = (rs.getString("sonsaat")== null) ? "09:00" : rs.getString("sonsaat") ;
				}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Son alýnan muayenenin saatini 10 dakika arttýrmak icin formatladýgým
		// yer
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = df.parse(saat);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, 10);
		String sonSaat = df.format(cal.getTime());
		return sonSaat;

	}

	// En sonki muayene sýrasýný alýp 1 fazlasýný yeni muayene olarak kayýt
	// ediyorum.
	public int muayeneSýrasýAl() {
		int sonMuayeneSýrasý = 0;
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

		try {

			PreparedStatement ps = conn
					.prepareStatement("select max(MuayeneSýrasý) as sonmuayene from  muayeneler where DoktorNo='"
							+ DoktorNo + "'");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				sonMuayeneSýrasý = rs.getInt("sonmuayene");
				System.out.println("sonMuayeneSýrasý = " + sonMuayeneSýrasý);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sonMuayeneSýrasý;

	}
}
