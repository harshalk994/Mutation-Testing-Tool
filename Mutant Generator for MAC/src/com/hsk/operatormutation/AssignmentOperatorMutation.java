//	This class is created to generate assignment operator mutants of the original class

package com.hsk.operatormutation;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.operatorstorage.AssignmentOpStorage;
import com.hsk.userinputs.SetClassNameProperty;

public class AssignmentOperatorMutation {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to generate assignment operator mutants--------------
	public void generateAssignmentOpMutantFiles() throws IOException {

		//-------Get the class name (Temp)------------------
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"/Temp.java";
		String mutantFileName = mPath+"/MuAssignmentOp";

		List<String> arithOpL = new ArrayList<String>();
		List<String> arithOpP = new ArrayList<String>();

		List<String> bitwiseOpL = new ArrayList<String>();
		List<String> bitwiseOpP = new ArrayList<String>();

		List<String> shiftOpL = new ArrayList<String>();
		List<String> shiftOpP = new ArrayList<String>();

		int count = 0;
		int arithPointer = 0;
		int bitwisePointer = 0;
		int shiftPointer = 0;

		//-------------------Retrieve the assignment operators that are captured from original class----------------------
		AssignmentOpStorage aos = new AssignmentOpStorage();
		arithOpL = aos.returnArithAssignOpList();
		aos.processArithAssignList(arithOpL);
		arithOpP = aos.retriveArithAssignmentProcessList();
		bitwiseOpL = aos.returnBitwiseAssignOpList();
		aos.processBitwiseAssignList(bitwiseOpL);
		bitwiseOpP = aos.retriveBitwiseAssignmentProcessList();	
		shiftOpL = aos.returnShiftAssignOpList();
		aos.processShiftAssignList(shiftOpL);
		shiftOpP = aos.retriveShiftAssignmentProcessList();

		FileReader source = null;
		BufferedReader br = null;
		FileWriter targetFile = null;
		BufferedWriter bw = null;

		//-----------if arithassignment op ist is not empty, proceed with generating mutants----------------
		if(arithOpL.isEmpty() == false) {
			for(int i=0;i<arithOpP.size();i++) {
				count++;
				if(i!=0 && i % 5 == 0) {
					arithPointer++;
				}
				String s = arithOpL.get(arithPointer);
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				while((line = br.readLine()) != null) {

					if(line.contains(className)) {
						String newLine = line.replaceAll(className, "MuAssignmentOp"+count);
						bw.write(newLine);
						bw.newLine();	
					}
					else if(line.equalsIgnoreCase(s)) {
						String newLine = line.replace(line, arithOpP.get(i));
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

		//-----------if bitwiseassignment op ist is not empty, proceed with generating mutants----------------
		if(bitwiseOpL.isEmpty()==false) {
			for(int i=0;i<bitwiseOpP.size();i++) {
				count++;
				if(i!=0 && i % 2 == 0) {
					bitwisePointer++;
				}
				String s = bitwiseOpL.get(bitwisePointer);
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				while((line = br.readLine()) != null) {
					if(line.contains(className)) {
						String newLine = line.replaceAll(className, "MuAssignmentOp"+count);
						//scp.setCName(updateCName);
						bw.write(newLine);
						bw.newLine();						
					}
					else if(line.equalsIgnoreCase(s)) {


						String newLine = line.replace(line, bitwiseOpP.get(i));
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


		//-----------if shiftassignment op ist is not empty, proceed with generating mutants----------------
		if(shiftOpL.isEmpty() == false) {
			for(int i=0;i<shiftOpP.size();i++) {
				count++;
				if(i!=0 && i % 1 == 0) {
					shiftPointer++;
				}
				String s = shiftOpL.get(shiftPointer);
				source = new FileReader(tempFileName);
				br = new BufferedReader(source);
				targetFile = new FileWriter(mutantFileName + count + ".java");
				bw = new BufferedWriter(targetFile);
				String line; 
				while((line = br.readLine()) != null) {
					if(line.contains(className)) {
						String newLine = line.replaceAll(className, "MuAssignmentOp"+count);
						bw.write(newLine);
						bw.newLine();							
					}
					else if(line.equalsIgnoreCase(s)) {


						String newLine = line.replace(line, shiftOpP.get(i));
						bw.write(newLine);
						bw.newLine();
					}else {
						bw.write(line);
						bw.newLine();
					}

				}
				br.close();
				bw.close();	
			}


		}

		System.out.println("Assignment Op Mutants generated: " + count);
		source.close();
		targetFile.close();


	}



}
