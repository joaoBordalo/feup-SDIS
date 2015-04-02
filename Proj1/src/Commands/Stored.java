package commands;

public class Stored {
	
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

	public Stored(String ver, int fId, int chunkN)
	{
		message = new Header("STORED", ver, fId, chunkN).getHeaderMsgS() + "\n\r";
		
	}

}
