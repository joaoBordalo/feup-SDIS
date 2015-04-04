package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;

@SuppressWarnings("serial")
public class FreeSpaceMenu extends JFrame {

	private JPanel contentPane;
	private ServicesMenu previousMenu;
	private float spaceToFree;

	/**
	 * Create the frame.
	 */
	public FreeSpaceMenu(ServicesMenu previousMenu) {
		this.previousMenu=previousMenu;
		
		setTitle("Free Space");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		float maxSpace=this.previousMenu.getConfigsMenu().getMaximumSpace();
		JSpinner spinnerFreeSpace = new JSpinner();
		spinnerFreeSpace.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(maxSpace), new Float(1)));
		spinnerFreeSpace.setBounds(295, 37, 89, 20);
		contentPane.add(spinnerFreeSpace);
		
		Label labelFreeSpace = new Label("Free Space from Disk (GB)");
		labelFreeSpace.setBounds(62, 37, 180, 22);
		contentPane.add(labelFreeSpace);
		
		
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
		btnCancel.setBounds(335, 127, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnFreeSpace = new JButton("Free Space");
		btnFreeSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				previousMenu.setEnabled(true);
				previousMenu.setVisible(true);
				spaceToFree=(float) spinnerFreeSpace.getValue();
				FreeSpaceMenu.this.previousMenu.getConfigsMenu().setMaximumSpace(maxSpace-spaceToFree);
			}
		});
		btnFreeSpace.setBounds(205, 127, 120, 23);
		contentPane.add(btnFreeSpace);
		
	}

	public float getSpaceToFree() {
		return spaceToFree;
	}

	public void setSpaceToFree(float spaceToFree) {
		this.spaceToFree = spaceToFree;
	}

	public ServicesMenu getPreviousMenu() {
		return previousMenu;
	}

	public void setPreviousMenu(ServicesMenu previousMenu) {
		this.previousMenu = previousMenu;
	}
}
