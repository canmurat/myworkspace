package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.toedter.calendar.JDateChooser;

public class MuayeneleriGoster extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JTable table;
	private JTextField textAd;
	private JTextField textSoyad;
	public JDateChooser dateChooser;
	static DefaultTableModel model;

	static String[] basliklar = { "DoktorNo", "Doktor Adý",
			"Doktor Soyadý", "Hasta Tc No", "HastaAdý", "Hasta Soyadý",
			"Tarih", "Saat","MuayeneSýrasý" };

	public static void main(String[] args) throws ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuayeneleriGoster muayeneleriGoster = new MuayeneleriGoster();
					muayeneleriGoster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void MuayeneleriGoruntule(String sorgu)
			throws ClassNotFoundException {

		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		table.setModel(model);

		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

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

	
				String MuayeneNo = rs.getString("MuayeneNo");
				String DoktorNo = rs.getString("DoktorNo");
				String DoktorAd = rs.getString("Ad");
				String Soyad = rs.getString("Soyad");
				String HastaTcNo = rs.getString("HastaTcNo");
				String HastaAdý = rs.getString("HastaAd");
				String HastaSoyad = rs.getString("HastaSoyad");
				String Tarih = rs.getString("tarih");
				String Saat = rs.getString("Saat");
				String MuayeneSýrasý = rs.getString("MuayeneSýrasý");

				model.addRow(new Object[] {DoktorNo, DoktorAd,
						Soyad, HastaTcNo, HastaAdý, HastaSoyad, Tarih, Saat, MuayeneSýrasý });
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

	public MuayeneleriGoster() {
		setTitle("MUAYENELER\u0130 GOSTER");
		
		table = new JTable();
		table.setBounds(10, 77, 648, 223);
		getContentPane().add(table);

		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		table.setModel(model);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 440);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.YELLOW);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(648, 190);
		scroll.setLocation(10, 67);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scroll);

		model.setColumnIdentifiers(basliklar);
		table.setModel(model);

		JLabel lblDoktorAd = new JLabel("Doktor Ad\u0131 : ");
		lblDoktorAd.setBounds(10, 31, 88, 14);
		getContentPane().add(lblDoktorAd);

		textAd = new JTextField();
		textAd.setBounds(84, 28, 118, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JLabel lblNewLabel = new JLabel("Doktor Soyad\u0131 : ");
		lblNewLabel.setBounds(233, 31, 88, 14);
		getContentPane().add(lblNewLabel);

		textSoyad = new JTextField();
		textSoyad.setBounds(331, 28, 112, 20);
		getContentPane().add(textSoyad);
		textSoyad.setColumns(10);

		JButton btnMuayeneleriGoruntule = new JButton(
				"Muayeneleri Goruntule");
		btnMuayeneleriGoruntule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textAd.getText().isEmpty() && textSoyad.getText().isEmpty()) {  //Kullanici Adi & Sifre girilmemis ise hepsini goster

					try {
						MuayeneleriGoruntule("select muayeneler.MuayeneNo,doktorlar.DoktorNo,doktorlar.Ad ,doktorlar.Soyad,"
								+ "hastalar.HastaTcNo ,hastalar.HastaAd,hastalar.HastaSoyad,muayeneler.tarih,muayeneler.Saat,muayeneler.MuayeneSýrasý from muayeneler"
								+ "  inner join doktorlar on doktorlar.DoktorNo = muayeneler.DoktorNo inner "
								+ "join hastalar on hastalar.HastaTcNo = muayeneler.HastaTcNo ");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				} else {      // Kullanici Adý Sifre girilmis ise onlara gore arama yap
					try {
						MuayeneleriGoruntule("select muayeneler.MuayeneNo,doktorlar.DoktorNo,doktorlar.Ad ,doktorlar.Soyad,"
								+ "hastalar.HastaTcNo ,hastalar.HastaAd,hastalar.HastaSoyad,muayeneler.tarih,muayeneler.Saat,muayeneler.MuayeneSýrasý from muayeneler"
								+ " inner join doktorlar on doktorlar.DoktorNo = muayeneler.DoktorNo inner "
								+ "join hastalar on hastalar.HastaTcNo = muayeneler.HastaTcNo and ( doktorlar.Ad ='"
								+ textAd.getText().trim()
								+ "' or doktorlar.Soyad = '"
								+ textSoyad.getText().trim() + "')");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				}
			}
		});
		btnMuayeneleriGoruntule.setBackground(Color.GREEN);
		btnMuayeneleriGoruntule.setBounds(474, 27, 163, 23);
		getContentPane().add(btnMuayeneleriGoruntule);

		JButton btnMuayeneSil = new JButton("Muayene Sil");
		btnMuayeneSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int secilenSatir = table.getSelectedRow();

				String MuayeneSýrasý = (String) table.getValueAt(secilenSatir, 8);

				String silinecekNo = MuayeneSýrasý;
				System.out.println("silinecekNo= "+silinecekNo);

				int secim = JOptionPane
						.showConfirmDialog(
								// Silmek istediginizden Emin misiniz ?
								null,
								"Secilen Muayeneyi silmek istediginizden emin misiniz ? ",
								"Muayene", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						new MuayeneSil(silinecekNo);
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
		btnMuayeneSil.setBounds(522, 337, 107, 53);
		getContentPane().add(btnMuayeneSil);
		btnMuayeneSil.setBackground(Color.GREEN);
		dateChooser = new JDateChooser(new Date());
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(3, 337, 172, 20);
		getContentPane().add(dateChooser);

		JButton btnTariheGoreAra = new JButton("TAR\u0130HE GORE ARA");
		btnTariheGoreAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				String tarih = fmt.format(dateChooser.getDate());
				if (textAd.getText().isEmpty() && textSoyad.getText().isEmpty()
						&& !tarih.isEmpty()) {

					try {
						MuayeneleriGoruntule("select muayeneler.MuayeneNo,doktorlar.DoktorNo,doktorlar.Ad ,doktorlar.Soyad,"
								+ "hastalar.HastaTcNo ,hastalar.HastaAd,hastalar.HastaSoyad,muayeneler.tarih,muayeneler.Saat,muayeneler.MuayeneSýrasý from "
								+ "muayeneler  inner join doktorlar on doktorlar.DoktorNo = muayeneler.DoktorNo inner "
								+ "join hastalar on hastalar.HastaTcNo = muayeneler.HastaTcNo Where muayeneler.tarih='"
								+ tarih + "'");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				} else if ((!textAd.getText().isEmpty() || !textSoyad.getText().isEmpty())
						&& !tarih.isEmpty()) {
					try {
						MuayeneleriGoruntule("select muayeneler.MuayeneNo,doktorlar.DoktorNo,doktorlar.Ad ,doktorlar.Soyad,"
								+ "hastalar.HastaTcNo ,hastalar.HastaAd,hastalar.HastaSoyad,muayeneler.tarih,muayeneler.Saat,muayeneler.MuayeneSýrasý from "
								+ "muayeneler  inner join doktorlar on doktorlar.DoktorNo = muayeneler.DoktorNo inner "
								+ "join hastalar on hastalar.HastaTcNo = muayeneler.HastaTcNo where (( doktorlar.Ad ='"
								+ textAd.getText().trim()
								+ "' and doktorlar.Soyad = '"
								+ textSoyad.getText().trim()
								+ "') or doktorlar.Ad='"
								+ textAd.getText().trim()
								+ "' or doktorlar.Soyad='"
								+ textSoyad.getText().trim() + "')and muayeneler.tarih='"
								+ tarih + "'");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				}

			}
		});
		btnTariheGoreAra.setBounds(185, 337, 163, 53);
		getContentPane().add(btnTariheGoreAra);
		btnTariheGoreAra.setBackground(Color.green);

	}
}
