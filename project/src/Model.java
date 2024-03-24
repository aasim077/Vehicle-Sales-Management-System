//package sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class Model extends JFrame {
    
	private JTextField tfmdl;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Model frame = new Model();
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
	public Model() {
		setTitle("MODEL");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][]"));
		JLabel mdlnm= new JLabel("MODEL NAME:");
		mdlnm.setBackground(new Color(95, 158, 160));
		mdlnm.setForeground(new Color(240, 248, 255));
		mdlnm.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		contentPane.add(mdlnm, "cell 0 1");
		tfmdl = new JTextField();
		contentPane.add(tfmdl, "cell 2 1,growx");
		tfmdl.setColumns(10);
		JButton btnsave = new JButton("save");
		btnsave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		save(e);
		}
		});
		contentPane.add(btnsave, "cell 1 3");
		try{
		//Load Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException ex)
		{
		JOptionPane.showMessageDialog(this, "Driver not loaded");
		}
		}
		void save(ActionEvent e)
		{
		try { String en = tfmdl.getText();
		String sql = "select * from model where modelname=?";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample", "root", "969646");
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, en);
		ResultSet rs = st.executeQuery();
		//int uc = st.executeUpdate( );
		if(rs.next()) {
		JOptionPane.showMessageDialog(this,"model already exists.");
		
		}else {
		
		sql="insert into model (modelname) value(?)";
		st=con.prepareStatement(sql);
		//st.setInt(1,pk);
		st.setString(1, en);
		st.executeUpdate();
		tfmdl.setText("");

		JOptionPane.showMessageDialog(this,"model saved");
		}
		con.close();
		}catch(SQLException ex) {
		JOptionPane.showMessageDialog(this,"Database Error: "+ex.getMessage());
		}
		}
}