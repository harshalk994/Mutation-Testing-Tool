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
		String tempFileName = mPath+"/Temp.java";

		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if((line.contains("System.out.println") && line.contains("\"")) || (line.contains("\"") && line.contains("+")) || (line.contains("\"") && line.contains("*")) || (line.contains("\"") && line.contains("/")) || (line.contains("\"") && line.contains("-")) || (line.contains("\"") && line.contains("%")))
				continue;
			
			if(line.contains("System.lineSeparator") && line.contains("+"))
				continue;
			
			if(line.contains("String") && line.contains("+"))
				continue;
			
			if(line.contains("toString") && line.contains("+"))
				continue;
				
			if(line.contains("replace") && line.contains("+"))
				continue;
			
			if(line.contains("Buffer") && line.contains("+"))
				continue;
			
			if(line.contains("System.exit"))
				continue;
			
			if(line.contains("getKeyChar()"))
				continue;
			
			if(line.contains("out.println"))
				continue;
			
			if(line.contains("File"))
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
			
			if(line.contains("throw") || line.contains("throws"))
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

			if((line.contains("+") && !(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("--")) && !(line.contains("return+")) && !(line.contains("return +")) && !(line.contains("return++")) && !(line.contains("return ++")) && !(line.contains("e+")) && !(line.contains("Double") && line.contains("+")) && !(line.contains("Float") && line.contains("+"))) || (line.contains("-") && !(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("return-")) && !(line.contains("return -")) && !(line.contains("return--")) && !(line.contains("return --")) && !(line.contains("e-")) && !(line.contains("Double") && line.contains("-")) && !(line.contains("Float") && line.contains("-")))  || (line.contains("*") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("*="))) || (line.contains("/")) && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("/=")) || (line.contains("%") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("%=")))) {
				if((line.contains("+") || line.contains("-")) && (line.contains("L"+";") || line.contains("L"+")"))) {
					String[] words = line.split("");
					for(int i=0;i<words.length-1;i++) {
						if(words[i].contains("+") || words[i].contains("-")) {
							for(int j=i+1;j<words.length;j++) {
								if(words[j].contains("L") && (words[j+1].contains(";") || words[j+1].contains(")"))) {
									String newLine=String.join("", words);
									opTwoL.add(newLine);
								}
							}
						}
					}
				}else {
					opList.add(line);
				}
			}

			if(line.contains("=+") || line.contains("= +")) {
				opTwoL.add(line);
			}

			if(line.contains("=-") || line.contains("= -")) {
				opTwoL.add(line);
			}
			
			if(line.contains("e+") || line.contains("e-") || (line.contains("Double") && (line.contains("+") || line.contains("-"))) || (line.contains("Float") && (line.contains("+") || line.contains("-")))) {
				opTwoL.add(line);
			}
		}
		br.close();
		fr.close();
	}

	//-------Method to return the arithmetic operator list generated using operators found in original class-------------
	public List<String> returnOpList(){
//		System.out.println("========PRINTING LIST=========");
//		for(int i=0;i<opList.size();i++) {
//			System.out.println(opList.get(i));
//		}
		return opList;
	}

	//-------Method to return the arithmetic operator list generated using operators found in original class-------------
	public List<String> returnOpTwoList(){
//		System.out.println("========PRINTING LIST TWO=========");
//		for(int i=0;i<opTwoL.size();i++) {
//			System.out.println(opTwoL.get(i));
//		}
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
//		System.out.println("===========PRINT MODIFIED LIST============");
//		for(int i=0;i<opP.size();i++) {
//			System.out.println(opP.get(i));
//		}
		return opP;
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessTwoList(){
//		System.out.println("===========PRINT MODIFIED LIST TWO============");
//		for(int i=0;i<opTwoP.size();i++) {
//			System.out.println(opTwoP.get(i));
//		}
		return opTwoP;
	}
}
