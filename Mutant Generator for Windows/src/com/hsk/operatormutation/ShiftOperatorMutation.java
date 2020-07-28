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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.operatorstorage.ShiftOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

//This class is created to generated shift operator mutants of the original class
public class ShiftOperatorMutation {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to generate shift operator mutants--------------
	public void generateShiftOpMutantFiles() throws IOException {

		//-------Get the class name (Temp)------------------
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuShiftOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		int count = 0;
		int pointer = 0;

		//-------------------Retrieve the shift operators that are captured from original class----------------------
		ShiftOpStorage ss = new ShiftOpStorage();
		opL = ss.returnShiftOpList();
		ss.processList(opL);
		opP = ss.retriveShiftProcessList();

		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		//-----------proceed with generating mutants----------------
		for(int i=0;i<opP.size();i++) {
			count++;
			if(i!=0 && i % 2 == 0) {
				pointer++;
			}
			String s = opL.get(pointer);
			source = new FileReader(tempFileName);
			br = new BufferedReader(source);
			targetFile = new FileWriter(mutantFileName + count + ".java");
			bw = new BufferedWriter(targetFile);
			String line; 
			while((line = br.readLine()) != null) {
				if(line.contains(className)) {
					String newLine = line.replaceAll(className, "MuShiftOp"+count);
					bw.write(newLine);
					bw.newLine();						
				}
				else if(line.equalsIgnoreCase(s)) {
					String newLine = line.replace(line, opP.get(i));
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
		}

		source.close();
		targetFile.close();
		System.out.println("Shift Op Mutants generated: " + count);

	}


}
