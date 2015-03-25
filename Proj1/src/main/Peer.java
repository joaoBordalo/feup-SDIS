package main;


import java.io.IOException;

import comSystem.MChannelSock;

public class Peer {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		if(args.length<6)
		{
			System.out.println("wrong number of arguments");
			System.out.println("usage-> java peer <MCadd> <MCport> <MDBadd> <MDBport> <MDRadd> <MDBport>");
		}
		else
		{
			MChannelSock MC = new MChannelSock(args[0], Integer.parseInt(args[1]));
		}
			
	}

}
