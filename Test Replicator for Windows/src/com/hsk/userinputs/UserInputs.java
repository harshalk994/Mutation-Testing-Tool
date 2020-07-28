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


package com.hsk.userinputs;
import com.hsk.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

//This class is created to read the config file and capture the user inputs from it
public class UserInputs {

	private static String originalTestPath;
	private static String testCopyPath;
	private static String mutantPath;
	private static String nameOfClassUnderTest;
	private static String dependentClassName;
	private static char testForDClass;
	private static String originalClassName;

	//------------Method to read the config file and get the user inputs set by the user--------------
	public void readProperties() {
		File configFile = new File("config.properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			String oPath = props.getProperty("originaltestpath");
			String mPath = props.getProperty("testcopypath");
			String pName = props.getProperty("testpackagename");
			String mPName = props.getProperty("originalprogrampackagename");
			String mutantsPath = props.getProperty("mutantdestination");
			String originalClassName = props.getProperty("testclassname");
			String dClassName = props.getProperty("dependentclassname");
			String testDClass = props.getProperty("testdependentclass(y/n)");
			String oCName = props.getProperty("originalclassname");

			//--------------Pass the inputs to the setter method that will store and set the values for all userinputs to be used in the tool-------------
			setProperties(oPath, mPath, pName, mutantsPath, originalClassName, mPName, dClassName, testDClass, oCName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//-----------Setter method to store and set the userinputs for use by different classes of the tool----------------
	public void setProperties(String oPath, String mPath, String pName, String mutantsPath, String orignalCName, String mPName, String dClassName, String testDClass, String oCName) throws IOException {
		originalTestPath = oPath;
		testCopyPath = mPath;
		mutantPath = mutantsPath;
		nameOfClassUnderTest = orignalCName;
		dependentClassName = dClassName;
		originalClassName = oCName;

		if(testDClass.isBlank()==false) {
			char dClassChoice = testDClass.charAt(0);
			if(dClassChoice=='y' || dClassChoice=='n') {
				testForDClass = dClassChoice;
			}
		}

		String appendPath;
		String appendMPath;

		//------------If a package name exists for the test class, then create a similar package directory structure in the Generated Test Copies folder--------------
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "\\");
				testCopyPath = mPath+"\\"+appendPath;
				Path path = Paths.get(testCopyPath);

				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Directory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}else {
				testCopyPath = mPath+"\\"+pName;
				Path path = Paths.get(testCopyPath);

				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Directory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}
		}

		//------------If a package name exists for the original class, then create a similar package directory structure in the Generated Mutants folder--------------
		if(mPName!=null) {
			if(mPName.contains(".")) {
				appendMPath = mPName.replace(".", "\\");
				mutantPath = mutantsPath+"\\"+appendMPath;
				Path path = Paths.get(mutantPath);

				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Directory structure created");
				}else {
					System.out.println("Directory already exists");
				}
			}else {
				mutantPath = mutantsPath+"\\"+mPName;
				Path path = Paths.get(mutantPath);

				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Directory structure created");
				}else {
					System.out.println("Directory already exists");
				}
			}
		}
		System.out.println("Name of class Under test is: " + nameOfClassUnderTest);
	}

	//----------Method to return the original test path------------
	public String returnOPath() {
		return originalTestPath;
	}

	//---------Method to return the Generated Test Copies path-------------
	public String returnTestCopyPath() {
		return testCopyPath;
	}

	//--------------Method to return the Generated Mutants path--------------
	public String returnMutantPath() {
		return mutantPath;
	}

	//---------Method to return the name of class under test---------------
	public String returnOriginalCName() {
		return nameOfClassUnderTest;
	}

	//---------Method to return the name of dependent class under test---------------
	public String returnDClassName() {
		return dependentClassName;
	}

	//---------Method to return the dependent class flag---------------
	public char returnDClassChoice() {
		return testForDClass;
	}

	//---------Method to return the name of original class---------------
	public String returnOriginalClassName() {
		return originalClassName;
	}

}
