package restaurant_management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EmployeeOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeOrder frame = new EmployeeOrder();
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
	public EmployeeOrder() {
		con=Connector.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Name Of Food");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_4.setBounds(89, 34, 161, 55);
		contentPane.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(264, 34, 177, 55);
		contentPane.add(textField_4);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst=con.prepareStatement("select food_id,food_name,price from food where food_name=?");
					pst.setString(1,textField_4.getText());
					ResultSet r=pst.executeQuery();
					if(r.next()==false) {
						JOptionPane.showMessageDialog(null,"Sorry Record not Found!!");
						
					}
					else {
						textField.setText(r.getString("food_name"));
						textField_1.setText(r.getString("food_id"));
						textField_2.setText(r.getString("price"));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_2.setBounds(506, 34, 122, 55);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(30, 159, 85, 27);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(109, 159, 122, 27);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Food Id");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(30, 211, 85, 35);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 211, 96, 31);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_2.setBounds(30, 275, 104, 35);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(144, 275, 96, 31);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(30, 334, 85, 41);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(123, 334, 96, 32);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("Add Food");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q="insert into EmployeeOrder (table_id,order_name,order_id,price,quantity) values(?,?,?,?,?)";
					PreparedStatement pst=(PreparedStatement) con.prepareStatement(q);
					pst.setString(2, textField.getText());
					pst.setString(3, textField_1.getText());
					pst.setString(4, textField_2.getText());
					pst.setString(5, textField_3.getText());
					pst.setString(1, (String)comboBox.getSelectedItem());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sucessfully Added Food");
					
					
					
				} 
				catch(Exception e1) {
					e1.printStackTrace();
				}
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(33, 393, 145, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton_3.setBounds(339, 471, 122, 34);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_5 = new JLabel("Table Id");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_5.setBounds(400, 153, 145, 38);
		contentPane.add(lblNewLabel_5);
		
	    comboBox = new JComboBox();
	    comboBox.addItem("A1");
	    comboBox.addItem("A2");
	    comboBox.addItem("A3");
		comboBox.setBounds(532, 159, 112, 28);
		contentPane.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Delete Food");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q="delete from employeeOrder where table_id=?";
					PreparedStatement pst=(PreparedStatement) con.prepareStatement(q);
					pst.setString(1,(String)comboBox.getSelectedItem() );
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sucessfully Deleted Food");
					
					
					
				} 
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBounds(253, 393, 155, 35);
		contentPane.add(btnNewButton_1);
	}
}
