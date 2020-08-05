/* 
	   Copyright 2020 Dr. Klaas-Jan Stol, Harshal Kasle
	
	   Licensed under the Apache License, Version 2.0 (the "License");
	   you may not use this file except in compliance with the License.
	   You may obtain a copy of the License at
	
	       http://www.apache.org/licenses/LICENSE-2.0
	
	   Unless required by applicable law or agreed to in writing, software
	   distributed under the License is distributed on an "AS IS" BASIS,
	   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	   See the License for the specific language governing permissions and
	   limitations under the License.
 */


package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import com.hsk.userinputs.SetClassNameProperty;

/*
 * This class is created to process the original class file and generate a temp
 * file (this is the second pass for processing the original class)
 */
public class TempFileProcessorSecondPass {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to get the original class name-----------------
	public void getCName(String originalCName) {
		className = originalCName;
	}

	//----------Method to process the original class and create a temporary copy of it (second pass)-----------------
	public void processTempFile() throws IOException {
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"\\FirstTemp.java";
		String newTempFile = mPath+"\\SecondTemp.java";

		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);

		String line;
		String updateCName = "SecondTemp";

		while((line = br.readLine()) != null) {

			if(line.contains(className) && !(line.contains(".") && line.contains(className+"(")) && !(line.contains("."+className))
					&& !(line.contains("int") && line.contains(className+"(")) && !(line.contains("void") && line.contains(className+"("))
					&& !(line.contains("float") && line.contains(className+"(")) && !(line.contains("String") && line.contains(className+"("))
					&& !(line.contains("double") && line.contains(className+"(")) && !(line.contains("char") && line.contains(className+"("))
					&& !(line.contains("short") && line.contains(className+"(")) && !(line.contains("long") && line.contains(className+"("))
					) {
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
						if(words[k].contains("+") && !(words[k+1].contains("+") || words[k+1].contains("="))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-") || words[k+1].contains("="))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*") && !(words[k+1].contains("*") || words[k+1].contains("="))) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/") && !(words[k+1].contains("/") || words[k+1].contains("="))) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%") && !(words[k+1].contains("%") || words[k+1].contains("="))) {
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
						if(words[k].contains("+") && !(words[k+1].contains("+") || words[k+1].contains("="))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-") || words[k+1].contains("="))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*") && !(words[k+1].contains("*") || words[k+1].contains("="))) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/") && !(words[k+1].contains("/") || words[k+1].contains("="))) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%") && !(words[k+1].contains("%") || words[k+1].contains("="))) {
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
						if(words[k].contains("+") && !(words[k+1].contains("+") || words[k+1].contains("="))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-") || words[k+1].contains("="))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*") && !(words[k+1].contains("*") || words[k+1].contains("="))) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/") && !(words[k+1].contains("/") || words[k+1].contains("="))) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%") && !(words[k+1].contains("%") || words[k+1].contains("="))) {
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
						if(words[k].contains("+") && !(words[k+1].contains("+") || words[k+1].contains("="))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-") || words[k+1].contains("="))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*") && !(words[k+1].contains("*") || words[k+1].contains("="))) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/") && !(words[k+1].contains("/") || words[k+1].contains("="))) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%") && !(words[k+1].contains("%") || words[k+1].contains("="))) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
				}

				String newLine = String.join("", words);
				bw.write(newLine);
				bw.newLine();
			}
			else if((line.contains("|") && line.contains("&")) && !(line.contains("||") && line.contains("&&"))) {				
				String[] words = line.split("");
				String replaceOR = "|" + System.lineSeparator();
				String replaceAND = "&" + System.lineSeparator();
				for(int k=0;k<words.length;k++) {
					if(words[k].contains("|")) {
						String temp = replaceOR;
						words[k] = temp;
					}
					if(words[k].contains("&")) {
						String temp = replaceAND;
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
