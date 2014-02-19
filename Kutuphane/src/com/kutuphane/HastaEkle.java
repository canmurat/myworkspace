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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class HastaEkle extends JFrame {

	private static JPanel contentPane;
	private JTextField textTcNo;
	private JTextField textAd;
	private JTextField textSoyad;
	private JTextField textCinsiyet;
	private JTextField textTel;
	private JTextField textKGrup;
	public  JDateChooser textDTarih;
	public  JDateChooser textKTarih;
	public JTextArea textAdres;
	public JLabel lblHastaKayT;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaEkle frame = new HastaEkle();
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
	public HastaEkle() {
		setTitle("Hasta Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.YELLOW);
		JLabel lblHastaTcNo = new JLabel("Hasta Tc No : ");
		lblHastaTcNo.setBounds(10, 26, 78, 14);
		contentPane.add(lblHastaTcNo);
		
		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(10, 53, 46, 14);
		contentPane.add(lblAd);
		
		JLabel lblSoyadl = new JLabel("Soyad:");
		lblSoyadl.setBounds(10, 78, 46, 14);
		contentPane.add(lblSoyadl);
		
		JLabel lblNewLabel = new JLabel("Dogum Tarihi : ");
		lblNewLabel.setBounds(10, 103, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCinsiyet = new JLabel("Cinsiyet:");
		lblCinsiyet.setBounds(10, 128, 46, 14);
		contentPane.add(lblCinsiyet);
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setBounds(10, 153, 67, 14);
		contentPane.add(lblTel);
		
		JLabel lblKanGurubu = new JLabel("Kan Gurubu : ");
		lblKanGurubu.setBounds(10, 178, 78, 14);
		contentPane.add(lblKanGurubu);
		
		JLabel lblAdres = new JLabel("Adres : ");
		lblAdres.setBounds(10, 228, 67, 14);
		contentPane.add(lblAdres);
		
		textTcNo = new JTextField();
		textTcNo.setBounds(121, 23, 150, 20);
		contentPane.add(textTcNo);
		textTcNo.setColumns(10);
		
		textAd = new JTextField();
		textAd.setBounds(121, 50, 150, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textSoyad = new JTextField();
		textSoyad.setBounds(121, 75, 150, 20);
		contentPane.add(textSoyad);
		textSoyad.setColumns(10);
		
		textCinsiyet = new JTextField();
		textCinsiyet.setBounds(121, 125, 150, 20);
		contentPane.add(textCinsiyet);
		textCinsiyet.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(121, 150, 150, 20);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		textKGrup = new JTextField();
		textKGrup.setBounds(121, 175, 150, 20);
		contentPane.add(textKGrup);
		textKGrup.setColumns(10);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					yeniHastaEkle();
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		btnEkle.setBounds(202, 309, 89, 39);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		textAdres = new JTextArea();
		textAdres.setBounds(121, 228, 192, 70);
		contentPane.add(textAdres);
		
		lblHastaKayT = new JLabel("Hasta Kay\u0131t Tarihi :");
		lblHastaKayT.setBounds(10, 203, 115, 14);
		contentPane.add(lblHastaKayT);
		
		textDTarih = new JDateChooser();
		textDTarih.setDateFormatString("yyyy-MM-dd");
		textDTarih.setBounds(121, 103, 150, 20);
		contentPane.add(textDTarih);
		
		textKTarih = new JDateChooser();
		textKTarih.setDateFormatString("yyyy-MM-dd");
		textKTarih.setBounds(121, 203, 150, 20);
		contentPane.add(textKTarih);
	}
	public void yeniHastaEkle() throws ClassNotFoundException
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
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String tcno = textTcNo.getText();
		String ad = textAd.getText();
		String soyad = textSoyad.getText();
		String tarih = fmt.format(textDTarih.getDate());
		String cinsiyet = textCinsiyet.getText();
		String adres = textAdres.getText();
		String tel = textTel.getText();
		String kanGrup = textKGrup.getText();
		String hastaKayýtTarih = fmt.format(textKTarih.getDate());
		try {

			PreparedStatement ps = conn
					.prepareStatement("insert into hastalar (HastaTcNo,HastaAd,HastaSoyad,HastaDogumTarih,HastaCinsiyet,HastaAdres,HastaTelefon,HastaKanGurubu,HastaKayýtTarih) values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1,tcno.trim());
			ps.setString(2, ad.trim());
			ps.setString(3, soyad.trim());
			ps.setString(4, tarih);
			ps.setString(5, cinsiyet.trim());
			ps.setString(6, adres.trim());
			ps.setString(7, tel.trim());
			ps.setString(8, kanGrup.trim());
			ps.setString(9, hastaKayýtTarih);
			ps.executeUpdate();
				
			JOptionPane.showMessageDialog(null, "Bilgileri girilen hasta baþarý ile eklendi!");
			
			textTcNo.setText("");
			textAd.setText("");
			textSoyad.setText("");
			textDTarih.setDate(null);
			textCinsiyet.setText("");
			textAdres.setText("");
			textTel.setText("");
			textKGrup.setText("");
			textKTarih.setDate(null);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}