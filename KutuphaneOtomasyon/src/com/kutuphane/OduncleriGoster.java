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


public class OduncleriGoster extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JTable table;
	private JTextField textAd;
	static DefaultTableModel model;

	static String[] basliklar = { "Kitap No", "Kitap Adý",
			"Uye Adý", "Uye No", "Tarih" };

	public static void main(String[] args) throws ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OduncleriGoster oduncleriGoster = new OduncleriGoster();
					oduncleriGoster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void OduncleriGoster(String sorgu)
			throws ClassNotFoundException {

		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		table.setModel(model);

		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=kutuphane;user=mehmet;password=1234567";
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

	
				String KitapId = rs.getString("kitap_id");
				String KitapAd = rs.getString("kitap_ad");
				String UyeId = rs.getString("uye_id");
				String UyeAd = rs.getString("uye_ad");
				String Tarih = rs.getString("odunc_tarih");

				model.addRow(new Object[] {KitapId, KitapAd, UyeId, UyeAd, Tarih });
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

	public OduncleriGoster() {
		setTitle("ODUNC GOSTER");
		
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
		setBounds(100, 100, 684, 352);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(463, 190);
		scroll.setLocation(10, 67);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scroll);

		model.setColumnIdentifiers(basliklar);
		table.setModel(model);

		JLabel labelKitapId = new JLabel("Kitap Ad :");
		labelKitapId.setBounds(540, 45, 118, 14);
		getContentPane().add(labelKitapId);

		textAd = new JTextField();
		textAd.setBounds(508, 70, 118, 20);
		getContentPane().add(textAd);
		textAd.setColumns(10);

		JButton btnOduncleriGoster = new JButton(
				"Odunc Goruntule");
		btnOduncleriGoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textAd.getText().isEmpty()) { 
					try {
						OduncleriGoster("select t_odunc.kitap_id,t_kitap.kitap_ad,t_uye.uye_ad,t_uye.uye_id,t_odunc.odunc_tarih"
								+ " from t_odunc"
								+ " inner join t_kitap on t_odunc.kitap_id = t_kitap.kitap_id inner"
								+ " join t_uye on t_uye.uye_id = t_odunc.uye_id");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				} else {      
					try {
						String KitapAd=textAd.getText();
						OduncleriGoster("select t_odunc.kitap_id,t_kitap.kitap_ad,t_uye.uye_ad,t_uye.uye_id,t_odunc.odunc_tarih"
								+ " from t_odunc"
								+ " inner join t_kitap on t_odunc.kitap_id = t_kitap.kitap_id inner"
								+ " join t_uye on t_uye.uye_id = t_odunc.uye_id"
								+ " where t_kitap.kitap_ad='"+KitapAd+"'");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				}
			}
		});
		btnOduncleriGoster.setBackground(Color.WHITE);
		btnOduncleriGoster.setBounds(495, 96, 163, 23);
		getContentPane().add(btnOduncleriGoster);

		JButton btnOduncSil = new JButton("Odunc Sil");
		btnOduncSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int secilenSatir = table.getSelectedRow();

				String kitap_id = (String) table.getValueAt(secilenSatir, 0);
				String kitap_ad = (String) table.getValueAt(secilenSatir, 1);
				String uye_ad = (String) table.getValueAt(secilenSatir, 2);
				String uye_id = (String) table.getValueAt(secilenSatir, 3);
				String odunc_tarih = (String) table.getValueAt(secilenSatir, 4);

				
				int secim = JOptionPane
						.showConfirmDialog(
								// Silmek istediginizden Emin misiniz ?
								null,
								"Ödünç Silinsin mi ? ",
								"Odunc", JOptionPane.YES_NO_CANCEL_OPTION);
				if (secim == JOptionPane.YES_OPTION) { // Cevap YES_OPTION(EVET)
														// ise sil.

					try {
						new OduncSil(kitap_id, kitap_ad, uye_ad, uye_id, odunc_tarih);
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
		btnOduncSil.setBounds(519, 200, 107, 53);
		getContentPane().add(btnOduncSil);

	}
}
