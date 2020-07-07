import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class FinalTempProcessor {
	
	private static String mPath;
	private static String className;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void processTempFile() throws IOException {
		
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

    	String tempFileName = mPath+"\\SecondTemp.java";
    	String newTempFile = mPath+"\\Temp.java";
		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);
		
		String line;
		while((line = br.readLine()) != null) {
			
			if(line.contains(className)) {
				String[] words = line.split(" ");
				//String[] brackets = line.split("");
				for(int i=0;i<words.length;i++) {
					if(words[i].contains(className)) {
						String temp = "Temp";
						words[i] = temp;
						scp.setCName(temp);
//						System.out.println("Final Class Name is: " + scp.getClassName());
					}
				}

				String newLine = String.join(" ", words);
				//String secondLine = String.join("", brackets);
				
				bw.write(newLine);
				//bw.write(secondLine);
				bw.newLine();
			}
				
//				if(line.contains("class") && !(line.contains("(")) && !(line.contains(")"))) {
//					String[] words = line.split(" ");
//					for(int j=0; j<words.length; j++) {
//						//String replaceW = word.substring(word.indexOf("s ") +1, word.indexOf('{'));
//						String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//						String newW = replaceW.trim();
//						//System.out.println(newW);
//						if(words[j].contains(newW)) {
//							//System.out.println(replaceW);
//							//words[j].replaceAll(newW, "M"+count);
//							
//							String temp = "Temp";
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
				
			else if(line.contains("+") && !(line.contains("\"")) && !(line.contains("+="))){
					String[] words = line.split("");
					String replaceA = "+" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("+")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("+")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("-") && !(line.contains("\"")) && !(line.contains("-="))){
					String[] words = line.split("");
					String replaceA = "-" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("-")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("-")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("*") && !(line.contains("\"")) && !(line.contains("*="))){
					String[] words = line.split("");
					String replaceA = "*" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("*")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("*")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("/") && !(line.contains("\"")) && !(line.contains("/="))){
					String[] words = line.split("");
					String replaceA = "/" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("/")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("/")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("%") && !(line.contains("\"")) && !(line.contains("%="))){
					String[] words = line.split("");
					String replaceA = "%" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("%")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("%")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("&") && !(line.contains("\"")) && !(line.contains("&="))){
					String[] words = line.split("");
					String replaceA = "&" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("&")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("&")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("|") && !(line.contains("\"")) && !(line.contains("|="))){
					String[] words = line.split("");
					String replaceA = "|" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("|")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("|")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("^") && !(line.contains("\"")) && !(line.contains("^="))){
					String[] words = line.split("");
					String replaceA = "^" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("^")) {
							for(int j=i+2;j<words.length;j++) {
								if(words[j].contains("^")) {
									String temp = replaceA;
									words[i] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("+=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("+") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("+") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("-=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("-") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("-") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("*=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("*") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("*") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("/=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("/") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("/") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("%=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("%") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("%") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("&=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("&") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("&") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("|=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("|") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("|") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("^=") && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("^") && words[i+1].contains("=")) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("^") && words[j+1].contains("=")) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains(">>") && !(line.contains(">>>")) && !(line.contains(">>>=")) && !(line.contains(">>=")) && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = ">" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains(">") && words[i+1].contains(">") && (!(words[i+2].contains(">")) || !(words[i+2].contains("=")))) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains(">") && words[j+1].contains(">") && (!(words[j+2].contains(">")) || !(words[j+2].contains("=")))) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains(">>>") && !(line.contains(">>>=")) && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = ">" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains(">") && words[i+1].contains(">") && words[i+2].contains(">") && (!(words[i+3].contains("=")))) {
							for(int j=i+4;j<words.length;j++) {
								if(words[j].contains(">") && words[j+1].contains(">") && words[j+2].contains(">") && !(words[j+3].contains("="))) {
									String temp = replaceA;
									words[i+2] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("<<") && !(line.contains("<<=")) && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "<" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("<") && words[i+1].contains("<") && !(words[i+2].contains("="))) {
							for(int j=i+3;j<words.length;j++) {
								if(words[j].contains("<") && words[j+1].contains("<") && !(words[j+2].contains("="))) {
									String temp = replaceA;
									words[i+1] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains("<<=") && !(line.contains("<<")) && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains("<") && words[i+1].contains("<") && words[i+2].contains("=") && !(words[i+2].contains("<"))) {
							for(int j=i+4;j<words.length;j++) {
								if(words[j].contains("<") && words[j+1].contains("<") && words[j+2].contains("=") && !(words[j+2].contains("<"))) {
									String temp = replaceA;
									words[i+2] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains(">>=") && !(line.contains(">>")) && !(line.contains(">>>=")) && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains(">") && words[i+1].contains(">") && words[i+2].contains("=") && !(words[i+2].contains(">"))) {
							for(int j=i+4;j<words.length;j++) {
								if(words[j].contains(">") && words[j+1].contains(">") && words[j+2].contains("=") && !(words[j+2].contains(">"))) {
									String temp = replaceA;
									words[i+2] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains(">>>=") && !(line.contains(">>>")) && !(line.contains("\""))){
					String[] words = line.split("");
					String replaceA = "=" + System.lineSeparator();
					for(int i=0;i<words.length;i++) {
						if(words[i].contains(">") && words[i+1].contains(">") && words[i+2].contains(">") && words[i+3].contains("=")) {
							for(int j=i+5;j<words.length;j++) {
								if(words[j].contains(">") && words[j+1].contains(">") && words[j+2].contains(">") && words[j+3].contains("=")) {
									String temp = replaceA;
									words[i+3] = temp;
								}
							}
						}
					}
					String newLine = String.join("", words);
					
					bw.write(newLine);
					bw.newLine();
				}
//				else if((!line.contains("System.out.println") && !(line.contains("++")) && line.contains("+") && line.contains("*")) || (!line.contains("System.out.println") && !(line.contains("++")) && line.contains("+") && line.contains("+")) ||(!line.contains("System.out.println") && !(line.contains("++")) && line.contains("+") && line.contains("-")) || (!line.contains("System.out.println") && !(line.contains("++")) && line.contains("+") && line.contains("/")) || (!line.contains("System.out.println") && !(line.contains("++")) && line.contains("+") && line.contains("%"))){
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
//				else if((!line.contains("System.out.println") && !(line.contains("--")) && line.contains("-") && line.contains("*")) || (!line.contains("System.out.println") && !(line.contains("--")) && line.contains("-") && line.contains("-")) || (!line.contains("System.out.println") && !(line.contains("--")) && line.contains("-") && line.contains("+")) || (!line.contains("System.out.println") && !(line.contains("--")) && line.contains("-") && line.contains("/")) || (!line.contains("System.out.println") && !(line.contains("--")) && line.contains("-") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "-" + System.lineSeparator();
//						
// 						if(words[k].contains("-")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!line.contains("System.out.println") && line.contains("*") && line.contains("+")) || (!line.contains("System.out.println") && line.contains("*") && line.contains("-")) || (!line.contains("System.out.println") && line.contains("*") && line.contains("/")) || (!line.contains("System.out.println") && line.contains("*") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "*" + System.lineSeparator();
//						
// 						if(words[k].contains("*")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!line.contains("System.out.println") && line.contains("/") && line.contains("*")) || (!line.contains("System.out.println") && line.contains("/") && line.contains("-")) || (!line.contains("System.out.println") && line.contains("/") && line.contains("+")) || (!line.contains("System.out.println") && line.contains("/") && line.contains("%"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "/" + System.lineSeparator();
//						
// 						if(words[k].contains("/")) {
//							String temp = replaceA;
//							words[k] = temp;
//						}
//					}
//					String newLine = String.join("", words);
//					
//					bw.write(newLine);
//					bw.newLine();
//				}
//				else if((!line.contains("System.out.println") && line.contains("%") && line.contains("*")) || (!line.contains("System.out.println") && line.contains("%") && line.contains("-")) || (!line.contains("System.out.println") && line.contains("%") && line.contains("/")) || (!line.contains("System.out.println") && line.contains("%") && line.contains("+"))){
//					String[] words = line.split("");
//					for(int k=0;k<words.length;k++) {
//						String replaceA = "%" + System.lineSeparator();
//						
// 						if(words[k].contains("%")) {
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
		}
		
		br.close();
		bw.close();
		source.close();
		targetFile.close();
	}

}
