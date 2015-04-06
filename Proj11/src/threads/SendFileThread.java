package threads;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import comSystem.MSock;
import commands.PutChunk;
import protocols.Backup;

public class SendFileThread implements Runnable{

	private Backup bc;
	private MSock MDB;
	private MSock MC;
	private int windowTime;
	private String body;
	private int i;

	public SendFileThread(Backup bc, MSock MDB, MSock MC, String body, int i)
	{
		this.bc=bc;
		this.MDB=MDB;
		this.windowTime=500;
		this.body=body;
		this.i=i;
	}
	@Override
	public void run() {

		//for(int i=0; i<bc.getNchunks();i++)
		{
			
			{


				//String body= new String(bc.getChunks().get(i));
				String chunktosend;
				try {
					chunktosend = new PutChunk("1.0",bc.getFileID(), i, bc.getRepliDegree(), body).getMessageS();
					//System.out.println(chunktosend);
					MDB.send(chunktosend);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public MSock getMC() {
		return MC;
	}

	public void setMC(MSock mC) {
		MC = mC;
	}

}
