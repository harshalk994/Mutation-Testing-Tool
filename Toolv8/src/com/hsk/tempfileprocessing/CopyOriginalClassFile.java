package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.hsk.operatorstorage.ArithmeticOpStorage;

public class CopyOriginalClassFile {
	
	private static String mPath;
	private static String fPath;
	private static String className;
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	public void createTempFile(String file) throws IOException {
	
////		String[] locatewords = fPath.split("\\\\");
////		for(int i=0;i<locatewords.length;i++) {
////			if(locatewords[i].contains(".java")) {
////				String newW = locatewords[i].replace(".java", "");
////				className = newW;
////				
////			}
////		}
//		System.out.println("Classname is : " + className);
//		SetClassNameProperty scp = new SetClassNameProperty();
		//scp.setCName(className);
		//System.out.println("In original class copy name was set to : " + scp.getCName());
		//TempFileGenerator tfg = new TempFileGenerator();
		
//		String updateCName = "OriginalTempCopy";
//		scp.setCName(updateCName);
    	String tempFileName = mPath+"\\OriginalTempCopy.java";
    	//System.out.println("NAME IS : " + tempFileName);
//    	System.out.println("In Original Class Copy name was set to: " + scp.getCName());
		FileReader source = new FileReader(file);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(tempFileName);
		BufferedWriter bw = new BufferedWriter(targetFile);
		List<String> opList = new ArrayList<String>();
		ArithmeticOpStorage op = new ArithmeticOpStorage();

		String line;
		
		while((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();	
			}
		br.close();
		bw.close();
		source.close();
		targetFile.close();
		}
	}


