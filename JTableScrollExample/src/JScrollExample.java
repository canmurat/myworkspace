import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JButton;


public class JScrollExample extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	String[] basliklar = {"baslik1","baslik2","baslýk3333333333333333333333" +
			"3","baslýk4444444444444444444444444444","baslýk5555555555555555555555","baslýk66666666666666666666666"};
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JScrollExample frame = new JScrollExample();
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
	public JScrollExample() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 416);
		
		table = new JTable();
		getContentPane().add(table);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(basliklar);
		model.addRow(new Object[] {"ahmet","mehmet","addddddddddddd","ffffffffffffffff","ddddddddddddddddd","nnnnnnnnnnnnnnnnnnnn"});
		getContentPane().setLayout(new BorderLayout(0, 0));
		table.setModel(model);
		
		JScrollPane pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(pane);
	}

}
