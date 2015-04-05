package info;

public class BackupFile {
	
	private String fileName;
	private int replicationDegree;
	private float fileSize;
	private boolean backuped;
	private int numberChunks;
	
	public BackupFile(String fileName, int replicationDegree, int numberChunks,float fileSize)
	{
		this.fileName=fileName;
		this.replicationDegree=replicationDegree;
		this.numberChunks=numberChunks;
		this.fileSize=fileSize;
		this.backuped = false;
	}
	public BackupFile(String fileName, int replicationDegree, int numberChunks, float fileSize,boolean backuped)
	{
		this.fileName=fileName;
		this.replicationDegree=replicationDegree;
		this.numberChunks=numberChunks;
		this.fileSize=fileSize;
		this.backuped = backuped;
	}
	
	@Override
	public String toString()
	{
		return fileName + " " + replicationDegree + " " + numberChunks + " " + fileSize + " " + backuped + "\n";
		
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

}
