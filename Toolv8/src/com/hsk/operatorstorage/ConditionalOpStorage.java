//	This class is created to store conditional operators found in the original class

package com.hsk.operatorstorage;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConditionalOpStorage {
	private static List<String> conditionOpL = new ArrayList<String>();
	private static List<String> conditionOpP = new ArrayList<String>();
	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store conditional operators found in original class--------------
	public void processOp() throws IOException{
		String tempFileName = mPath+"\\Temp.java";
		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;

		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println") && line.contains("+"))
				continue;

			if(line.contains("'&&'") || line.contains("'||'"))
				continue;

			if(line.contains("&&") && !(line.contains("\"&&\""))) {
				conditionOpL.add(line);
			}else if(line.contains("||") && !(line.contains("\"||\""))) {
				conditionOpL.add(line);
			}	
		}

		br.close();
		fr.close();
	}

	//-------Method to return the conditional operator list generated using operators found in original class-------------
	public List<String> returnOpList(){
		return conditionOpL;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> conditionOpL ) {
		for(String s : conditionOpL) {
			if(s.contains("&&")) {
				conditionOpP.add(s.replace("&&", "||"));

			}
			if(s.contains("||")) {
				conditionOpP.add(s.replace("||", "&&"));
			}
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessList(){
		return conditionOpP;
	}
}
