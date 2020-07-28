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
import java.util.ArrayList;
import java.util.List;

import com.hsk.userinputs.UserInputs;

//This class is created to get a count of the total number of mutants that are generated
public class TotalMutants {
	public void totalMutantsCount() throws IOException {
		String mPath;
		String tPath;
		String rPath;

		//----------Get the userinputs captured from the config file-----------
		UserInputs ui = new UserInputs();
		mPath = ui.returnMutantPath();
		tPath = ui.returnMTestPath();
		rPath = ui.returnReportsPath();

		//------------Get the files stored in the Generated Mutants folder-------------
		File directoryPath = new File(mPath);

		//----------Write to the mutant.properties file--------------
		FileWriter fw = new FileWriter("mutant.properties");

		String contents[] = directoryPath.list();

		//----------Array to store mutants from Generated Mutants folder (skip the temp file)-------------
		String mutants[] = new String[contents.length-1];

		List<String> removeNull = new ArrayList<String>();

		//----------Store mutants from Generated Mutants folder (skip the temp file)-------------
		for(int l=0, m=0; l<contents.length; l++) {
			if(contents[l].contains("Temp")) 
				continue;

			mutants[m++] = contents[l];    	  
		}

		//---------Remove null values from the array--------------
		for(String s : mutants) {
			if(s!=null && s.length() > 0) {
				removeNull.add(s);
			}
		}

		mutants = removeNull.toArray(new String[removeNull.size()]);

		//---------Display the Total number of mutants and write the value to mutant.properties file---------------
		String totalMutants = "totalmutants=";
		int count = mutants.length;
		System.out.println("Total Mutants Generated: " + count);
		fw.write(totalMutants+count);
		fw.flush();
		fw.close();

	}

}
