package commands;

import java.io.UnsupportedEncodingException;

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

	public PutChunk(String ver, String fId, int chunkN, int repDegree, String body ) throws UnsupportedEncodingException
	{
		//System.out.println("body byte: " +body);
		
		//System.out.println("body string: " + new String(body));
		message = new Header("PUTCHUNK", ver, fId, chunkN, repDegree).getHeaderMsgS()+ body;
		
	}

}
