package com.kutuphane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

public class AnaEkran extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkran frame = new AnaEkran();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AnaEkran() {
		setTitle("ANA EKRAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JFrame doktorPenceresi = new DoktorIslemleri();
		JButton btnDoktorIslemleri = new JButton("Doktor Islemleri");
		btnDoktorIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				doktorPenceresi.setVisible(true);
			}
		});
		doktorPenceresi.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				doktorPenceresi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnDoktorIslemleri.setBounds(10, 49, 140, 60);
		btnDoktorIslemleri.setBackground(Color.GREEN);
		contentPane.add(btnDoktorIslemleri);
		contentPane.setBackground(Color.yellow);
		final MuayeneEkraný muayeneekrani = new MuayeneEkraný();
		JButton btnNewButton = new JButton("Muayene");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				muayeneekrani.setVisible(true);
			}
		});
		muayeneekrani.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				muayeneekrani.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(160, 49, 129, 60);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(Color.GREEN);
		final JFrame hastapenceresi = new Hastaislemleri();
		JButton btnHastaIslemleri = new JButton("Hasta Islemleri");
		btnHastaIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hastapenceresi.setVisible(true);
			}
		});
		hastapenceresi.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				hastapenceresi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnHastaIslemleri.setBounds(299, 49, 140, 60);
		contentPane.add(btnHastaIslemleri);
		btnHastaIslemleri.setBackground(Color.GREEN);
		final JFrame randevu = new RandevuEkrani();
		JButton btnRandevuVer = new JButton("Randevu Ver");
		btnRandevuVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				randevu.setVisible(true);
			}
		});
		randevu.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				randevu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		btnRandevuVer.setBounds(449, 49, 129, 60);
		contentPane.add(btnRandevuVer);
		btnRandevuVer.setBackground(Color.GREEN);
		JButton btnCks = new JButton("C\u0131k\u0131s");
		btnCks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int secim = JOptionPane.showConfirmDialog(null, "Cýkmak istediginizden emin misiniz ?","Cikis",JOptionPane.YES_NO_OPTION);
				
				if(secim == JOptionPane.YES_OPTION){
					System.exit(0);
				}
				else{
					return;
				}
			}
		});
		btnCks.setBounds(480, 269, 98, 60);
		contentPane.add(btnCks);
		btnCks.setBackground(Color.GREEN);
		JCalendar calendar = new JCalendar(new Date());
		calendar.setBounds(10, 176, 191, 153);
		contentPane.add(calendar);
	}
}
