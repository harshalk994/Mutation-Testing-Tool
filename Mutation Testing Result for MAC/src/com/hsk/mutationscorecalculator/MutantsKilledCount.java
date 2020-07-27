//	This class is created to get a count of the total number of mutants that are killed

package com.hsk.mutationscorecalculator;
import com.hsk.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hsk.userinputs.UserInputs;

public class MutantsKilledCount {
	
	private static String reportPath;
	
	public void countKilledMutants() throws IOException {
		
		//----------Get the userinputs captured from the config file-----------
		UserInputs ui = new UserInputs();
		reportPath = ui.returnReportsPath();
		
		//----------Write to the mutant.properties file--------------
		FileWriter fw = new FileWriter("mutant.properties", true);
		
		//------------Get the files stored in the reports\html folder-------------
		File directoryPath = new File(reportPath);
		String contents[] = directoryPath.list();

		int count = 0;
		String failedTests[];
		
		//---------Get a count of all the failed tests-------------
		for(int i=0;i<contents.length;i++) {
			if(contents[i].contains("Test-fails")) {
				count++;
			}
		}
		
		//-------------number of tests failed = number of mutants killed, display the count and also store it in the mutant.properties file----------
		System.out.println("Number of mutants killed: " + count);
		String mutantKilled = "mutantskilled=";
		fw.write("\n"+mutantKilled+count);
		fw.flush();
		fw.close();
	}


}
