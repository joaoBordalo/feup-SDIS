package comSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;



public class MSock {
	
	private DatagramSocket sendSocket;
	private MulticastSocket receiveSocket;
	
	private InetAddress mAddress;
	private int mPort;
	
	
	public MSock(String mAddr, int mPrt ) throws IOException
	{
		
		mAddress= InetAddress.getByName(mAddr);
		mPort= mPrt;
		
		sendSocket= new DatagramSocket();
		
		
		
		receiveSocket= new MulticastSocket(mPort);
		receiveSocket.joinGroup(mAddress);
	
	};
	
	public void send(String msg) throws IOException
	{
		
		DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
				msg.getBytes().length, mAddress, mPort);
		
		sendSocket.send(msgPacket);
		
		System.out.println("enviei:" +msg);
	}
	
	public String receive() throws IOException
	{
		
        byte[] buf = new byte[1024];

		DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
        receiveSocket.receive(msgPacket);

        String msg = new String(buf, 0, buf.length);
        
        System.out.println("recebi: " + msg);
        return msg;
	}
	
	
}
