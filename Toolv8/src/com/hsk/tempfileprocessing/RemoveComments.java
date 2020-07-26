package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveComments {
	
	private static String mPath;
	private static String fPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	
	public void removeComments() throws IOException {
//		List<String> paths = new ArrayList<String>();
//    	FileDetailsStorage fds3 = new FileDetailsStorage();
//    	paths = fds3.returnPaths();
//    	String mPath = null;
//    	
//    	for(int i=0;i<paths.size();) {
//    		i++;
//    		mPath = paths.get(i);
//    		break;		
//    	}
    	String tempFileName = mPath+"\\OriginalTempCopy.java";
		
		Cleaner cleaner = new Cleaner();
		String line = cleaner.readFile();
		String newLine = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
		//System.out.println(newLine);
		FileWriter fw = new FileWriter(tempFileName);
		fw.write(newLine);
		fw.close();
		
		System.out.println("Temp File generated successfully!!");
		
		
		
		
		
		
//		FileReader source = new FileReader("src/Temp.java");
//		BufferedReader br = new BufferedReader(source);
//		FileWriter targetFile = new FileWriter("src/TempC.java");
//		BufferedWriter bw = new BufferedWriter(targetFile);
//		
//		String line;
//		line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
//		//String multiLine = "/\*.*\n.*\*\/";
//		
//		while((line = br.readLine()) != null) {
//			line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
//			
//			
//			bw.write(line);
//			bw.newLine();
//			
//		}
//		
//		bw.close();
//		br.close();
//		targetFile.close();
//		source.close();
//	}
	}

}
