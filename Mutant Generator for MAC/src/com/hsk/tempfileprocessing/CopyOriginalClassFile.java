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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//This class is created to generate a copy of the original class and store it in the Generated Mutants folder
public class CopyOriginalClassFile {

	private static String mPath;
	private static String fPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to get the path of the original class-----------------
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	//----------Method to create a copy of the original class and store it in Generate Mutants folder-----------------
	public void createTempFile(String file) throws IOException {
		String tempFileName = mPath+"/OriginalTempCopy.java";

		FileReader source = new FileReader(file);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(tempFileName);
		BufferedWriter bw = new BufferedWriter(targetFile);

		String line;

		while((line = br.readLine()) != null) {
			bw.write(line);
			bw.newLine();	
		}
		br.close();
		bw.close();
		source.close();
		targetFile.close();
	}
}