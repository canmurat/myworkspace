package gorselpaket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ORnek extends JFrame {

	private JPanel contentPane;
	static Connection conn;
	Boolean uye= false;
	String kullaniciadi;
	String sifre;
	
	/**
	 * Launch the application.
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
				
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ORnek frame = new ORnek();
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
	public ORnek() throws ClassNotFoundException {
		setTitle("HASTANE OTOMASYON");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKullaniciAdi = new JLabel("Kullanici Adi :");
		lblKullaniciAdi.setBounds(67, 67, 70, 14);
		contentPane.add(lblKullaniciAdi);
		
		JLabel lblSifre = new JLabel("Sifre :");
		lblSifre.setBounds(67, 92, 58, 14);
		contentPane.add(lblSifre);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(147, 67, 132, 16);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(147, 92, 132, 16);
		contentPane.add(textArea_1);
		
		JButton btnGirisYap = new JButton("Giris Yap");
		
		btnGirisYap.setBounds(157, 119, 89, 23);
		contentPane.add(btnGirisYap);
	
		
		kullaniciadi = textArea.getText();
		sifre = textArea_1.getText();
		
		btnGirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(KullaniciGiris.KullaniciSorgu(kullaniciadi, sifre))
					{
						uye = true;
						new AnaPencere().setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No Record Found","Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
