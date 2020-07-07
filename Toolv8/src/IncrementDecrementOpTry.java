import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IncrementDecrementOpTry {
	
	private static String mPath;
	private static String className;
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
public void generateIncDecOpMutantFiles() throws IOException {
	
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();
		
		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuIncDecOp";
		List<String> incDecOpL = new ArrayList<String>();
		List<String> incDecOpP = new ArrayList<String>();
		
		int count = 0;
		int pointer = 0;
		
		IncrementDecrementOpStorage ios = new IncrementDecrementOpStorage();
		ios.processOp();
		incDecOpL = ios.returnOpList();
		//ios.printIncDecList();
		ios.processList(incDecOpL);
		incDecOpP = ios.retriveProcessList();
		
		
		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		for(int i=0;i<incDecOpP.size();i++) {
			count++;
			if(i!=0 && i % 1 == 0) {
				//System.out.println(i);
				pointer++;
				//System.out.println(pointer);
			}
			String s = incDecOpL.get(pointer);
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
								String temp = "MuIncDecOp"+count;
								words[k] = temp;
								//scp.setCName(temp);
//								System.out.println("Final Class Name is: " + scp.getClassName());
							}
						}
						String newLine = String.join(" ", words);
						//String secondLine = String.join("", brackets);
						
						bw.write(newLine);
						//bw.write(secondLine);
						bw.newLine();
					}
					
//					if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
//						String[] words = line.split(" ");
//						for(int k=0; k<words.length; k++) {
//							
//							String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//							String newW = replaceW.trim();
//							
//							if(words[k].contains(newW)) {
//																
//								String temp = "MuIncDecOp"+count;
//								words[k] = temp;
//								//System.out.println(words[k]);
//							}
//						}
//						String newLine = String.join(" ", words);
//						bw.write(newLine);
//						bw.newLine();
//					
//					}
					else if(line.equalsIgnoreCase(s)) {
						
						
						String newLine = line.replace(line, incDecOpP.get(i));
						bw.write(newLine);
						bw.newLine();
						continue;
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
		System.out.println("Increment/Decrement Op Mutants generated: " + count);
		
	}

}
