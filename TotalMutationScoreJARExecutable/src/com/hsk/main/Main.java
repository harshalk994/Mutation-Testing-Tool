package com.hsk.main;
import com.hsk.*;
import java.io.IOException;

import com.hsk.deletemutants.DeleteKilledMutants;
import com.hsk.deletemutants.DeleteKilledTests;
import com.hsk.mutationscorecalculator.MutantsKilledCount;
import com.hsk.mutationscorecalculator.TotalMutants;
import com.hsk.mutationscorecalculator.TotalSurvived;
import com.hsk.userinputs.UserInputs;

public class Main {
	
	private static String className;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		char deleteChoice;
		UserInputs ui = new UserInputs();
		ui.readProperties();
		deleteChoice = ui.returnDeleteChoice();
		className = ui.returnOriginalCName();
		
		System.out.println("Mutation Testing Result for Class " + className + " is given below:");
		
		TotalMutants tm = new TotalMutants();
		tm.totalMutantsCount();
		
		MutantsKilledCount mk = new MutantsKilledCount();
		mk.countKilledMutants();
		
		TotalSurvived ts = new TotalSurvived();
		ts.totalSurvivedMutants();
		
		
		if(deleteChoice == 'y') {
			System.out.println("\n");
			System.out.println("-------------Deleting files--------------------");
			DeleteKilledMutants dm = new DeleteKilledMutants();
			dm.deleteMutants();
			
			DeleteKilledTests dt = new DeleteKilledTests();
			dt.deleteTests();
		}
		
		//Thread.sleep(10000);
		
		
	}

}
