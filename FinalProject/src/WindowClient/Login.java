package WindowClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblIp.setBounds(108, 74, 115, 33);
		contentPane.add(lblIp);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField.setBounds(272, 70, 236, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblUsername.setBounds(108, 152, 149, 33);
		contentPane.add(lblUsername);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField_1.setBounds(272, 149, 236, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblPassword.setBounds(108, 234, 135, 33);
		contentPane.add(lblPassword);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField_2.setBounds(272, 231, 236, 39);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSignIn = new JButton("sign in");
		btnSignIn.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnSignIn.setBounds(337, 363, 171, 41);
		contentPane.add(btnSignIn);
		
		JButton btnSignUp = new JButton("sign up");
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnSignUp.setBounds(123, 363, 171, 41);
		contentPane.add(btnSignUp);
		
		JButton btnEnterAsGuest = new JButton("Enter as guest");
		btnEnterAsGuest.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnEnterAsGuest.setBounds(453, 500, 207, 41);
		contentPane.add(btnEnterAsGuest);
	}
}
