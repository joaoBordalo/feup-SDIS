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

@SuppressWarnings("serial")
public class ConfigurationMenu extends JFrame {

	private JPanel contentPane;
	private int mcPort;
	private int mdbPort;
	private int mdrPort;
	private float maximumSpace;

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

	public float getMaximumSpace() {
		return maximumSpace;
	}

	public void setMaximumSpace(float maximumSpace) {
		this.maximumSpace = maximumSpace;
	}

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
		
		setTitle("BackUP Service");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		//===================================================================================
		//SPINNERS
		JSpinner mCspinner = new JSpinner();
		mCspinner.setBounds(307, 54, 100, 20);
		contentPane.add(mCspinner);

		JSpinner mDBspinner = new JSpinner();
		mDBspinner.setBounds(307, 85, 100, 20);
		contentPane.add(mDBspinner);

		JSpinner mDRspinner = new JSpinner();
		mDRspinner.setBounds(307, 117, 100, 20);
		contentPane.add(mDRspinner);

		JSpinner bSpacespinner = new JSpinner();
		bSpacespinner.setModel(new SpinnerNumberModel(new Float(64), new Float(64), null, new Float(1)));
		bSpacespinner.setBounds(307, 169, 100, 20);
		contentPane.add(bSpacespinner);


		//===================================================================================
		//LABELS
		JLabel mDBLabel = new JLabel("MultiCast Data BackUp Channel Port");
		mDBLabel.setBounds(44, 85, 253, 20);
		contentPane.add(mDBLabel);

		JLabel mDRLabel = new JLabel("MultiCast Data Recover Channel Port");
		mDRLabel.setBounds(44, 117, 253, 20);
		contentPane.add(mDRLabel);

		JLabel bSpaceLabel = new JLabel("Maximum Backup Space (GB)");
		bSpaceLabel.setBounds(44, 169, 192, 20);
		contentPane.add(bSpaceLabel);

		JLabel mCLabel = new JLabel("MultiCast Control Channel Port");
		mCLabel.setBounds(44, 54, 253, 20);
		contentPane.add(mCLabel);

		JLabel lblServiceConfiguration = new JLabel("Service Configuration");
		lblServiceConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServiceConfiguration.setBounds(21, 11, 159, 32);
		contentPane.add(lblServiceConfiguration);

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
							maximumSpace = (float) bSpacespinner.getValue();
							ServicesMenu frame = new ServicesMenu(ConfigurationMenu.this);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
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
				Vector <Integer> configsFile = new Vector <Integer>();
				String line=null;
				
				
				try
				{
					
				URL path = ClassLoader.getSystemResource(filename);
				if(path==null) {
					System.err.format("Can´t find or file '%s' doesen't exist\n",filename);
					return;
				}
				
					File f = new File(path.toURI());
					System.out.println(f.getAbsolutePath());
					BufferedReader reader = new BufferedReader(new FileReader(f));
					
					while ((line = reader.readLine()) != null)
					{
						System.out.println(line);

						Integer value = Integer.parseInt(line);
						configsFile.add(value);
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

				if (configsFile.size()!= 4)
				{
					System.err.format("File '%s' doesn't have the service configurations right.", filename);
					return;
				}

				mCspinner.setValue(configsFile.get(0));
				mDBspinner.setValue(configsFile.get(1));
				mDRspinner.setValue(configsFile.get(2));
				bSpacespinner.setValue(configsFile.get(3));
			}

		});
		btnLoadConfiguratons.setBounds(116, 227, 181, 23);
		contentPane.add(btnLoadConfiguratons);


	}
}
