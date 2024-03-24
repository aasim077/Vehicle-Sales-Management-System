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
import javax.swing.JScrollBar;

public class new_sale extends JFrame {

	private JPanel contentPane;
	private JTextField tfid;
	private JTextField tfdt;
	private JTextField tfadd;
	private final JComboBox comboBox = new JComboBox();
	private final JTextField tfcnt = new JTextField();
	/**
	 * @wbp.nonvisual location=-9,-21
	 */
	private final JLabel label = new JLabel("New label");
	/**
	 * @wbp.nonvisual location=561,89
	 */
	private final JLabel label_1 = new JLabel("New label");
	private JTextField tfcity;
	private JTextField tfstate;
	private JTextField tfnm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_sale frame = new new_sale();
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
	public new_sale() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(120, 120, 490, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][grow]", "[][][][][grow][][][][][][][][][][][][][][][][][]"));
	
    	JLabel lbid = new JLabel("ID:");
		lbid.setForeground(new Color(240, 248, 255));
		lbid.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lbid, "cell 1 3");
		
		tfid = new JTextField();
		contentPane.add(tfid, "cell 3 3,alignx left");
		tfid.setColumns(10);
		
		JLabel lbdt = new JLabel("SALES DATE :");
		lbdt.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lbdt.setForeground(new Color(240, 248, 255));
		contentPane.add(lbdt, "cell 1 4");
		
		tfdt = new JTextField();
		contentPane.add(tfdt, "cell 3 4,alignx left");
		tfdt.setColumns(10);
		
		JLabel lbvhcl = new JLabel("VEHICLE :");
		lbvhcl.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lbvhcl.setForeground(new Color(240, 248, 255));
		contentPane.add(lbvhcl, "cell 1 5");
		contentPane.add(comboBox, "cell 3 5");
		comboBox.removeAll();
		
		JLabel lbnm = new JLabel("CUSTOMER NAME");
		lbnm.setForeground(new Color(240, 248, 255));
		lbnm.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lbnm, "cell 1 7,alignx left,aligny center");
		
		tfnm = new JTextField();
		contentPane.add(tfnm, "cell 3 7,growx");
		tfnm.setColumns(10);
		
		JLabel lbadd = new JLabel("ADDRESS:");
		lbadd.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lbadd.setForeground(new Color(240, 248, 255));
		contentPane.add(lbadd, "cell 1 9");
		
		tfadd = new JTextField();
		contentPane.add(tfadd, "cell 3 9,alignx left");
		tfadd.setColumns(10);
		
		JLabel lbcnt = new JLabel("CONTACT");
		lbcnt.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lbcnt.setForeground(new Color(240, 248, 255));
		contentPane.add(lbcnt, "cell 1 11");
		tfcnt.setText("");
		contentPane.add(tfcnt, "cell 3 11");
		tfcnt.setColumns(10);
		
		JLabel lbcity = new JLabel("CITY");
		lbcity.setForeground(new Color(240, 248, 255));
		lbcity.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lbcity, "cell 1 13");
		
		tfcity = new JTextField();
		contentPane.add(tfcity, "cell 3 13,growx");
		tfcity.setColumns(10);
		
		JLabel lbstate = new JLabel("STATE");
		lbstate.setForeground(new Color(240, 248, 255));
		lbstate.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lbstate, "cell 1 15,alignx left,aligny bottom");
		
		tfstate = new JTextField();
		contentPane.add(tfstate, "cell 3 15,growx");
		tfstate.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_4, "cell 1 17");
		
		JButton btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saving(e);
			}
		});
		contentPane.add(btnsave, "cell 2 19");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "969646");

			String sql = "select * from model order by modelname"; //selecting streamname table order by order
			PreparedStatement st = con.prepareStatement(sql);   // when ? comes then we input some values
			ResultSet rs= st.executeQuery();        //fetch d data as row and columns 
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
		String sldate = tfdt.getText();
		String  vh= (String)comboBox.getSelectedItem();//String eml = tfemail.getText();
		String cname = tfnm.getText();
		String add=tfadd.getText();//fetching and coverting selecting item from combo box into string type  
		String cnt=tfcnt.getText();
		String city=tfcity.getText();
		String state=tfstate.getText();
	 try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample","root","969646"); 
			String sql = "select * from salesp where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id)); //value of question mark in the sql query
			ResultSet rs = ps.executeQuery();     
			if(rs.next()) {
				 JOptionPane.showMessageDialog(this,"vehicle already exist");

				
			}
			else
			{    
				sql = "insert into salesp(id ,sales_date,vehicle_id,cname,address,cont,city,state) values ( ?,?,( select id from model where modelname=?) ,?  , ?,?,?,?)  ";				
				PreparedStatement ps1 = con.prepareStatement(sql); 
				ps1.setInt(1,Integer.parseInt(id));  //value of question mark in the sql query
				ps1.setString(2, sldate);
				ps1.setString(3, vh);
				ps1.setString(4, cname);
             	ps1.setString(5, add);
				ps1.setString(6, cnt );
				ps1.setString(7, city );
				ps1.setString(8, state );
				
				if(ps1.executeUpdate()>0){    //update the values of quertion mark '?' in the sql
					 JOptionPane.showMessageDialog(this," New Vehicle added");
					 String sql2=" update vehicle  SET state='sold' where model =?";
					 PreparedStatement ps2 = con.prepareStatement(sql2); 
					 ps2.setString(1, vh );
					 ps2.executeUpdate();
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


