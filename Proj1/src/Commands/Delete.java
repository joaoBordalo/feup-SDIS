package commands;

public class Delete {
	
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

	public Delete(String ver, int fId, int chunkN, String body )
	{
		message = new Header("DELETE", ver, fId).getHeaderMsgS() + "\n\r";
		
	}


}
