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


package com.hsk.main;
import com.hsk.*;
import java.io.IOException;
import java.util.Scanner;

import com.hsk.testreplicator.TestReplicatorDependentClass;
import com.hsk.testreplicator.TestReplicatorTry;
import com.hsk.userinputs.UserInputs;

//Main class/Entry point of TestReplicatorWindows JAR executable
public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		String originalTestLocation;
		String testCopyLocation;;
		String nameOfClassUnderTest;
		String mutantPath;
		String dName;
		String oCName;
		char testChoice;
		boolean flag=true;

		//----------reading config file and retrieving the required user inputs-------------
		UserInputs ui = new UserInputs();
		ui.readProperties();
		originalTestLocation = ui.returnOPath();
		testCopyLocation = ui.returnTestCopyPath();
		nameOfClassUnderTest = ui.returnOriginalCName();
		mutantPath = ui.returnMutantPath();
		testChoice = ui.returnDClassChoice();
		dName = ui.returnDClassName();
		oCName = ui.returnOriginalClassName();

		//----------If testdependentclass flag is set to y, execute the below if block for creating copies of the dependent class tests, otherwise execute the else block to create copies of the original class tests---------------
		if(testChoice=='y') {
			TestReplicatorDependentClass trdc = new TestReplicatorDependentClass();
			trdc.getClassName(nameOfClassUnderTest);
			trdc.getDClName(dName);
			trdc.getFPath(originalTestLocation);
			trdc.getMPath(mutantPath);
			trdc.getPath(testCopyLocation);
			trdc.getTMPath(testCopyLocation);
			trdc.getOriginalClassName(oCName);
			trdc.testReplicator();
		}else {
			TestReplicatorTry tr = new TestReplicatorTry();
			tr.getPath(testCopyLocation);
			tr.getFPath(originalTestLocation);
			tr.getTMPath(testCopyLocation);
			tr.getMPath(mutantPath);
			tr.getClassName(nameOfClassUnderTest);
			tr.testReplicator();
		}
	}

}
