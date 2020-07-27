//	This class is created to get a count of the total number of mutants that survived

package com.hsk.mutationscorecalculator;
import com.hsk.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TotalSurvived {

	public void totalSurvivedMutants() throws IOException {
		//------read the mutant.properties file------------
		File mutantProps = new File("mutant.properties");

		try {
			FileReader reader = new FileReader(mutantProps);
			Properties props = new Properties();
			props.load(reader);

			//-----------get the count of total number of mutants generated and killed--------------
			int totalMutants = Integer.parseInt(props.getProperty("totalmutants"));
			int killedMutants = Integer.parseInt(props.getProperty("mutantskilled"));

			//-----------pass the values to the countSurvivedMutants method that will calculate how many mutants survived---------
			countSurvivedMutants(totalMutants, killedMutants);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//----------Method to calculate the number of mutants that survived and then calculate the mutation score and display it---------------
	public static void countSurvivedMutants(int tM, int kM) {
		int survivedMutants = tM - kM;
		System.out.println("Total Mutants Survived: " +survivedMutants);
		int denominator = kM + survivedMutants;
		float score = (float)kM/(float)denominator;
		System.out.printf("Mutation score: %.2f", score);

	}


}
