//package sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class salescr extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					salescr frame = new salescr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	void open(ActionEvent e){//for vehicles page
		try {
			vehiclea ab = new vehiclea();
			ab.setVisible(true);
			ab.setPreferredSize(getPreferredSize());
			
		} catch (Exception   e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,e2.getMessage());
		}
	}
	void open2(ActionEvent e){//for vehicles search page
		try {
			search ab = new search();
			ab.setVisible(true);
			ab.setPreferredSize(getPreferredSize());
			
		} catch (Exception   e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,e2.getMessage());
		}
	}
	void open3(ActionEvent e){//for adding sales  page
		try {
			new_sale ab = new new_sale();
			ab.setVisible(true);
			ab.setBounds(120, 120, 490, 350);
			ab.setPreferredSize(getPreferredSize());
			
		} catch (Exception   e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,e2.getMessage());
		}
	}
	void open4(ActionEvent e){//for sales report page
		try {
			Sales_Report ab = new Sales_Report();
			ab.setVisible(true);
			ab.setPreferredSize(getPreferredSize());
			
		} catch (Exception   e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,e2.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public salescr() {
		getContentPane().setBackground(new Color(0, 0, 51));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[][][][][][grow]", "[][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Different Vehicles for different journeys");
		lblNewLabel.setFont(new Font("MS PGothic", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		getContentPane().add(lblNewLabel, "cell 4 2,alignx right");
		
		JLabel lblNewLabel_1 = new JLabel("     Explore the Ford Range for all your journeys.");
		lblNewLabel_1.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		getContentPane().add(lblNewLabel_1, "cell 4 3");
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME");
		lblNewLabel_2.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(240, 230, 140));
		getContentPane().add(lblNewLabel_2, "cell 4 4,alignx center,aligny center");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("VEHICLE");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add vehicle");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open(e);
			}
		});
		mntmNewMenuItem.setBackground(new Color(175, 238, 238));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Search Vehicle");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open2(e);
			}
		});
		mntmNewMenuItem_1.setBackground(new Color(175, 238, 238));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("SALES");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New Sales");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open3(e);
			}
		});
		mntmNewMenuItem_2.setBackground(new Color(175, 238, 238));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sales Report");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open4(e);
			}
		});
		mntmNewMenuItem_3.setBackground(new Color(175, 238, 238));
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}

}
