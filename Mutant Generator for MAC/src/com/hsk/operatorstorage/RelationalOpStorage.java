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

//This class is created to store relational operators found in the original class
public class RelationalOpStorage {

	private static List<String> conditionOpL = new ArrayList<String>();
	private static List<String> conditionOpP = new ArrayList<String>();
	private static List<String> conditionOpLNotEqual = new ArrayList<String>();
	private static List<String> conditionOpPNotEqual = new ArrayList<String>();
	
	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to store relational operators found in original class--------------
	public void processOp() throws IOException{
		String tempFileName = mPath+"/Temp.java";
		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String check = "="+System.lineSeparator();

		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println"))
				continue; 

			if(line.contains("out.println"))
				continue;
			
			if(line.contains("@"))
				continue;
			
			if(line.contains("<Temp>"))
				continue;
			
			if(line.contains("\""))
				continue;
			
			if(line.contains("List") || line.contains("ArrayList") || line.contains("LinkedList") || line.contains("Vector") || line.contains("Stack"))
				continue;

			if(line.contains("Set") || line.contains("HashSet") || line.contains("Hashtable") || line.contains("TreeSet") || line.contains("LinkedHashSet") || line.contains("EnumSet") || line.contains("CopyOnWriteArraySet"))
				continue;

			if(line.contains("Queue") || line.contains("Class<") || line.contains("PriorityQueue") || line.contains("Collection") || line.contains("Function") || line.contains("Iterable") || line.contains("Publisher") || line.contains("Flowable") || line.contains("Observable") || line.contains("Single") || line.contains("Maybe") || line.contains("Iterator") || line.contains("Comparator") || line.contains("LinkedBlockingQueue") || line.contains("ArrayBlockingQueue") || line.contains("PriorityBlockingQueue") || line.contains("DelayQueue") || line.contains("SynchronousQueue") || line.contains("BlockingQueue") || line.contains("TransferQueue") || line.contains("LinkedTransferQueue") || line.contains("LinkedQueue"))
				continue;

			if(line.contains("<String>") || line.contains("<Integer>") || line.contains("<Character>") || line.contains("<Boolean>") || line.contains("<Byte>") || line.contains("<Float>") || line.contains("<Long>") || line.contains("<Short>") || line.contains("<Double>"))
				continue;
			
			if(line.contains("ThreadLocal"))
				continue;
			
			if(line.contains("DirectoryStream"))
				continue;
			
			if(line.contains("Comparator"))
				continue;
			
			if(line.contains("Map") || line.contains("HashMap") || line.contains("TreeMap") || line.contains("EnumMap"))
				continue;
			
			if(line.contains("Constructor"))
				continue;

			if(line.contains("implements") || line.contains("extends") || line.contains("public") || line.contains("private") || line.contains("protected") || line.contains("this"))
				continue;
			
			if(line.contains("'>'") || line.contains("'<'") || line.contains("'=='") || line.contains("'!='") || line.contains("'>='") || line.contains("'<='"))
				continue;
			
			if(line.contains("->") || line.contains("<-") || line.contains("=>"))
				continue;

			if(line.contains(">") && !(line.contains("\">\"")) && !(line.contains(">=")) && !(line.contains("\">=\"")) && !(line.contains(">>")) && !(line.contains("\">>\"")) && !(line.contains(">>=")) && !(line.contains("\">>=\"")) && !(line.contains(">>>")) && !(line.contains("\">>>\""))){
				String[] words = line.split("");
				for(int i=0;i<words.length;i++) {
					if(words[i].contains(">") && !(words[i+1].contains(">"))){
						String newLine = String.join("", words);
						conditionOpL.add(newLine);
					}
				}
			}else if(line.contains("<") && !(line.contains("\"<\"")) && !(line.contains("<=")) && !(line.contains("\"<=\"")) && !(line.contains("<<=")) && !(line.contains("\"<<=\"")) && !(line.contains("<<")) && !(line.contains("\"<<\""))) {
				String[] words = line.split("");
				for(int i=0;i<words.length;i++) {
					if(words[i].contains("<") && !(words[i+1].contains("<"))){
						String newLine = String.join("", words);
						conditionOpL.add(newLine);
					}
				}
			}else if(line.contains("==") && !(line.contains("\"==\"") || line.contains(">>>") || line.contains(">>") || line.contains(">>>=") || line.contains(">>=") || line.contains("<<") || line.contains("<<=")) && (line.contains("null") || line.contains("getKey") || line.contains("getValue") || line.contains("this")) ) {
				conditionOpLNotEqual.add(line);		
			}else if(line.contains("==") && !(line.contains("\"==\"") || line.contains(">>>") || line.contains(">>") || line.contains(">>>=") || line.contains(">>=") || line.contains("<<") || line.contains("<<=")) && !(line.contains("null"))) {
				//System.out.println(line);
				String[] words = line.split("");
				//String regex = ".*[A-Z].*";
				for(int i=0;i<words.length;i++) {
					if(words[i].contains("=") && words[i+1].contains("=")) {
//						System.out.println(words[i]);
//						System.out.println(words[i+1]);
//						System.out.println(words[i+2]);
						boolean hasUppercase = !words[i+2].equals(words[i+2].toLowerCase());
						boolean hasUppercaseTwo = !words[i+3].equals(words[i+3].toLowerCase());
						if(words[i+2].isBlank() == false && (!hasUppercase)) {
							String newLine = String.join("", words);
							conditionOpL.add(newLine);
						}else if(words[i+3].isBlank() == false && (!hasUppercaseTwo)) {
							String newLine = String.join("", words);
							conditionOpL.add(newLine);
						}
					}
				}
			}else if(line.contains("!=") && !(line.contains("\"!=\"") || line.contains(">>>") || line.contains(">>") || line.contains(">>>=") || line.contains(">>=") || line.contains("<<") || line.contains("<<=")) && (line.contains("null") || line.contains("getKey") || line.contains("getValue") ||line.contains("this"))) {
				conditionOpLNotEqual.add(line);	
			}else if(line.contains("!=") && !(line.contains("\"!=\"") || line.contains(">>>") || line.contains(">>") || line.contains(">>>=") || line.contains(">>=") || line.contains("<<") || line.contains("<<=")) && !(line.contains("null"))) {
				//System.out.println(line);
				String[] words = line.split("");
				//String regex = ".*[A-Z].*";
				for(int i=0;i<words.length;i++) {
					if(words[i].contains("!") && words[i+1].contains("=")) {
						boolean hasUppercase = !words[i+2].equals(words[i+2].toLowerCase());
						boolean hasUppercaseTwo = !words[i+3].equals(words[i+3].toLowerCase());
						if(words[i+2].isBlank() == false && (!hasUppercase)) {
							String newLine = String.join("", words);
							conditionOpL.add(newLine);
						}else if(words[i+3].isBlank() == false && (!hasUppercaseTwo)) {
							String newLine = String.join("", words);
							conditionOpL.add(newLine);
						}
					}
				}
			}else if(line.contains(">=") && !(line.contains("\">=\"")) && !(line.contains(">>=")) && !(line.contains("\">>=\"")) && !(line.contains(">>>")) && !(line.contains(">>>="))) {
				conditionOpL.add(line);
			}else if(line.contains("<=") && !(line.contains("\"<=\"")) && !(line.contains("<<=")) && !(line.contains("\"<<=\""))) {
				conditionOpL.add(line);
			}
		}

		br.close();
		fr.close();			
	}

	//-------Method to return the relational operator list generated using operators found in original class-------------
	public List<String> returnRelOpList(){
//		System.out.println("========= PRINTING THE LIST ===============");
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
		return conditionOpL;
	}
	
	//-------Method to return the relational operator list generated using operators found in original class-------------
	public List<String> returnRelOpListNotEqual(){
		return conditionOpLNotEqual;
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processList(List<String> conditionOpL ) {
		for(String s : conditionOpL) {
			if(s.contains(">") && !(s.contains(">="))) {
				conditionOpP.add(s.replace(">", "<"));
				conditionOpP.add(s.replace(">", "=="));
				conditionOpP.add(s.replace(">", "!="));
				conditionOpP.add(s.replace(">", ">="));
				conditionOpP.add(s.replace(">", "<="));
			}
			if(s.contains("<") && !(s.contains("<="))) {
				conditionOpP.add(s.replace("<", ">"));
				conditionOpP.add(s.replace("<", "=="));
				conditionOpP.add(s.replace("<", "!="));
				conditionOpP.add(s.replace("<", ">="));
				conditionOpP.add(s.replace("<", "<="));
			}
			if(s.contains("==")) {
				conditionOpP.add(s.replace("==", "<"));
				conditionOpP.add(s.replace("==", ">"));
				conditionOpP.add(s.replace("==", "!="));
				conditionOpP.add(s.replace("==", ">="));
				conditionOpP.add(s.replace("==", "<="));
			}
			if(s.contains("!=")) {
				conditionOpP.add(s.replace("!=", "<"));
				conditionOpP.add(s.replace("!=", "=="));
				conditionOpP.add(s.replace("!=", ">"));
				conditionOpP.add(s.replace("!=", ">="));
				conditionOpP.add(s.replace("!=", "<="));
			}
			if(s.contains(">=")) {
				conditionOpP.add(s.replace(">=", "<"));
				conditionOpP.add(s.replace(">=", "=="));
				conditionOpP.add(s.replace(">=", "!="));
				conditionOpP.add(s.replace(">=", ">"));
				conditionOpP.add(s.replace(">=", "<="));
			}
			if(s.contains("<=")) {
				conditionOpP.add(s.replace("<=", "<"));
				conditionOpP.add(s.replace("<=", "=="));
				conditionOpP.add(s.replace("<=", "!="));
				conditionOpP.add(s.replace("<=", ">"));
				conditionOpP.add(s.replace("<=", ">="));
			}	
		}
	}

	//-------Generate mutants for the respective operators store them in another list-------------
	public void processListNotEqual(List<String> conditionOpLNotEqual ) {
		for(String s : conditionOpLNotEqual) {
			if(s.contains("==")) {
				conditionOpPNotEqual.add(s.replace("==", "!="));
			}
			if(s.contains("!=")) {
				conditionOpPNotEqual.add(s.replace("!=", "=="));
			}
		}
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveRelProcessList(){
		return conditionOpP;
	}

	//----------Return the operator list that stores mutants of operators found in original class------------
	public List<String> retriveRelProcessListNotEq(){
		return conditionOpPNotEqual;
	}
}
