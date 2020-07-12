import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class TempFileProcessor {
	
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
//		className = scp.getCName();
//		System.out.println("In TempFileProcessor got the class name as : " + scp.getCName());
		//System.out.println("Cname in TFP : " + className);
		
		
//		TempFileGenerator tfg = new TempFileGenerator();
//		className = tfg.getClass().getName();
//		Properties props = new Properties();
		
		String tempFileName = mPath+"\\OriginalTempCopy.java";
    	String newTempFile = mPath+"\\FirstTemp.java";
    	//System.out.println("In temp file process got : " + className);
		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);
		
		String classKeyword = "class";
		String regex = ".*\\class" + Pattern.quote(classKeyword) + "\\class.*";
		String line;
		String updateCName = "FirstTemp";
		scp.setCName(updateCName);
		while((line = br.readLine()) != null) {
			
			if(line.contains(className)) {

				
				String newLine = line.replaceAll(className, updateCName);
				bw.write(newLine);
				bw.newLine();
				
		}
//
//			
//			if(line.contains(className)) {
//				String[] words = line.split(" ");
//				//String[] brackets = line.split("");
//				for(int i=0;i<words.length;i++) {
//					if(words[i].contains(className)) {
//						String temp = "SecondTemp";
//						words[i] = temp;
//						scp.setCName(temp);
//					}
//				}
////				for(int j=0;j<brackets.length;j++) {
////					if(brackets[j].contains("(") && brackets[j+1].contains(")")) {
////						String replaceBrackets = words[j-1]+System.lineSeparator();
////						String temp = replaceBrackets;
////						words[j-1] = temp;
////					}
////				}
//				String newLine = String.join(" ", words);
//				//String secondLine = String.join("", brackets);
//				
//				bw.write(newLine);
//				//bw.write(secondLine);
//				bw.newLine();
//			}
//			else if(line.contains(className) && line.contains("()")) {
//				String[] words = line.split("");
//				String[] brackets = line.
//				for(int s=0; s<words.length; s++) {
//					if(words[s].contains("(")) {
//						String replaceBrackets = words[s-1]+System.lineSeparator();
//						if(words[s].contains("(") && words[s+1].contains(")")){
//							String temp = replaceBrackets;
//							words[s-1] = temp;
//							//System.out.println(words[s-1]);
//							
//						}
//					}
//				}
//				String newLine = String.join("", words);
//				
//				bw.write(newLine);
//				bw.newLine();
//			}
				
//				if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
//					String[] words = line.split(" ");
//					for(int j=0; j<words.length; j++) {
//						//String replaceW = word.substring(word.indexOf("s ") +1, word.indexOf('{'));
//						String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//						String newW = replaceW.trim();
//						//scp.setClassName(newW);
//						//System.out.println(newW);
//						if(words[j].contains(newW)) {
//							//System.out.println(replaceW);
//							//words[j].replaceAll(newW, "M"+count);
//							
//							String temp = "SecondTemp";
//							words[j] = temp;
//							//System.out.println(words[j]);
//						}
//					}
//					String newLine = String.join(" ", words);
//		
//					bw.write(newLine);
//					bw.newLine();
//				
//				}
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
//						boolean contains = words[k].matches(".*\\\"+\"\\b.*");
//						System.out.println(words[k].matches(".*\\\"+\"\\b.*"));
						//if(!contains) {
							String replaceA = "+" + System.lineSeparator();
							
	 						if((words[k].contains("\"") && words[k+1].contains("+"))) {
								String temp = replaceA;
								words[k+1] = temp;
							}else if((words[k].contains("\"") && words[k+2].contains("+")))	{
								String temp = replaceA;
								words[k+2] = temp;
							}
						//}
						
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
//				else if((!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("*")) || (!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("-")) || (!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("/")) || (!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						String replaceB = "-" + System.lineSeparator();
//						String replaceC = "*" + System.lineSeparator();
//						String replaceD = "/" + System.lineSeparator();
//						String replaceE = "%" + System.lineSeparator();
// 						if(words[k].contains("+")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
// 						if(words[k].contains("-")) {
//							String temp = replaceB;
//							words[k] = temp;
//						}
// 						if(words[k].contains("*")) {
//							String temp = replaceC;
//							words[k] = temp;
//						}
// 						if(words[k].contains("/")) {
//							String temp = replaceD;
//							words[k] = temp;
//						}
// 						if(words[k].contains("%")) {
//							String temp = replaceE;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("\"")) && line.contains("-") && line.contains("*")) || (!(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("\"")) && line.contains("-") && line.contains("+")) || (!(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("\"")) && line.contains("-") && line.contains("/")) || (!(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("\"")) && line.contains("-") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						String replaceB = "-" + System.lineSeparator();
//						String replaceC = "*" + System.lineSeparator();
//						String replaceD = "/" + System.lineSeparator();
//						String replaceE = "%" + System.lineSeparator();
// 						if(words[k].contains("+")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
// 						if(words[k].contains("-")) {
//							String temp = replaceB;
//							words[k] = temp;
//						}
// 						if(words[k].contains("*")) {
//							String temp = replaceC;
//							words[k] = temp;
//						}
// 						if(words[k].contains("/")) {
//							String temp = replaceD;
//							words[k] = temp;
//						}
// 						if(words[k].contains("%")) {
//							String temp = replaceE;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!(line.contains("++")) && !(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("+")) || (!(line.contains("++")) && !(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("-")) || (!(line.contains("++")) && !(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("/")) || (!(line.contains("++")) && !(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) &&  !(line.contains("\"")) && line.contains("*") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						String replaceB = "-" + System.lineSeparator();
//						String replaceC = "*" + System.lineSeparator();
//						String replaceD = "/" + System.lineSeparator();
//						String replaceE = "%" + System.lineSeparator();
// 						if(words[k].contains("+")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
// 						if(words[k].contains("-")) {
//							String temp = replaceB;
//							words[k] = temp;
//						}
// 						if(words[k].contains("*")) {
//							String temp = replaceC;
//							words[k] = temp;
//						}
// 						if(words[k].contains("/")) {
//							String temp = replaceD;
//							words[k] = temp;
//						}
// 						if(words[k].contains("%")) {
//							String temp = replaceE;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!(line.contains("++")) && !(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("*")) || (!(line.contains("++")) && !(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("-")) || (!(line.contains("++")) && !(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("+")) || (!(line.contains("++")) && !(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						String replaceB = "-" + System.lineSeparator();
//						String replaceC = "*" + System.lineSeparator();
//						String replaceD = "/" + System.lineSeparator();
//						String replaceE = "%" + System.lineSeparator();
// 						if(words[k].contains("+")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
// 						if(words[k].contains("-")) {
//							String temp = replaceB;
//							words[k] = temp;
//						}
// 						if(words[k].contains("*")) {
//							String temp = replaceC;
//							words[k] = temp;
//						}
// 						if(words[k].contains("/")) {
//							String temp = replaceD;
//							words[k] = temp;
//						}
// 						if(words[k].contains("%")) {
//							String temp = replaceE;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!(line.contains("++")) && !(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("*")) || (!(line.contains("++")) && !(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("-")) || (!(line.contains("++")) && !(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("/")) || (!(line.contains("++")) && !(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("+"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						String replaceB = "-" + System.lineSeparator();
//						String replaceC = "*" + System.lineSeparator();
//						String replaceD = "/" + System.lineSeparator();
//						String replaceE = "%" + System.lineSeparator();
// 						if(words[k].contains("+")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
// 						if(words[k].contains("-")) {
//							String temp = replaceB;
//							words[k] = temp;
//						}
// 						if(words[k].contains("*")) {
//							String temp = replaceC;
//							words[k] = temp;
//						}
// 						if(words[k].contains("/")) {
//							String temp = replaceD;
//							words[k] = temp;
//						}
// 						if(words[k].contains("%")) {
//							String temp = replaceE;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
				else if((line.contains("case") && line.contains(":"))){
					String[] words = line.split("");
					for(int k=0;k<words.length;k++) {
						String replaceA = ":" + System.lineSeparator();
						
 						if(words[k].contains(":")) {
							String temp = replaceA;
							words[k] = temp;
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
//				else if(line.contains(className)) {
//					String[] words = line.split(" ");
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains(className)) {
//							String temp = "SecondTemp";
//							words[i] = temp;
//						}
//					}
//					String newLine = String.join(" ", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("+")){
//					String[] words = line.split("");
//					String replaceA = "+" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("+")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("+")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("-")){
//					String[] words = line.split("");
//					String replaceA = "-" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("-")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("-")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("*")){
//					String[] words = line.split("");
//					String replaceA = "*" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("*")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("*")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("/")){
//					String[] words = line.split("");
//					String replaceA = "/" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("/")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("/")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("%")){
//					String[] words = line.split("");
//					String replaceA = "%" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("%")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("%")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("&")){
//					String[] words = line.split("");
//					String replaceA = "&" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("&")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("&")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("|")){
//					String[] words = line.split("");
//					String replaceA = "|" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("|")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("|")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("^")){
//					String[] words = line.split("");
//					String replaceA = "^" + System.lineSeparator();
//					for(int i=0;i<words.length;i++) {
//						if(words[i].contains("^")) {
//							for(int j=i+2;j<words.length;j++) {
//								if(words[j].contains("^")) {
//									String temp = replaceA;
//									words[i] = temp;
//								}
//							}
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if(line.contains("+") && line.contains("\"")){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "+" + System.lineSeparator();
//						
// 						if((words[k].contains("\"") && words[k-1].contains("+"))) {
//							String temp = replaceA;
//							words[k-1] = temp;
//						}else if((words[k].contains("\"") && words[k-2].contains("+")))	{
//							String temp = replaceA;
//							words[k-2] = temp;
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
		
//		while((line = br.readLine()) != null) {
//			System.out.println("In temp file processor IM HERE");
//			if(line.contains(className) && line.contains("(") && line.contains(")")) {
//				System.out.println("I found bracket line");
//				String[] brackets = line.split("");
//				for(int j=0;j<brackets.length;j++) {
//					if(brackets[j].contains("(")) {
//						String replaceBrackets = brackets[j-1]+System.lineSeparator();
//						String temp = replaceBrackets;
//						brackets[j-1] = temp;
//						System.out.println("HERE: " + brackets[j-1]);
//					}
//			}
//				String newLine = String.join("", brackets);
//				bw.write(newLine);
//				bw.newLine();
//				
//			}
////			else {
////				bw.write(line);
////				bw.newLine();
////			}
//		}
//		
		br.close();
		bw.close();
		source.close();
		targetFile.close();
		
}
	
//	public void setProperties(String cName){
//		className = cName;	
//		System.out.println("Name is: " + className);
//	}
//	
//	public String returnCName() {
//		return className;
//	}
}
