

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField tfid;
	private JPasswordField tfpw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	   void saving(ActionEvent e)
		{
			try{
				String id=tfid.getText(); 
				String psw=tfpw.getText();
				if(!(((id.equals("ABC") && psw.equals("xyz"))) ||((id.equals("DEF") && psw.equals("aaa")))))
				{
					JOptionPane.showMessageDialog(this,"ACCESS DENIED:INCORRECT ID or INCORRECT PASSWORD");


				}
				else
				{
					salescr ob=new salescr();
					ob.setVisible(true);
					ob.setPreferredSize(getPreferredSize());
				}
			}
             
			catch(Exception e1){

				JOptionPane.showMessageDialog(this, "error:"+e1.getMessage());
				}
			
		}
	   
	   		

	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][grow]", "[][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("  FORD");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 23));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblNewLabel, "cell 3 0");
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1, "cell 2 2");
		
		tfid = new JTextField();
		tfid.setBackground(new Color(245, 245, 245));
		tfid.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(tfid, "cell 4 2,growx");
		tfid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD:");
		lblNewLabel_2.setForeground(new Color(240, 248, 255));
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2, "cell 2 3,alignx trailing");
		
		tfpw = new JPasswordField();
		contentPane.add(tfpw, "cell 4 3,growx");
		
			JButton btnNewButton = new JButton("LOGIN");
			btnNewButton.setBackground(new Color(135, 206, 235));
			btnNewButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				    saving(e);
					
				}
			});
			btnNewButton.setFont(new Font("Bookman Old Style", Font.ITALIC, 12));
			contentPane.add(btnNewButton, "cell 3 5");
	}
	}