package restaurant_management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class EmployeeLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	Connection con=null;
	public EmployeeLogin() {
		con=Connector.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 539);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeLoginPage = new JLabel("Employee Login Page");
		lblEmployeeLoginPage.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblEmployeeLoginPage.setBounds(265, 24, 367, 57);
		contentPane.add(lblEmployeeLoginPage);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(98, 183, 146, 57);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_2.setBounds(98, 292, 146, 64);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String user = textField.getText();
					String pwd= String.valueOf(passwordField.getPassword());
					PreparedStatement pst =(PreparedStatement) con.prepareStatement("select * from employee where employeename=? and pass2=?");
					pst.setString(1,user);
					pst.setString(2,pwd);
					ResultSet r=pst.executeQuery();
					if(r.next()) {
						EmployeeWindow j=new EmployeeWindow();
						j.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"incorrect id or password");
					}
				}
				  catch(Exception e1) {
					  e1.printStackTrace();
				  }
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton.setBounds(98, 430, 146, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton_1.setBounds(478, 430, 130, 46);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(265, 183, 185, 44);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(265, 306, 208, 34);
		contentPane.add(passwordField);
	}
}
