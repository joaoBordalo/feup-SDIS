package threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import commands.Stored;
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
					String commandToParse= peer.getMC().receive();//nao sei se isto vai devolver em bytes e nao faz conversao implicita
					parseMessage(commandToParse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}

		if(threadName=="MDB")
		{
			while(true)
			{
				try {
					String BackupChunkToParse= peer.getMDB().receive();
					parseMessage(BackupChunkToParse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}

		if(threadName=="MDR")
		{
			while(true)
			{
				try {
					String RecoverChunkToParse= peer.getMDR().receive();
					parseMessage(RecoverChunkToParse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


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

	public void parseMessage(String msg)
	{
		String[] tokens = msg.split(" ");

		switch (tokens[0]) {
		case "PUTCHUNK":
			SaveChunk(tokens);

			break;
		case "STORED":

			break;
		case "GETCHUNK":

			break;
		case "CHUNK":

			break;
		case "DELETE":

			break;

		case "REMOVED":

			break;

		default:
			break;
		}
	}

	public void SaveChunk(String [] chunkData)
	{
		File newFile = new File(chunkData[2]+"."+ chunkData[3]); //file id no [2] e chunkno no [3]
		FileOutputStream out;

		try {
			out = new FileOutputStream(newFile);
			out.write(chunkData[chunkData.length].getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			
			sleep(new Random().nextInt(401));
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new Stored(chunkData[1], Integer.parseInt(chunkData[2]), Integer.parseInt(chunkData[3]));

		try {

			peer.getMC().send(new Stored(chunkData[1], Integer.parseInt(chunkData[2]), 
					Integer.parseInt(chunkData[3])).getMessageS());

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



}
