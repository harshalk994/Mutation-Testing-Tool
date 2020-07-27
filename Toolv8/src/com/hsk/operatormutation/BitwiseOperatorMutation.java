//	This class is created to generate bitwise operator mutants of the original class

package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.operatorstorage.BitwiseOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

public class BitwiseOperatorMutation {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to generate bitwise operator mutants--------------
	public void generateBitwiseOpMutantFiles() throws IOException {

		//-------Get the class name (Temp)------------------
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"\\Temp.java";
		String mutantFileName = mPath+"\\MuBitwiseOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		int count = 0;
		int pointer = 0;

		//-------------------Retrieve the bitwise operators that are captured from original class----------------------
		BitwiseOpStorage bws = new BitwiseOpStorage();
		opL = bws.returnBitwiseOpList();
		bws.processList(opL);
		opP = bws.retriveBitwiseProcessList();

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
					String newLine = line.replaceAll(className, "MuBitwiseOp"+count);
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
		System.out.println("Bitwise Op Mutants generated: " + count);

	}


}
