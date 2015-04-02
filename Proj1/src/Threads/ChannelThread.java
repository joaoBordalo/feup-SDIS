package Threads;

import java.io.IOException;

import main.*;

public class ChannelThread extends Thread{


	private Thread t;
	private String threadName;
	private Peer peer;


	public ChannelThread(Peer peer, String name){
		this.peer=peer;
		threadName = name;
		System.out.println("Creating " +  threadName );
	}
	
	
	public void run() {
		System.out.println("Running " +  threadName );
		
		if(threadName=="MC")
		{
			while(true)
			{
				try {
					String CommandToParse= peer.getMC().receive();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//tratar da string CommandToParse
			}
		}
		
		if(threadName=="MDB")
		{
			while(true)
			{
				try {
					String BackupChunkToParse= peer.getMDB().receive();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//tratar da string BackupChunkToParse
			}
		}
		
		if(threadName=="MDR")
		{
			while(true)
			{
				try {
					String RecoverChunkToParse= peer.getMDR().receive();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//tratar da string RecoverChunkToParse
			}
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	
	
	public void start ()
	{
		System.out.println("Starting " +  threadName );
		if (t == null)
		{
			t = new Thread (this, threadName);
			t.start ();
		}
	}

}
