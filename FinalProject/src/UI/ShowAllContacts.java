package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EntityManager.PhoneNumberEntityManager;
import POJO.PhoneRecord;
import RequestResponseManager.ShowAllContactsRequestResponseManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowAllContacts extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int prePage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAllContacts frame = new ShowAllContacts(1);
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
	public ShowAllContacts(int prePage) {
		this.prePage=prePage;
		
		setTitle("show all contacts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 644);
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
		scroll.setBounds(81, 64, 658, 345);
		contentPane.add(scroll);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prePage==1){
					Login.getLogin().setVisible(true);
					dispose();
					
				}else if(prePage==2){
					ClientAccessPanel.getPanel().setVisible(true);
					dispose();
					
				}
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnBack.setBounds(81, 458, 171, 41);
		contentPane.add(btnBack);
		
		//getting the contacts from contact Entity Manager
		
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
		
	}
}
