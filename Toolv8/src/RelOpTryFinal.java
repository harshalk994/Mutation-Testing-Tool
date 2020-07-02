import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelOpTryFinal {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void generateRelOpMutantFiles() throws IOException {
		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuRelOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		int count = 0;
		int pointer = 0;
		
		RelOpStorageFinal rs = new RelOpStorageFinal();
		//rs.processOp();
		opL = rs.returnRelOpList();
		rs.processList(opL);
		opP = rs.retriveRelProcessList();
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
					if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
						String[] words = line.split(" ");
						for(int k=0; k<words.length; k++) {
							
							String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
							String newW = replaceW.trim();
							
							if(words[k].contains(newW)) {
																
								String temp = "MuRelOp"+count;
								words[k] = temp;
								//System.out.println(words[k]);
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
		System.out.println("Relational Op Mutants generated: " + count);
		
	}
	

}
