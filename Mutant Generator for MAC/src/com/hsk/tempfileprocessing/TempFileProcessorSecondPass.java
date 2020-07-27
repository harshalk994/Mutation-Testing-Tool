//	This class is created to process the original class file and generate a temp file (this is the second pass for processing the original class)

package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import com.hsk.userinputs.SetClassNameProperty;

public class TempFileProcessorSecondPass {

	private static String mPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to get the original class name-----------------
	public void getCName(String originalCName) {
		className = originalCName;
	}

	//----------Method to process the original class and create a temporary copy of it (second pass)-----------------
	public void processTempFile() throws IOException {
		SetClassNameProperty scp = new SetClassNameProperty();
		className = scp.getCName();

		String tempFileName = mPath+"/FirstTemp.java";
		String newTempFile = mPath+"/SecondTemp.java";

		FileReader source = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(newTempFile);
		BufferedWriter bw = new BufferedWriter(targetFile);

		String line;
		String updateCName = "SecondTemp";

		while((line = br.readLine()) != null) {

			if(line.contains(className)) {
				String newLine = line.replaceAll(className, updateCName);
				scp.setCName(updateCName);
				bw.write(newLine);
				bw.newLine();				
			}
			else if((!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("*")) || (!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("+")) || (!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("/")) || (!(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("\"")) && line.contains("-") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
						if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
				}

				String newLine = String.join("", words);		
				bw.write(newLine);
				bw.newLine();
			}
			else if((!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("+")) || (!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("-")) || (!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) && !(line.contains("\"")) && line.contains("*") && line.contains("/")) || (!(line.contains("*=")) && !(line.contains("=*")) && !(line.contains("= *")) &&  !(line.contains("\"")) && line.contains("*") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
						if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
				}

				String newLine = String.join("", words);				
				bw.write(newLine);
				bw.newLine();
			}
			else if((!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("*")) || (!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("-")) || (!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("+")) || (!(line.contains("/=")) && !(line.contains("=/")) && !(line.contains("= /")) && !(line.contains("\"")) && line.contains("/") && line.contains("%"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
						if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
				}

				String newLine = String.join("", words);
				bw.write(newLine);
				bw.newLine();
			}
			else if((!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("*")) || (!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("-")) || (!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("/")) || (!(line.contains("%=")) && !(line.contains("=%")) && !(line.contains("= %")) && !(line.contains("\"")) && line.contains("%") && line.contains("+"))){
				String[] words = line.split("");
				for(int k=0;k<words.length;k++) {
					if(!(words[k].contains("+") && (words[k+1].contains("+"))) || !(words[k].contains("-") && (words[k+1].contains("-")))) {
						String replaceA = "+" + System.lineSeparator();
						String replaceB = "-" + System.lineSeparator();
						String replaceC = "*" + System.lineSeparator();
						String replaceD = "/" + System.lineSeparator();
						String replaceE = "%" + System.lineSeparator();
						if(words[k].contains("+") && !(words[k+1].contains("+"))) {
							String temp = replaceA;
							words[k] = temp;
						}
						if(words[k].contains("-") && !(words[k+1].contains("-"))) {
							String temp = replaceB;
							words[k] = temp;
						}
						if(words[k].contains("*")) {
							String temp = replaceC;
							words[k] = temp;
						}
						if(words[k].contains("/")) {
							String temp = replaceD;
							words[k] = temp;
						}
						if(words[k].contains("%")) {
							String temp = replaceE;
							words[k] = temp;
						}
					}
				}

				String newLine = String.join("", words);
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
		source.close();
		targetFile.close();

	}

}
