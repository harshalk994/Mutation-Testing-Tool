//	This class is created to store arithmetic operators found in the original class

package com.hsk.operatorstorage;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticOpStorage {

	List<String> opList = new ArrayList<String>();
	List<String> opP = new ArrayList<String>();
	List<String> opTwoL = new ArrayList<String>();
	List<String> opTwoP = new ArrayList<String>();

	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store arithmetic operators found in original class--------------
	public void processOp() throws IOException{
		String tempFileName = mPath+"\\Temp.java";

		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if((line.contains("System.out.println") && line.contains("\"")) || (line.contains("\"") && line.contains("+")) || (line.contains("\"") && line.contains("*")) || (line.contains("\"") && line.contains("/")) || (line.contains("\"") && line.contains("-")) || (line.contains("\"") && line.contains("%")))
				continue;

			if(line.contains("case"))
				continue;

			if(line.contains("\""))
				continue;

			if(line.contains("switch"))
				continue;

			if(line.contains("import"))
				continue;

			if(line.contains("return")) {
				if((line.contains("return-") || line.contains("return -") || line.contains("return+") || line.contains("return +")) && (!(line.contains("return--")) && !(line.contains("return --")) && !(line.contains("return++")) && !(line.contains("return ++")))) {
					opTwoL.add(line);
				}
			}

			if(line.contains("\"+\"") || line.contains("\"-\"") || line.contains("\"*\"") || line.contains("\"/\"") || line.contains("\"%\""))
				continue;

			if(line.contains("'+'") || line.contains("'-'") || line.contains("'*'") || line.contains("'/'") || line.contains("'%'"))
				continue;

			if((line.contains("+") && !(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("--")) && !(line.contains("return+")) && !(line.contains("return +")) && !(line.contains("return++")) && !(line.contains("return ++"))) || (line.contains("-") && !(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("return-")) && !(line.contains("return -")) && !(line.contains("return--")) && !(line.contains("return --")))  || (line.contains("*") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("*="))) || (line.contains("/")) && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("/=")) || (line.contains("%") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("%=")))) {
				opList.add(line);
			}

			if(line.contains("=+") || line.contains("= +")) {
				opTwoL.add(line);
			}

			if(line.contains("=-") || line.contains("= -")) {
				opTwoL.add(line);
			}
		}
		br.close();
		fr.close();
	}

	//-------Method to return the arithmetic operator list generated using operators found in original class-------------
	public List<String> returnOpList(){
		return opList;
	}

	//-------Method to return the arithmetic operator list generated using operators found in original class-------------
	public List<String> returnOpTwoList(){
		return opTwoL;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> opL ) {
		for(String s : opL) {
			if(s.contains("-")) {
				opP.add(s.replace("-", "+"));
				opP.add(s.replace("-", "*"));
				opP.add(s.replace("-", "/"));
				opP.add(s.replace("-", "%"));
			}
			if(s.contains("+")) {
				opP.add(s.replace("+", "-"));
				opP.add(s.replace("+", "*"));
				opP.add(s.replace("+", "/"));
				opP.add(s.replace("+", "%"));
			}
			if(s.contains("*")) {
				opP.add(s.replace("*", "+"));
				opP.add(s.replace("*", "-"));
				opP.add(s.replace("*", "/"));
				opP.add(s.replace("*", "%"));
			}
			if(s.contains("/")) {
				opP.add(s.replace("/", "+"));
				opP.add(s.replace("/", "-"));
				opP.add(s.replace("/", "*"));
				opP.add(s.replace("/", "%"));
			}
			if(s.contains("%")) {
				opP.add(s.replace("%", "+"));
				opP.add(s.replace("%", "-"));
				opP.add(s.replace("%", "*"));
				opP.add(s.replace("%", "/"));
			}	
		}
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processTwoList(List<String> opTwoL ) {
		for(String s : opTwoL) {
			if(s.contains("-")) {
				opTwoP.add(s.replace("-", "+"));
			}
			if(s.contains("+")) {
				opTwoP.add(s.replace("+", "-"));
			}	
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessList(){
		return opP;
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessTwoList(){
		return opTwoP;
	}
}
