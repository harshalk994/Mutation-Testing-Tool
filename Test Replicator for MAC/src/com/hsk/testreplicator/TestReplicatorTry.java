//	This class is created to generate copies of the original class test case

package com.hsk.testreplicator;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.userinputs.SetClassNameProperty;

public class TestReplicatorTry {

	private static String mutantsPath;
	private static String tmPath;
	private static String nameOfTestClass;
	private static String nameOfClassUnderTest;
	private static String mPath;
	private static String fPath;
	private static String className;

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

	//------------Method to generate equal number of test copies as the number of mutants--------------
	public void testReplicator() throws IOException {
		SetClassNameProperty scp = new SetClassNameProperty();
		String mLocation=mPath;
		String[] locatewords = fPath.split("/");

		//----------Extract the original test case name from the original test path--------------
		for(int i=0;i<locatewords.length;i++) {
			if(locatewords[i].contains(".java")) {
				String newW = locatewords[i].replace(".java", "");
				className = newW;
			}
		}


		nameOfTestClass = className;
		File directoryPath = new File(mutantsPath);
		List<String> removeNull = new ArrayList<String>();

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
			if(contents[l].contains("Temp")) 
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

		FileReader source = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		String location=tmPath;

		//-------Generate copies of the dependent class tests-----------------
		for(int k=0, p=0;k<trimmedStr.length;k++,p++) {
			source = new FileReader(fPath);
			br = new BufferedReader(source);
			fw = new FileWriter(location+"/"+className[p]+".java");
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
				else if(line.contains(nameOfClassUnderTest)) {
					String newLine = line.replaceAll(nameOfClassUnderTest, trimmedStr[k]);
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
