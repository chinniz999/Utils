package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWrite {
	
	public void writetoFile(){
		File file=new File("C:\\Users\\user\\Desktop\\A.csv");
		PrintWriter printWriter = null;
		try {
			printWriter=new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		printWriter.write("HAi");
		printWriter.write(",");
		printWriter.write("HEllo");
		printWriter.flush();
		printWriter.close();
	}
	
	public static void main(String[] args) {
		
		FileWrite write=new FileWrite();
		write.writetoFile(); 
		
	}

}
