package protocols;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import main.Peer;
import commands.PutChunk;

public class Backup {

	private String fileName;
	private String pathFile;
	private int repliDegree;
	private byte[][] chunks;
	private int nchunks;
	private Peer peer;
	private String fileID;

	public Backup (Peer peer,String fileName, int repliDegree, String pathFile) throws IOException, URISyntaxException
	{
		this.fileName=fileName;
		this.repliDegree=repliDegree;
		this.pathFile=pathFile;
		fileID= new String();

		RandomAccessFile file = new RandomAccessFile(pathFile, "r");

		int length=64*1024;
		byte [] chunk = new byte [length];
		nchunks=  (int) Math.ceil((file.length()/length))+1;
		chunks=new byte[nchunks][length];
		int it =0;

		while(file.read(chunk, it*length, length)!=-1)
		{
			chunks[it]=chunk;
			it++;
		}
	}

	public void run() throws IOException, NoSuchAlgorithmException
	{
		for(int j = 0; j < nchunks; j++)
		{
			//prepare PUTCHUNK
			PutChunk chunkToSend = new PutChunk("1.0", getFileID(), j, repliDegree, chunks[j].toString());
			int timeToCheck=500;
			//send message
			for(int i=1; i<6; i++)
			{
				int repCounter=0;
				peer.getMDB().send(chunkToSend.getMessageS());
				for (long stop=System.nanoTime()+TimeUnit.MILLISECONDS.toNanos(timeToCheck); stop>System.nanoTime();) 
				{
					String[] receivedMsg = peer.getMC().receive().getMessage().split(" ");
					if(receivedMsg[0]=="STORED")
					{
						repCounter++;
					}
				}
				if(repCounter>=repliDegree)
				{
					break;
				}
				else
				{
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


	public byte[][] getChunks() {
		return chunks;
	}

	public void setChunks(byte[][] chunks) {
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
		
		fileID=hash.toString();
		
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}


}
