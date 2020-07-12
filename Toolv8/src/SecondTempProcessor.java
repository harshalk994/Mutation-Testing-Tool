import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class SecondTempProcessor {
	
	private static String mPath;
	//private static String fPath;
	private static String className;
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void getCName(String originalCName) {
		className = originalCName;
	}
	
//	public void getFPath(String originalFilePath) {
//		fPath = originalFilePath;
//	}
	
	public void processTempFile() throws IOException {
		
//		String[] locatewords = fPath.split("\\\\");
//		for(int i=0;i<locatewords.length;i++) {
//			if(locatewords[i].contains(".java")) {
//				String newW = locatewords[i].replace(".java", "");
//				className =newW;
//			}
//		}
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();
//		System.out.println("In TempFileProcessor got the class name as : " + scp.getCName());
		//System.out.println("Cname in TFP : " + className);
		
		
//		TempFileGenerator tfg = new TempFileGenerator();
//		className = tfg.getClass().getName();
//		Properties props = new Properties();
		
		String tempFileName = mPath+"\\FirstTemp.java";
    	String newTempFile = mPath+"\\SecondTemp.java";
    	//System.out.println("In temp file process got : " + className);
		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);
		
		String classKeyword = "class";
		String regex = ".*\\class" + Pattern.quote(classKeyword) + "\\class.*";
		String line;
		String updateCName = "SecondTemp";
		//scp.setCName(updateCName);
		while((line = br.readLine()) != null) {
			
			if(line.contains(className)) {

				
				String newLine = line.replaceAll(className, updateCName);
				scp.setCName(updateCName);
				bw.write(newLine);
				bw.newLine();
				
		}
			else if((!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("*")) || (!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("+")) || (!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("/")) || (!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
							if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
							if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
							if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
							if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
							if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
					}

				String newLine = String.join("", words);
				
				bw.write(newLine);
				bw.newLine();
			}
			else if((!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("+")) || (!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("-")) || (!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("/")) || (!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) &&  !(line.contains("\"")) && line.contains("*") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
							if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
							if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
							if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
							if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
							if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
					}

					
				String newLine = String.join("", words);
				
				bw.write(newLine);
				bw.newLine();
			}
			else if((!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("*")) || (!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("-")) || (!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("+")) || (!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
							if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
							if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
							if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
							if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
							if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
					}

				String newLine = String.join("", words);
				
				bw.write(newLine);
				bw.newLine();
			}
			else if((!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("*")) || (!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("-")) || (!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("/")) || (!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("+"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
							if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
							if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
							if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
							if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
							if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
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
