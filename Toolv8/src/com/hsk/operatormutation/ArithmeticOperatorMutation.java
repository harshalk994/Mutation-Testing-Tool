package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.hsk.operatorstorage.ArithmeticOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

public class ArithmeticOperatorMutation {
	
	private static String mPath;
	private static String className;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	
	public void generateArithOpMutantFiles() throws IOException {
		
		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuArithOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		
		List<String> opTwoL = new ArrayList<String>();
		List<String> opTwoP = new ArrayList<String>();
		
		int count = 0;
		int pointer = 0;
		int pointerTwo = 0;
		String classKeyword = "class";
		String regex = ".*\\class" + Pattern.quote(classKeyword) + "\\class.*";
		
		
		ArithmeticOpStorage os = new ArithmeticOpStorage();
		os.processOp();
		
		opL = os.returnOpList();
		os.processList(opL);
		opP = os.retriveProcessList();
		//os.printPostOp();
		
		opTwoL = os.returnOpTwoList();
		//os.printOp();
		os.processTwoList(opTwoL);
		opTwoP = os.retriveProcessTwoList();
		//os.printPostOpTwo();
		
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();
		
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
				if(i!=0 && i % 4 == 0) {
					//System.out.println(i);
					pointer++;
					//System.out.println(pointer);
				}
				String s = opL.get(pointer);
//				if(s.contains("(")) {
//					s.replace("(", "\\(");
//				}
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
							
							
							String newLine = line.replaceAll(className, "MuArithOp"+count);
							//scp.setCName(updateCName);
							bw.write(newLine);
							bw.newLine();
							
					}
						
//						if(line.contains(className)) {
//							String[] words = line.split(" ");
//							//String[] brackets = line.split("");
//							for(int k=0;k<words.length;k++) {
//								if(words[k].contains(className)) {
//									String temp = "MuArithOp"+count;
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
//								//System.out.println(newW);
//								setProperties(newW);
//								//System.out.println("Class name is: " + className);
//								
//								if(words[k].contains(newW)) {
//																	
//									String temp = "MuArithOp"+count;
//									words[k] = temp;
//									//System.out.println(words[k]);
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
							continue;
//							if(i!=0 && i % 3 == 0) {
//								pointer++;
//								System.out.println(i);
//							//	System.out.println(pointer);
//							}
					}
//						else if(line.contains(className)) {
//							String[] words = line.split(" ");
//							for(int k=0;k<words.length;k++) {
//								if(words[k].contains(className)) {
//									String temp = "MuArithOp"+count;
//									words[k] = temp;
//								}
//							}
//							String newLine = String.join(" ", words);
//							
//							bw.write(newLine);
//							bw.newLine();
//						}
//						else if(line.contains(className)) {
//							String[] words = line.split(" ");
//							for(int c=0;c<words.length;c++) {
//								if(words[c].contains(className)) {
//									String temp = "MuArithOp"+count;
//									words[c] = temp;
//								}
//							}
//							String newLine = String.join(" ", words);
//							
//							
//							
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
		
		if(opTwoL.isEmpty() == false) {
			for(int k=0;k<opTwoP.size();k++) {
				count++;
				if(k!=0 && k % 1 == 0) {
					//System.out.println(i);
					pointerTwo++;
					//System.out.println(pointer);
				}
				String s = opTwoL.get(pointerTwo);
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
							
							
							String newLine = line.replaceAll(className, "MuArithOp"+count);
							//scp.setCName(updateCName);
							bw.write(newLine);
							bw.newLine();
							
						}
						
//						if(line.contains(className)) {
//							String[] words = line.split(" ");
//							//String[] brackets = line.split("");
//							for(int i=0;i<words.length;i++) {
//								if(words[i].contains(className)) {
//									String temp = "MuArithOp"+count;
//									words[i] = temp;
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
//							for(int a=0; a<words.length; a++) {
//								
//								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//								String newW = replaceW.trim();
//								setProperties(newW);
//								
//								if(words[a].contains(newW)) {
//																	
//									String temp = "MuArithOp"+count;
//									words[a] = temp;
//									//System.out.println(words[k]);
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
							
							
							String newLine = line.replace(line, opTwoP.get(k));
							bw.write(newLine);
							bw.newLine();
							continue;
//							if(i!=0 && i % 3 == 0) {
//								pointer++;
//								System.out.println(i);
//							//	System.out.println(pointer);
//							}
					}
//						else if(line.contains(className)) {
//							String[] words = line.split(" ");
//							for(int c=0;c<words.length;c++) {
//								if(words[c].contains(className)) {
//									String temp = "MuArithOp"+count;
//									words[c] = temp;
//								}
//							}
//							String newLine = String.join(" ", words);
//							
//							
//							
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
		

		source.close();
		targetFile.close();
		System.out.println("Arithmetic Op Mutants generated: " + count);
		
	}
	
//	public void setProperties(String cName){
//		className = cName;	
//	}
	
	
//	public void buildList(List<String> opL ) {
//		opL.add("a = a - b;");
//		opL.add("b = b - a;");
//		lcount= opL.size();
//		System.out.println("size of list is:" + lcount );
//		
//	}
	
//	public void processList(List<String> opL ) {
//		for(String s : opL) {
//			if(s.contains("-")) {
//				//s.replace("-", "+");
//				opP.add(s.replace("-", "+"));
//				opP.add(s.replace("-", "*"));
//				opP.add(s.replace("-", "/"));
//				opP.add(s.replace("-", "%"));
//			}
//			if(s.contains("+")) {
//				//s.replace("-", "+");
//				opP.add(s.replace("+", "-"));
//				opP.add(s.replace("+", "*"));
//				opP.add(s.replace("+", "/"));
//				opP.add(s.replace("+", "%"));
//			}
//			if(s.contains("*")) {
//				//s.replace("-", "+");
//				opP.add(s.replace("*", "+"));
//				opP.add(s.replace("*", "-"));
//				opP.add(s.replace("*", "/"));
//				opP.add(s.replace("*", "%"));
//			}
//			if(s.contains("/")) {
//				//s.replace("-", "+");
//				opP.add(s.replace("/", "+"));
//				opP.add(s.replace("/", "-"));
//				opP.add(s.replace("/", "*"));
//				opP.add(s.replace("/", "%"));
//			}
//			if(s.contains("%")) {
//				//s.replace("-", "+");
//				opP.add(s.replace("%", "+"));
//				opP.add(s.replace("%", "-"));
//				opP.add(s.replace("%", "*"));
//				opP.add(s.replace("%", "/"));
//			}
//				
//		}
//	}
//	
//	public void printNewList() {
//		for(int i=0;i<opP.size();i++) {
//			System.out.println(opP.get(i));
//		}
//	}
	
	

}