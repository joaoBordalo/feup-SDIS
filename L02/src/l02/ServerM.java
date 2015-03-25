package l02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerM {

	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;

	
	//usage java Server <srvc_port> <mcast_addr> <mcast_port> 
	public static void main(String[] args) throws UnknownHostException,
			InterruptedException {
		// Get the address that we are going to connect to.
		InetAddress addr = InetAddress.getByName(args[1]);

		// Open a new DatagramSocket, which will be used to send the data.
		try (DatagramSocket serverSocket = new DatagramSocket()) {
			
			byte[] buf = new byte[256];
			 while (true) {
				String msg = args[0];

				// Create a packet that will contain the data
				// (in the form of bytes) and send it.
				DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
						msg.getBytes().length, addr, Integer.parseInt(args[2]));
				
				serverSocket.send(msgPacket);

				System.out.println("sent service port " + msg);
				Thread.sleep(500);
				
				
		             // Receive the information and print it.
		             msgPacket = new DatagramPacket(buf, buf.length);
		             serverSocket.receive(msgPacket);

		             msg = new String(buf, 0, buf.length);
		             System.out.println("recebi: " + msg);
		         }
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		
	}
}