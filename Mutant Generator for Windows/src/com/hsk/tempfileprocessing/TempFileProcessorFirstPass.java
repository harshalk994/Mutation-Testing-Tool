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

		String tempFileName = mPath+"\\OriginalTempCopy.java";
		String newTempFile = mPath+"\\FirstTemp.java";

		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);

		String line;
		String updateCName = "FirstTemp";
		scp.setCName(updateCName);

		String and = "&&";
		String or = "||";
		String updateAnd = "&&\n";
		String updateOr = "||\n";

		while((line = br.readLine()) != null) {

			if(line.contains(className)) {
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
