package protocols;

import java.io.File;
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

		//BufferedInputStream file = new BufferedInputStream(new FileInputStream(this.pathFile));
		File f= new File(this.pathFile);
		RandomAccessFile file = new RandomAccessFile(f, "r");
		System.out.println(pathFile);

		int length=64*1000;
		byte [] chunk = new byte [length];
		//nchunks=  (int) Math.ceil((file)/length))+1;
		System.out.println("nchunks" + nchunks);
		chunks=new Vector<byte[]>();
		int it =0;

		
		while(file.read(chunk)!=-1)
		{
			file.seek(length*(it+1));
			System.out.println(it);
		//	System.out.println("chunks vector:" + chunks.size());
			chunks.add(chunk);
			//System.out.println("chunks vector at " +it + chunks.get(it).toString());
			
			it++;
		}
		nchunks=it ;
		file.close();
	}

	public void run(Vector<byte[]> chunksToSend ) throws IOException, NoSuchAlgorithmException
	{
		for(int j = 0; j < nchunks; j++)
		{
			System.out.println("chunktosend size" + chunksToSend.get(j).length);
			//System.out.println("-------------------------");
			//prepare PUTCHUNK
			PutChunk chunkToSend = new PutChunk("1.0", getFileID(), j, repliDegree, new String(chunksToSend.get(j)));
			int timeToCheck=500;
			
			//System.out.println("chunk data to send :" + chunkToSend.getMessageS());
			//send message
			//for(int i=1; i<6; i++)
			//{
				int repCounter=0;
				peer.getMDBthread().start();
				peer.getMDB().send(chunkToSend.getMessageS());
				
				
				
				//String[] receivedMsg = peer.getMC().receive().getMessage().trim().split(" ");
				
				/*for (long stop=System.nanoTime()+TimeUnit.MILLISECONDS.toNanos(timeToCheck); stop>System.nanoTime();) 
				{	System.out.println("counting STOREDS");
					
					
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
				}*/
				
			//}
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
	
	


}
