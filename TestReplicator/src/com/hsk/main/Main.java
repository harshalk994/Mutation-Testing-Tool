//	Main class/Entry point of TestReplicatorWindows JAR executable

package com.hsk.main;
import com.hsk.*;
import java.io.IOException;
import java.util.Scanner;

import com.hsk.testreplicator.TestReplicatorDependentClass;
import com.hsk.testreplicator.TestReplicatorTry;
import com.hsk.userinputs.UserInputs;

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
			//System.out.println("HERE");
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
