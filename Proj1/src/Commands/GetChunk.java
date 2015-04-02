package commands;

public class GetChunk {
	
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

	public GetChunk(String ver, int fId, int chunkN)
	{
		message = new Header("GETCHUNK", ver, fId, chunkN).getHeaderMsgS() + "\n\r";
		
	}

}
