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

public class YazarIslemleri extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	static Connection conn;
	
	String[] basliklar = { "Yazar ID", "Yazar Ad", "Yazar Soyad" };
	String YazarID;
	String YazarAd;
    String YazarSoyad;
	private JTextField textAd;
	private JTextField textSoyad;
	DefaultTableModel model;
	String yazarId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YazarIslemleri frame = new YazarIslemleri();
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

				YazarID = rs.getString("yazar_id").trim();
				YazarAd = rs.getString("yazar_ad").trim();
				YazarSoyad = rs.getString("yazar_soyad").trim();
				
				YazarID = (YazarID == null) ? "" : YazarID;    // DB 'den alýnan degerler null ise "" ile degistir.
				YazarAd = (YazarAd == null) ? "" : YazarAd ;                        // bunu yapmamým amacý , null degerlerde verilen hayatý gidermek
				YazarSoyad = (YazarSoyad == null) ? "" : YazarSoyad ;

				
				model.addRow(new Object[] { YazarID, YazarAd, YazarSoyad});
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

	public YazarIslemleri() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 409);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Yazar Islemleri");

		JButton btnYazarAra = new JButton("Yazar Ara");
		btnYazarAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (YazarAra(textAd.getText())) {

						TabloyuDoldur("select * from t_yazar where (yazar_ad='"
								+ textAd.getText() + "'",table);

					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});
		btnYazarAra.setBounds(518, 82, 118, 23);
		getContentPane().add(btnYazarAra);
		btnYazarAra.setBackground(Color.WHITE);
		table = new JTable();
		table.setBounds(40, 83, 511, 210);
		getContentPane().add(table);
		
		final KitapEkle kitapekle = new KitapEkle();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				System.out.println(table.getValueAt(table.getSelectedRow(),
						0).toString() +"table.getValueAt(table.getSelectedRow()");
				kitapekle.textYazarId.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
				
				int secilensatir = table.getSelectedRow();
				yazarId= (String) table.getValueAt(secilensatir, 0);
			}
		});
		
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
					TabloyuDoldur("select * from t_yazar",table);
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
			}
		});
		btnGoruntule.setBounds(117, 24, 118, 55);
		getContentPane().add(btnGoruntule);
		btnGoruntule.setBackground(Color.WHITE);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(607, 196);
		scroll.setLocation(29, 134);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// scroll.add(table, null);
		getContentPane().add(scroll);

		JButton btnEkle = new JButton("EKLE");
		final JFrame yazarekle = new YazarEkle();
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				yazarekle.setVisible(true);
			}
		});
		yazarekle.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				yazarekle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}

		});

		btnEkle.setBounds(256, 24, 89, 55);
		getContentPane().add(btnEkle);
		btnEkle.setBackground(Color.WHITE);
		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(480, 24, 28, 14);
		getContentPane().add(lblAd);

		textAd = new JTextField();
		textAd.setBounds(518, 21, 118, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JLabel lblSoyad = new JLabel("Soyad:");
		lblSoyad.setBounds(474, 57, 46, 14);
		getContentPane().add(lblSoyad);

		textSoyad = new JTextField();
		textSoyad.setBounds(518, 51, 122, 20);
		getContentPane().add(textSoyad);
		textSoyad.setColumns(10);

		JButton btnSil = new JButton("Sil");
		btnSil.setBackground(Color.WHITE);
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int secilenSatir = table.getSelectedRow();

				String TcKimlikNo = (String) table.getValueAt(secilenSatir, 0);

				String silinecekNo = TcKimlikNo;

				int secim = JOptionPane
						.showConfirmDialog(
								// Silmek istediginizden Emin misiniz ?
								null,
								"Secilen Yazarý silmek istediginizden emin misiniz ? ",
								"Yazarý Sil", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						new YazarSil(silinecekNo);
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
		btnSil.setBounds(375, 24, 89, 55);
		getContentPane().add(btnSil);
		final YazarGuncelle yazarGuncelle = new YazarGuncelle();
		JButton btnGuncelle = new JButton("GUNCELLE");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				yazarGuncelle.setVisible(true);
			}
		});
		btnGuncelle.setBounds(10, 24, 85, 55);
		getContentPane().add(btnGuncelle);
		btnGuncelle.setBackground(Color.WHITE);
		yazarGuncelle.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				yazarGuncelle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}

		});

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {

				yazarGuncelle.textYazarId.setText(table.getValueAt(table.getSelectedRow(),
						0).toString());
			
				
				int secilensatir = table.getSelectedRow();
				YazarID = (String) table.getValueAt(secilensatir, 0);
			}
		});
	}

	public Boolean YazarAra(String YazarAd)
			throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Boolean yazarvarmi = false;

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
					.prepareStatement("select * from t_yazar");

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				String yazar_ad = rs.getString("yazar_ad");
			

				if (YazarAd.trim().equals(yazar_ad.trim())) {
					yazarvarmi = true;

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
		return yazarvarmi;

	}
}
