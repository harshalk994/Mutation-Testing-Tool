import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArithOpTry3 {
	
	
	public void generateArithOpMutantFiles() throws IOException {
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		int count = 0;
		int pointer = 0;
		
		OperatorStorage os = new OperatorStorage();
		os.processOp();
		opL = os.returnOpList();
		os.processList(opL);
		opP = os.retriveProcessList();
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

		for(int i=0;i<opP.size();i++) {
			count++;
			if(i!=0 && i % 4 == 0) {
				System.out.println(i);
				pointer++;
				System.out.println(pointer);
			}
			String s = opL.get(pointer);
			System.out.println(opL.get(pointer));
			source = new FileReader("src/Temp.java");
			br = new BufferedReader(source);
			targetFile = new FileWriter("src/MuArithOp" + count + ".java");
			bw = new BufferedWriter(targetFile);
			String line; 
			//String[] text = new String[opL.size()];
			

				while((line = br.readLine()) != null) {
				//	System.out.println("Here1");
					if(line.contains("class")) {
						String[] words = line.split(" ");
						for(int k=0; k<words.length; k++) {
							
							String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
							String newW = replaceW.trim();
							
							if(words[k].contains(newW)) {
																
								String temp = "MuArithOp"+count;
								words[k] = temp;
								System.out.println(words[k]);
							}
						}
						String newLine = String.join(" ", words);
						
						
						
						bw.write(newLine);
						bw.newLine();
					
					}else if(line.contains(s)) {
						
						
						String newLine = line.replace(line, opP.get(i));
						bw.write(newLine);
						bw.newLine();
//						if(i!=0 && i % 3 == 0) {
//							pointer++;
//							System.out.println(i);
//						//	System.out.println(pointer);
//						}
				}
					
					
					
				
					else {
						bw.write(line);
						bw.newLine();
					}

			}
				br.close();
				bw.close();	
		}

		source.close();
		targetFile.close();
		System.out.println("Copying completed");
		
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
