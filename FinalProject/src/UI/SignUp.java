package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.User;
import RequestResponseManager.SignUpRequestResponseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField IPTextField;
	private JTextField usernameTextField;
	private JTextField PassTextField;
	private JTextField nameTextField;
	private JTextField familyTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle("sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblIp.setBounds(154, 74, 115, 33);
		contentPane.add(lblIp);
		
		IPTextField = new JTextField();
		IPTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		IPTextField.setBounds(336, 70, 236, 39);
		contentPane.add(IPTextField);
		IPTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblUsername.setBounds(154, 147, 145, 33);
		contentPane.add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		usernameTextField.setBounds(336, 143, 236, 39);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblPassword.setBounds(154, 224, 133, 33);
		contentPane.add(lblPassword);
		
		PassTextField = new JTextField();
		PassTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		PassTextField.setBounds(336, 220, 236, 39);
		contentPane.add(PassTextField);
		PassTextField.setColumns(10);
		
		JLabel lblName = new JLabel("name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblName.setBounds(154, 301, 115, 33);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		nameTextField.setBounds(336, 297, 236, 39);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblFamily = new JLabel("family:");
		lblFamily.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblFamily.setBounds(154, 384, 115, 33);
		contentPane.add(lblFamily);
		
		familyTextField = new JTextField();
		familyTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		familyTextField.setBounds(336, 380, 236, 39);
		contentPane.add(familyTextField);
		familyTextField.setColumns(10);
		
		JButton btnSignUp = new JButton("sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpRequestResponseManager srrm=new SignUpRequestResponseManager();
				User user=new User(usernameTextField.getText(), PassTextField.getText(), nameTextField.getText(), familyTextField.getText() , "user", 3);
				boolean status=srrm.SignUp( IPTextField.getText(), user);
				if(status){
					JOptionPane.showMessageDialog(contentPane, "signed up successfully");
					Login.getLogin().setVisible(true);
					dispose();
					
				}else{
					//duplicate username 
					JOptionPane.showMessageDialog(contentPane, "duplicate username, choose another username");
					
				}
				
			}
		});
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnSignUp.setBounds(401, 499, 171, 41);
		contentPane.add(btnSignUp);
	}
}
