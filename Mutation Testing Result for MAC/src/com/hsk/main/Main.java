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

import com.hsk.deletemutants.DeleteKilledMutants;
import com.hsk.deletemutants.DeleteKilledTests;
import com.hsk.mutationscorecalculator.MutantsKilledCount;
import com.hsk.mutationscorecalculator.TotalMutants;
import com.hsk.mutationscorecalculator.TotalSurvived;
import com.hsk.userinputs.UserInputs;

//This is the main class/entry point of the MutationTestingResultWindows JAR
public class Main {

	private static String className;

	public static void main(String[] args) throws IOException, InterruptedException {
		char deleteChoice;

		//----------Get the userinputs captured from the config file-----------
		UserInputs ui = new UserInputs();
		ui.readProperties();
		deleteChoice = ui.returnDeleteChoice();
		className = ui.returnOriginalCName();

		System.out.println("Mutation Testing Result for Class " + className + " is given below:");

		//----------Display the total mutants generated count------------
		TotalMutants tm = new TotalMutants();
		tm.totalMutantsCount();

		//----------Display the total mutants killed count------------
		MutantsKilledCount mk = new MutantsKilledCount();
		mk.countKilledMutants();

		//----------Display the total mutants survived count------------
		TotalSurvived ts = new TotalSurvived();
		ts.totalSurvivedMutants();

		//------------If the user has selected to delete the mutants and corresponding tests that killed the mutants, proceed with below deletion mechanism----------
		if(deleteChoice == 'y') {
			System.out.println("\n");
			System.out.println("-------------Deleting files--------------------");
			DeleteKilledMutants dm = new DeleteKilledMutants();
			dm.deleteMutants();

			DeleteKilledTests dt = new DeleteKilledTests();
			dt.deleteTests();
		}
	}

}
