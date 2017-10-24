package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.PhoneRecord;

import RequestResponseManager.AddContactRequestResponseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import POJO.*;

public class AddContact extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField familyTextFiled;
	private JTextField phoneNumberTextField;
	private JTextField mobileTextField;
	private JTextField EmailTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContact frame = new AddContact();
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
	public AddContact() {
		setTitle("add contact");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblName.setBounds(195, 89, 115, 33);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		nameTextField.setBounds(435, 85, 236, 39);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblFamily = new JLabel("family:");
		lblFamily.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblFamily.setBounds(195, 170, 115, 33);
		contentPane.add(lblFamily);
		
		familyTextFiled = new JTextField();
		familyTextFiled.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		familyTextFiled.setBounds(435, 166, 236, 39);
		contentPane.add(familyTextFiled);
		familyTextFiled.setColumns(10);
		
		JLabel lblPhonenumber = new JLabel("phoneNumber:");
		lblPhonenumber.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblPhonenumber.setBounds(195, 251, 187, 33);
		contentPane.add(lblPhonenumber);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		phoneNumberTextField.setBounds(435, 247, 236, 39);
		contentPane.add(phoneNumberTextField);
		phoneNumberTextField.setColumns(10);
		
		JLabel lblMobileNumber = new JLabel("mobile Number:");
		lblMobileNumber.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblMobileNumber.setBounds(195, 330, 226, 33);
		contentPane.add(lblMobileNumber);
		
		mobileTextField = new JTextField();
		mobileTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		mobileTextField.setBounds(435, 327, 236, 39);
		contentPane.add(mobileTextField);
		mobileTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblEmail.setBounds(195, 413, 115, 33);
		contentPane.add(lblEmail);
		
		EmailTextField = new JTextField();
		EmailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		EmailTextField.setBounds(435, 410, 236, 39);
		contentPane.add(EmailTextField);
		EmailTextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//add a contact
				AddContactRequestResponseManager acrm=new AddContactRequestResponseManager();
				PhoneRecord phoneRecord=new PhoneRecord();
				ID id=new ID(nameTextField.getText(), familyTextFiled.getText());
				phoneRecord.setId(id); phoneRecord.setPhoneNumber(phoneNumberTextField.getText());
				phoneRecord.setMobileNumber(mobileTextField.getText()); phoneRecord.setEmail(EmailTextField.getText());
				boolean status=acrm.addContact(phoneRecord);
				
				if(status){
					JOptionPane.showMessageDialog(contentPane, "contact saved successfully");
					
				}else{
					
					JOptionPane.showMessageDialog(contentPane, "this person already exists in phonebook");
					
				}
				
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnAdd.setBounds(503, 524, 171, 41);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ClientAccessPanel.getPanel().setVisible(true);
			dispose();
				
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnBack.setBounds(195, 524, 171, 41);
		contentPane.add(btnBack);
	}
}
