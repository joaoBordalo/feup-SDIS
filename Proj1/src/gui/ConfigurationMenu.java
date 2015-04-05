package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Vector;

import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

import main.Peer;

import java.awt.Label;

@SuppressWarnings("serial")
public class ConfigurationMenu extends JFrame {

	private JPanel contentPane;
	private int mcPort;
	private int mdbPort;
	private int mdrPort;
	private String mcIP;
	private String mdbIP;
	private String mdrIP;
	private int maximumSpace;
	public Peer peer;


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

		setTitle("Service Configuration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		//===================================================================================
		//SPINNERS
		JSpinner mCspinner = new JSpinner();
		mCspinner.setBounds(368, 54, 90, 20);
		contentPane.add(mCspinner);

		JSpinner mDBspinner = new JSpinner();
		mDBspinner.setBounds(368, 85, 90, 20);
		contentPane.add(mDBspinner);

		JSpinner mDRspinner = new JSpinner();
		mDRspinner.setBounds(368, 117, 90, 20);
		contentPane.add(mDRspinner);

		JSpinner bSpacespinner = new JSpinner();
		bSpacespinner.setModel(new SpinnerNumberModel(new Float(64), new Float(64), null, new Float(1)));
		bSpacespinner.setBounds(321, 169, 100, 20);
		contentPane.add(bSpacespinner);


		//===================================================================================
		//LABELS
		JLabel mDBLabel = new JLabel("MultiCast Data BackUp Channel");
		mDBLabel.setBounds(10, 85, 181, 20);
		contentPane.add(mDBLabel);

		JLabel mDRLabel = new JLabel("MultiCast Data Recover Channel");
		mDRLabel.setBounds(10, 117, 181, 20);
		contentPane.add(mDRLabel);

		JLabel bSpaceLabel = new JLabel("Maximum Backup Space (GB)");
		bSpaceLabel.setBounds(44, 169, 192, 20);
		contentPane.add(bSpaceLabel);

		JLabel mCLabel = new JLabel("MultiCast Control Channel");
		mCLabel.setBounds(10, 54, 181, 20);
		contentPane.add(mCLabel);

		JLabel lblServiceConfiguration = new JLabel("Service Configuration");
		lblServiceConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServiceConfiguration.setBounds(21, 11, 159, 32);
		contentPane.add(lblServiceConfiguration);

		Label label = new Label("BROADCAST IP");
		label.setBounds(229, 21, 81, 22);
		contentPane.add(label);

		Label label_1 = new Label("PORT");
		label_1.setBounds(396, 21, 62, 22);
		contentPane.add(label_1);

		//===================================================================================
		//TEXT FIELDS

		JTextField textFieldMC = new JTextField();
		textFieldMC.setBounds(199, 54, 145, 20);
		contentPane.add(textFieldMC);
		textFieldMC.setColumns(10);

		JTextField textFieldMDB = new JTextField();
		textFieldMDB.setColumns(10);
		textFieldMDB.setBounds(199, 85, 145, 20);
		contentPane.add(textFieldMDB);

		JTextField textFieldMDR = new JTextField();
		textFieldMDR.setColumns(10);
		textFieldMDR.setBounds(199, 117, 145, 20);
		contentPane.add(textFieldMDR);


		//===================================================================================
		//BUTTONS
		JButton btnApplyConfiguration = new JButton("Apply Configuration");
		btnApplyConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							setVisible(false);

							mcPort=(int) mCspinner.getValue();
							mdbPort=(int) mDBspinner.getValue();
							mdrPort=(int) mDRspinner.getValue();
							maximumSpace = (int) bSpacespinner.getValue();
							mcIP=textFieldMC.getText();
							mdbIP=textFieldMC.getText();
							mdrIP=textFieldMC.getText();
							ServicesMenu frame = new ServicesMenu(ConfigurationMenu.this);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						String[] args= new String[6];
						args[0]= mcIP;
						args[1]= Integer.toString(mcPort);
						args[2]= mdbIP;
						args[3]= Integer.toString(mdbPort) ;
						args[4]= mdrIP;
						args[5]= Integer.toString(mdrPort);
						peer = new Peer();
						peer.init(args);
					}
				});

			}
		});
		btnApplyConfiguration.setBounds(321, 227, 153, 23);
		contentPane.add(btnApplyConfiguration);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(10, 227, 89, 23);
		contentPane.add(btnCancel);

		JButton btnLoadConfiguratons = new JButton("Load Configurations File");
		btnLoadConfiguratons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename="configs.txt";
				Vector <String> configsFile = new Vector <String>();
				String line=null;
				try
				{

					URL path = ClassLoader.getSystemResource(filename);
					if(path==null) {
						System.err.format("Can´t find or file '%s' doesen't exist\n",filename);
						return;
					}

					File f = new File(path.toURI());
					BufferedReader reader = new BufferedReader(new FileReader(f));

					while ((line = reader.readLine()) != null)
					{

						configsFile.add(line);

					}
					reader.close();

				}
				catch (NumberFormatException e1)
				{
					System.err.format("'%s' isn't in the right format.\n", line);
					return;
				}
				catch(SecurityException e1){
					System.err.format("No permitions to access '%s'.\n", filename);
					return;
				}
				catch (Exception e1)
				{
					System.err.format("Exception occurred trying to read '%s'.\n", filename);
					e1.printStackTrace();
					return;

				}

				if (configsFile.size()!= 7)
				{
					System.err.format("File '%s' doesn't have the service configurations right.", filename);
					return;
				}
				textFieldMC.setText(configsFile.get(0));
				mCspinner.setValue(Integer.parseInt(configsFile.get(1)));
				textFieldMDB.setText(configsFile.get(2));
				mDBspinner.setValue(Integer.parseInt(configsFile.get(3)));
				textFieldMDR.setText(configsFile.get(4));
				mDRspinner.setValue(Integer.parseInt(configsFile.get(5)));
				bSpacespinner.setValue(Integer.parseInt(configsFile.get(6)));
			}

		});
		btnLoadConfiguratons.setBounds(116, 227, 181, 23);
		contentPane.add(btnLoadConfiguratons);
	}


	//===================================================================================
	//===================================================================================
	//Other Methods
	public int getMcPort() {
		return mcPort;
	}

	public void setMcPort(int mcPort) {
		this.mcPort = mcPort;
	}

	public int getMdbPort() {
		return mdbPort;
	}

	public void setMdbPort(int mdbPort) {
		this.mdbPort = mdbPort;
	}

	public int getMdrPort() {
		return mdrPort;
	}

	public void setMdrPort(int mdrPort) {
		this.mdrPort = mdrPort;
	}

	public int getMaximumSpace() {
		return maximumSpace;
	}

	public void setMaximumSpace(int maximumSpace) {
		this.maximumSpace = maximumSpace;
	}


	public String getMcIP() {
		return mcIP;
	}

	public void setMcIP(String mcIP) {
		this.mcIP = mcIP;
	}

	public String getMdbIP() {
		return mdbIP;
	}

	public void setMdbIP(String mdbIP) {
		this.mdbIP = mdbIP;
	}

	public String getMdrIP() {
		return mdrIP;
	}

	public void setMdrIP(String mdrIP) {
		this.mdrIP = mdrIP;
	}

}
