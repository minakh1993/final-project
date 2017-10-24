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

import POJO.User;
import RequestResponseManager.DeleteContactRequestResponseManager;
import RequestResponseManager.DeleteUserRequestResponseManager;
import RequestResponseManager.ShowAllUserRequestResponseManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagement frame = new UserManagement();
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
	public UserManagement() {
		setTitle("user management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "username", "name", "family", "role" }));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(92, 15, 715, 467);
		contentPane.add(scroll);

		ShowAllUserRequestResponseManager s = new ShowAllUserRequestResponseManager();
		System.out.println(s.showAllUsers().get(0).getClass());
		List<User> list = (List<User>) s.showAllUsers();
		System.out.println(list.get(0).getClass());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}

		// drawing the table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {

			model.insertRow(i, new Object[] { list.get(i).getUsername(), list.get(i).getName(), list.get(i).getFamily(),
					list.get(i).getRole() });

		}

		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int row = table.getSelectedRow();
				if (row == -1) {
					// no row is selected
					JOptionPane.showMessageDialog(contentPane, "please choose a contact first");

				} else {
					// a row is selected and want to update it
					
					new EditUser(list.get(row)).setVisible(true);
					dispose();
					

				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnUpdate.setBounds(546, 515, 171, 41);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int row = table.getSelectedRow();
				if (row == -1) {
					// no row is selected
					JOptionPane.showMessageDialog(contentPane, "please choose a contact first");

				} else {
					// a row is selected and want to update it
					int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to delete this contact?",
							"Choose", JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						// delete the contact
						DeleteUserRequestResponseManager dcrr = new DeleteUserRequestResponseManager();
						dcrr.deleteUser(list.get(row).getUsername());
						model.removeRow(row);
					}

				}

			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnDelete.setBounds(163, 515, 171, 41);
		contentPane.add(btnDelete);

	}
}
