package gui;


import info.BackupFile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

@SuppressWarnings("serial")
public class ServicesMenu extends JFrame {

	private JPanel contentPane;
	private ConfigurationMenu configsMenu;
	private Vector <BackupFile> backupedfilesList = new Vector <BackupFile>();
	private File backupedFile;
	private int availableSpace;

	/**
	 * Create the frame.
	 */
	public ServicesMenu(ConfigurationMenu configsMenu) {
		
		//availableSpace =this.configsMenu.getMaximumSpace();
		
		this.setConfigsMenu(configsMenu);
		
		try {
			manageBackupedFile();
		} catch (IOException e1) {
			System.err.format("File \"backupedFiles.txt\" couldn't be created or loaded");
			e1.printStackTrace();
		}

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

	//===================================================================================
	//===================================================================================
	//Other Methods 
	
	private void manageBackupedFile() throws IOException
	{
		 backupedFile = new File("backupedFiles.txt");
		
		if(backupedFile.exists())//load file
		{
				loadBackupedFiles(backupedFile);
		}
		else//create file
		{
			backupedFile.createNewFile();
		}
	}
	
	private void loadBackupedFiles(File backupedFile) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(backupedFile));
		String content=null;
		String[] parsedContent= new String[5];
		while ((content = reader.readLine()) != null)
		{
			parsedContent=content.split(" ");
			BackupFile backupFile= new BackupFile(parsedContent[0],Integer.parseInt(parsedContent[1]),Integer.parseInt(parsedContent[2]),Float.parseFloat(parsedContent[3]),Boolean.parseBoolean(parsedContent[4]));
			backupedfilesList.add(backupFile);
		}
		reader.close();
	}
	
	public boolean spaceAvailable()
	{
		int usedSpace=0;
		
		for (int i = 0; i < backupedfilesList.size();i++)
		{
			usedSpace +=backupedfilesList.get(i).getFileSize();
		}
		
		return configsMenu.getMaximumSpace()>usedSpace;
		
	}
	public File getBackupedFile() {
		return backupedFile;
	}

	public void setBackupedFile(File backupedFile) {
		this.backupedFile = backupedFile;
	}

	public ConfigurationMenu getConfigsMenu() {
		return configsMenu;
	}

	public void setConfigsMenu(ConfigurationMenu configsMenu) {
		this.configsMenu = configsMenu;
	}

	public void addBackupedfileList(BackupFile file)
	{
		backupedfilesList.add(file);
	}

	public Vector <BackupFile> getBackupedfilesList() {
		return backupedfilesList;
	}

	public void setBackupedfilesList(Vector <BackupFile> backupedfilesList) {
		this.backupedfilesList = backupedfilesList;
	}
}
