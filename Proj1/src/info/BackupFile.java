package info;

public class BackupFile {
	
	private String fileName;
	private int replicationDegree;
	private float fileSize;
	private boolean backuped;
	
	public BackupFile(String fileName, int replicationDegree, float fileSize)
	{
		this.fileName=fileName;
		this.replicationDegree=replicationDegree;
		this.fileSize=fileSize;
		this.backuped = false;
	}
	public BackupFile(String fileName, int replicationDegree, float fileSize,boolean backuped)
	{
		this.fileName=fileName;
		this.replicationDegree=replicationDegree;
		this.fileSize=fileSize;
		this.backuped = backuped;
	}
	
	@Override
	public String toString()
	{
		return fileName + " " + replicationDegree + " " + fileSize + " " + backuped;
		
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

}
