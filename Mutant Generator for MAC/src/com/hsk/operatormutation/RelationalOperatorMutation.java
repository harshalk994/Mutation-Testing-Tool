//	This class is created to generated relational operator mutants of the original class

package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.operatorstorage.RelationalOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

public class RelationalOperatorMutation {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to generate relational operator mutants-------------
	public void generateRelOpMutantFiles() throws IOException {

		//-------Get the class name (Temp)------------------
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"/Temp.java";
		String mutantFileName = mPath+"/MuRelOp";
		List<String> opL = new ArrayList<String>();
		List<String> opP = new ArrayList<String>();
		List<String> opLNotEq = new ArrayList<String>();
		List<String> opPNotEq = new ArrayList<String>();

		int count = 0;
		int pointer = 0;
		int pointerTwo = 0;

		//-------------------Retrieve the relational operators that are captured from original class----------------------
		RelationalOpStorage rs = new RelationalOpStorage();
		opL = rs.returnRelOpList();
		rs.processList(opL);
		opP = rs.retriveRelProcessList();	
		opLNotEq = rs.returnRelOpListNotEqual();
		rs.processListNotEqual(opLNotEq);
		opPNotEq = rs.retriveRelProcessListNotEq();

		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		//-----------if op ist is not empty, proceed with generating mutants----------------
		if(opL.isEmpty() == false) {
			for(int i=0;i<opP.size();i++) {
				count++;
				if(i!=0 && i % 5 == 0) {
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
						String newLine = line.replaceAll(className, "MuRelOp"+count);
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
		}

		//-----------if op ist is not empty, proceed with generating mutants----------------
		if(opLNotEq.isEmpty() == false) {
			for(int i=0;i<opPNotEq.size();i++) {
				count++;
				if(i!=0 && i % 1 == 0) {
					pointerTwo++;
				}
				String s = opLNotEq.get(pointerTwo);
				System.out.println(opLNotEq.get(pointerTwo));
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				while((line = br.readLine()) != null) {
					if(line.contains(className)) {
						String newLine = line.replaceAll(className, "MuRelOp"+count);
						bw.write(newLine);
						bw.newLine();					
					}
					else if(line.equalsIgnoreCase(s)) {
						String newLine = line.replace(line, opPNotEq.get(i));
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


		source.close();
		targetFile.close();
		System.out.println("Relational Op Mutants generated: " + count);

	}


}
