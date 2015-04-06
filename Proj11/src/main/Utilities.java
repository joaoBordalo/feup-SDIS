package main;

import java.io.File;

public class Utilities {
	
	/*
	 * copyright from:
	 *  http://stackoverflow.com/questions/1040868/java-syntax-and-meaning-behind-b1ef9157-binary-address
	 */
	public static String byteArrayToString(byte[] in) {
	    char out[] = new char[in.length * 2];
	    for (int i = 0; i < in.length; i++) {
	        out[i * 2] = "0123456789ABCDEF".charAt((in[i] >> 4) & 15);
	        out[i * 2 + 1] = "0123456789ABCDEF".charAt(in[i] & 15);
	    }
	    return new String(out);
	}
	
	public static boolean deleteDirectoryFiles(File inc) {
        if( inc.exists() ) {
            File[] files = inc.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectoryFiles(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( inc.delete() );
    }

}
