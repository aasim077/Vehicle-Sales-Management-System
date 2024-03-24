//package sales;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Sales_Report extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbmdl;
	private JComboBox cbdt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales_Report frame = new Sales_Report();
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
	public Sales_Report() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1px][424px]", "[][1px][42px][209px]"));
		
		JLabel lblNewLabel = new JLabel("SALES REPORT");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(240, 255, 240));
		contentPane.add(lblNewLabel, "cell 1 0,alignx center");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		contentPane.add(panel, "cell 1 1,growx,aligny top");
		
		JLabel lblstream = new JLabel("DATE");
		lblstream.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblstream.setForeground(new Color(240, 248, 255));
		panel.add(lblstream);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		cbdt = new JComboBox();
		cbdt.setBackground(new Color(240, 248, 255));
		panel_2.add(cbdt);
		
		JLabel lblbranch = new JLabel("MODEL");
		lblbranch.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblbranch.setForeground(new Color(240, 248, 255));
		panel.add(lblbranch);
		
		cbmdl = new JComboBox();
		cbmdl.setBackground(new Color(240, 248, 255));
		panel.add(cbmdl);
		
		JButton btnsearch = new JButton("search");
		btnsearch.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnsearch.setBackground(new Color(240, 255, 240));
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(e);
			}
		});
		panel.add(btnsearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, "cell 1 3,grow");
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(table);
		table.setBackground(new Color(240, 248, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				
				"ID", "SALES DATE", "VEHICLE", "CAR NAME", "ADDRESS", "CONTACT", "CITY", "STATE"
			}
		));
		try{
			//Load Driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "969646");

				 String sql = "select * from model order by modelname";
				  				   PreparedStatement st = con.prepareStatement(sql);
				   ResultSet rs= st.executeQuery();
				   while(rs.next())
				   {
					   String na=rs.getString(2);
					   cbmdl.addItem(na);
				   }
				   rs.close();
				 
				    sql = "select id,sales_date from salesp order by sales_date";
  				    st = con.prepareStatement(sql);
                           rs= st.executeQuery();
                        while(rs.next())
                                 {
	              String na=rs.getString(2);
	                cbdt.addItem(na);
                         }
                       rs.close();
 
				   con.close();
		}
		catch(SQLException ex) {
			   JOptionPane.showMessageDialog(this,"Database Error: "+ex.getMessage());
			   }
		catch(ClassNotFoundException ex)
		{
			JOptionPane.showMessageDialog(this, "Driver not loaded");
		}
	}

	 private void show(ActionEvent e)
     {
    	 try {
 			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "969646");
 			
 			
 			String str= (String)cbmdl.getSelectedItem();
 			
 			 String sql = "select  id from model where modelname=?";
 			   PreparedStatement st = con.prepareStatement(sql);
 			   st.setString(1,str);
                 ResultSet rs= st.executeQuery();
                 rs.next();
                 int sid= rs.getInt(1);
                 
                 String x= (String)cbdt.getSelectedItem();
                 sql= "select id from salesp where sales_date=?";
                 st=con.prepareStatement(sql);
                 st.setString(1, x);
                 rs=st.executeQuery();
                 rs.next();
                 int sx=rs.getInt(1);
                  
            //     "ID", "SALES DATE", "VEHICLE", "CAR NAME", "ADDRESS", "CONTACT", "CITY", "STATE"     
                 
 		 sql = "select s.id,s.sales_date,m.modelname,s.cname,s.address,s.cont,s.city,s.state from salesp s, model m  where s.vehicle_id=m.id AND s.vehicle_id=? AND s.id=?  ;\r\n"
 					+ "";
 			 st = con.prepareStatement(sql);
 			 st.setInt(1, sid);
 			 st.setInt(2, sx);
 			
               rs=st.executeQuery();
 			DefaultTableModel dt= new DefaultTableModel();
 			dt.addColumn("ID.");
 			dt.addColumn("SALES DATE.");
 			dt.addColumn("VEHICLE");
 			dt.addColumn("CUSTOMER NAME");
 			dt.addColumn("ADDRESS");
 			dt.addColumn("CONTACT");
 			dt.addColumn("CITY");
 			dt.addColumn("STATE");
 			if(rs.next())
 			{
 			do
 			{String data[]=new String[8];
 			data[0]=rs.getString(rs.getInt(1));
 			data[1]=rs.getString(2);
 			data[2]=rs.getString(3);
 			data[3]=rs.getString(4);
 			data[4]=rs.getString(5);
 			data[5]=rs.getString(6);
 			data[6]=rs.getString(7);
 			data[7]=rs.getString(8);
 			
 			dt.addRow(data);
 			}
 				
 			while(rs.next());
 			table.setModel(dt);
 		}
 			else
 			{
 				JOptionPane.showMessageDialog(this, "no record");
 			}
 			con.close();
 		}
 		catch(SQLException ex) {
 			   JOptionPane.showMessageDialog(this,"Database Error: "+ex.getMessage());
 			   }

     }
}
