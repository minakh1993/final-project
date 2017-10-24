package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.ID;
import POJO.PhoneRecord;
import RequestResponseManager.UpdateContactRequestResponseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateContact extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextFieled;
	private JTextField familyTextField;
	private JTextField phoneTextField;
	private JTextField mobileTextFiled;
	private JTextField emailTextField;

	

	/**
	 * Create the frame.
	 */
	public UpdateContact(PhoneRecord phoneRecord) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 784, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblName.setBounds(151, 73, 115, 33);
		contentPane.add(lblName);
		
		JLabel lblFamily = new JLabel("family:");
		lblFamily.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblFamily.setBounds(151, 150, 115, 33);
		contentPane.add(lblFamily);
		
		JLabel lblPhoneNumber = new JLabel("phone number:");
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblPhoneNumber.setBounds(151, 234, 206, 33);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblMobileNumber = new JLabel("mobile number:");
		lblMobileNumber.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblMobileNumber.setBounds(151, 313, 206, 33);
		contentPane.add(lblMobileNumber);
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblEmail.setBounds(151, 403, 115, 33);
		contentPane.add(lblEmail);
		
		nameTextFieled = new JTextField();
		nameTextFieled.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		nameTextFieled.setText(phoneRecord.getId().getName());
		nameTextFieled.setBounds(355, 70, 236, 39);
		contentPane.add(nameTextFieled);
		nameTextFieled.setColumns(10);
		
		familyTextField = new JTextField();
		familyTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		familyTextField.setText(phoneRecord.getId().getFamily());
		familyTextField.setBounds(355, 147, 236, 39);
		contentPane.add(familyTextField);
		familyTextField.setColumns(10);
		
		phoneTextField = new JTextField();
		phoneTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		phoneTextField.setText(phoneRecord.getPhoneNumber());
		phoneTextField.setBounds(355, 231, 236, 39);
		contentPane.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		mobileTextFiled = new JTextField();
		mobileTextFiled.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		mobileTextFiled.setText(phoneRecord.getMobileNumber());
		mobileTextFiled.setBounds(355, 310, 236, 39);
		contentPane.add(mobileTextFiled);
		mobileTextFiled.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		emailTextField.setText(phoneRecord.getEmail());
		emailTextField.setBounds(355, 389, 236, 39);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update the contact
				UpdateContactRequestResponseManager urrm=new UpdateContactRequestResponseManager();
				boolean status=urrm.updateContact(new PhoneRecord(new ID(nameTextFieled.getText(), familyTextField.getText()), phoneTextField.getText(), mobileTextFiled.getText(), emailTextField.getText()));
				if(status){
					JOptionPane.showMessageDialog(contentPane, "saved successfully");
				}else{
					JOptionPane.showMessageDialog(contentPane, "this contact doesn't exist");
					
				}
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnUpdate.setBounds(425, 485, 171, 41);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EditContacts().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnBack.setBounds(151, 485, 171, 41);
		contentPane.add(btnBack);
	}
}
