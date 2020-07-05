import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentOpTry {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void generateAssignmentOpMutantFiles() throws IOException {

		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuAssignmentOp";
		
		List<String> arithOpL = new ArrayList<String>();
		List<String> arithOpP = new ArrayList<String>();
		
		List<String> bitwiseOpL = new ArrayList<String>();
		List<String> bitwiseOpP = new ArrayList<String>();
		
		List<String> shiftOpL = new ArrayList<String>();
		List<String> shiftOpP = new ArrayList<String>();
		
		int count = 0;
		int arithPointer = 0;
		int bitwisePointer = 0;
		int shiftPointer = 0;
		
		AssignmentOpStorage aos = new AssignmentOpStorage();
		//aos.processOp();
		arithOpL = aos.returnArithAssignOpList();
		//aos.printArithAssignmentList();
		aos.processArithAssignList(arithOpL);
		arithOpP = aos.retriveArithAssignmentProcessList();
		
		bitwiseOpL = aos.returnBitwiseAssignOpList();
		aos.processBitwiseAssignList(bitwiseOpL);
		bitwiseOpP = aos.retriveBitwiseAssignmentProcessList();
		
		shiftOpL = aos.returnShiftAssignOpList();
		aos.processShiftAssignList(shiftOpL);
		shiftOpP = aos.retriveShiftAssignmentProcessList();
		
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
		
		if(arithOpL.isEmpty() == false) {
			for(int i=0;i<arithOpP.size();i++) {
				count++;
				if(i!=0 && i % 5 == 0) {
					//System.out.println(i);
					arithPointer++;
					//System.out.println(arithPointer);
				}
				String s = arithOpL.get(arithPointer);
				//System.out.println(arithOpL.get(arithPointer));
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				//String[] text = new String[opL.size()];
				

					while((line = br.readLine()) != null) {
//					//	System.out.println("Here1");
						if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
							String[] words = line.split(" ");
							for(int k=0; k<words.length; k++) {
								
								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
								String newW = replaceW.trim();
								
								if(words[k].contains(newW)) {
																	
									String temp = "MuAssignmentOp"+count;
									words[k] = temp;
									//System.out.println(words[k]);
								}
							}
							String newLine = String.join(" ", words);
							bw.write(newLine);
							bw.newLine();
						
						}else if(line.equalsIgnoreCase(s)) {
							
							
							String newLine = line.replace(line, arithOpP.get(i));
							bw.write(newLine);
							bw.newLine();
////							if(i!=0 && i % 3 == 0) {
////								pointer++;
////								System.out.println(i);
////							//	System.out.println(pointer);
////							}
					}
						
						
						
					
						else {
							bw.write(line);
							bw.newLine();
						}

				}
					br.close();
					bw.close();	
			}

//			source.close();
//			targetFile.close();
//			System.out.println("ArithAssignment Op Mutants generated successfully!!");
		}
		
		
		if(bitwiseOpL.isEmpty()==false) {
			for(int i=0;i<bitwiseOpP.size();i++) {
				count++;
				if(i!=0 && i % 2 == 0) {
					//System.out.println(i);
					bitwisePointer++;
					//System.out.println(bitwisePointer);
				}
				String s = bitwiseOpL.get(bitwisePointer);
				//System.out.println(bitwiseOpL.get(bitwisePointer));
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				//String[] text = new String[opL.size()];
				

					while((line = br.readLine()) != null) {
					//	System.out.println("Here1");
						if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
							String[] words = line.split(" ");
							for(int k=0; k<words.length; k++) {
								
								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
								String newW = replaceW.trim();
								
								if(words[k].contains(newW)) {
																	
									String temp = "MuAssignmentOp"+count;
									words[k] = temp;
									//System.out.println(words[k]);
								}
							}
							String newLine = String.join(" ", words);
							bw.write(newLine);
							bw.newLine();
						
						}else if(line.equalsIgnoreCase(s)) {
							
							
							String newLine = line.replace(line, bitwiseOpP.get(i));
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

//			source.close();
//			targetFile.close();
//			System.out.println("BitwiseAssignment Op Mutants generated successfully!!");
		}
		
		
		if(shiftOpL.isEmpty() == false) {
			for(int i=0;i<shiftOpP.size();i++) {
				count++;
				if(i!=0 && i % 1 == 0) {
					//System.out.println(i);
					shiftPointer++;
					//System.out.println(shiftPointer);
				}
				String s = shiftOpL.get(shiftPointer);
				//System.out.println(shiftOpL.get(shiftPointer));
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				//String[] text = new String[opL.size()];
				

					while((line = br.readLine()) != null) {
//					//	System.out.println("Here1");
						if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
							String[] words = line.split(" ");
							for(int k=0; k<words.length; k++) {
								
								String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
								String newW = replaceW.trim();
								
								if(words[k].contains(newW)) {
																	
									String temp = "MuAssignmentOp"+count;
									words[k] = temp;
									//System.out.println(words[k]);
								}
							}
							String newLine = String.join(" ", words);
							bw.write(newLine);
							bw.newLine();
						
						}else if(line.equalsIgnoreCase(s)) {
							
							
							String newLine = line.replace(line, shiftOpP.get(i));
							bw.write(newLine);
							bw.newLine();
////							if(i!=0 && i % 3 == 0) {
////								pointer++;
////								System.out.println(i);
////							//	System.out.println(pointer);
////							}
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
		
		System.out.println("Assignment Op Mutants generated: " + count);
		source.close();
		targetFile.close();
		
		
	}



}
