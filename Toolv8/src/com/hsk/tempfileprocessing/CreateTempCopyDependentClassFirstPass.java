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

public class CreateTempCopyDependentClassFirstPass {
	
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
	
	public void createTempCopy() throws IOException {
//		List<String> dependentList = new ArrayList<String>();
//		List<String> dependentArithList = new ArrayList<String>();
//		List<String> dependentAssignList = new ArrayList<String>();
//		List<String> dependentBitwiseList = new ArrayList<String>();
//		List<String> dependentConditionalList = new ArrayList<String>();
//		List<String> dependentIncDecList = new ArrayList<String>();
//		List<String> dependentRelList = new ArrayList<String>();
//		List<String> dependentShiftList = new ArrayList<String>();
		
		List<String> copyArray = new ArrayList<String>();
		List<String> copyMArray = new ArrayList<String>();
		List<String> dependentMList = new ArrayList<String>();
		
		//File directoryPath = new File(fPath);
		File directoryMPath = new File(mPath);
		//String contents[] = directoryPath.list();
		String mContents[] = directoryMPath.list();
		
		//copyArray = Arrays.asList(contents);
		copyMArray = Arrays.asList(mContents);
		
//		for(int i=0;i<copyArray.size();i++) {
//			//String printOClass = copyArray.get(i).contains(oClassName);
//			if(copyArray.get(i).contains(".java") && !(copyArray.get(i).contains(oClassName))) {
//				//System.out.println("Got the value as: " + copyArray.get(i));
//				dependentList.add(copyArray.get(i));
//			}
//		}
		
//		for(int i=0;i<dependentList.size();i++) {
//			if(dependentList.get(i).contains(".java")) {
//				String replaceWord = dependentList.get(i).replace(".java", "");
//				//System.out.println("Replaced word is: " + replaceWord);
//				//dependentList.add(replaceWord);
//				dependentList.set(i, replaceWord);
//				
//			}
//		}
		
		for(int i=0;i<copyMArray.size();i++) {
			//String printOClass = copyArray.get(i).contains(oClassName);
			if(copyMArray.get(i).contains(".java") && !(copyMArray.get(i).contains("Temp"))) {
				//System.out.println("Got the value as: " + copyArray.get(i));
				dependentMList.add(copyMArray.get(i));
			}
		}
		
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		int arithPointer=0;
		int assignPointer=0;
		int bitwisePointer=0;
		int conditionPointer=0;
		int incDecPointer=0;
		int relPointer=0;
		int shiftPointer=0;
		
		int count=0;
		
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
				fr = new FileReader(dPath);
				br = new BufferedReader(fr);
				fw = new FileWriter(mPath+"\\"+dClassName+"FirstTemp"+count+".java");
				bw = new BufferedWriter(fw);
				
				String line; 
					
					while((line = br.readLine()) != null) {
						if((line.contains("extends") || line.contains("class")) && !(line.contains("("))) {
							String[] words = line.split(" ");
							String replaceW = "extends"+System.lineSeparator();
//							String replaceCW = dClassName;
//							String newreplaceCW = replaceCW.replace(replaceCW, replaceCW+"FirstTemp"+count);
//							//count++;
//							for(int i=0;i<words.length;i++) {
//								if(words[i].contains(replaceCW)) {
//									String temp = newreplaceCW;
//									words[i] = temp;
//								}
//							}
							
							for(int i=0;i<words.length;i++) {
								if(words[i].contains("extends")) {
									String temp = replaceW;
									words[i] = temp;
								}
							}
							
							
							String newLine = String.join(" ", words);
							bw.write(newLine);
							bw.newLine();
						}
//						else if(line.contains(oClassName)) {
//							String newLine = line.replaceAll(oClassName, dependentRelList.get(relCopyPointer));
//							relCopyPointer++;
//							bw.write(newLine);
//							bw.newLine();
//						}
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
