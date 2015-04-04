package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import java.awt.Label;
import java.util.Vector;

@SuppressWarnings("serial")
public class RestoreMenu extends JFrame {

	private JPanel contentPane;
	private ServicesMenu previousMenu;
	private String[] restoredFiles;
	private String selectedRestoreFile;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RestoreMenu(ServicesMenu previousMenu) {
		this.setPreviousMenu(previousMenu);
		setTitle("Restore File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		//===================================================================================
		//LABEL
		Label labelChooseFile = new Label("Choose the backuped file to restore");
		labelChooseFile.setBounds(10, 43, 198, 22);
		contentPane.add(labelChooseFile);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(226, 45, 198, 20);
		comboBox.setModel(new DefaultComboBoxModel(getRestoredFiles()));
		contentPane.add(comboBox);
		

		//===================================================================================
		//BUTTONS
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				previousMenu.setEnabled(true);
				previousMenu.setVisible(true);

			}
		});
		btnCancel.setBounds(335, 146, 89, 23);
		contentPane.add(btnCancel);

		JButton btnRestore = new JButton("Restore");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				previousMenu.setEnabled(true);
				previousMenu.setVisible(true);
				selectedRestoreFile = restoredFiles[comboBox.getSelectedIndex()];
			}
		});
		btnRestore.setBounds(236, 146, 89, 23);
		contentPane.add(btnRestore);
	}

	//===================================================================================
	//===================================================================================
	//Other Methods 
	public ServicesMenu getPreviousMenu() {
		return previousMenu;
	}
	public void setPreviousMenu(ServicesMenu previousMenu) {
		this.previousMenu = previousMenu;
	}
	public String[] getRestoredFiles() {

		Vector <String> aux =new Vector<String>();
		for(int i =0;i < previousMenu.getBackupedfilesList().size();i++)
		{
			aux.add(previousMenu.getBackupedfilesList().get(i).getFileName());
		}
		int nFilesRestored = aux.size();
		restoredFiles = new String[nFilesRestored];
		for (int i = 0; i < nFilesRestored;i++ ) {
			System.out.println(aux.get(i));
			restoredFiles[i]=aux.get(i);
		}
		return restoredFiles;
	}
	public void setRestoredFiles(String[] restoredFiles) {
		this.restoredFiles = restoredFiles;
	}

	public String getSelectedRestoreFile() {
		return selectedRestoreFile;
	}

	public void setSelectedRestoreFile(String selectedRestoreFile) {
		this.selectedRestoreFile = selectedRestoreFile;
	}


}


