//	This class is created to read the config file and capture the user inputs from it

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
	
	//------------Method to read the config file and get the user inputs set by the user--------------
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
		    
		    //--------------Pass the inputs to the setter method that will store and set the values for all userinputs to be used in the tool-------------
		    setProperties(mutantsPath, originalClassName, pName, testPath, testPName, rPath, deleteChoice);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//-----------Setter method to store and set the userinputs for use by different classes of the tool----------------
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

		String appendPath;
		String appendTPath;
		
		//------------If a package name exists for the original class, then create a similar package directory structure in the Generated Mutants folder--------------
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "\\");
				mutantPath = mutantsPath+"\\"+appendPath;
			}else {
				mutantPath = mutantsPath+"\\"+pName;
			}
		}
			
		//------------If a package name exists for the test class, then append test path with the package name--------------
		if(testPName!=null) {
			if(testPName.contains(".")) {
				appendTPath = testPName.replace(".", "\\");
				mTestPath = testPath + "\\" + appendTPath;
				reportsPath = rPath + "\\" + appendTPath;
			}else {
				mTestPath = testPath+"\\"+testPName;
				reportsPath = rPath + "\\" + testPName;
			}
		}
	}
		
	//-------Method to return the Generated Mutants folder path----------	
	public String returnMutantPath() {
		return mutantPath;
	}
	
	//-------Method to return the original class name----------
	public String returnOriginalCName() {
		return nameOfClassUnderTest;
	}
	
	//-------Method to return the Generated Test Copies path----------
	public String returnMTestPath() {
		return mTestPath;
	}
	
	//-------Method to return the reports\html path----------
	public String returnReportsPath() {
		return reportsPath;
	}
	
	//-------Method to return the delete mutants flag----------
	public char returnDeleteChoice() {
		return deletionChoice;
	}

}
