package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class DoktorGuncelle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textAd;
	public JTextField textSoyad;
	public JTextField textCinsiyet;
	public JTextField textAdres;
	public JTextField textTel;
	public JTextField textUzmanlik;
	public JTextField textDoktorNo;
	public JDateChooser textDTarih;

	public static void main(String[] args) throws ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorEkle frame = new DoktorEkle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoktorGuncelle() {
		setBackground(Color.YELLOW);
		setTitle("Doktoru Guncelle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDoktorAd = new JLabel("Doktor Ad\u0131 :");
		lblDoktorAd.setBounds(10, 36, 74, 14);
		contentPane.add(lblDoktorAd);
		
		JLabel lblDoktorSoyad = new JLabel("Doktor Soyad\u0131 :");
		lblDoktorSoyad.setBounds(10, 61, 74, 14);
		contentPane.add(lblDoktorSoyad);
		
		JLabel lblNewLabel = new JLabel("Cinsiyet :");
		lblNewLabel.setBounds(10, 115, 60, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Adres :");
		lblNewLabel_1.setBounds(10, 190, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTel = new JLabel("Tel : ");
		lblTel.setBounds(10, 140, 46, 14);
		contentPane.add(lblTel);
		
		JLabel lblUzmanlk = new JLabel("Uzmanl\u0131k : ");
		lblUzmanlk.setBounds(10, 165, 74, 14);
		contentPane.add(lblUzmanlk);
		
		textAd = new JTextField();
		textAd.setBounds(116, 33, 132, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textSoyad = new JTextField();
		textSoyad.setBounds(116, 58, 132, 20);
		contentPane.add(textSoyad);
		textSoyad.setColumns(10);
		
		textCinsiyet = new JTextField();
		textCinsiyet.setBounds(116, 112, 132, 20);
		contentPane.add(textCinsiyet);
		textCinsiyet.setColumns(10);
		
		textAdres = new JTextField();
		textAdres.setBounds(116, 190, 132, 59);
		contentPane.add(textAdres);
		textAdres.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(116, 137, 132, 20);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		textUzmanlik = new JTextField();
		textUzmanlik.setBounds(116, 162, 132, 20);
		contentPane.add(textUzmanlik);
		textUzmanlik.setColumns(10);
		
		JButton btnEkle = new JButton("Guncelle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DoktoruGuncelle();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEkle.setBounds(149, 283, 99, 23);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		JLabel lblDokttorno = new JLabel("DokttorNo :");
		lblDokttorno.setBounds(10, 11, 74, 14);
		contentPane.add(lblDokttorno);
		
		textDoktorNo = new JTextField();
		textDoktorNo.setBounds(116, 8, 132, 20);
		contentPane.add(textDoktorNo);
		textDoktorNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dogum Tarihi :");
		lblNewLabel_2.setBounds(10, 86, 90, 14);
		contentPane.add(lblNewLabel_2);
		
		textDTarih = new JDateChooser();
		textDTarih.setDateFormatString("yyyy-MM-dd");
		textDTarih.setBounds(116, 86, 132, 20);
		contentPane.add(textDTarih);
		
		
	}
	public void DoktoruGuncelle() throws ClassNotFoundException
	{
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Hastane;user=mehmet;password=1234567";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String doktorNo = textDoktorNo.getText();
		String ad = textAd.getText();
		String soyad = textSoyad.getText();
		
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String tarih = fmt.format(textDTarih.getDate());
		
//		Date dateFromDateChooser = textDTarih.getDate();
//		String tarih = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);  // Db'deki date alanýný JDateChooser'a formatladým.
		String cinsiyet = textCinsiyet.getText();
		String tel = textTel.getText();
		String uzmanlik = textUzmanlik.getText();
		String adres = textAdres.getText();
		
		
		try {

			PreparedStatement ps = conn
					.prepareStatement("update doktorlar set DoktorNo= '"+doktorNo+"',Ad='"+ad+"',Soyad='"+soyad+"',DogumTarihi='"+tarih+"',Cinsiyet='"+cinsiyet+"',Adres='"+adres+"',Tel='"+tel+"',Uzmanlýk='"+uzmanlik+"'where DoktorNo='"+doktorNo+"'");
		
			
			ps.executeUpdate();
				
			JOptionPane.showMessageDialog(null, "Doktor Basarý ile guncellendi !");
			textDoktorNo.setText("");
			textAd.setText("");
			textSoyad.setText("");
			textCinsiyet.setText("");
			textTel.setText("");
			textUzmanlik.setText("");
			textAdres.setText("");
			textDTarih.setDate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
