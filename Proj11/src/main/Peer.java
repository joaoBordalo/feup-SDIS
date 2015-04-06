package main;


import java.io.IOException;







import java.net.InetAddress;
import java.net.UnknownHostException;

import threads.*;
import comSystem.*;

public class Peer {
	
	private String localIP;
	private MSock MC, MDB, MDR;
	 
	 
	
	public Peer()
	{
		try {
			InetAddress IP=InetAddress.getLocalHost();
			localIP=IP.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public MSock getMC() {
		return MC;
	}

	public void setMC(MSock mC) {
		MC = mC;
	}

	public MSock getMDB() {
		return MDB;
	}

	public void setMDB(MSock mDB) {
		MDB = mDB;
	}

	public MSock getMDR() {
		return MDR;
	}

	public void setMDR(MSock mDR) {
		MDR = mDR;
	}


	public void init(String args[])
	{
		try {
			MC = new MSock(args[0], Integer.parseInt(args[1]));
			
			 MDB = new MSock(args[2], Integer.parseInt(args[3]));
			 
			 MDR = new MSock(args[4], Integer.parseInt(args[5]));
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		 
	}


	public String getLocalIP() {
		return localIP;
	}

	public void setLocalIP(String localIP) {
		this.localIP = localIP;
	}
	
/*

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		
		if(args.length==0)
		{
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
		else if(args.length<7)
		{
			System.out.println("wrong number of arguments");
			System.out.println("usage-> java peer <MCadd> <MCport> <MDBadd> <MDBport> <MDRadd> <MDBport> <DiskSize>");
		}
		else
		{
			 Peer peer=new Peer();
			 
			 peer.init(args);
			 
			 ConfigurationMenu cf = new ConfigurationMenu();
			 cf.setMcIP(args[0]);
			 cf.setMcPort(Integer.parseInt(args[1]));
			 cf.setMdbIP(args[2]);
			 cf.setMdbPort(Integer.parseInt(args[3]));
			 cf.setMdrIP(args[4]);
			 cf.setMdrPort(Integer.parseInt(args[5]));
			 cf.setMcIP(args[6]);
			 
			 EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ServicesMenu frame = new ServicesMenu(cf);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			 
		}
		
		
			
	}*/

}
