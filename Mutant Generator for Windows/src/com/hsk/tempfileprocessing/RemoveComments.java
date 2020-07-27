//	This class is created to process the original class and remove comments from the file so that the size of the file can be reduced

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
	
	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	//----------Method to get the original class folder path-----------------
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	
	//----------Method to remove comments from the original class file-----------------
	public void removeComments() throws IOException {
    	String tempFileName = mPath+"\\OriginalTempCopy.java";
		
		Cleaner cleaner = new Cleaner();
		String line = cleaner.readFile();
		String newLine = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

		FileWriter fw = new FileWriter(tempFileName);
		fw.write(newLine);
		fw.close();
		
		System.out.println("Temp File generated successfully!!");
	}

}
