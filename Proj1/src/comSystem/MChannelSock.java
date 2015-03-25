package comSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MChannelSock {
	
	private DatagramSocket sendSocket;
	private MulticastSocket receiveSocket;
	
	private InetAddress mAddress;
	private int mPort;
	
	
	public MChannelSock(String mAddr, int mPrt ) throws IOException
	{
		
		mAddress= InetAddress.getByName(mAddr);
		mPort= mPrt;
		
		sendSocket= new DatagramSocket();
		
		
		
		receiveSocket= new MulticastSocket(mPort);
		receiveSocket.joinGroup(mAddress);
	
	};
	
	void sendMc(String msg) throws IOException
	{
		
		DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
				msg.getBytes().length, mAddress, mPort);
		
		sendSocket.send(msgPacket);
		
		System.out.println("enviei:" +msg);
	}
	
	void receiveMc() throws IOException
	{
		
        byte[] buf = new byte[256];

		DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
        receiveSocket.receive(msgPacket);

        String msg = new String(buf, 0, buf.length);
        
        System.out.println("recebi: " + msg);
	}
	
	
	public static void main(String[] args) throws UnknownHostException,
	InterruptedException {
	}
	
	
	
	

}
