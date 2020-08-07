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
import java.util.Arrays;
import java.util.List;

/*
 * This class is created to generate a copy of the dependent class (this is the second pass for
 * processing the dependent class)
 */
public class CreateTempCopyDependentClassSecondPass {

	private static String dPath;
	private static String mPath;
	private static String dClassName;
	private static String oClassName;

	//----------Method to get the dependent class path-----------------
	public void getDPath(String dependentCPath) {
		dPath = dependentCPath;
	}

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to get the dependent class name-----------------
	public void getCName(String cName) {
		dClassName = cName;
	}

	//----------Method to get the original class name-----------------
	public void getOCName(String oCName) {
		oClassName = oCName;
	}

	//----------Method to create copy of the dependent class (second pass)-----------------
	public void createClassCopy() throws IOException {

		List<String> copyArray = new ArrayList<String>();
		List<String> copyMArray = new ArrayList<String>();
		List<String> dependentMList = new ArrayList<String>();
		List<String> dependentList = new ArrayList<String>();

		//---------Get the files stored in Generated Mutants Folder---------------
		File directoryMPath = new File(mPath);
		String mContents[] = directoryMPath.list();
		copyMArray = Arrays.asList(mContents);

		//----------Store the FirstTemp class files from the Generated Mutants folder in the arraylist------------
		for(int i=0;i<copyMArray.size();i++) {
			if(copyMArray.get(i).contains(".java") && (copyMArray.get(i).contains("FirstTemp"))) {
				dependentMList.add(copyMArray.get(i));
			}
		}

		//----------Store the mutants from the Generated Mutants folder except the FirstTemp and Temp class files in the arraylist------------
		for(int i=0;i<copyMArray.size();i++) {
			if(copyMArray.get(i).contains(".java") && !(copyMArray.get(i).contains("FirstTemp")) && !(copyMArray.get(i).contains("Temp"))) {
				dependentList.add(copyMArray.get(i));
			}
		}

		//-----------process the dependentList to remove the .java extensions from the file names----------
		for(int i=0;i<dependentList.size();i++) {
			if(dependentList.get(i).contains(".java")) {
				String replaceWord = dependentList.get(i).replace(".java", "");
				dependentList.set(i, replaceWord);				
			}
		}

		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		int count = 0;

		
		//--------------If the arraylist is not empty, proceed and create equal number of copies of dependent class as there are mutant classes-----------------
		if(dependentMList.isEmpty() == false) {

			for(int k=0;k<dependentMList.size();k++) {
				count++;

				fr = new FileReader(mPath+"\\"+dClassName+"FirstTemp"+count+".java");
				br = new BufferedReader(fr);
				fw = new FileWriter(mPath+"\\"+dClassName+"Copy"+count+".java");
				bw = new BufferedWriter(fw);

				String line; 

				while((line = br.readLine()) != null) {
					if((line.contains(dClassName) || line.contains("public"+" "+dClassName+"(") || line.contains("private"+" "+dClassName+"(")) && !(line.contains("."+dClassName+"(")) && !(line.contains(".") && line.contains(dClassName+"(")) && !(line.contains("void") && line.contains("main"))
							&& !((line.contains("public"+" "+"int") || line.contains("private"+" "+"int") || line.contains("protected"+" "+"int")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"void") || line.contains("private"+" "+"void") || line.contains("protected"+" "+"void")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"float") || line.contains("private"+" "+"float") || line.contains("protected"+" "+"float")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"String") || line.contains("private"+" "+"String") || line.contains("protected"+" "+"String")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"double") || line.contains("private"+" "+"double") || line.contains("protected"+" "+"double")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"char") || line.contains("private"+" "+"char") || line.contains("protected"+" "+"char")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"short") || line.contains("private"+" "+"short") || line.contains("protected"+" "+"short")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"long") || line.contains("private"+" "+"long") || line.contains("protected"+" "+"long")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"int") || line.contains("private"+" "+"static"+" "+"int") || line.contains("protected"+" "+"static"+" "+"int")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"void") || line.contains("private"+" "+"static"+" "+"void") || line.contains("protected"+" "+"static"+" "+"void")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"float") || line.contains("private"+" "+"static"+" "+"float") || line.contains("protected"+" "+"static"+" "+"float")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"String") || line.contains("private"+" "+"static"+" "+"String") || line.contains("protected"+" "+"static"+" "+"String")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"double") || line.contains("private"+" "+"static"+" "+"double") || line.contains("protected"+" "+"static"+" "+"double")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"char") || line.contains("private"+" "+"static"+" "+"char") || line.contains("protected"+" "+"static"+" "+"char")) && line.contains(dClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"short") || line.contains("private"+" "+"static"+" "+"short") || line.contains("protected"+" "+"static"+" "+"short")) && line.contains(dClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"long") || line.contains("private"+" "+"static"+" "+"long") || line.contains("protected"+" "+"static"+" "+"long")) && line.contains(dClassName+"("))
							) {
						String newLine = line.replaceAll(dClassName, dClassName+"Copy"+count);
						bw.write(newLine);
						bw.newLine();
					}
					else if((line.contains(oClassName) || line.contains("public"+" "+oClassName+"(") || line.contains("private"+" "+oClassName+"(")) && !(line.contains("."+oClassName)) && !(line.contains("void") && line.contains("main"))
							&& !((line.contains("public"+" "+"int") || line.contains("private"+" "+"int") || line.contains("protected"+" "+"int")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"void") || line.contains("private"+" "+"void") || line.contains("protected"+" "+"void")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"float") || line.contains("private"+" "+"float") || line.contains("protected"+" "+"float")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"String") || line.contains("private"+" "+"String") || line.contains("protected"+" "+"String")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"double") || line.contains("private"+" "+"double") || line.contains("protected"+" "+"double")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"char") || line.contains("private"+" "+"char") || line.contains("protected"+" "+"char")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"short") || line.contains("private"+" "+"short") || line.contains("protected"+" "+"short")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"long") || line.contains("private"+" "+"long") || line.contains("protected"+" "+"long")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"int") || line.contains("private"+" "+"static"+" "+"int") || line.contains("protected"+" "+"static"+" "+"int")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"void") || line.contains("private"+" "+"static"+" "+"void") || line.contains("protected"+" "+"static"+" "+"void")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"float") || line.contains("private"+" "+"static"+" "+"float") || line.contains("protected"+" "+"static"+" "+"float")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"String") || line.contains("private"+" "+"static"+" "+"String") || line.contains("protected"+" "+"static"+" "+"String")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"double") || line.contains("private"+" "+"static"+" "+"double") || line.contains("protected"+" "+"static"+" "+"double")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"char") || line.contains("private"+" "+"static"+" "+"char") || line.contains("protected"+" "+"static"+" "+"char")) && line.contains(oClassName+"("))
							&& !((line.contains("public"+" "+"static"+" "+"short") || line.contains("private"+" "+"static"+" "+"short") || line.contains("protected"+" "+"static"+" "+"short")) && line.contains(oClassName+"(")) && !((line.contains("public"+" "+"static"+" "+"long") || line.contains("private"+" "+"static"+" "+"long") || line.contains("protected"+" "+"static"+" "+"long")) && line.contains(oClassName+"("))
							) {
						String newLine = line.replaceAll(oClassName, dependentList.get(k));
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
		}

		fr.close();
		fw.close();

	}

}
