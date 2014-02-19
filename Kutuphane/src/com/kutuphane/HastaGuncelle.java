package com.kutuphane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextPane;
import com.toedter.calendar.JDateChooser;

public class HastaGuncelle extends JFrame {

	public static JPanel contentPane;
	public JTextField textTcNo;
	public JTextField textAd;
	public JTextField textSoyad;
	public JTextField textCinsiyet;
	public JTextField textTel;
	public JTextField textKGrup;
	public JDateChooser textKTarih;
	public JDateChooser textDTarih;
	public JTextArea textAdres;
	
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
	public HastaGuncelle() {
		setBackground(Color.YELLOW);
		setTitle("HASTA GUNCELLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		lblAdres.setBounds(10, 233, 67, 14);
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
		
		JButton btnEkle = new JButton("Guncelle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					HastayýGuncelle();
					JOptionPane.showMessageDialog(null, "Hasta Basarý ile Guncellenmistir!");
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		btnEkle.setBounds(184, 314, 104, 39);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		textAdres = new JTextArea();
		textAdres.setBounds(121, 233, 192, 70);
		contentPane.add(textAdres);
		
		textDTarih = new JDateChooser();
		textDTarih.setDateFormatString("yyyy-MM-dd");
		textDTarih.setBounds(121, 103, 150, 20);
		contentPane.add(textDTarih);
		
		JLabel lblNewLabel_1 = new JLabel("Hasta Kay\u0131t Tarihi : ");
		lblNewLabel_1.setBounds(10, 203, 121, 14);
		contentPane.add(lblNewLabel_1);
		
		textKTarih = new JDateChooser();
		textKTarih.setDateFormatString("yyyy-MM-dd");
		textKTarih.setBounds(121, 203, 150, 20);
		contentPane.add(textKTarih);
	}
	public void HastayýGuncelle() throws ClassNotFoundException
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
		String tcno = textTcNo.getText();
		String ad = textAd.getText();
		String soyad = textSoyad.getText();
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String Dtarih = fmt.format(textDTarih.getDate());
		String cinsiyet = textCinsiyet.getText();
		String adres = textAdres.getText();
		String tel = textTel.getText();
		String kanGrup = textKGrup.getText();
		String Ktarih = fmt.format(textKTarih.getDate());
		try {

			PreparedStatement ps = conn
					.prepareStatement("update hastalar set HastaTcNo = '"+tcno+"',HastaAd='"+ad+"',HastaSoyad='"+soyad+"',HastaDogumTarih='"+Dtarih+"',HastaCinsiyet='"+cinsiyet+"',HastaAdres='"+adres+"',HastaTelefon='"+tel+"',HastaKanGurubu='"+kanGrup+"',HastaKayýtTarih='"+Ktarih+"' where HastaTcNo='"+tcno+"'");

			//			ps.setString(1,tcno);
//			ps.setString(2, ad);
//			ps.setString(3,soyad);
//			ps.setString(4, tarih);
//			ps.setString(5, cinsiyet);
//			ps.setString(6, adres);
//			ps.setString(7, tel);
//			ps.setString(8, kanGrup);
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