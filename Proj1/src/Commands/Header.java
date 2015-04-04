package commands;

public class Header{
	
	//private String messageType, version;
	//private int fileId, chunkNo, replicationDegree;
	private String headerMsg;
	
	
	public Header(String mType, String ver, String fId, int chunkN, int repDegree)
	{
		headerMsg = mType + " "  + ver + " "  + fId + " " + chunkN + " " + repDegree + " "  + "\n\r";
	}
	
	public Header(String mType, String ver, String fId, int chunkN)
	{
		headerMsg = mType+ " "  + ver + " "  + fId + " " + chunkN + " " + "\n\r";
	}
	
	public Header(String mType, String ver, String fId)
	{
		headerMsg = mType + " "  + ver + " "  + fId + " " + "\n\r";
	}


	public byte[] getHeaderMsgB() {
		return headerMsg.getBytes();
	}
	
	public String getHeaderMsgS() {
		return headerMsg;
	}


	public void setHeaderMsg(String headerMsg) {
		this.headerMsg = headerMsg;
	}
	

}
