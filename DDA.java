package dropDownAdd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DDA extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DDA frame = new DDA();
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
	public DDA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cityname = comboBox.getSelectedItem().toString();
				String ConnectionUrl = "jdbc:sqlserver://INBOOK_X1_SLIM;databaseName =drdAdd;integratedSecurity=true;encrypt=false";
				String user = "INBOOK_X1_SLIM\\Akash";
				String pwd = null;
				try {
					Connection con = DriverManager.getConnection(ConnectionUrl,user,pwd);
				    PreparedStatement st = con.prepareStatement("select * from cityMaster where name ='" + cityname +"'");
					ResultSet rs = st.executeQuery();
					int Scode = 0;
					int Sstatecode=0;
					int Scountrycode=0;
					String Sname = null;
					while(rs.next()) {
						
						Scode = rs.getInt("code");
						Sname = rs.getString("name");
						Sstatecode = rs.getInt("stateCode");
						Scountrycode= rs.getInt("countryCode");
					}
					
					PreparedStatement st1 = con.prepareStatement("select * from stateMaster where code = "+Sstatecode);
					ResultSet rs1 = st1.executeQuery();
					int SScode =0;
					String SSname=null;
					while(rs1.next()) {
						SScode = rs1.getInt("code");
						SSname = rs1.getString("name");
								
					}
					
					PreparedStatement st2 = con.prepareStatement("select * from countryMaster where code = "+Scountrycode);
					ResultSet rs2 = st2.executeQuery();
					int SCcode =0;
					String SCname=null;
					while(rs2.next()) {
						SCcode = rs2.getInt("code");
						SCname = rs2.getString("name");
								
					}
					
//					System.out.println(Scode+" "+Sname);
					textField.setText(SSname);
					textField_1.setText(SCname);
				}catch(SQLException e1 ) {
					System.out.println("sorry brother ! please check the problem and come back");
					e1.printStackTrace();
				}
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"select one", "Kanpur", "Agra", "Surat", "Jhansi"}));
		comboBox.setBounds(320, 55, 232, 37);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setEditable(false);
		textField.setBounds(320, 172, 232, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(320, 286, 232, 37);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("City");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(94, 51, 43, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblState.setBounds(94, 165, 58, 44);
		contentPane.add(lblState);
		
		JLabel lblNewLabel_1_1 = new JLabel("Country");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(94, 286, 88, 44);
		contentPane.add(lblNewLabel_1_1);
	}
}
