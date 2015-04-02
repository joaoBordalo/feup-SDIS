package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ConfigurationMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationMenu frame = new ConfigurationMenu();
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
	public ConfigurationMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("BackUP Service");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner MCspinner = new JSpinner();
		MCspinner.setBounds(307, 54, 100, 20);
		contentPane.add(MCspinner);
		
		JSpinner MDBspinner = new JSpinner();
		MDBspinner.setBounds(307, 85, 100, 20);
		contentPane.add(MDBspinner);
		
		JSpinner MDRspinner = new JSpinner();
		MDRspinner.setBounds(307, 117, 100, 20);
		contentPane.add(MDRspinner);
		
		
		JLabel MDBLabel = new JLabel("MultiCast Data BackUp Channel Port");
		MDBLabel.setBounds(44, 85, 253, 20);
		contentPane.add(MDBLabel);
		
		JLabel MDRLabel = new JLabel("MultiCast Data Recover Channel Port");
		MDRLabel.setBounds(44, 117, 253, 20);
		contentPane.add(MDRLabel);
		
		JButton btnApplyConfiguration = new JButton("Apply Configuration");
		btnApplyConfiguration.setBounds(321, 227, 153, 23);
		contentPane.add(btnApplyConfiguration);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 227, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnLoadConfiguratons = new JButton("Load Configurations");
		btnLoadConfiguratons.setBounds(132, 227, 165, 23);
		contentPane.add(btnLoadConfiguratons);
		
		JSpinner MSpacespinner = new JSpinner();
		MSpacespinner.setBounds(307, 159, 100, 20);
		contentPane.add(MSpacespinner);
		
		JLabel BSpaceLabel = new JLabel("Backup Space available");
		BSpaceLabel.setBounds(44, 159, 192, 17);
		contentPane.add(BSpaceLabel);
		
		JLabel MCLabel = new JLabel("MultiCast Control Channel Port");
		MCLabel.setBounds(44, 54, 253, 20);
		contentPane.add(MCLabel);
		
		JLabel lblServiceConfiguration = new JLabel("Service Configuration");
		lblServiceConfiguration.setBounds(21, 11, 146, 19);
		contentPane.add(lblServiceConfiguration);
	}
}
