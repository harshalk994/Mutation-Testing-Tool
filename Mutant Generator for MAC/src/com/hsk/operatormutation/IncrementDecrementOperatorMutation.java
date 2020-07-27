//	This class is created to generated increment/decrement operator mutants of the original class

package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.hsk.operatorstorage.IncrementDecrementOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

public class IncrementDecrementOperatorMutation {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to generate inc/dec operator mutants--------------	
	public void generateIncDecOpMutantFiles() throws IOException {

		//-------Get the class name (Temp)------------------
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"/Temp.java";
		String mutantFileName = mPath+"/MuIncDecOp";
		List<String> incDecOpL = new ArrayList<String>();
		List<String> incDecOpP = new ArrayList<String>();

		int count = 0;
		int pointer = 0;

		//-------------------Retrieve the assignment operators that are captured from original class----------------------
		IncrementDecrementOpStorage ios = new IncrementDecrementOpStorage();
		ios.processOp();
		incDecOpL = ios.returnOpList();
		ios.processList(incDecOpL);
		incDecOpP = ios.retriveProcessList();


		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		//-----------proceed with generating mutants----------------
		for(int i=0;i<incDecOpP.size();i++) {
			count++;
			if(i!=0 && i % 1 == 0) {
				pointer++;
			}
			String s = incDecOpL.get(pointer);
			source = new FileReader(tempFileName);
			br = new BufferedReader(source);
			targetFile = new FileWriter(mutantFileName + count + ".java");
			bw = new BufferedWriter(targetFile);
			String line; 
			while((line = br.readLine()) != null) {
				if(line.contains(className)) {
					String newLine = line.replaceAll(className, "MuIncDecOp"+count);
					bw.write(newLine);
					bw.newLine();					
				}
				else if(line.equalsIgnoreCase(s)) {
					String newLine = line.replace(line, incDecOpP.get(i));
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


		source.close();
		targetFile.close();
		System.out.println("Increment/Decrement Op Mutants generated: " + count);

	}

}
