package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.userinputs.SetClassNameProperty;

public class CreateTempTest {
	
	private static String mPath;
	private static String fPath;
	private static String nameOfClassUnderTest;
	
	private static String className;
	
	public void getPath(String testFilePath) {
		mPath = testFilePath;
	}
	
	public void getFPath(String originalTestPath) {
		fPath = originalTestPath;
	}
	
	public void getClassName(String cName) {
		nameOfClassUnderTest = cName;
	}
	
	public void createTempCopy() throws IOException {
		
	SetClassNameProperty scp = new SetClassNameProperty();
	
	FileReader source = null;
	BufferedReader br = null;
	FileWriter fw = null;
	BufferedWriter bw = null;
	
	String location=mPath;
	String[] locatewords = fPath.split("\\\\");
	for(int i=0;i<locatewords.length;i++) {
		if(locatewords[i].contains(".java")) {
			String newW = locatewords[i].replace(".java", "");
			className = newW;
			scp.setCName(className);
			
		}
	}
	
	//nameOfClassUnderTest = "OriginalProgram";
	//String appendClassBracket = nameOfClassUnderTest+"()";
	
		String originalPath = fPath;
		String copyPath = mPath;
	
	
		source = new FileReader(originalPath);
		br = new BufferedReader(source);
		fw = new FileWriter(copyPath+"\\"+"TempTest.java");
		bw = new BufferedWriter(fw);
		
		String line;
		
		while((line = br.readLine()) != null) {
			//	System.out.println("Here1");
				if(line.contains(nameOfClassUnderTest)) {
					String[] words = line.split("");
					for(int s=0; s<words.length; s++) {
						if(words[s].contains("(")) {
							String replaceBrackets = words[s-1]+System.lineSeparator();
							if(words[s].contains("(") && words[s+1].contains(")")){
								String temp = replaceBrackets;
								words[s-1] = temp;
								//System.out.println(words[s-1]);
								
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}else {
					bw.write(line);
					bw.newLine();
				}
		}
		br.close();
		bw.close();
		source.close();
		fw.close();
		//System.out.println("TempTest.java generated successfully!!");

	}

}
