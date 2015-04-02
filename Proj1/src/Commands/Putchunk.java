package commands;

public class PutChunk {
	
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

	public PutChunk(String ver, int fId, int chunkN, int repDegree, String body )
	{
		message = new Header("PUTCHUNK", ver, fId, chunkN, repDegree).getHeaderMsgS() + "\n\r" + body;
		
	}

}
