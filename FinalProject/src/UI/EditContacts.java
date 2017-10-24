package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import POJO.PhoneRecord;
import RequestResponseManager.DeleteContactRequestResponseManager;
import RequestResponseManager.ShowAllContactsRequestResponseManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditContacts extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContacts frame = new EditContacts();
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
	public EditContacts() {
		setTitle("edit contacts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel( new Object[][] {},new String[] {
				"name", "family", "phone number", "mobile number", "email"
			}
		));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(75, 5, 722, 453);
		contentPane.add(scroll);
		
		
		
		
		ShowAllContactsRequestResponseManager s=new ShowAllContactsRequestResponseManager();
		List<PhoneRecord> list=s.showAllContacts();
		
		
		//drawing the table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));

			model.insertRow(i, new Object[] { list.get(i).getId().getName(), list.get(i).getId().getFamily()
					, list.get(i).getPhoneNumber(),list.get(i).getMobileNumber(), list.get(i).getEmail()});

		}
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//see which row is selected
				int row=table.getSelectedRow();
				if(row==-1){
					//no row is selected
					JOptionPane.showMessageDialog(contentPane, "please choose a contact first");
					
				}else{
					//a row is selected and want to update it
					UpdateContact upcontact=new UpdateContact(list.get(row));
					upcontact.setVisible(true);
					dispose();
					
				}
				
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnUpdate.setBounds(514, 495, 171, 41);
		contentPane.add(btnUpdate);
		

		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1){
					//no row is selected
					JOptionPane.showMessageDialog(contentPane, "please choose a contact first");
					
				}else{
					//a row is selected and want to update it
					int selectedOption = JOptionPane.showConfirmDialog(null, 
                            "Do you want to delete this contact?", 
                            "Choose", 
                            JOptionPane.YES_NO_OPTION); 
					if (selectedOption == JOptionPane.YES_OPTION) {
					   //delete the contact
						DeleteContactRequestResponseManager dcrr=new DeleteContactRequestResponseManager();
						dcrr.deleteContact(list.get(row).getId());
						model.removeRow(row);
					}
					
				}
				
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnDelete.setBounds(150, 495, 171, 41);
		contentPane.add(btnDelete);
	}
}
