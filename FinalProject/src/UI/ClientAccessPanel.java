package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 1001, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
