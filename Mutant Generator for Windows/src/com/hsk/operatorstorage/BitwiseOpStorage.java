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


package com.hsk.operatorstorage;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This class is created to store bitwise operators found in the original class
public class BitwiseOpStorage {

	private static List<String> bitwiseOpL = new ArrayList<String>();
	private static List<String> bitwiseOpP = new ArrayList<String>();


	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store bitwise operators found in original class--------------
	public void processOp() throws IOException{

		String tempFileName = mPath+"\\Temp.java";

		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println") && line.contains("+"))
				continue;

			if(line.contains("@"))
				continue;
			
			if(line.contains("'&'") || line.contains("'|'") || line.contains("'^'"))
				continue;

			if(line.contains("&") && !(line.contains("\"&\"")) && !(line.contains("&&")) && !(line.contains("\"&&\"")) && !(line.contains("&=")) && !(line.contains("\"&=\""))){
				bitwiseOpL.add(line);
			}else if(line.contains("|") && !(line.contains("\"|\"")) && !(line.contains("||")) && !(line.contains("\"||\"")) && !(line.contains("|=")) && !(line.contains("\"|=\""))) {
				bitwiseOpL.add(line);
			}else if(line.contains("^") && !(line.contains("\"^\"")) && !(line.contains("^=")) && !(line.contains("\"^=\""))) {
				bitwiseOpL.add(line);
			}
		}

		br.close();
		fr.close();	
	}

	//-------Method to return the bitwise operator list generated using operators found in original class-------------
	public List<String> returnBitwiseOpList(){
		return bitwiseOpL;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> bitwiseOpL ) {
		for(String s : bitwiseOpL) {
			if(s.contains("&")) {
				bitwiseOpP.add(s.replace("&", "|"));
				bitwiseOpP.add(s.replace("&", "^"));
			}
			if(s.contains("|")) {
				bitwiseOpP.add(s.replace("|", "&"));
				bitwiseOpP.add(s.replace("|", "^"));
			}
			if(s.contains("^")) {
				bitwiseOpP.add(s.replace("^", "|"));
				bitwiseOpP.add(s.replace("^", "&"));
			}				
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveBitwiseProcessList(){
		return bitwiseOpP;
	}
}
