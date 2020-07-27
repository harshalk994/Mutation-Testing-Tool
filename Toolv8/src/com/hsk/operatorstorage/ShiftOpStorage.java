//	This class is created to store shift operators found in the original class

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

public class ShiftOpStorage {

	private static List<String> shiftOpL = new ArrayList<String>();
	private static List<String> shiftOpP = new ArrayList<String>();
	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store shift operators found in original class--------------
	public void processOp() throws IOException{

		String tempFileName = mPath+"\\Temp.java";

		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if(line.contains("'>>'") || line.contains("'<<'") || line.contains("'>>>'"))
				continue;

			if(line.contains(">>") && !(line.contains("\">>\"")) && !(line.contains(">>=")) && !(line.contains("\">>=\"")) && !(line.contains(">>>")) && !(line.contains("\">>>\"")) && !(line.contains(">>>=")) && !(line.contains("\">>>=\""))){
				shiftOpL.add(line);
			}else if(line.contains("<<") && !(line.contains("\"<<\"")) && !(line.contains("<<=")) && !(line.contains("\"<<=\""))) {
				shiftOpL.add(line);
			}else if(line.contains(">>>") && !(line.contains("\">>>\"")) && !(line.contains(">>>=")) && !(line.contains("\">>>=\""))) {
				shiftOpL.add(line);
			}	
		}

		br.close();
		fr.close();
	}

	//-------Method to return the shift operator list generated using operators found in original class-------------
	public List<String> returnShiftOpList(){
		return shiftOpL;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> shiftOpL ) throws IOException {
		for(String s : shiftOpL) {
			if(s.contains(">>") && !(s.contains(">>>"))) {
				shiftOpP.add(s.replace(">>", "<<"));
				shiftOpP.add(s.replace(">>", ">>>"));
			}
			if(s.contains("<<")) {
				shiftOpP.add(s.replace("<<", ">>"));
				shiftOpP.add(s.replace("<<", ">>>"));
			}
			if(s.contains(">>>") ) {
				shiftOpP.add(s.replace(">>>", ">>"));
				shiftOpP.add(s.replace(">>>", "<<"));
			}
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveShiftProcessList(){
		return shiftOpP;
	}
}
