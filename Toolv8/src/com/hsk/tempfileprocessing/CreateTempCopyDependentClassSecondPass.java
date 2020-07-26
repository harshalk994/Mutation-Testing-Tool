package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTempCopyDependentClassSecondPass {
	
	private static String dPath;
	private static String mPath;
	private static String dClassName;
	private static String oClassName;
	
//	public void getFPath(String originalFilePath) {
//		fPath = originalFilePath;
//		System.out.println("Got FPath as: " + fPath);
//	}
	
	public void getDPath(String dependentCPath) {
		dPath = dependentCPath;
	}
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void getCName(String cName) {
		dClassName = cName;
	}
	
	public void getOCName(String oCName) {
		oClassName = oCName;
	}
	
	public void createClassCopy() throws IOException {
		
		List<String> copyArray = new ArrayList<String>();
		List<String> copyMArray = new ArrayList<String>();
		List<String> dependentMList = new ArrayList<String>();
		List<String> dependentList = new ArrayList<String>();
		
		//File directoryPath = new File(fPath);
		File directoryMPath = new File(mPath);
		//String contents[] = directoryPath.list();
		String mContents[] = directoryMPath.list();
		
		copyMArray = Arrays.asList(mContents);
			
		for(int i=0;i<copyMArray.size();i++) {
			//String printOClass = copyArray.get(i).contains(oClassName);
			if(copyMArray.get(i).contains(".java") && (copyMArray.get(i).contains("FirstTemp"))) {
				//System.out.println("Got the value as: " + copyArray.get(i));
				dependentMList.add(copyMArray.get(i));
			}
		}
		
		for(int i=0;i<copyMArray.size();i++) {
			//String printOClass = copyArray.get(i).contains(oClassName);
			if(copyMArray.get(i).contains(".java") && !(copyMArray.get(i).contains("FirstTemp")) && !(copyMArray.get(i).contains("Temp"))) {
				//System.out.println("Got the value as: " + copyArray.get(i));
				dependentList.add(copyMArray.get(i));
			}
		}
		
//		for(int i=0;i>dependentMList.size();i++) {
//			if(dependentMList.get(i).contains(".java")) {
//				dependentMList.get(i).replace(".java", "");
//			}
//		}
		
		for(int i=0;i<dependentList.size();i++) {
			if(dependentList.get(i).contains(".java")) {
				String replaceWord = dependentList.get(i).replace(".java", "");
				//System.out.println("Replaced word is: " + replaceWord);
				//dependentList.add(replaceWord);
				dependentList.set(i, replaceWord);
				
			}
		}
		
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		int count = 0;
		
		if(dependentMList.isEmpty() == false) {
//			int size = dependentList.size() * dependentRelList.size();
//			System.out.println("Size is: " + size);
//			int relCopyPointer = 0;
			
			for(int k=0;k<dependentMList.size();k++) {
				count++;
//				if(relCopyPointer == dependentRelList.size()) {
//					relCopyPointer = 0;
//				}
				
//				if(count > dependentRelList.size()) {
//					count = 1;
//				}
				
//				if(k!=0 && k % dependentRelList.size() == 0) {
//					System.out.println(k);
//					relPointer++;
//					System.out.println(relPointer);
//				}
				//String s = dependentList.get(relPointer);
//				System.out.println("--------------------------------------------");
//				System.out.println(dependentList.get(relPointer));
				fr = new FileReader(mPath+"\\"+dClassName+"FirstTemp"+count+".java");
				br = new BufferedReader(fr);
				fw = new FileWriter(mPath+"\\"+dClassName+"SecondTemp"+count+".java");
				bw = new BufferedWriter(fw);
				
				String line; 
					
					while((line = br.readLine()) != null) {
						if(line.contains(dClassName)) {
							String newLine = line.replaceAll(dClassName, dClassName+"SecondTemp"+count);
							bw.write(newLine);
							bw.newLine();
						}
						else if(line.contains(oClassName)) {
							String newLine = line.replaceAll(oClassName, dependentList.get(k));
							//relCopyPointer++;
							bw.write(newLine);
							bw.newLine();
						}
						else {
							bw.write(line);
							bw.newLine();
						}			
					}
						br.close();
						bw.close();	
				}
		}
		
		fr.close();
		fw.close();
			
	}

}
