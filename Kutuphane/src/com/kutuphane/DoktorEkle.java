package com.kutuphane;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class DoktorEkle extends JFrame {

	private JPanel contentPane;
	private JTextField textAd;
	private JTextField textSoyad;
	private JTextField textCinsiyet;
	private JTextField textAdres;
	private JTextField textTel;
	private JTextField textUzmanlik;
	private JTextField textDoktorTcNo;
	private JDateChooser textDTarih;

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

	public DoktorEkle() {
		
		setTitle("Doktor Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.YELLOW);
		JLabel lblDoktorAd = new JLabel("Doktor Ad\u0131 :");
		lblDoktorAd.setBounds(10, 58, 89, 14);
		contentPane.add(lblDoktorAd);
		
		JLabel lblDoktorSoyad = new JLabel("Doktor Soyad\u0131 :");
		lblDoktorSoyad.setBounds(10, 89, 89, 14);
		contentPane.add(lblDoktorSoyad);
		
		JLabel lblDoktor = new JLabel("Dogum Tarihi :");
		lblDoktor.setBounds(8, 120, 89, 14);
		contentPane.add(lblDoktor);
		
		JLabel lblNewLabel = new JLabel("Cinsiyet :");
		lblNewLabel.setBounds(10, 149, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Adres :");
		lblNewLabel_1.setBounds(10, 243, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTel = new JLabel("Tel : ");
		lblTel.setBounds(10, 180, 68, 14);
		contentPane.add(lblTel);
		
		JLabel lblUzmanlk = new JLabel("Uzmanl\u0131k : ");
		lblUzmanlk.setBounds(10, 210, 89, 14);
		contentPane.add(lblUzmanlk);
		
		textAd = new JTextField();
		textAd.setBounds(116, 55, 132, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textSoyad = new JTextField();
		textSoyad.setBounds(116, 86, 132, 20);
		contentPane.add(textSoyad);
		textSoyad.setColumns(10);
		
		textCinsiyet = new JTextField();
		textCinsiyet.setBounds(116, 146, 132, 20);
		contentPane.add(textCinsiyet);
		textCinsiyet.setColumns(10);
		
		textAdres = new JTextField();
		textAdres.setBounds(116, 242, 132, 59);
		contentPane.add(textAdres);
		textAdres.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(116, 177, 132, 20);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		textUzmanlik = new JTextField();
		textUzmanlik.setBounds(116, 208, 132, 20);
		contentPane.add(textUzmanlik);
		textUzmanlik.setColumns(10);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					yeniDoktorEkle();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEkle.setBounds(180, 316, 68, 23);
		contentPane.add(btnEkle);
		btnEkle.setBackground(Color.GREEN);
		JLabel lblDoktortcno = new JLabel("DoktorTcNo :");
		lblDoktortcno.setBounds(10, 27, 89, 14);
		contentPane.add(lblDoktortcno);
		
		textDoktorTcNo = new JTextField();
		textDoktorTcNo.setBounds(116, 24, 132, 20);
		contentPane.add(textDoktorTcNo);
		textDoktorTcNo.setColumns(10);
		
		textDTarih = new JDateChooser();
		textDTarih.setDateFormatString("yyyy-MM-dd");
		textDTarih.setBounds(116, 117, 132, 20);
		contentPane.add(textDTarih);
	}
	public void yeniDoktorEkle() throws ClassNotFoundException
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
		String doktorNo = textDoktorTcNo.getText();
		String ad = textAd.getText();
		String soyad = textSoyad.getText();
		String tarih = fmt.format(textDTarih.getDate());
		String cinsiyet = textCinsiyet.getText();
		String adres = textAdres.getText();
		String tel = textTel.getText();
		String uzmanlik = textUzmanlik.getText();
		try {

			PreparedStatement ps = conn
					.prepareStatement("insert into doktorlar (DoktorNo,Ad,Soyad,DogumTarihi,Cinsiyet,Adres,Tel,Uzmanlýk) values (?,?,?,?,?,?,?,?)");
			ps.setString(1,doktorNo);
			ps.setString(2, ad);
			ps.setString(3,soyad);
			ps.setString(4, tarih);
			ps.setString(5, cinsiyet);
			ps.setString(6, adres);
			ps.setString(7, tel);
			ps.setString(8, uzmanlik);			
			ps.executeUpdate();
				
			
			textDoktorTcNo.setText("");
			textAd.setText("");
			textSoyad.setText("");
			textDTarih.setDate(null);
			textCinsiyet.setText("");
			textAdres.setText("");
			textTel.setText("");
			textUzmanlik.setText("");
			
			JOptionPane.showMessageDialog(null, "Bilgileri girilen doktor baþarý ile eklendi!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
