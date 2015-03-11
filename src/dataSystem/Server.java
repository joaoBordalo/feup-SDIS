package dataSystem;
import java.util.Scanner;
import java.util.Vector;
import java.io.IOException;
import java.net.*;

import dataSystem.Parser.Request;


public class Server {
	
	private int nPlates;
	private Vector<Owner> owners;
	private int portNumber;
	private DatagramSocket socket;
	private int bufferMAXSize;
	
	
	public Server(int portNumber) throws SocketException{
		this.portNumber=portNumber;
		nPlates=0;
		socket = new DatagramSocket(portNumber);
		bufferMAXSize=512;
		//abrir socket
	}
	public int getBufferMAXSize() {
		return bufferMAXSize;
	}
	public void setBufferMAXSize(int bufferMAXSize) {
		this.bufferMAXSize = bufferMAXSize;
	}
	public int getnPlates() {
		return nPlates;
	}
	public void setnPlates(int nPlates) {
		this.nPlates = nPlates;
	}
	public Vector<Owner> getOwners() {
		return owners;
	}
	public void setOwners(Vector<Owner> owners) {
		this.owners = owners;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public DatagramSocket getSocket() {
		return socket;
	}
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public boolean validatePlate(String plate)
	{	
		int maxLength=8;
		int fieldLetter=0;
		int fieldNumber=0;
		
		if(plate.length()==maxLength)
		{
			
			if(plate.charAt(2)=='-'&& plate.charAt(5)=='-')
			{
				for(int i= 0; i < maxLength; i=i+3)
				{
					if('A'<=plate.charAt(i) && 'Z'>=plate.charAt(i))
					{// for first char of the field
						
						if('A'<=plate.charAt(i+1) && 'Z'>=plate.charAt(i+1))
						{//for second char of the field
							fieldLetter++;
						}
					}
					else if('0'<=plate.charAt(i) && '9'>=plate.charAt(i))
					{
						if('0'<=plate.charAt(i+1) && '9'>=plate.charAt(i+1))
						{
							fieldNumber++;
						}		
					}
				}
				
				if(fieldLetter == 1 && fieldNumber==2)
				{
					System.out.println("Valid Plate!");
					return true;
				}
				else
				{
					System.out.println("ups!");
					return false;
				}
			}
			else{
				System.out.println("Wrong sintax!!");
				return false;
			}
		}else{
			System.out.println("Wrong plate length");
			return false;
		}	
	}
	
	//assuming that parse has been done 
	public int register(String plate, String owner)
	{
		if(!findOwner(owner))
		{
		Plate newPlate= new Plate(plate);
		Owner newOwner= new Owner(owner,newPlate);
		owners.add(newOwner);// same as push_back in c++
		return nPlates++;
		}else
			return -1;
	}
	
	private boolean findOwner(String owner) {
		
		for (Owner ownerIt : owners) {
			if(ownerIt.getName()==owner)
				return true;
		}
		return false;
	}
	
	public String lookUp(String plate)
	{
		
		for (Owner owner : owners) {
			if(owner.getPlate().getPlate()==plate)
			{
				return owner.getName();
			}
		}
		return "NOT_FOUND";
	}
	
	public void main(String[] args) throws NumberFormatException, IOException{
		
		if (args.length<4)
		{
			System.out.println("invalid usage. try: java Server <port_number>  ");
			return;
		}
		// TODO verificar argumentos incluindo se no args[2] é um int
		
			Server server = new Server(Integer.parseInt(args[2].toString()));
			byte[] receiveBuffer = new byte[server.getBufferMAXSize()];
			DatagramPacket rPacket = new DatagramPacket(receiveBuffer, server.getBufferMAXSize());
			
			System.out.println("Waiting for Client");
			server.getSocket().receive(rPacket);
			// waiting for client anwser
			while(true)
			{
				Scanner a = new Scanner(System.in);
				String input = a.nextLine();
				//to close aplication
				if(input.equals("q")|| input.equals("Q"))
				{
					//closing socket
					a.close();
					System.out.println("Exiting");
					break;
				}
				
				
				
				//processing Client's anwser and do the parse
				String request= new String(rPacket.getData());
				
				Parser parsed= new Parser(request);
				String plate = parsed.getOperands().get(0);
				System.out.println(parsed.getOperator().toString());
				
				
				String msg= new String(parsed.getOperator().toString());
				
				for(int i = 0; i< parsed.getOperands().size(); i++)
				{
					msg+= " " + parsed.getOperands().get(i);
				}
				
				msg+= ": ";
				
				if(server.validatePlate(plate))
				{
					
					switch (parsed.getOperator())
					{
					case REGISTER:
						String owner = parsed.getOperands().get(1);
						int numberRegisted=register(plate, owner);
						msg+= numberRegisted;
						break;
					case LOOKUP:
						String Owner =lookUp(plate);
						msg+= Owner;
						default:
							msg+= "ERROR";
							break;
					}
					
				}
				
						DatagramPacket sPacket = new DatagramPacket(msg.getBytes(), server.getBufferMAXSize());
						socket.send(sPacket);

				a.close();
				
			}	
			
			server.getSocket().close();
			return;
			
	
}

}
