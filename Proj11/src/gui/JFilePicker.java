package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class JFilePicker extends JPanel {
	private String textFieldLabel;
	private String buttonLabel;
	private String fileName;

	private JLabel label;
	private JTextField textField;
	private JButton button;

	private JFileChooser fileChooser;

	private int mode;
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;

	public JFilePicker(String textFieldLabel, String buttonLabel) {
		this.setTextFieldLabel(textFieldLabel);
		this.setButtonLabel(buttonLabel);

		fileChooser = new JFileChooser();

		

		// creates the GUI
		label = new JLabel(textFieldLabel);
		label.setBounds(15, 150, 30, 20);

		textField = new JTextField(20);
		button = new JButton(buttonLabel);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);            
			}
		});

		add(label);
		add(textField);
		add(button);
		
		
		
	}

	private void buttonActionPerformed(ActionEvent evt) {
		if (mode == MODE_OPEN) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				setFileName(fileChooser.getSelectedFile().getName());
			}
		} else if (mode == MODE_SAVE) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				setFileName(fileChooser.getSelectedFile().getName());
			}
		}
	}


	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getSelectedFilePath() {
		return textField.getText();
	}

	public JFileChooser getFileChooser() {
		return this.fileChooser;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTextFieldLabel() {
		return textFieldLabel;
	}

	public void setTextFieldLabel(String textFieldLabel) {
		this.textFieldLabel = textFieldLabel;
	}

	public String getButtonLabel() {
		return buttonLabel;
	}

	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}
}
