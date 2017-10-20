package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.User;
import RequestResponseManager.SignInRequestResponseManager;
import WindowClient.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField IPTextField;
	private JTextField usernameTextField;
	private JTextField passTextField;
	private static Login login=new Login();

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = getLogin();
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
	
	public static Login getLogin(){
		return login;
	}
	
	private  Login() {
		setTitle("sign in");
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
		
		IPTextField = new JTextField();
		IPTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		IPTextField.setBounds(272, 70, 236, 39);
		contentPane.add(IPTextField);
		IPTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblUsername.setBounds(108, 152, 149, 33);
		contentPane.add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		usernameTextField.setBounds(272, 149, 236, 39);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblPassword.setBounds(108, 234, 135, 33);
		contentPane.add(lblPassword);
		
		passTextField = new JTextField();
		passTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		passTextField.setBounds(272, 231, 236, 39);
		contentPane.add(passTextField);
		passTextField.setColumns(10);
		
		JButton btnSignIn = new JButton("sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignInRequestResponseManager srrm=new SignInRequestResponseManager();
				System.out.println(IPTextField.getText());
				User user=new User(); user.setUsername(usernameTextField.getText()); user.setPassword(passTextField.getText());
				srrm.SignIn(IPTextField.getText(), user );
				if(UserManager.getAccessLevel()==0){
					//wrong username or password
					JOptionPane.showMessageDialog(contentPane, "wrong username or password");
				}else{
					dispose();
				}
			}
		});
		btnSignIn.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnSignIn.setBounds(337, 363, 171, 41);
		contentPane.add(btnSignIn);
		
		JButton btnSignUp = new JButton("sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SignUp().setVisible(true);
				dispose();
				
			}
		});
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnSignUp.setBounds(123, 363, 171, 41);
		contentPane.add(btnSignUp);
		
		JButton btnEnterAsGuest = new JButton("Enter as guest");
		btnEnterAsGuest.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnEnterAsGuest.setBounds(453, 500, 207, 41);
		contentPane.add(btnEnterAsGuest);
	}
}
