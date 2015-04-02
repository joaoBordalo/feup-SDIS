package main;


import java.io.IOException;



import comSystem.*;
import Threads.*;

public class Peer {
	
	 private MSock MC, MDB, MDR;
	 private ChannelThread MCthread, MDBthread, MDRthread;
	 
	 
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

	public ChannelThread getMCthread() {
		return MCthread;
	}

	public void setMCthread(ChannelThread mCthread) {
		MCthread = mCthread;
	}

	public ChannelThread getMDBthread() {
		return MDBthread;
	}

	public void setMDBthread(ChannelThread mDBthread) {
		MDBthread = mDBthread;
	}

	public ChannelThread getMDRthread() {
		return MDRthread;
	}

	public void setMDRthread(ChannelThread mDRthread) {
		MDRthread = mDRthread;
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
		
		MCthread= new ChannelThread(this, "MC");
		MCthread.start();
		MDBthread= new ChannelThread(this, "MDB");
		MDBthread.start();
		MDRthread= new ChannelThread(this, "MDR");
		MDRthread.start();
		 
	}
	


	public static void main(String[] args) throws NumberFormatException, IOException
	{
		if(args.length<6)
		{
			System.out.println("wrong number of arguments");
			System.out.println("usage-> java peer <MCadd> <MCport> <MDBadd> <MDBport> <MDRadd> <MDBport>");
		}
		else
		{
			 Peer peer=new Peer();
			 
			 peer.init(args);
		}
			
	}

}
