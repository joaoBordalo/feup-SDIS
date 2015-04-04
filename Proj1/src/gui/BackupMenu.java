package gui;

import info.BackupFile;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import protocols.Backup;



@SuppressWarnings("serial")
public class BackupMenu extends JFrame {

	private ServicesMenu previousMenu;
	private String pathfile;
	private String fileName;
	private int replicationDegree;
	
	public BackupMenu(ServicesMenu previousMenu) {
		setTitle("Backup File");
		
		this.setPreviousMenu(previousMenu);
		setBounds(100, 100, 450, 200);
		
		//FilePicker settings
		JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
		filePicker.setMode(JFilePicker.MODE_OPEN);
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("D:/"));
		filePicker.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(filePicker,BorderLayout.NORTH);
		
		JPanel spinnerPane= new JPanel();
		spinnerPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(spinnerPane, BorderLayout.CENTER);

		
		JLabel replicationDegreeLabel = new JLabel("Number of Replication Degree");
		replicationDegreeLabel.setBounds(26, 93, 200, 20);
		spinnerPane.add(replicationDegreeLabel);
		
		JSpinner ReplicationDegreespinner = new JSpinner();
		ReplicationDegreespinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		ReplicationDegreespinner.setBounds(324, 93, 100, 20);
		spinnerPane.add(ReplicationDegreespinner);
		
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		
		//===================================================================================
		//BUTTONS
		JButton btnBackup = new JButton("Backup");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				previousMenu.setEnabled(true);
				previousMenu.setVisible(true);
				setReplicationDegree((int) ReplicationDegreespinner.getValue());
				setPathfile(filePicker.getSelectedFilePath());
				setFileName(filePicker.getFileName());
				File f = new File(pathfile);
				BackupFile file= new BackupFile(fileName, replicationDegree, f.length());
				previousMenu.addBackupedfileList(file);
				
				//send chunks!!
				Backup bc;
				try {
					bc = new Backup(previousMenu.getConfigsMenu().peer,fileName,replicationDegree,pathfile);
					bc.run();
				} 
				catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnBackup.setActionCommand("OK");
		buttonPane.add(btnBackup);
		getRootPane().setDefaultButton(btnBackup);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				previousMenu.setEnabled(true);
				previousMenu.setVisible(true);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 200);
	}

	
	//===================================================================================
	//===================================================================================
	//Other Methods 
	public int getReplicationDegree() {
		return replicationDegree;
	}

	public void setReplicationDegree(int replicationDegree) {
		this.replicationDegree = replicationDegree;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPathfile() {
		return pathfile;
	}

	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}

	public ServicesMenu getPreviousMenu() {
		return previousMenu;
	}

	public void setPreviousMenu(ServicesMenu previousMenu) {
		this.previousMenu = previousMenu;
	}
	



}