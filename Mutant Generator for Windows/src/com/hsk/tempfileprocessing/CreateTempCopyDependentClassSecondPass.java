//	This class is created to generate a copy of the dependent class (this is the second pass for processing the dependent class)

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
				fw = new FileWriter(mPath+"\\"+dClassName+"SecondTemp"+count+".java");
				bw = new BufferedWriter(fw);

				String line; 

				while((line = br.readLine()) != null) {
					if(line.contains(dClassName)) {
						String newLine = line.replaceAll(dClassName, dClassName+"SecondTemp"+count);
						bw.write(newLine);
						bw.newLine();
					}
					else if(line.contains(oClassName)) {
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