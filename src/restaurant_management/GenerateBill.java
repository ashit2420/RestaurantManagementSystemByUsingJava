package restaurant_management;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class GenerateBill extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateBill frame = new GenerateBill();
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
	private JTextField textField;
	private JTable table;
	public GenerateBill() {
		con=Connector.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 548);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Display Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q="select * from EmployeeOrder where table_id=?";
					PreparedStatement pst=(PreparedStatement) con.prepareStatement(q);
					pst.setString(1, (String) comboBox.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++) {
						colName[i]=rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName); 
					String name,id,price,quantity,table;
					while(rs.next()) {
						table=rs.getString(1);
						name=rs.getString(2);
						id=rs.getString(3);
						price=rs.getString(4);
						quantity=rs.getString(5);
						
		
						String[] row= {table,name,id,price,quantity};
						model.addRow(row);
					}
					
					
				}
				 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton.setBounds(47, 149, 188, 56);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirm  Order");
		 
		btnNewButton_1.addActionListener(new ActionListener() {
			ResultSet rs=null;
			public void actionPerformed(ActionEvent e) {
				try {
					String q="select sum(price*quantity) as total from EmployeeOrder where table_id=?";
					PreparedStatement pst=(PreparedStatement) con.prepareStatement(q);
					pst.setString(1, (String)comboBox.getSelectedItem());
					 rs=pst.executeQuery();
					while(rs.next()) {
						String total=rs.getString(1);
						textField.setText(total);
					}
					JOptionPane.showMessageDialog(null,"Order Confirmed");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					
						
						String q="delete from EmployeeOrder where table_id=?";
						PreparedStatement pst=(PreparedStatement) con.prepareStatement(q);
						pst.setString(1, (String)comboBox.getSelectedItem());
					    pst.executeUpdate();
					}
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();

		        for (int row = 0; row < table.getRowCount(); row++) {
		            model.removeRow(row);
		        }
		        textField.setText("");
			} 
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBounds(47, 275, 211, 47);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_2.setBounds(79, 402, 129, 47);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Total");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(454, 389, 96, 47);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(530, 389, 129, 47);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Table Id");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(37, 61, 96, 56);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(444, 61, 391, 285);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	    comboBox = new JComboBox();
		comboBox.addItem("A1");
		comboBox.addItem("A2");
		comboBox.addItem("A3");
		comboBox.setBounds(135, 73, 111, 29);
		contentPane.add(comboBox);
		
	}
}
