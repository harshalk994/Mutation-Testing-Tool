import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempFileGenerator {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void createTempFile(String file) throws IOException {
//		List<String> paths = new ArrayList<String>();
//    	FileDetailsStorage fds3 = new FileDetailsStorage();
//    	paths = fds3.returnPaths();
//    	for(int i=0;i<paths.size();i++) {
//    		System.out.println(paths.get(i));
//    	}
//    	String mPath = null;
//    	
//    	for(int i=0;i<paths.size();) {
//    		i++;
//    		mPath = paths.get(i);
//    		break;		
//    	}
//    	
//    	System.out.println(mPath);
    	String tempFileName = mPath+"\\FirstTemp.java";
		FileReader source = new FileReader(file);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(tempFileName);
		BufferedWriter bw = new BufferedWriter(targetFile);
		List<String> opList = new ArrayList<String>();
		OperatorStorage op = new OperatorStorage();
		
		String line;
		while((line = br.readLine()) != null) {
//			String singleLineComment = line.substring(line.indexOf("//"));
//			System.out.println(singleLineComment);
//			String multiLineComment = line.substring(line.indexOf("/*"), line.indexOf("*/"));
//			System.out.println(multiLineComment);
//			
//			
//			if(!line.contains(singleLineComment)) {
			
			
//				
				if(line.contains("class") && !(line.contains("classify"))) {
					String[] words = line.split(" ");
					for(int j=0; j<words.length; j++) {
						//String replaceW = word.substring(word.indexOf("s ") +1, word.indexOf('{'));
						String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
						String newW = replaceW.trim();
						//System.out.println(newW);
						if(words[j].contains(newW)) {
							//System.out.println(replaceW);
							//words[j].replaceAll(newW, "M"+count);
							
							String temp = "FirstTemp";
							words[j] = temp;
							//System.out.println(words[j]);
						}
					}
					String newLine = String.join(" ", words);
		
					bw.write(newLine);
					bw.newLine();
				
				}
//				else if(line.contains("&&") || line.contains("||")){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "&" + System.lineSeparator();
//						String replaceO = "|" + System.lineSeparator();
// 						if(words[k].contains("&") && words[k+1].contains("&")) {
//							String temp = replaceA;
//							words[k+1] = temp;
//						}
// 						
// 						if(words[k].contains("|") && words[k+1].contains("|")) {
//							String temp = replaceO;
//							words[k+1] = temp;
//						}
//						
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("System.out.println") && line.contains("+") && !(line.contains("+="))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						
// 						if(words[k].contains("+")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}	
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
				
				else {
				bw.write(line);
				bw.newLine();
				}
//			}
			
		}
		
		br.close();
		bw.close();
		source.close();
		targetFile.close();
		
		//op.processOp(opList);
		
	}

}
