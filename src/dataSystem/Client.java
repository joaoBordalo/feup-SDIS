package dataSystem;
import java.net.*;


public class Client {
	
	private int host_name;
	private int port_number;
	private String oper;
	private String opnd[];
	
	
	
	public int getHost_name() {
		return host_name;
	}



	public void setHost_name(int host_name) {
		this.host_name = host_name;
	}



	public int getPort_number() {
		return port_number;
	}



	public void setPort_number(int port_number) {
		this.port_number = port_number;
	}



	public String getOper() {
		return oper;
	}



	public void setOper(String oper) {
		
		if(oper.equals("Register") || oper.equals("Lookup"))
		{
		this.oper = oper;
		}
		else
		{
			System.out.println("not a valid operator");
		}
	}



	public String[] getOpnd() {
		return opnd;
	}



	public void setOpnd(String[] opnd) {
		this.opnd = opnd;
	}


public static void main(String[] args)
	{
		if (args.length<5)
		{
			System.out.println("invalid usage. try: java Client <host_name> <port_number> <oper> <opnd>* ");
		}
		else
		{
			Client client = new Client();
			
			client.setHost_name(Integer.parseInt(args[0].toString()));
			client.setPort_number(Integer.parseInt(args[1].toString()));
			client.setOper(args[2].toString());
			
		 String[] temp = new String[args.length-3];
		 
		for(int i=0; i<temp.length; i++)
		{
			temp[i]=args[3+i].toString();
		}
	 
		client.setOpnd(temp);
			
		
		
		System.out.print("Client Debug\n");
		
		System.out.print("Host Name:");
		System.out.println(client.getHost_name());
		
		System.out.print("Port Number:");
		System.out.println(client.getPort_number());
		
		System.out.print("Operation:");
		System.out.println(client.getOper());
		
		System.out.print("Operation Args:");
		
		for(int i=0; i<temp.length; i++)
		{
		System.out.print(client.getOpnd()[i]+ " ");
		}
		System.out.print("\n");
		
		}
		
	}



}
