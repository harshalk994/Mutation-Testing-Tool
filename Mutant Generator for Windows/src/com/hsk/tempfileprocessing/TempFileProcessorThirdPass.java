//	This class is created to process the original class file and generate a temp file (this is the third pass for processing the original class)

package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import com.hsk.userinputs.SetClassNameProperty;

public class TempFileProcessorThirdPass {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to process the original class and create a temporary copy of it (third pass)-----------------
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
		String updateCName = "Temp";


		while((line = br.readLine()) != null) {

			if(line.contains(className)) {
				String newLine = line.replaceAll(className, updateCName);
				scp.setCName(updateCName);
				bw.write(newLine);
				bw.newLine();					
			}
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
			else if((!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("*")) || (!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("-")) || (!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("/")) || (!(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("\"")) && line.contains("+") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					String replaceA = "+" + System.lineSeparator();
					String replaceB = "-" + System.lineSeparator();
					String replaceC = "*" + System.lineSeparator();
					String replaceD = "/" + System.lineSeparator();
					String replaceE = "%" + System.lineSeparator();
					if(words[k].contains("+")) {
						String temp = replaceA;
						words[k] = temp;
					}
					if(words[k].contains("-")) {
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

				String newLine = String.join("", words);		
				bw.write(newLine);
				bw.newLine();
			}
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