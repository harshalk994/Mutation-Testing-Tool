package com.hsk.userinputs;
import com.hsk.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserInputs {
	
	private static String mutantPath;
	private static String reportsPath;
	private static String nameOfClassUnderTest;
	private static String testPackageName;
	private static String mTestPath;
	private static char deletionChoice;
	
	//private static String nameOfTestClass;
	
	public void readProperties() {
		File configFile = new File("config.properties");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String pName = props.getProperty("originalprogrampackagename");
		    String mutantsPath = props.getProperty("mutantdestination");
		    String originalClassName = props.getProperty("originalclassname");
		    String testPath = props.getProperty("testcopypath");
		    String testPName = props.getProperty("testpackagename");
		    String rPath = props.getProperty("reportspath");
		    String deleteChoice = props.getProperty("deletekilledmutants(y/n)");
		    
	
		    setProperties(mutantsPath, originalClassName, pName, testPath, testPName, rPath, deleteChoice);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void setProperties(String mutantsPath, String orignalCName, String pName, String testPath, String testPName, String rPath, String deleteChoice) throws IOException {

		mutantPath = mutantsPath;
		nameOfClassUnderTest = orignalCName;
		mTestPath = testPath;
		reportsPath = rPath;
		if(deleteChoice.isBlank()==false) {
			char dChoice = deleteChoice.charAt(0);
			if(dChoice == 'y' || dChoice == 'n') {
				deletionChoice = dChoice;
			}
		}
		
		//System.out.println("Name of class Under test was: " + nameOfClassUnderTest);
		//nameOfTestClass = originalTName;
		String appendPath;
		String appendTPath;
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "\\");
				mutantPath = mutantsPath+"\\"+appendPath;
			}else {
				mutantPath = mutantsPath+"\\"+pName;
			}
		}
			
		if(testPName!=null) {
			if(testPName.contains(".")) {
				appendTPath = testPName.replace(".", "\\");
				mTestPath = testPath + "\\" + appendTPath;
				reportsPath = rPath + "\\" + appendTPath;
				//reportsPath = rPath + "\\" + appendTPath;	
			}else {
				mTestPath = testPath+"\\"+testPName;
				reportsPath = rPath + "\\" + testPName;
			}
		}
	}
		
	

	
	public String returnMutantPath() {
		//System.out.println("Mutant path is: " + mutantPath);
		return mutantPath;
	}
	
	public String returnOriginalCName() {
		return nameOfClassUnderTest;
	}
	
	public String returnMTestPath() {
		//System.out.println("Test Path is: " + mTestPath);
		return mTestPath;
	}
	
	public String returnReportsPath() {
		//System.out.println("Reports path is: " + reportsPath);
		return reportsPath;
	}
	
	public char returnDeleteChoice() {
		return deletionChoice;
	}
	
//	public String returnOriginalTName() {
//		return nameOfTestClass;
//	}
}
