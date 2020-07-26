package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.operatorstorage.RelationalOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

public class RelationalOperatorMutation {
	
	private static String mPath;
	private static String className;
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void generateRelOpMutantFiles() throws IOException {
		
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuRelOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		List<String> opLNotEq = new ArrayList<String>();
		List<String> opPNotEq = new ArrayList<String>();
		
		int count = 0;
		int pointer = 0;
		int pointerTwo = 0;
		
		RelationalOpStorage rs = new RelationalOpStorage();
		//rs.processOp();
		opL = rs.returnRelOpList();
		//rs.printOp();
		rs.processList(opL);
		opP = rs.retriveRelProcessList();
		
		opLNotEq = rs.returnRelOpListNotEqual();
		//rs.printOpNotEq();
		rs.processListNotEqual(opLNotEq);
		opPNotEq = rs.retriveRelProcessListNotEq();
//		ArithOpTry3 aop = new ArithOpTry3();
//		aop.buildList(opL);
//		aop.processList(opL);
//		aop.printNewList();
	//	AopProcessorFinal aof = new AopProcessorFinal();
	//	aof.processFile(opL, opP);
		
		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		if(opL.isEmpty() == false) {
			for(int i=0;i<opP.size();i++) {
				count++;
				if(i!=0 && i % 5 == 0) {
					//System.out.println(i);
					pointer++;
					//System.out.println(pointer);
				}
				String s = opL.get(pointer);
				//System.out.println(opL.get(pointer));
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				//String[] text = new String[opL.size()];
				

					while((line = br.readLine()) != null) {
					//	System.out.println("Here1");
						
						if(line.contains(className)) {
							
							
							String newLine = line.replaceAll(className, "MuRelOp"+count);
							//scp.setCName(updateCName);
							bw.write(newLine);
							bw.newLine();
							
						}
						
//						if(line.contains(className)) {
//							String[] words = line.split(" ");
//							//String[] brackets = line.split("");
//							for(int k=0;k<words.length;k++) {
//								if(words[k].contains(className)) {
//									String temp = "MuRelOp"+count;
//									words[k] = temp;
//									//scp.setCName(temp);
////									System.out.println("Final Class Name is: " + scp.getClassName());
//								}
//							}
//							String newLine = String.join(" ", words);
//							//String secondLine = String.join("", brackets);
//							
//							bw.write(newLine);
//							//bw.write(secondLine);
//							bw.newLine();
//						}
						
//						if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
//							String[] words = line.split(" ");
//							for(int k=0; k<words.length; k++) {
//								
//								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//								String newW = replaceW.trim();
//								
//								if(words[k].contains(newW)) {
//																	
//									String temp = "MuRelOp"+count;
//									words[k] = temp;
//									System.out.println(words[k]);
//								}
//							}
//							String newLine = String.join(" ", words);
//							
//							
//							
//							bw.write(newLine);
//							bw.newLine();
//						
//						}
						else if(line.equalsIgnoreCase(s)) {
							
							
							String newLine = line.replace(line, opP.get(i));
							bw.write(newLine);
							bw.newLine();
//							if(i!=0 && i % 3 == 0) {
//								pointer++;
//								System.out.println(i);
//							//	System.out.println(pointer);
//							}
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
		
		if(opLNotEq.isEmpty() == false) {
			for(int i=0;i<opPNotEq.size();i++) {
				count++;
				if(i!=0 && i % 1 == 0) {
					//System.out.println(i);
					pointerTwo++;
					//System.out.println(pointer);
				}
				String s = opLNotEq.get(pointerTwo);
				System.out.println(opLNotEq.get(pointerTwo));
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				//String[] text = new String[opL.size()];
				

					while((line = br.readLine()) != null) {
					//	System.out.println("Here1");
						
						if(line.contains(className)) {
							
							
							String newLine = line.replaceAll(className, "MuRelOp"+count);
							//scp.setCName(updateCName);
							bw.write(newLine);
							bw.newLine();
							
						}
						

						else if(line.equalsIgnoreCase(s)) {
							
							
							String newLine = line.replace(line, opPNotEq.get(i));
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
		

		source.close();
		targetFile.close();
		System.out.println("Relational Op Mutants generated: " + count);
		
	}
	

}
