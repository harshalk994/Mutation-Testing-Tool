//	This class is created to generate a copy of the original class and store it in the Generated Mutants folder

package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CopyOriginalClassFile {

	private static String mPath;
	private static String fPath;
	private static String className;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to get the path of the original class-----------------
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	//----------Method to create a copy of the original class and store it in Generate Mutants folder-----------------
	public void createTempFile(String file) throws IOException {
		String tempFileName = mPath+"/OriginalTempCopy.java";

		FileReader source = new FileReader(file);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(tempFileName);
		BufferedWriter bw = new BufferedWriter(targetFile);

		String line;

		while((line = br.readLine()) != null) {
			bw.write(line);
			bw.newLine();	
		}
		br.close();
		bw.close();
		source.close();
		targetFile.close();
	}
}