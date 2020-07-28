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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//This class is created to store conditional operators found in the original class
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
