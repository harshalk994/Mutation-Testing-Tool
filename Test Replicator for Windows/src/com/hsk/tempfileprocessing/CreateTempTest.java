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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.userinputs.SetClassNameProperty;

//This class is created to generate a temporary copy of the original test class
public class CreateTempTest {

	private static String mPath;
	private static String fPath;
	private static String nameOfClassUnderTest;

	private static String className;

	//----------Method to get the Generated Test Copies folder path-----------------
	public void getPath(String testFilePath) {
		mPath = testFilePath;
	}

	//----------Method to get the original test path-----------------
	public void getFPath(String originalTestPath) {
		fPath = originalTestPath;
	}

	//----------Method to get the name of class that is being tested by the test case-----------------
	public void getClassName(String cName) {
		nameOfClassUnderTest = cName;
	}

	//----------Method to create the temporary class file of the original test-----------------
	public void createTempCopy() throws IOException {

		SetClassNameProperty scp = new SetClassNameProperty();

		FileReader source = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		String location=mPath;
		String[] locatewords = fPath.split("\\\\");

		//----------Extract the original test case name from the original test path--------------
		for(int i=0;i<locatewords.length;i++) {
			if(locatewords[i].contains(".java")) {
				String newW = locatewords[i].replace(".java", "");
				className = newW;
				scp.setCName(className);

			}
		}

		String originalPath = fPath;
		String copyPath = mPath;


		source = new FileReader(originalPath);
		br = new BufferedReader(source);
		fw = new FileWriter(copyPath+"\\"+"TempTest.java");
		bw = new BufferedWriter(fw);

		String line;

		while((line = br.readLine()) != null) {
			if(line.contains(nameOfClassUnderTest)) {
				String[] words = line.split("");
				for(int s=0; s<words.length; s++) {
					if(words[s].contains("(")) {
						String replaceBrackets = words[s-1]+System.lineSeparator();
						if(words[s].contains("(") && words[s+1].contains(")")){
							String temp = replaceBrackets;
							words[s-1] = temp;

						}
					}
				}

				String newLine = String.join("", words);
				bw.write(newLine);
				bw.newLine();
			}else {
				bw.write(line);
				bw.newLine();
			}
		}
		br.close();
		bw.close();
		source.close();
		fw.close();
	}

}
