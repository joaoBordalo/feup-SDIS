package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class ServicesMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServicesMenu frame = new ServicesMenu();
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
	public ServicesMenu() {
		
		setTitle("BackUP Service");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//===================================================================================
		//BUTTONS
		JButton backupButton = new JButton("BackUp File");
		backupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		backupButton.setBounds(56, 41, 163, 23);
		contentPane.add(backupButton);
		
		JButton restoreButton = new JButton("Restore Backuped File");
		restoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		restoreButton.setBounds(250, 41, 162, 23);
		contentPane.add(restoreButton);
		
		JButton deleteButton = new JButton("Delete Backuped File");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteButton.setBounds(56, 75, 163, 23);
		contentPane.add(deleteButton);
		
		JButton freeButton = new JButton("Free Backup space");
		freeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		freeButton.setBounds(250, 75, 162, 23);
		contentPane.add(freeButton);
		
		
		//===================================================================================
		//LABEL
		JLabel messageLogLabel = new JLabel("Message Log");
		messageLogLabel.setBounds(41, 111, 119, 14);
		contentPane.add(messageLogLabel);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(32, 131, 380, 160);
		contentPane.add(textArea);
	}
}
