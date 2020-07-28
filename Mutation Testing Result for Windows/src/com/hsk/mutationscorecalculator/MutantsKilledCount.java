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


package com.hsk.mutationscorecalculator;
import com.hsk.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hsk.userinputs.UserInputs;

//This class is created to get a count of the total number of mutants that are killed
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
