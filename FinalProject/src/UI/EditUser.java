package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.User;
import RequestResponseManager.UpdateUserRequestResponseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditUser extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField nameTextField;
	private JTextField familyTextField;

	
	public EditUser(User user) {
		setTitle("edit user");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblUsername.setBounds(186, 114, 156, 33);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblName.setBounds(186, 202, 115, 33);
		contentPane.add(lblName);
		
		JLabel lblFamily = new JLabel("family:");
		lblFamily.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblFamily.setBounds(186, 294, 115, 33);
		contentPane.add(lblFamily);
		
		JLabel lblRole = new JLabel("role:");
		lblRole.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblRole.setBounds(186, 384, 115, 33);
		contentPane.add(lblRole);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		usernameTextField.setText(user.getUsername());
		usernameTextField.setBounds(347, 111, 236, 39);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		nameTextField.setText(user.getName());
		nameTextField.setBounds(347, 198, 236, 39);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		familyTextField = new JTextField();
		familyTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		familyTextField.setText(user.getFamily());
		familyTextField.setBounds(347, 290, 236, 39);
		contentPane.add(familyTextField);
		familyTextField.setColumns(10);
		
		JComboBox<String> roleComboBox = new JComboBox<String>();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"user", "manager", "generalManager"}));
		roleComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		roleComboBox.setBounds(347, 381, 236, 39);
		contentPane.add(roleComboBox);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(roleComboBox.getSelectedIndex());
				UpdateUserRequestResponseManager urrm=new UpdateUserRequestResponseManager();
				boolean status=urrm.updateUser(new User(usernameTextField.getText(), user.getPassword(),nameTextField.getText(), familyTextField.getText(),(String)roleComboBox.getSelectedItem(), 3-roleComboBox.getSelectedIndex()));

				if(status){
					JOptionPane.showMessageDialog(contentPane, "updated successfully");
				}else{
					JOptionPane.showMessageDialog(contentPane, "failed");
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnUpdate.setBounds(492, 491, 171, 41);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new UserManagement().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnBack.setBounds(130, 491, 171, 41);
		contentPane.add(btnBack);
	}
}
