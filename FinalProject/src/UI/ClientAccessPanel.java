package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WindowClient.UserManager;

import javax.swing.JRadioButton;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientAccessPanel extends JFrame {

	private JPanel contentPane;
	private static ClientAccessPanel instance=new ClientAccessPanel();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * 
	 */
	
	public static ClientAccessPanel getPanel(){
		return instance;
	}
	
	private ClientAccessPanel() {
		setTitle("client Access Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 802);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnAddAContact = new JRadioButton("Add Contact");
		rdbtnAddAContact.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		rdbtnAddAContact.setBounds(189, 99, 251, 41);
		contentPane.add(rdbtnAddAContact);
		
		JRadioButton rdbtnShowAllContacts = new JRadioButton("show All Contacts");
		rdbtnShowAllContacts.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		rdbtnShowAllContacts.setBounds(189, 188, 383, 41);
		contentPane.add(rdbtnShowAllContacts);
		
		JRadioButton rdbtnEditContacts = new JRadioButton("Edit Contacts");
		rdbtnEditContacts.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		rdbtnEditContacts.setBounds(189, 279, 251, 41);
		contentPane.add(rdbtnEditContacts);
		
		JRadioButton rdbtnUsersActivities = new JRadioButton("Users Activities");
		rdbtnUsersActivities.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		rdbtnUsersActivities.setBounds(189, 368, 251, 41);
		contentPane.add(rdbtnUsersActivities);
		
		JRadioButton rdbtnUserManagement = new JRadioButton("User Management");
		rdbtnUserManagement.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		rdbtnUserManagement.setBounds(189, 454, 281, 41);
		contentPane.add(rdbtnUserManagement);
		
		
		//grouping the buttons
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnAddAContact);     //user access
		group.add(rdbtnShowAllContacts);  //user access
		group.add(rdbtnEditContacts);    //manager access
		group.add(rdbtnUsersActivities); //manager access
		group.add(rdbtnUserManagement); //general Manager access
		
		
		
		
		
		// point ask event manager to handle the not allowed options
		JButton btnSelect = new JButton("select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnAddAContact.isSelected()){
					//allowed for all users
				    new AddContact().setVisible(true);
				    dispose();
					
					
				}else if(rdbtnShowAllContacts.isSelected()){
					//allowed for all users 
					//int 2 means this user has already entered the system
					new ShowAllContacts(2).setVisible(true);
					dispose();
					
					
					
				}else if(rdbtnEditContacts.isSelected()){
					//allowed for mangers and above
					if(UserManager.getAccessLevel()==3){
						//not allowed
						JOptionPane.showMessageDialog(contentPane, "this option is not allowed");
						
						
					}else{
						//allowed
						new EditContacts().setVisible(true);
						dispose();
					}
					
					
				}else if(rdbtnUsersActivities.isSelected()){
					//allowed for mangers and above
					if(UserManager.getAccessLevel()==3){
						//not allowed
						JOptionPane.showMessageDialog(contentPane, "this option is not allowed");
						
						
					}else{
						//allowed
						new ShowUserActivities().setVisible(true);
						dispose();
					}
					
					
				}else if(rdbtnUserManagement.isSelected()){
					//only allowed for general Manager
					if(UserManager.getAccessLevel()==1){
						new UserManagement().setVisible(true);
						dispose();
						
					}else{
						JOptionPane.showMessageDialog(contentPane, "this option is not allowed");
					}
					
					
					
				}else{
					// no option choosen
					JOptionPane.showMessageDialog(contentPane, "select an option first");
				}
			}
		});
		btnSelect.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnSelect.setBounds(431, 569, 171, 41);
		contentPane.add(btnSelect);
	}
}
