package main;

import java.net.InetAddress;

public class PeerID {

	private InetAddress ip;
	private int port;

	public PeerID(InetAddress ip, int port)
	{
		this.ip=ip;
		this.port=port;
	}

	public boolean equals(PeerID peer)
	{
		if(this.ip.equals(peer.getIp()) && this.port==peer.getPort())
		{
			return true;
		}
		else 
			return false;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}



}
