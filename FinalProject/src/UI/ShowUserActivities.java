package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import POJO.Event;
import RequestResponseManager.ShowEventsRequestResponseManager;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowUserActivities extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUserActivities frame = new ShowUserActivities();
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
	public ShowUserActivities() {
		setTitle("show user activities");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel( new Object[][] {},new String[] {
				"code", "date", "description", "user"
			}
		));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setViewportBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 255, 255), new Color(255, 175, 175), Color.PINK, Color.CYAN));
		scroll.setBounds(26, 5, 881, 468);
		contentPane.add(scroll);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientAccessPanel.getPanel().setVisible(true);
				dispose();
				
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btnBack.setBounds(26, 501, 171, 41);
		contentPane.add(btnBack);
		
		ShowEventsRequestResponseManager srrm=new ShowEventsRequestResponseManager();
		List<Event> list=srrm.showEvents();
		
		//drawing the table
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				System.out.println(list.size());
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
                    String name="deleted user";
					if(list.get(i).getUser()!=null){
					name=list.get(i).getUser().getUsername();	
					}
					model.insertRow(i, new Object[] { list.get(i).getCode(), list.get(i).getDate(), 
							list.get(i).getDescription(), name});

				}
		
	}
}
