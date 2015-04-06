package info;

public class BackupFile {
	
	private String fileName;
	private int replicationDegree;
	private float fileSize;
	private boolean backuped;
	private int numberChunks;
	private String fileId;
	

	public BackupFile(String fileName, int replicationDegree, int numberChunks,float fileSize,String fileId)
	{
		this.fileName=fileName;
		this.replicationDegree=replicationDegree;
		this.numberChunks=numberChunks;
		this.fileSize=fileSize;
		this.backuped = false;
		this.fileId=fileId;
	}
	public BackupFile(String fileName, int replicationDegree, int numberChunks, float fileSize,boolean backuped,String fileId)
	{
		this.fileName=fileName;
		this.replicationDegree=replicationDegree;
		this.numberChunks=numberChunks;
		this.fileSize=fileSize;
		this.backuped = backuped;
		this.fileId=fileId;
	}
	
	@Override
	public String toString()
	{
		return fileName + " " + replicationDegree + " " + numberChunks + " " + fileSize + " " + backuped + " " + fileId;
		
	}
	
	public void backupComplete()
	{
		backuped=true;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getReplicationDegree() {
		return replicationDegree;
	}
	public void setReplicationDegree(int replicationDegree) {
		this.replicationDegree = replicationDegree;
	}
	public float getFileSize() {
		return fileSize;
	}
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}
	public boolean isBackuped() {
		return backuped;
	}
	public void setBackuped(boolean backuped) {
		this.backuped = backuped;
	}
	public int getNumberChunks() {
		return numberChunks;
	}
	public void setNumberChunks(int numberChunks) {
		this.numberChunks = numberChunks;
	}

	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
