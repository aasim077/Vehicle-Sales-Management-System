//package sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class vehiclea extends JFrame {

	private JPanel contentPane;
	private JTextField tfid;
	private JTextField tfcolor;
	private JTextField tfcno;
	private final JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vehiclea frame = new vehiclea();
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
	public vehiclea() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][]", "[][][][][][][][][][][][][]"));
	
    	JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lblNewLabel, "cell 1 0");
		
		tfid = new JTextField();
		contentPane.add(tfid, "cell 3 0,alignx left");
		tfid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("MODEL NO:");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		contentPane.add(lblNewLabel_1, "cell 1 2");
		contentPane.add(comboBox, "cell 3 2");
		
		JLabel lblNewLabel_2 = new JLabel("COlOR:");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(240, 248, 255));
		contentPane.add(lblNewLabel_2, "cell 1 4");
		
		tfcolor = new JTextField();
		contentPane.add(tfcolor, "cell 3 4,alignx left");
		tfcolor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CHASIS NO:");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(new Color(240, 248, 255));
		contentPane.add(lblNewLabel_3, "cell 1 6");
		
		tfcno = new JTextField();
		contentPane.add(tfcno, "cell 3 6,alignx left");
		tfcno.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_4, "cell 1 8");
		
		JButton btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saving(e);
			}
		});
		contentPane.add(btnsave, "cell 2 10");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "969646");

			String sql = "select * from model order by modelname"; 
			PreparedStatement st = con.prepareStatement(sql);   
			ResultSet rs= st.executeQuery();       
			comboBox.removeAll();                
			while(rs.next())
			{
			String na=rs.getString(2);     
			comboBox.addItem(na);      
			}
			rs.close(); 
			
			
			
		}
		 catch(SQLException ex){
			 JOptionPane.showMessageDialog(this,"Database Error" +ex.getMessage());
			 
		 }
		catch(ClassNotFoundException ex){
		JOptionPane.showMessageDialog(this,"Database Driver Not Found");
		}
		
	}
	private void saving(ActionEvent e)
	{
	 String id = tfid.getText();// textfield is the typeof string
		String color = tfcolor.getText();
		String  cn= tfcno.getText();
		String md = (String)comboBox.getSelectedItem();   //fetching and coverting selecting item from combo box into string type  
		
	 try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample","root","969646"); 
			String sql = "select * from vehicle where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id)); 
			ResultSet rs = ps.executeQuery();     
			if(rs.next()) {
				 JOptionPane.showMessageDialog(this,"vehicle already exist");

				
			}
			else
			{    
				sql = "insert into Vehicle(id ,model,color,chasis_no) values ( ?,( select id from model where modelname=?) ,?  , ?) ";				
				PreparedStatement ps1 = con.prepareStatement(sql); 
				ps1.setInt(1,Integer.parseInt(id));  //value of question mark in the sql query
				ps1.setString(2, md);
				ps1.setString(3, color);
				ps1.setString(4, cn);
             	//ps1.setString(5, cn);
				//ps1.setString(6, eml );
				if(ps1.executeUpdate()>0){    //update the values of quertion mark '?' in the sql
					 JOptionPane.showMessageDialog(this," New Vehicle added");
				}
				else{
					 JOptionPane.showMessageDialog(this,"New Vehicle not added");

				}
	}
		con.close();	
			
	       
	 }	 
	 catch(SQLException ex){
		 JOptionPane.showMessageDialog(this,"Database Error" +ex.getMessage());
		 
	 }
		 
	}
	}


