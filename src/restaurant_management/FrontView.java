package restaurant_management;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrontView extends JFrame {

	private JPanel Front_view;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontView frame = new FrontView();
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
	public FrontView() {
		setForeground(Color.CYAN);
		setBackground(Color.CYAN);
		setTitle("Restaurant Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 765);
		Front_view = new JPanel();
		Front_view.setBackground(Color.CYAN);
		Front_view.setForeground(Color.GREEN);
		Front_view.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.CYAN));

		setContentPane(Front_view);
		Front_view.setLayout(null);
		
		JButton admin_button = new JButton("Login For Admin");
		admin_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin j = new AdminLogin();
				j.setVisible(true);
			}
		});
		admin_button.setBackground(Color.RED);
		admin_button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		admin_button.setBounds(276, 628, 289, 74);
		Front_view.add(admin_button);
		
		JLabel label1 = new JLabel("Restaurant Management System");
		label1.setForeground(Color.RED);
		label1.setBackground(Color.RED);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		label1.setBounds(179, 24, 501, 60);
		Front_view.add(label1);
		
		JButton user_button = new JButton("Login For User");
		user_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin j=new UserLogin();
				j.setVisible(true);
			}
		});
		user_button.setBackground(Color.RED);
		user_button.setForeground(Color.BLACK);
		user_button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		user_button.setBounds(269, 172, 289, 74);
		Front_view.add(user_button);
		
		JButton new_user = new JButton("New User");
		new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup j=new Signup();
				j.setVisible(true);
			}
		});
		new_user.setFont(new Font("Times New Roman", Font.BOLD, 25));
		new_user.setBackground(Color.RED);
		new_user.setBounds(269, 349, 296, 74);
		Front_view.add(new_user);
		
		JLabel lblNewLabel = new JLabel("New User? SignUp -");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(19, 360, 240, 53);
		Front_view.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Login For Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLogin j=new EmployeeLogin();
				j.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton.setBounds(269, 497, 296, 74);
		Front_view.add(btnNewButton);
	}
}
