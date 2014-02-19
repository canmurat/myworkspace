package gorselpaket;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AnaPencere extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static Connection conn;
	String hastaad;
	String hastasoyad;
	int i=0;
	String[] columnNames = {"Ad","Soyad"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaPencere frame = new AnaPencere();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public AnaPencere() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon image = new ImageIcon("images/arti.png");
		JButton btnHastaKaydet = new JButton("Hasta Kaydet", image);
		btnHastaKaydet.setBounds(33, 33, 115, 80);
		contentPane.add(btnHastaKaydet);

		JButton btnNewButton = new JButton("Randevu Ver");
		btnNewButton.setBounds(170, 33, 124, 80);
		contentPane.add(btnNewButton);

		JButton btnRandevuIptal = new JButton("Randevu \u0130ptal");
		btnRandevuIptal.setBounds(314, 33, 124, 80);
		contentPane.add(btnRandevuIptal);

		JButton btnCks = new JButton("Cikis Yap");
		btnCks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int secim = JOptionPane.showConfirmDialog(null,
						"Çýkmak istediðinize emin misiniz ?", "Çýkýþ",
						JOptionPane.YES_NO_OPTION);

				if (secim == JOptionPane.YES_OPTION)
					System.exit(0);
				else
					return;
			}

		});
		btnCks.setBounds(535, 308, 100, 42);
		contentPane.add(btnCks);
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
	///	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		final DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		JButton btnNewButton_1 = new JButton("Hastalar\u0131 Goruntule");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					PreparedStatement ps = conn
							.prepareStatement("select * from hastalar");
					/*
					 * if UDF, and need to pass params, can do something like:
					 * ...prepareStatement("select * from UDF('" + UDFinputVal + "')"
					 */

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						hastaad = rs.getString("Ad");
						hastasoyad = rs.getString("Soyad");
						
						model.addRow(new Object[]{hastaad,hastasoyad});
						i++;
						}
					if(i <1)
					{
					JOptionPane.showMessageDialog(null, "No Record Found","Error",
					JOptionPane.ERROR_MESSAGE);
					}
					if(i ==1)
					{
					System.out.println(i+" Record Found");
					}
					rs.close();
					ps.close();

				} catch (Exception 	e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(462, 33, 124, 80);
		contentPane.add(btnNewButton_1);
		
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		
		table.setBackground(Color.WHITE);
		table.setBounds(33, 140, 492, 194);
		contentPane.add(table);
		contentPane.add(scroll);
	}
}
