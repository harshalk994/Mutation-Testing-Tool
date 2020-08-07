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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hsk.userinputs.SetClassNameProperty;

/*
 * This class is created to process the original class file and generate an initial temp 
 * file (this is the first pass for processing the original class)
 */
public class TempFileProcessorFirstPass {

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

	//----------Method to process the original class and create a temporary copy of it (first pass)-----------------
	public void processTempFile() throws IOException {
		SetClassNameProperty scp = new SetClassNameProperty();

		String tempFileName = mPath+"/OriginalTempCopy.java";
		String newTempFile = mPath+"/FirstTemp.java";

		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);

		String line;
		String updateCName = "FirstTemp";
		scp.setCName(updateCName);

		String regex = "[a-z]"+className; 
		String regexTwo = className+"[a-zA-Z]";
		Pattern p = Pattern.compile(regex);
		Pattern p2 = Pattern.compile(regexTwo);
		
		String and = "&&";
		String or = "||";
		String updateAnd = "&&\n";
		String updateOr = "||\n";

		while((line = br.readLine()) != null) {
			Matcher m = p.matcher(line);
			Matcher m2 = p2.matcher(line);
			if(m.find() || m2.find()) {
					bw.write(line);
					bw.newLine();	
			}
			else if((line.contains(className) || line.contains("public"+" "+className+"(") || line.contains("private"+" "+className+"(")) && !(line.contains("."+className)) && !(line.contains("void") && line.contains("main")) 
					&& !((line.contains("public"+" "+"int") || line.contains("private"+" "+"int") || line.contains("protected"+" "+"int")) && line.contains(className+"(")) && !((line.contains("public"+" "+"void") || line.contains("private"+" "+"void") || line.contains("protected"+" "+"void")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"float") || line.contains("private"+" "+"float") || line.contains("protected"+" "+"float")) && line.contains(className+"(")) && !((line.contains("public"+" "+"String") || line.contains("private"+" "+"String") || line.contains("protected"+" "+"String")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"double") || line.contains("private"+" "+"double") || line.contains("protected"+" "+"double")) && line.contains(className+"(")) && !((line.contains("public"+" "+"char") || line.contains("private"+" "+"char") || line.contains("protected"+" "+"char")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"short") || line.contains("private"+" "+"short") || line.contains("protected"+" "+"short")) && line.contains(className+"(")) && !((line.contains("public"+" "+"long") || line.contains("private"+" "+"long") || line.contains("protected"+" "+"long")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"static"+" "+"int") || line.contains("private"+" "+"static"+" "+"int") || line.contains("protected"+" "+"static"+" "+"int")) && line.contains(className+"(")) && !((line.contains("public"+" "+"static"+" "+"void") || line.contains("private"+" "+"static"+" "+"void") || line.contains("protected"+" "+"static"+" "+"void")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"static"+" "+"float") || line.contains("private"+" "+"static"+" "+"float") || line.contains("protected"+" "+"static"+" "+"float")) && line.contains(className+"(")) && !((line.contains("public"+" "+"static"+" "+"String") || line.contains("private"+" "+"static"+" "+"String") || line.contains("protected"+" "+"static"+" "+"String")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"static"+" "+"double") || line.contains("private"+" "+"static"+" "+"double") || line.contains("protected"+" "+"static"+" "+"double")) && line.contains(className+"(")) && !((line.contains("public"+" "+"static"+" "+"char") || line.contains("private"+" "+"static"+" "+"char") || line.contains("protected"+" "+"static"+" "+"char")) && line.contains(className+"("))
					&& !((line.contains("public"+" "+"static"+" "+"short") || line.contains("private"+" "+"static"+" "+"short") || line.contains("protected"+" "+"static"+" "+"short")) && line.contains(className+"(")) && !((line.contains("public"+" "+"static"+" "+"long") || line.contains("private"+" "+"static"+" "+"long") || line.contains("protected"+" "+"static"+" "+"long")) && line.contains(className+"("))
					) {
				String newLine = line.replaceAll(className, updateCName);
				bw.write(newLine);
				bw.newLine();				
			}
			else if(line.contains(and)) {
				String newLine = line.replace(and, updateAnd);
				bw.write(newLine);
				bw.newLine();
			}
			else if(line.contains(or)) {
				String newLine = line.replace(or, updateOr);
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
