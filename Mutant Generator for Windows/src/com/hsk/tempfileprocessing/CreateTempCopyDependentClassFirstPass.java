//	This class is created to generate a copy of the dependent class (this is the first pass for processing the dependent class)

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

public class CreateTempCopyDependentClassFirstPass {

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

	//----------Method to create copy of the dependent class (first pass)-----------------
	public void createTempCopy() throws IOException {

		List<String> copyArray = new ArrayList<String>();
		List<String> copyMArray = new ArrayList<String>();
		List<String> dependentMList = new ArrayList<String>();

		//---------Get the files stored in Generated Mutants Folder---------------
		File directoryMPath = new File(mPath);
		String mContents[] = directoryMPath.list();
		copyMArray = Arrays.asList(mContents);

		//----------Store the mutants from the Generated Mutants folder except the Temp file in the arraylist------------
		for(int i=0;i<copyMArray.size();i++) {
			if(copyMArray.get(i).contains(".java") && !(copyMArray.get(i).contains("Temp"))) {
				dependentMList.add(copyMArray.get(i));
			}
		}

		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		int arithPointer=0;
		int assignPointer=0;
		int bitwisePointer=0;
		int conditionPointer=0;
		int incDecPointer=0;
		int relPointer=0;
		int shiftPointer=0;

		int count=0;

		//--------------If the arraylist is not empty, proceed and create equal number of copies of dependent class as there are mutant classes-----------------
		if(dependentMList.isEmpty() == false) {

			for(int k=0;k<dependentMList.size();k++) {
				count++;

				fr = new FileReader(dPath);
				br = new BufferedReader(fr);
				fw = new FileWriter(mPath+"\\"+dClassName+"FirstTemp"+count+".java");
				bw = new BufferedWriter(fw);

				String line; 

				while((line = br.readLine()) != null) {
					if((line.contains("extends") || line.contains("class")) && !(line.contains("("))) {
						String[] words = line.split(" ");
						String replaceW = "extends"+System.lineSeparator();

						for(int i=0;i<words.length;i++) {
							if(words[i].contains("extends")) {
								String temp = replaceW;
								words[i] = temp;
							}
						}

						String newLine = String.join(" ", words);
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
