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
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This class is created to store arithmetic operators found in the original class
public class ArithmeticOpStorage {

	List<String> opList = new ArrayList<String>();
	List<String> opP = new ArrayList<String>();
	List<String> opTwoL = new ArrayList<String>();
	List<String> opTwoP = new ArrayList<String>();

	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store arithmetic operators found in original class--------------
	public void processOp() throws IOException{
		String tempFileName = mPath+"\\Temp.java";

		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if((line.contains("System.out.println") && line.contains("\"")) || (line.contains("\"") && line.contains("+")) || (line.contains("\"") && line.contains("*")) || (line.contains("\"") && line.contains("/")) || (line.contains("\"") && line.contains("-")) || (line.contains("\"") && line.contains("%")))
				continue;
			
			if(line.contains("System.exit"))
				continue;
			
			if(line.contains("getKeyChar()"))
				continue;
			
			if(line.contains("out.println"))
				continue;
			
			if(line.contains("@"))
				continue;

			if(line.contains("case"))
				continue;

			if(line.contains("\""))
				continue;

			if(line.contains("switch"))
				continue;

			if(line.contains("import"))
				continue;

			if(line.contains("\"+\"") || line.contains("\"-\"") || line.contains("\"*\"") || line.contains("\"/\"") || line.contains("\"%\""))
				continue;

			if(line.contains("'+'") || line.contains("'-'") || line.contains("'*'") || line.contains("'/'") || line.contains("'%'"))
				continue;
			
			if(line.contains("->") || line.contains("<-") || line.contains("=>"))
				continue;
			
			if(line.contains("return")) {
				if((line.contains("return-") || line.contains("return -") || line.contains("return+") || line.contains("return +")) && (!(line.contains("return--")) && !(line.contains("return --")) && !(line.contains("return++")) && !(line.contains("return ++")))) {
					opTwoL.add(line);
				}
			}

			if((line.contains("+") && !(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("--")) && !(line.contains("return+")) && !(line.contains("return +")) && !(line.contains("return++")) && !(line.contains("return ++"))) || (line.contains("-") && !(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("return-")) && !(line.contains("return -")) && !(line.contains("return--")) && !(line.contains("return --")))  || (line.contains("*") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("*="))) || (line.contains("/")) && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("/=")) || (line.contains("%") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("%=")))) {
				opList.add(line);
			}

			if(line.contains("=+") || line.contains("= +")) {
				opTwoL.add(line);
			}

			if(line.contains("=-") || line.contains("= -")) {
				opTwoL.add(line);
			}
		}
		br.close();
		fr.close();
	}

	//-------Method to return the arithmetic operator list generated using operators found in original class-------------
	public List<String> returnOpList(){
		return opList;
	}

	//-------Method to return the arithmetic operator list generated using operators found in original class-------------
	public List<String> returnOpTwoList(){
		return opTwoL;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> opL ) {
		for(String s : opL) {
			if(s.contains("-")) {
				opP.add(s.replace("-", "+"));
				opP.add(s.replace("-", "*"));
				opP.add(s.replace("-", "/"));
				opP.add(s.replace("-", "%"));
			}
			if(s.contains("+")) {
				opP.add(s.replace("+", "-"));
				opP.add(s.replace("+", "*"));
				opP.add(s.replace("+", "/"));
				opP.add(s.replace("+", "%"));
			}
			if(s.contains("*")) {
				opP.add(s.replace("*", "+"));
				opP.add(s.replace("*", "-"));
				opP.add(s.replace("*", "/"));
				opP.add(s.replace("*", "%"));
			}
			if(s.contains("/")) {
				opP.add(s.replace("/", "+"));
				opP.add(s.replace("/", "-"));
				opP.add(s.replace("/", "*"));
				opP.add(s.replace("/", "%"));
			}
			if(s.contains("%")) {
				opP.add(s.replace("%", "+"));
				opP.add(s.replace("%", "-"));
				opP.add(s.replace("%", "*"));
				opP.add(s.replace("%", "/"));
			}	
		}
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processTwoList(List<String> opTwoL ) {
		for(String s : opTwoL) {
			if(s.contains("-")) {
				opTwoP.add(s.replace("-", "+"));
			}
			if(s.contains("+")) {
				opTwoP.add(s.replace("+", "-"));
			}	
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessList(){
		return opP;
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessTwoList(){
		return opTwoP;
	}
}
