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


package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.hsk.operatorstorage.ArithmeticOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

//This class is created to generate arithmetic operator mutants of the original class
public class ArithmeticOperatorMutation {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to generate arithmetic operator mutants--------------
	public void generateArithOpMutantFiles() throws IOException {

		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuArithOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();

		List<String> opTwoL = new ArrayList<String>();
		List<String> opTwoP = new ArrayList<String>();

		int count = 0;
		int pointer = 0;
		int pointerTwo = 0;

		//-------------------Retrieve the arithmetic operators that are captured from original class----------------------
		ArithmeticOpStorage os = new ArithmeticOpStorage();
		os.processOp();
		opL = os.returnOpList();
		os.processList(opL);
		opP = os.retriveProcessList();
		opTwoL = os.returnOpTwoList();
		os.processTwoList(opTwoL);
		opTwoP = os.retriveProcessTwoList();


		//-------Get the class name (Temp)------------------
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();


		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		//-----------if arith op ist is not empty, proceed with generating mutants----------------
		if(opL.isEmpty() == false) {
			for(int i=0;i<opP.size();i++) {
				count++;
				if(i!=0 && i % 4 == 0) {
					pointer++;
				}
				String s = opL.get(pointer);
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 

				while((line = br.readLine()) != null) {

					if(line.contains(className) && !(line.contains(".") && line.contains(className+"(")) && !(line.contains("."+className))
							&& !(line.contains("int") && line.contains(className+"(")) && !(line.contains("void") && line.contains(className+"("))
							&& !(line.contains("float") && line.contains(className+"(")) && !(line.contains("String") && line.contains(className+"("))
							&& !(line.contains("double") && line.contains(className+"(")) && !(line.contains("char") && line.contains(className+"("))
							&& !(line.contains("short") && line.contains(className+"(")) && !(line.contains("long") && line.contains(className+"("))
							) {			
						String newLine = line.replaceAll(className, "MuArithOp"+count);
						bw.write(newLine);
						bw.newLine();							
					}
					else if(line.equalsIgnoreCase(s)) {
						String newLine = line.replace(line, opP.get(i));
						bw.write(newLine);
						bw.newLine();
						continue;
					}		
					else {
						bw.write(line);
						bw.newLine();
					}

				}
				br.close();
				bw.close();	
			}
		}

		//-----------if arith op ist is not empty, proceed with generting mutants----------------
		if(opTwoL.isEmpty() == false) {
			for(int k=0;k<opTwoP.size();k++) {
				count++;
				if(k!=0 && k % 1 == 0) {
					pointerTwo++;
				}
				String s = opTwoL.get(pointerTwo);
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 

				while((line = br.readLine()) != null) {

					if(line.contains(className) && !(line.contains(".") && line.contains(className+"(")) && !(line.contains("."+className))
							&& !(line.contains("int") && line.contains(className+"(")) && !(line.contains("void") && line.contains(className+"("))
							&& !(line.contains("float") && line.contains(className+"(")) && !(line.contains("String") && line.contains(className+"("))
							&& !(line.contains("double") && line.contains(className+"(")) && !(line.contains("char") && line.contains(className+"("))
							&& !(line.contains("short") && line.contains(className+"(")) && !(line.contains("long") && line.contains(className+"("))
							) {
						String newLine = line.replaceAll(className, "MuArithOp"+count);
						bw.write(newLine);
						bw.newLine();
					}
					else if(line.equalsIgnoreCase(s)) {	
						String newLine = line.replace(line, opTwoP.get(k));
						bw.write(newLine);
						bw.newLine();
						continue;
					}
					else {
						bw.write(line);
						bw.newLine();
					}

				}
				br.close();
				bw.close();	

			}
		}


		source.close();
		targetFile.close();
		System.out.println("Arithmetic Op Mutants generated: " + count);

	}

}
