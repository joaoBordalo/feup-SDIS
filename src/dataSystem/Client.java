package dataSystem;
import java.io.IOException;
import java.net.*;




public class Client {
	
	private String host_name;
	private int port_number;
	private int bufferMAXSize=512;
	
	
	public int getBufferMAXSize(){
		return bufferMAXSize;
	}
	public String getHost_name() {
		return host_name;
	}



	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}



	public int getPort_number() {
		return port_number;
	}



	public void setPort_number(int port_number) {
		this.port_number = port_number;
	}


public static void main(String[] args) throws IOException
	{
		if (args.length<5)
		{
			System.out.println("invalid usage. try: java Client <host_name> <port_number> <oper> <opnd>* ");
			return;
		}
		
			Client client = new Client();
			
			client.setHost_name(args[0]);
			client.setPort_number(Integer.parseInt(args[1].toString()));
			
		 String[] temp = new String[args.length-3];
		 
		for(int i=0; i<temp.length; i++)
		{
			temp[i]=args[3+i].toString();
		}
		
		System.out.print("Client Debug\n");
		
		System.out.print("Host Name:");
		System.out.println(client.getHost_name());
		
		System.out.print("Port Number:");
		System.out.println(client.getPort_number());
		
		System.out.print("Operation:");
		
		System.out.print("Operation Args:");
		System.out.print("\n");
		
		
		DatagramSocket socket = new DatagramSocket();
		byte[] sentBuffer = new byte[client.getBufferMAXSize()];
		String msg;
		msg = new String(args[4]);
		for(int i=5;i<args.length;i++)
		{
			msg += " " + args[i];
		}
		sentBuffer = msg.getBytes();
		InetAddress address = InetAddress.getByName(client.getHost_name());
		DatagramPacket packet = new DatagramPacket(sentBuffer, client.getBufferMAXSize(),address,client.getPort_number());
		socket.send(packet);
		
		
		//getting anwser
		
		byte[] rbuf = new byte[client.getBufferMAXSize()];
		packet = new DatagramPacket(rbuf, client.getBufferMAXSize());
		socket.receive(packet);

		String received = new String(packet.getData());
		System.out.println("Messaged recieved: " + received);
		socket.close();
		return;
	}



}
