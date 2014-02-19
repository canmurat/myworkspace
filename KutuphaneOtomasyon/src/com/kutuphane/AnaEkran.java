package com.kutuphane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AnaEkran extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public AnaEkran() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final UyeIslemleri uyeislemleri = new UyeIslemleri();
		JButton btnUyeIslemleri = new JButton("Uye Islemleri");
		btnUyeIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uyeislemleri.setVisible(true);

			}
		});
		uyeislemleri.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				uyeislemleri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		btnUyeIslemleri.setBounds(99, 11, 146, 48);
		contentPane.add(btnUyeIslemleri);

		final KitapIslemleri kitapislemleri = new KitapIslemleri();
		JButton btnKtapIslemleri = new JButton("K\u0131tap Islemleri");
		btnKtapIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kitapislemleri.setVisible(true);
			}
		});
		kitapislemleri.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				kitapislemleri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnKtapIslemleri.setBounds(99, 70, 146, 48);
		contentPane.add(btnKtapIslemleri);

		final YazarIslemleri yazarislemleri = new YazarIslemleri();
		JButton btnYazarIslemleri = new JButton("Yazar Islemleri");
		btnYazarIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				yazarislemleri.setVisible(true);
				
			}
		});
		yazarislemleri.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				yazarislemleri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnYazarIslemleri.setBounds(99, 129, 146, 48);
		contentPane.add(btnYazarIslemleri);
		
		

		final OduncIslemleri oduncIslemleri = new OduncIslemleri();
		oduncIslemleri.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				oduncIslemleri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		JButton btnOdunc = new JButton("Odunc \u0130slemleri");
		btnOdunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oduncIslemleri.setVisible(true);
				
			}
		});
		btnOdunc.setBounds(99, 194, 146, 48);
		contentPane.add(btnOdunc);
	}
}
