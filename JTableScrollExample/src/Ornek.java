import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Ornek extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	String[] basliklar = {"baslik1","baslik2","baslýk3333333333333333333333" +
			"3","baslýk4444444444444444444444444444","baslýk5555555555555555555555","baslýk66666666666666666666666"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ornek frame = new Ornek();
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
	public Ornek() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 346);
		getContentPane().setLayout(null);

		table = new JTable();
		table.setBounds(1, 1, 440, 0);
		getContentPane().add(table);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		model.addRow(new Object[] {"ahmet","mehmet","addddddddddddd","ffffffffffffffff","ddddddddddddddddd","nnnnnnnnnnnnnnnnnnnn"});
		table.setModel(model);
		
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 284, 162);
	//	scroll.setHorizontalScrollBar(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		 scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		 scroll.add(table, null);
		 getContentPane().add(scroll);
		 
		 JButton btnLaylaylay = new JButton("Laylaylay");
		 btnLaylaylay.setBounds(10, 235, 89, 23);
		 getContentPane().add(btnLaylaylay);

//		JScrollPane pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//			    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		getContentPane().add(pane);
		

	}
}
