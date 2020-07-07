import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArithOpTry3 {
	
	private static String mPath;
	private static String className;
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	
	public void generateArithOpMutantFiles() throws IOException {
		
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();
		
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
		
		OperatorStorage os = new OperatorStorage();
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
							String[] words = line.split(" ");
							//String[] brackets = line.split("");
							for(int k=0;k<words.length;k++) {
								if(words[k].contains(className)) {
									String temp = "MuArithOp"+count;
									words[k] = temp;
									//scp.setCName(temp);
//									System.out.println("Final Class Name is: " + scp.getClassName());
								}
							}
							String newLine = String.join(" ", words);
							//String secondLine = String.join("", brackets);
							
							bw.write(newLine);
							//bw.write(secondLine);
							bw.newLine();
						}
//						if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
//							String[] words = line.split(" ");
//							for(int k=0; k<words.length; k++) {
//								
//								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//								String newW = replaceW.trim();
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
							String[] words = line.split(" ");
							//String[] brackets = line.split("");
							for(int i=0;i<words.length;i++) {
								if(words[i].contains(className)) {
									String temp = "MuArithOp"+count;
									words[i] = temp;
									//scp.setCName(temp);
//									System.out.println("Final Class Name is: " + scp.getClassName());
								}
							}
							String newLine = String.join(" ", words);
							//String secondLine = String.join("", brackets);
							
							bw.write(newLine);
							//bw.write(secondLine);
							bw.newLine();
						}
						
//						if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
//							String[] words = line.split(" ");
//							for(int a=0; a<words.length; a++) {
//								
//								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//								String newW = replaceW.trim();
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
