package dataSystem;
import java.util.Scanner;
import java.util.Vector;
import java.io.IOException;
import java.net.*;


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
	
	public int register(char[] plate, String onwer)
	{
		return 0;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		if (args.length<4)
		{
			System.out.println("invalid usage. try: java Server <port_number>  ");
		}
		// TODO verificar argumentos incluindo se no args[2] é um int
		else
		{
			Server server = new Server(Integer.parseInt(args[2].toString()));
			
			// entrar em loop à espera de resposta do cliente
			while(true)
			{
				Scanner a = new Scanner(System.in);
				String input = a.nextLine();
				//to close aplication
				if(input.equals("q")|| input.equals("Q"))
				{
					//closing socket
					a.close();
					server.getSocket().close();
					System.out.println("Existing");
					break;
				}
				
				byte[] receiveBuffer = new byte[server.getBufferMAXSize()];
				DatagramPacket packet = new DatagramPacket(receiveBuffer, server.getBufferMAXSize());
				
				System.out.println("Waiting for Client");
				server.getSocket().receive(packet);
				
				//processing Client's anwser
				
				String plate = null;
				if(server.validatePlate(plate))
				{
					Plate rightPlate = new Plate(plate);
				}
				
				//processar conteudo do cliente
				
				a.close();
			}
			
		
			
		 
		
		
		
		
	}
}

}
