import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempFileProcessor {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void processTempFile() throws IOException {

    	String tempFileName = mPath+"\\FirstTemp.java";
    	String newTempFile = mPath+"\\Temp.java";
		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);
		
		String line;
		while((line = br.readLine()) != null) {
				
				if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
					String[] words = line.split(" ");
					for(int j=0; j<words.length; j++) {
						//String replaceW = word.substring(word.indexOf("s ") +1, word.indexOf('{'));
						String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
						String newW = replaceW.trim();
						//System.out.println(newW);
						if(words[j].contains(newW)) {
							//System.out.println(replaceW);
							//words[j].replaceAll(newW, "M"+count);
							
							String temp = "Temp";
							words[j] = temp;
							//System.out.println(words[j]);
						}
					}
					String newLine = String.join(" ", words);
		
					bw.write(newLine);
					bw.newLine();
				
				}
				else if(line.contains("&&") || line.contains("||")){
					String[] words = line.split("");
					for(int k=0;k<words.length;k++) {
						String replaceA = "&" + System.lineSeparator();
						String replaceO = "|" + System.lineSeparator();
 						if(words[k].contains("&") && words[k+1].contains("&")) {
							String temp = replaceA;
							words[k+1] = temp;
						}
 						
 						if(words[k].contains("|") && words[k+1].contains("|")) {
							String temp = replaceO;
							words[k+1] = temp;
						}
						
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("System.out.println") && line.contains("+") && !(line.contains("+="))){
					String[] words = line.split("");
					for(int k=0;k<words.length;k++) {
						String replaceA = "+" + System.lineSeparator();
						
 						if((words[k].contains("\"") && words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k+1] = temp;
						}else if((words[k].contains("\"") && words[k+2].contains("+")))	{
							String temp = replaceA;
							words[k+2] = temp;
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				
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
		
}
}
