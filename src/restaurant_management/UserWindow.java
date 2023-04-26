package restaurant_management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UserWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserWindow frame = new UserWindow();
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
	private JTable table;
	public UserWindow() {
		con=Connector.createConnection();
		setTitle("user window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 557);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Order Food");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFood j=new OrderFood();
				j.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton.setBounds(268, 119, 169, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Book Tabe");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton_1.setBounds(268, 228, 169, 54);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin j=new UserLogin();
				j.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton_3.setBounds(105, 436, 118, 49);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton_4.setBounds(494, 436, 130, 49);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("USER  WINDOW");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(234, 10, 331, 54);
		contentPane.add(lblNewLabel);
		
		final JToggleButton tbtn = new JToggleButton("Available  Food");
		tbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tbtn.isSelected()) {
				try {
					Statement st=con.createStatement();
					String q="select * from food";
					ResultSet rs= st.executeQuery(q);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++) {
						colName[i]=rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName); 
					String id,name,category,price;
					while(rs.next()) {
						id=rs.getString(1);
						name=rs.getString(2);
						category=rs.getString(3);
						price=rs.getString(4);
		
						String[] row= {id,name,category,price};
						model.addRow(row);
					}
					
					
				}
				 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					int i=table.getRowCount();
					DefaultTableModel dt=(DefaultTableModel) table.getModel();
					for(int j=0;j<i;j++) {
						dt.removeRow(0);
					}
					
				}
			}
		});
		tbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tbtn.setBounds(268, 328, 169, 54);
		contentPane.add(tbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(592, 36, 262, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
