
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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class search extends JFrame {

	private JPanel contentPane;
	private JComboBox cbdt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search frame = new search();
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
	public search() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1px][424px]", "[][1px][][42px][209px]"));
		
		JLabel lblNewLabel = new JLabel("VEHICLE LIST");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(240, 255, 240));
		contentPane.add(lblNewLabel, "cell 1 0,alignx center");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		contentPane.add(panel, "cell 1 1,growx,aligny top");
		
		JLabel lblstream = new JLabel("MODEL    ");
		lblstream.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblstream.setForeground(new Color(240, 248, 255));
		panel.add(lblstream);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		cbdt = new JComboBox();
		cbdt.setBackground(new Color(240, 248, 255));
		panel_2.add(cbdt);
		
		
		
		JButton btnsearch = new JButton("search");
		panel.add(btnsearch);
		btnsearch.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnsearch.setBackground(new Color(240, 255, 240));
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, "cell 1 4,grow");
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "MODEL", "COLOR", "CHASIS_NO.", "STATE"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(81);
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "969646");

				 String sql = "select * from model order by modelname";
				  				   PreparedStatement st = con.prepareStatement(sql);
				   ResultSet rs= st.executeQuery();
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
			
			
			String str= (String)cbdt.getSelectedItem();
			
			 String sql = "select  id from model where modelname=?";
			   PreparedStatement st = con.prepareStatement(sql);
			   st.setString(1,str);
                ResultSet rs= st.executeQuery();
                rs.next();
                int sid= rs.getInt(1);
                
               
                 
                
                
		 sql = "select v.id,m.modelname,v.color,v.chasis_no,v.state from vehicle v, model m  where v.model=m.id  AND v.model=?;\r\n"
					+ "";
			 st = con.prepareStatement(sql);
			 st.setInt(1, sid);
			
              rs=st.executeQuery();
			DefaultTableModel dt= new DefaultTableModel();
			dt.addColumn("ID.");
			dt.addColumn("MODEL.");
			dt.addColumn("COLOR");
			dt.addColumn("CHASIS_NO");
			dt.addColumn("STATE");
			
			if(rs.next())
			{
			do
			{String data[]=new String[5];
			int id= rs.getInt(1);
			System.out.println("id="+id);
			data[0]=String.valueOf(id);
			data[1]=rs.getString(2);
			data[2]=rs.getString(3);
			data[3]=rs.getString(4);
			data[4]=rs.getString(5);
			
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
