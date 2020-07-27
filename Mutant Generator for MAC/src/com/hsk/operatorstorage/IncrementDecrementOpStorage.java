//	This class is created to store increment/decrement operators found in the original class

package com.hsk.operatorstorage;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IncrementDecrementOpStorage {
	
	List<String> incdecOpL = new ArrayList<String>();
	List<String> incdecOpP = new ArrayList<String>();
	private static String mPath;
	
	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store increment/decrement operators found in original class--------------
	public void processOp() throws IOException{
		String tempFileName = mPath+"/Temp.java";
	
		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		while((line = br.readLine()) != null) {
			if((line.contains("System.out.println") && line.contains("\"")) || (line.contains("\"") && line.contains("+")))
				continue;
			
			if(line.contains("'++'") || line.contains("'--'"))
				continue;
			
			if((line.contains("++") && !(line.contains("\"++\""))) || (line.contains("--") && !(line.contains("\"--\"")))) {	
					incdecOpL.add(line);
				}
		}
		br.close();
		fr.close();	
	}
	
	//-------Method to return the increment/decrement operator list generated using operators found in original class-------------
	public List<String> returnOpList(){
		return incdecOpL;
	}
	
	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> incdecOpL ) {
		for(String s : incdecOpL) {
			if(s.contains("--")) {;
				incdecOpP.add(s.replace("--", "++"));
			}
			if(s.contains("++")) {
				incdecOpP.add(s.replace("++", "--"));
			}	
		}
	}
	
	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveProcessList(){
		return incdecOpP;
	}
}
