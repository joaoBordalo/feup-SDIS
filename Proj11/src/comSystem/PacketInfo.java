package comSystem;

import java.net.InetAddress;

public class PacketInfo {
	
	private String message;
	private InetAddress senderIp;
	private int senderPort;
	
	public String getMessage() {
		return message.trim();
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InetAddress getSenderIp() {
		return senderIp;
	}

	public void setSenderIp(InetAddress senderIp) {
		this.senderIp = senderIp;
	}

	public int getSenderPort() {
		return senderPort;
	}

	public void setSenderPort(int senderPort) {
		this.senderPort = senderPort;
	}

	public PacketInfo(String message, InetAddress senderIp, int senderPort)
	{
		this.message = message;
		this.senderIp = senderIp;
		this.senderPort = senderPort;
	}

	public PacketInfo() {
		// TODO Auto-generated constructor stub
	}

}
