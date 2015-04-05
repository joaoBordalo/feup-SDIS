package protocols;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import main.Peer;
import main.Utilities;
import commands.PutChunk;

public class Backup {

	private String fileName;
	private String pathFile;
	private int repliDegree;
	private Vector<byte[]> chunks;
	private int nchunks;
	private Peer peer;
	private String fileID;

	public Backup (Peer peer,String fileName, int repliDegree, String pathFile) throws IOException, URISyntaxException
	{
		this.peer=peer;
		this.fileName=fileName;
		this.repliDegree=repliDegree;
		this.pathFile=pathFile;
		fileID= new String();

		BufferedInputStream file = new BufferedInputStream(new FileInputStream(this.pathFile));
		System.out.println(pathFile);

		int length=64*1000;
		byte [] chunk = new byte [length];
		//nchunks=  (int) Math.ceil((file)/length))+1;
		System.out.println("nchunks" + nchunks);
		chunks=new Vector<byte[]>();
		int it =0;

		while(file.read(chunk,0,length)!=-1)
		{
			
			System.out.println(it);
			chunks.add(chunk);
			System.out.println(chunks.get(it));
			it++;
		}
		nchunks=it;
		file.close();
	}

	public void run() throws IOException, NoSuchAlgorithmException
	{
		for(int j = 0; j < nchunks; j++)
		{
			//prepare PUTCHUNK
			PutChunk chunkToSend = new PutChunk("1.0", getFileID(), j, repliDegree, new String(chunks.get(j)));
			int timeToCheck=500;
			
			System.out.println("chunk to send size:" + chunkToSend.getMessageS().length());
			//send message
			for(int i=1; i<6; i++)
			{
				int repCounter=0;
				peer.getMDB().send(chunkToSend.getMessageS());
				for (long stop=System.nanoTime()+TimeUnit.MILLISECONDS.toNanos(timeToCheck); stop>System.nanoTime();) 
				{	System.out.println("counting STOREDS");
					String[] receivedMsg = peer.getMC().receive().getMessage().trim().split(" ");
					
					if(receivedMsg[0]=="STORED")
					{
						System.out.println("increasing repcounter");
						repCounter++;
					}
				}
				if(repCounter>=repliDegree)
				{
					System.out.println("got all the STORED needed");
					break;
				}
				else
				{
					System.out.println("increasing time");
					timeToCheck=2*timeToCheck;
				}
				
			}
		}
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getRepliDegree() {
		return repliDegree;
	}

	public void setRepliDegree(int repliDegree) {
		this.repliDegree = repliDegree;
	}


	public Vector<byte[]> getChunks() {
		return chunks;
	}

	public void setChunks(Vector<byte[]> chunks) {
		this.chunks = chunks;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public Peer getPeer() {
		return peer;
	}

	public void setPeer(Peer peer) {
		this.peer = peer;
	}

	public String getFileID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String text= new String (fileName+dateFormat.format(date));
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes("UTF-8"));
		
		fileID=Utilities.byteArrayToString(hash);
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	/*
	 * copyright from:
	 *  http://stackoverflow.com/questions/1040868/java-syntax-and-meaning-behind-b1ef9157-binary-address
	 */
	


}
