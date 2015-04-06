package threads;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import main.PeerID;
import comSystem.MSock;
import comSystem.PacketInfo;

public class ReceiveStoredThread implements Runnable{

	private MSock MC;
	private int repDegree;
	private Vector<PeerID> Answered;
	private int actualDegree;
	private int windowTime;


	public ReceiveStoredThread(MSock MC, int repDegree, int windowTime) {
		this.MC=MC;
		this.repDegree=repDegree;
		this.setActualDegree(0);
		this.windowTime=windowTime;
	}
	@Override
	public void run()
	{
		for (long stop=System.nanoTime()+TimeUnit.MILLISECONDS.toNanos(getWindowTime()); stop>System.nanoTime();)
		{
			try {
				PacketInfo receivedStore = getMC().receive();

				String[] msg = receivedStore.getMessage().split(" ");

				PeerID peer=new PeerID(receivedStore.getSenderIp(), receivedStore.getSenderPort());
				if(msg[0]=="STORED" && !peerExists(peer))
				{
					Answered.add(peer);
					setActualDegree(getActualDegree() + 1);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public MSock getMC() {
		return MC;
	}

	public void setMC(MSock mC) {
		MC = mC;
	}

	public int getWindowTime() {
		return windowTime;
	}

	public void setWindowTime(int windowTime) {
		this.windowTime = windowTime;
	}

	public boolean peerExists(PeerID id)
	{

		for(int i=0;i<getAnswered().size();i++)
		{
			if (id.equals(getAnswered().get(i)))
				return true;
		}
		return false;
	}

	public Vector<PeerID> getAnswered() {
		return Answered;
	}

	public void setAnswered(Vector<PeerID> answered) {
		Answered = answered;
	}

	public int getActualDegree() {
		return actualDegree;
	}

	public void setActualDegree(int actualDegree) {
		this.actualDegree = actualDegree;
	}

	public int getRepDegree() {
		return repDegree;
	}

	public void setRepDegree(int repDegree) {
		this.repDegree = repDegree;
	}

}
