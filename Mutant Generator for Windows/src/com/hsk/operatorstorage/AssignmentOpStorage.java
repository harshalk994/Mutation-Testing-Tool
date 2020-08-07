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


package com.hsk.operatorstorage;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This class is created to store assignment operators found in the original class
public class AssignmentOpStorage {

	private static List<String> assignmentArithOpL = new ArrayList<String>();
	private static List<String> assignmentArithOpP = new ArrayList<String>();

	private static List<String> assignmentBitwiseOpL = new ArrayList<String>();
	private static List<String> assignmentBitwiseOpP = new ArrayList<String>();

	private static List<String> assignmentShiftOpL = new ArrayList<String>();
	private static List<String> assignmentShiftOpP = new ArrayList<String>();


	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store assignment operators found in original class--------------	
	public void processOp() throws IOException{

		String tempFileName = mPath+"\\Temp.java";

		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println") && line.contains("+"))
				continue;

			if(line.contains("out.println"))
				continue;
			
			if(line.contains("\""))
				continue;
			
			if(line.contains("@"))
				continue;
			
			if(line.contains("'+='") || line.contains("'-='") || line.contains("'*='") || line.contains("'/='") || line.contains("'%='") || line.contains("'&='") || line.contains("'|='") || line.contains("'^='") || line.contains("'<<='") || line.contains("'>>='"))
				continue;

			if(line.contains("+=") && !(line.contains("\"+=\""))) {
				assignmentArithOpL.add(line);
			}else if(line.contains("-=") && !(line.contains("\"-=\""))) {
				assignmentArithOpL.add(line);
			}else if(line.contains("*=") && !(line.contains("\"*=\""))) {
				assignmentArithOpL.add(line);
			}else if(line.contains("/=") && !(line.contains("\"/=\""))) {
				assignmentArithOpL.add(line);
			}else if(line.contains("%=") && !(line.contains("\"%=\""))) {
				assignmentArithOpL.add(line);
			}else if(line.contains("&=") && !(line.contains("\"&=\""))) {
				assignmentBitwiseOpL.add(line);
			}else if(line.contains("|=") && !(line.contains("\"|=\""))) {
				assignmentBitwiseOpL.add(line);
			}else if(line.contains("^=") && !(line.contains("\"^=\""))) {
				assignmentBitwiseOpL.add(line);
			}else if(line.contains(">>=") && !(line.contains("\">>=\""))) {
				assignmentShiftOpL.add(line);
			}else if(line.contains("<<=") && !(line.contains("\"<<=\""))) {
				assignmentShiftOpL.add(line);
			}
		}

		br.close();
		fr.close();
	}

	//-------Method to return the arithmetic assignment operator list generated using operators found in original class-------------
	public List<String> returnArithAssignOpList(){
		return assignmentArithOpL;
	}

	//-------Method to return the bitwise assignment operator list generated using operators found in original class-------------
	public List<String> returnBitwiseAssignOpList(){
		return assignmentBitwiseOpL;
	}

	//-------Method to return the shift assignment operator list generated using operators found in original class-------------
	public List<String> returnShiftAssignOpList(){
		return assignmentShiftOpL;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processArithAssignList(List<String> assignmentArithOpL ) {
		for(String s : assignmentArithOpL) {
			if(s.contains("+=")) {
				assignmentArithOpP.add(s.replace("+=", "="));
				assignmentArithOpP.add(s.replace("+=", "-="));
				assignmentArithOpP.add(s.replace("+=", "*="));
				assignmentArithOpP.add(s.replace("+=", "/="));
				assignmentArithOpP.add(s.replace("+=", "%="));
			}
			if(s.contains("-=")) {
				assignmentArithOpP.add(s.replace("-=", "="));
				assignmentArithOpP.add(s.replace("-=", "+="));
				assignmentArithOpP.add(s.replace("-=", "*="));
				assignmentArithOpP.add(s.replace("-=", "/="));
				assignmentArithOpP.add(s.replace("-=", "%="));
			}
			if(s.contains("*=")) {
				assignmentArithOpP.add(s.replace("*=", "="));
				assignmentArithOpP.add(s.replace("*=", "+="));
				assignmentArithOpP.add(s.replace("*=", "-="));
				assignmentArithOpP.add(s.replace("*=", "/="));
				assignmentArithOpP.add(s.replace("*=", "%="));
			}
			if(s.contains("/=")) {
				assignmentArithOpP.add(s.replace("/=", "="));
				assignmentArithOpP.add(s.replace("/=", "+="));
				assignmentArithOpP.add(s.replace("/=", "-="));
				assignmentArithOpP.add(s.replace("/=", "*="));
				assignmentArithOpP.add(s.replace("/=", "%="));
			}
			if(s.contains("%=")) {
				assignmentArithOpP.add(s.replace("%=", "="));
				assignmentArithOpP.add(s.replace("%=", "+="));
				assignmentArithOpP.add(s.replace("%=", "-="));
				assignmentArithOpP.add(s.replace("%=", "*="));
				assignmentArithOpP.add(s.replace("%=", "/="));
			}
		}
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processBitwiseAssignList(List<String> assignmentBitwiseOpL ) {
		for(String s : assignmentBitwiseOpL) {
			if(s.contains("&=")) {
				assignmentBitwiseOpP.add(s.replace("&=", "|="));
				assignmentBitwiseOpP.add(s.replace("&=", "^="));
			}
			if(s.contains("|=")) {
				assignmentBitwiseOpP.add(s.replace("|=", "&="));
				assignmentBitwiseOpP.add(s.replace("|=", "^="));
			}
			if(s.contains("^=")) {
				assignmentBitwiseOpP.add(s.replace("^=", "&="));
				assignmentBitwiseOpP.add(s.replace("^=", "|="));
			}
		}
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processShiftAssignList(List<String> assignmentShiftOpL ) {
		for(String s : assignmentShiftOpL) {
			if(s.contains(">>=")) {
				assignmentShiftOpP.add(s.replace(">>=", "<<="));
			}
			if(s.contains("<<=")) {
				assignmentShiftOpP.add(s.replace("<<=", ">>="));
			}
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveArithAssignmentProcessList(){
		return assignmentArithOpP;
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveBitwiseAssignmentProcessList(){
		return assignmentBitwiseOpP;
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveShiftAssignmentProcessList(){
		return assignmentShiftOpP;
	}
}
