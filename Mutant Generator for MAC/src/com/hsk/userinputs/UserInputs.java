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
	
	private static String originalpath;
	private static String dependentpath;
	private static String originalpathWOCname;
	private static String mutantpath;
	private static String finalPath;
	private static String mTestPath;
	private static String originalPackageName;
	private static String testPackageName;
	private static String originalClassName; 
	private static String dependentClassName;
	private static char arithop=' ';
	private static char assignmentop=' ';
	private static char bitwiseop=' ';
	private static char conditionalop=' ';
	private static char incdecop=' ';
	private static char relationalop=' ';
	private static char shiftop=' ';
	
	//------------Method to read the config file and get the user inputs set by the user--------------
	public void readProperties() {
		File configFile = new File("config.properties");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String oPath = props.getProperty("originalprogrampath");
		    String oPathWOCName = props.getProperty("originalprogrampath");
		    String testPath = props.getProperty("testcopypath");
		    String mPath = props.getProperty("mutantdestination");
		    String pName = props.getProperty("originalprogrampackagename");
		    String testPName = props.getProperty("testpackagename");
		    String cName = props.getProperty("originalclassname");
		    String dCName = props.getProperty("dependentclassname");
		    String arith = props.getProperty("arithop(y/n)");
		    String assign = props.getProperty("assignmentop(y/n)");
		    String bitwise = props.getProperty("bitwiseop(y/n)");
		    String conditional = props.getProperty("conditionalop(y/n)");
		    String incdec = props.getProperty("incrementdecrementop(y/n)");
		    String relational = props.getProperty("relationalop(y/n)");
		    String shift = props.getProperty("shiftop(y/n)");
		    
		    //-----Append the original path with the original class name---------
		    String newOPath=oPath+"/"+cName+".java";
		    
		  //-----Append the dependent path with the dependent class name---------
		    String dPath = oPath+"/"+dCName+".java";
		    
		    //--------------Pass the inputs to the setter method that will store and set the values for all userinputs to be used in the tool-------------
		    setProperties(newOPath, dPath, oPathWOCName, testPath, mPath, pName, testPName, cName, dCName, arith, assign, bitwise, conditional, incdec, relational, shift);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	//-----------Setter method to store and set the userinputs for use by different classes of the tool----------------
	public void setProperties(String newOPath, String dPath, String oPathWOCName, String testPath, String mPath, String pName, String testPName, String oCName, String dCName, String arith, String assign, String bitwise, String conditional, String incdec, String relational, String shift) throws IOException {
		originalpath = newOPath;
		dependentpath = dPath;
		originalpathWOCname = oPathWOCName;
		mutantpath = mPath;
		originalClassName = oCName;
		dependentClassName = dCName;
		mTestPath = testPath;
		originalPackageName = pName;
		testPackageName = testPName;
		
		if(arith.isBlank()==false) {
			char convertedarith = arith.charAt(0);
			if(convertedarith=='y' || convertedarith=='n') {
				arithop = convertedarith;
			}
		}
		
		if(assign.isBlank()==false) {
			char convertedassign = assign.charAt(0);
			if(convertedassign=='y' || convertedassign=='n') {
				assignmentop = convertedassign;
			}
		}
		
		if(bitwise.isBlank()==false) {
			char convertedbitwise = bitwise.charAt(0);
			if(convertedbitwise=='y' || convertedbitwise=='n') {
				bitwiseop = convertedbitwise;
			}
		}
		
		if(conditional.isBlank()==false) {
			char convertedconditional = conditional.charAt(0);
			if(convertedconditional=='y' || convertedconditional=='n') {
				conditionalop = convertedconditional;
			}
		}
		
		if(incdec.isBlank()==false) {
			char convertedincdec = incdec.charAt(0);
			if(convertedincdec=='y' || convertedincdec=='n') {
				incdecop = convertedincdec;
			}
		}
		
		if(relational.isBlank()==false) {
			char convertedrelational = relational.charAt(0);
			if(convertedrelational=='y' || convertedrelational=='n') {
				relationalop = convertedrelational;
			}
		}
		
		if(shift.isBlank()==false) {
			char convertedshift = shift.charAt(0);
			if(convertedshift=='y' || convertedshift=='n') {
				shiftop = convertedshift;
			}
		}
		
		//------------If a package name exists for the original class, then create a similar package directory structure in the Generated Mutants folder--------------
		String appendPath;
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "/");
				mutantpath = mPath+"/"+appendPath;
				Path path = Paths.get(mutantpath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Directory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}else {
				mutantpath = mPath+"/"+pName;
				Path path = Paths.get(mutantpath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Directory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}
		}
		
		//------------If a package name exists for the test class, then append test path with the package name--------------
		String appendTPath;
		if(testPName!=null) {
			if(testPName.contains(".")) {
				appendTPath = testPName.replace(".", "/");
				mTestPath = testPath+"/"+appendTPath;
			}else {
				mTestPath = testPath+"/"+testPName;
			}
		}
	}
	
	
	//-------Method to return the original class path with class name----------
	public String returnOPath() {
		return originalpath;
	}
	
	//-------Method to return the original class path without the class name----------
	public String returnOPathWoCName() {
		return originalpathWOCname;
	}
	
	//-------Method to return the dependent class path----------
	public String returnDPath() {
		return dependentpath;
	}
	
	//-------Method to return the Generated Mutants folder path----------
	public String returnMPath() {
		return mutantpath;
	}
	
	//-------Method to return the original class name----------
	public String returnCName() {
		return originalClassName;
	}
	
	//-------Method to return the dependent class name----------
	public String returnDName() {
		return dependentClassName;
	}
	
	//-------Method to return the arithmetic operator flag----------
	public char returnArithOp() {
		return arithop;
	}
	
	//-------Method to return the assignment operator flag----------
	public char returnAssignOp() {
		return assignmentop;
	}
	
	//-------Method to return the bitwise operator flag----------
	public char returnBitwiseOp() {
		return bitwiseop;
	}
	
	//-------Method to return the conditional operator flag----------
	public char returnConditionalOp() {
		return conditionalop;
	}
	
	//-------Method to return the increment/decrement operator flag----------
	public char returnIncDecOp() {
		return incdecop;
	}
	
	//-------Method to return the relational operator flag----------
	public char returnRelationalOp() {
		return relationalop;
	}
	
	//-------Method to return the shift operator flag----------
	public char returnShiftOp() {
		return shiftop;
	}
	
	//-------Method to return the Generated Test Copies folder path----------
	public String returnTPath() {
		return mTestPath;
	}
	
	//-------Method to return the original class package name----------
	public String returnOPName() {
		return originalPackageName;
	}
	
	//-------Method to return the test class package name----------
	public String returnTPName() {
		return testPackageName;
	}
}
