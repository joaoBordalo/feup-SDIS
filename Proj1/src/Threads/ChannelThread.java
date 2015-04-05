package threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
					String commandToParse= peer.getMC().receive().getMessage();
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
					String BackupChunkToParse= peer.getMDB().receive().getMessage();
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
					String RecoverChunkToParse= peer.getMDR().receive().getMessage();
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

	public void parseMessage(String msg) throws UnsupportedEncodingException
	{
		//System.out.println("msg to parse: "+ msg);
		
		 int headerEnd = msg.indexOf("\r\n");
		//int bodyStart = msg.lastIndexOf("\r\n");
		 
		 String subs = msg.substring(0,headerEnd).trim();
		 String subsbody = msg.substring(headerEnd+1).trim();
		 //System.out.println("header end: "+ headerEnd);
		 //System.out.println("body start: "+ (headerEnd+1));
		 
		// System.out.println("subs: "+ subs);
		 //System.out.println("subsbody: "+ subsbody);
		 
		 String[] tokens = subs.split(" ");
		 
		 
		
		/*for(int i=0; i<tokens.length;i++)
		{
			if(i==tokens.length-1)
			{
				System.out.println("last token " + i+ ": "+ tokens[i]);
			}else
			{
		System.out.println("splited tokens " + i+ ": "+ tokens[i]);
			}
		}*/
		
		switch (tokens[0]) {
		case "PUTCHUNK":
			SaveChunk(tokens, subsbody);

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

	public void SaveChunk(String [] chunkData, String Body)
	{
		File newFile = new File(chunkData[2]+"."+ chunkData[3]); //file id no [2] e chunkno no [3]
		RandomAccessFile out;
		
		
		File chunkFile = new File("chunkfile.txt");
		
		try {
			out = new RandomAccessFile(newFile,"rw");
			//out.writeBytes(Utilities.byteArrayToString(Body.getBytes()));
			out.writeBytes(Body);
			out.close();
			if(!chunkFile.exists())
			{
				chunkFile.createNewFile();
			
			}
			
			Path chunkfilepath = Paths.get("chunkfile.txt");
			try (BufferedWriter writer = Files.newBufferedWriter(chunkfilepath, StandardOpenOption.APPEND)) {
	            writer.write(chunkData[2]+"."+ chunkData[3] + " " + chunkData[4] + " 0 \n");
	            writer.close();
	        } catch (IOException e) {
	            System.err.println(e);
	        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*try {
			
			sleep(new Random().nextInt(401));
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		//new Stored(chunkData[1], Integer.parseInt(chunkData[2]), Integer.parseInt(chunkData[3]));

		try {

			peer.getMC().send(new Stored(chunkData[1], chunkData[2], 
					Integer.parseInt(chunkData[3])).getMessageS());

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
