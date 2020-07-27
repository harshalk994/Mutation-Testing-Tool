//	This class is created to generate copies of the dependent class test case

package com.hsk.testreplicator;
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

public class TestReplicatorDependentClass {

	private static String mutantsPath;
	private static String tmPath;
	private static String nameOfTestClass;
	private static String nameOfClassUnderTest;
	private static String mPath;
	private static String fPath;
	private static String originalClassName;
	private static String className;
	private static String dependentClassName;

	//----------Method to get the Generated Test Copies folder path-----------------
	public void getTMPath(String testFilePath) {
		tmPath = testFilePath;
	}

	//----------Method to get the Generated Mutants folder path-----------------
	public void getMPath(String mutantPath) {
		mutantsPath = mutantPath;
	}

	//----------Method to get the name of class that is being tested by the test case-----------------
	public void getClassName(String cName) {
		nameOfClassUnderTest = cName;
	}

	//----------Method to get the Generated Test Copies folder path-----------------
	public void getPath(String testFilePath) {
		mPath = testFilePath;
	}

	//----------Method to get the original test path-----------------
	public void getFPath(String originalTestPath) {
		fPath = originalTestPath;
	}

	//----------Method to get the dependent class name-----------------
	public void getDClName(String dName) {
		dependentClassName = dName;
	}

	//----------Method to get the original class name-----------------
	public void getOriginalClassName(String oCName) {
		originalClassName = oCName;
	}

	//------------Method to generate equal number of test copies as the number of mutants--------------
	public void testReplicator() throws IOException {
		SetClassNameProperty scp = new SetClassNameProperty();
		String mLocation=mPath;
		String[] locatewords = fPath.split("\\\\");

		//----------Extract the original test case name from the dependent test path--------------
		for(int i=0;i<locatewords.length;i++) {
			if(locatewords[i].contains(".java")) {
				String newW = locatewords[i].replace(".java", "");
				className = newW;
			}
		}


		nameOfTestClass = className;
		System.out.println("Name of test class is: " + nameOfTestClass);
		File directoryPath = new File(mutantsPath);
		List<String> removeNull = new ArrayList<String>();
		List<String> removeDNull = new ArrayList<String>();

		//Array of all files in the Generated Mutants Folder
		String contents[] = directoryPath.list();

		//This array will store list of mutants apart from the Temp file
		String trimmedStr[] = new String[contents.length-1];

		//This array will extract and store the class names of the mutants
		String className[] = new String[trimmedStr.length];

		for(int i=0; i<contents.length; i++) {
			if(contents[i].contains(".java")) {
				contents[i]  = contents[i].replace(".java", "");
			}         
		}


		for(int l=0, m=0; l<contents.length; l++) {
			if(contents[l].matches("Temp") || (contents[l].contains("Mu") && contents[l].contains("Op"))) 
				continue;

			trimmedStr[m++] = contents[l];
		}

		for(String s : trimmedStr) {
			if(s!=null && s.length() > 0) {
				removeNull.add(s);
			}
		}

		trimmedStr = removeNull.toArray(new String[removeNull.size()]);

		for(int v=0, a=0;v<trimmedStr.length;v++,a++) {
			className[a] = trimmedStr[v];
			className[a]=className[a]+"Test";
		}

		for(String s : className) {
			if(s!=null && s.length() > 0) {
				removeDNull.add(s);
			}
		}

		className = removeDNull.toArray(new String[removeDNull.size()]);


		FileReader source = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		String location=tmPath;

		//-------Generate copies of the dependent class tests-----------------
		for(int k=0, p=0;k<trimmedStr.length;k++,p++) {
			source = new FileReader(fPath);
			br = new BufferedReader(source);
			fw = new FileWriter(location+"\\"+className[p]+".java");
			bw = new BufferedWriter(fw);

			String line;

			while((line = br.readLine()) != null) {
				if(line.contains(nameOfTestClass)) {
					String[] words = line.split(" ");

					for(int i=0;i<words.length;i++) {
						if(words[i].contains(nameOfTestClass)) {
							String temp = className[p];
							words[i] = temp;
						}
					}

					String newLine = String.join(" ", words);
					bw.write(newLine);
					bw.newLine();
				}
				else if(line.contains(dependentClassName)) {		
					String newLine = line.replaceAll(dependentClassName, trimmedStr[k]);
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
		fw.close();
		System.out.println("Test Copies generated in the specified folder.");

	}


}
