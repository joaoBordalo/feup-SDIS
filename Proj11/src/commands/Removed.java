package commands;

public class Removed {
	
private String message;
	
	public String getMessageS() {
		return message;
	}
	
	public byte[] getMessageB() {
		return message.getBytes();
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Removed(String ver, String fId, int chunkN)
	{
		message = new Header("REMOVED", ver, fId, chunkN).getHeaderMsgS() + "\n\r";
		
	}

}
