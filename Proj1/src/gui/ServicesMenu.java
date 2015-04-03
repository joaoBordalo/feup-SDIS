package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.util.Vector;

@SuppressWarnings("serial")
public class ServicesMenu extends JFrame {
	
	private JPanel contentPane;
	private ConfigurationMenu configsMenu;
	private Vector <String> backupedFileNames=new Vector <String>();

	/**
	 * Create the frame.
	 */
	public ServicesMenu(ConfigurationMenu configsMenu) {
		
		this.setConfigsMenu(configsMenu);
		
		setTitle("BackUP Services");
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
				BackupMenu backupMenu = new BackupMenu(ServicesMenu.this);
				setEnabled(false);
				backupMenu.setVisible(true);
			}
		});
		backupButton.setBounds(56, 41, 163, 23);
		contentPane.add(backupButton);
		
		JButton restoreButton = new JButton("Restore Backuped File");
		restoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestoreMenu restoreMenu = new RestoreMenu(ServicesMenu.this);
				setEnabled(false);
				restoreMenu.setVisible(true);
			}
		});
		restoreButton.setBounds(250, 41, 162, 23);
		contentPane.add(restoreButton);
		
		JButton deleteButton = new JButton("Delete Backuped File");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteMenu deleteMenu = new DeleteMenu(ServicesMenu.this);
				setEnabled(false);
				deleteMenu.setVisible(true);
			}
		});
		deleteButton.setBounds(56, 75, 163, 23);
		contentPane.add(deleteButton);
		
		JButton freeButton = new JButton("Free Backup space");
		freeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FreeSpaceMenu freeSpaceMenu = new FreeSpaceMenu(ServicesMenu.this);
				setEnabled(false);
				freeSpaceMenu.setVisible(true);
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

	public ConfigurationMenu getConfigsMenu() {
		return configsMenu;
	}

	public void setConfigsMenu(ConfigurationMenu configsMenu) {
		this.configsMenu = configsMenu;
	}

	public Vector <String> getBackupedFileNames() {
		
		return backupedFileNames;
	}
	
	public void addBackupedFile(String fileName)
	{
		backupedFileNames.add(fileName);
	}

	public void setBackupedFileNames(Vector <String> backupedFileNames) {
		this.backupedFileNames = backupedFileNames;
	}
}
