package commands;

public class Chunk {
	
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

	public Chunk(String ver, int fId, int chunkN, String body )
	{
		message = new Header("CHUNK", ver, fId, chunkN).getHeaderMsgS() + "\n\r" + body;
		
	}

}
