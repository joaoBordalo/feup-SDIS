package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class DeleteMenu extends JFrame {

	private JPanel contentPane;
	private ServicesMenu previousMenu;
	private String[] deletedFiles;
	private String selectedDeletedFile;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DeleteMenu(ServicesMenu previousMenu) {
		this.setPreviousMenu(previousMenu);
		setTitle("Delete File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		//===================================================================================
		//LABEL
		Label labelChooseFile = new Label("Choose the backuped file to delete");
		labelChooseFile.setBounds(10, 43, 198, 22);
		contentPane.add(labelChooseFile);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(226, 45, 198, 20);
		comboBox.setModel(new DefaultComboBoxModel(getDeletedFiles()));
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

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				previousMenu.setEnabled(true);
				previousMenu.setVisible(true);
				selectedDeletedFile = deletedFiles[comboBox.getSelectedIndex()];
			}
		});
		btnDelete.setBounds(236, 146, 89, 23);
		contentPane.add(btnDelete);
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
	public String[] getDeletedFiles() {

		Vector <String> aux = previousMenu.getBackupedFileNames();
		int nFilesRestored = aux.size();
		deletedFiles = new String[nFilesRestored];
		for (int i = 0; i < nFilesRestored;i++ ) {
			deletedFiles[i]=aux.get(i);
		}
		return deletedFiles;
	}
	public void setDeletedFiles(String[] restoredFiles) {
		this.deletedFiles = restoredFiles;
	}

	public String getSelectedDeletedFile() {
		return selectedDeletedFile;
	}

	public void setSelectedDeletedFile(String selectedRestoreFile) {
		this.selectedDeletedFile = selectedRestoreFile;
	}

}
