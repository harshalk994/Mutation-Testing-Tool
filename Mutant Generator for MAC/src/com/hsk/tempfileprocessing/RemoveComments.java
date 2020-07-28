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

/*
 * This class is created to process the original class and remove comments 
 * from the file so that the size of the file can be reduced
*/ 
public class RemoveComments {
	
	private static String mPath;
	private static String fPath;
	
	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	//----------Method to get the original class folder path-----------------
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	
	//----------Method to remove comments from the original class file-----------------
	public void removeComments() throws IOException {
    	String tempFileName = mPath+"/OriginalTempCopy.java";
		
		Cleaner cleaner = new Cleaner();
		String line = cleaner.readFile();
		String newLine = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

		FileWriter fw = new FileWriter(tempFileName);
		fw.write(newLine);
		fw.close();
		
		System.out.println("Temp File generated successfully!!");
	}

}
