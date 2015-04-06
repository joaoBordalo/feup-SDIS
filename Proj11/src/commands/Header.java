package commands;

public class Header{
	
	//private String messageType, version;
	//private int fileId, chunkNo, replicationDegree;
	private String headerMsg;
	
	
	public Header(String mType, String ver, String fId, int chunkN, int repDegree)
	{	
		headerMsg = mType + " "  + ver + " "  + fId + " " + chunkN + " " + repDegree + " "  + "\r\n" + "\r\n";
	}
	
	public Header(String mType, String ver, String fId, int chunkN)
	{
		headerMsg = mType+ " "  + ver + " "  + fId + " " + chunkN + " " + "\r\n" + "\r\n";
	}
	
	public Header(String mType, String ver, String fId)
	{
		headerMsg = mType + " "  + ver + " "  + fId + " " + "\r\n" + "\r\n";
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
