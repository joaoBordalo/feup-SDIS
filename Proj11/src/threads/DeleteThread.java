package threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import main.Utilities;
import comSystem.MSock;

public class DeleteThread implements Runnable{
	
	private MSock MC;
	
	public DeleteThread(MSock MC)
	{
		this.MC =MC; 
	}
	
	@Override
	public void run() {
		System.out.println("running delete thread");
		while(true)
		{
			try {
				String command = MC.receive().getMessage();
				
				String[] tokens= command.split(" ");
				
				
				switch (tokens[0]) {
				case "DELETE":
					String path = new File(tokens[2]).getAbsolutePath();
					//System.out.println(path);
					Utilities.deleteDirectoryFiles(new File(path));
					break;

				
				default:
					break;
				}
				
				
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
