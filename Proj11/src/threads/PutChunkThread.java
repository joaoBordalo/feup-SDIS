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



import main.Utilities;
import comSystem.MSock;
import comSystem.PacketInfo;
import commands.Stored;


// objective: listens the MDB or MDR channels to get chunks
public class PutChunkThread implements Runnable{



	private MSock MDB;
	private MSock MC;


	public PutChunkThread(MSock MDB, MSock MC){

		this.MDB=MDB;
		this.MC=MC;
	}

	@Override
	public void run() {

		try {
			while(true)
			{
				PacketInfo message = MDB.receive();
			/*	if(!message.getSenderIp().getHostAddress().equals(InetAddress.getLocalHost().getHostAddress()))
				{
					
				}*/
				parseMessage(message.getMessage());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void parseMessage(String msg) throws UnsupportedEncodingException
	{
		//System.out.println("msg to parse: "+ msg);

		int headerEnd = msg.indexOf("\r\n");
		//int bodyStart = msg.lastIndexOf("\r\n");

		String subs = msg.substring(0,headerEnd);
		String subsbody = msg.substring(headerEnd+4);
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

		
		default:
			break;
		}
	}

	public void SaveChunk(String [] msgHeader, String Body)
	{
		File dir= new File(msgHeader[2]);
		
		if(!dir.exists())
		{
			dir.mkdir();
		}
		File newFile = new File(msgHeader[2]+ '\\' +msgHeader[2]+"."+ msgHeader[3]); //file id no [2] e chunkno no [3]
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
				writer.write(msgHeader[2]+"."+ msgHeader[3] + " " + msgHeader[4] + " 0 \n");
				writer.close();
			} catch (IOException e) {
				System.err.println(e);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stored storedToSend = new Stored("1.0", msgHeader[2], Integer.parseInt(msgHeader[3]));
		try {
			MC.send(storedToSend.getMessageS());
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

		/*try {

			peer.getMC().send(new Stored(chunkData[1], chunkData[2], 
					Integer.parseInt(chunkData[3])).getMessageS());

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}



}
