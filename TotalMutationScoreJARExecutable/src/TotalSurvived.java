import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TotalSurvived {
	
	public void totalSurvivedMutants() throws IOException {
		File mutantProps = new File("mutant.properties");
		
		try {
		    FileReader reader = new FileReader(mutantProps);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    int totalMutants = Integer.parseInt(props.getProperty("totalmutants"));
		    int killedMutants = Integer.parseInt(props.getProperty("mutantskilled"));
		    countSurvivedMutants(totalMutants, killedMutants);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void countSurvivedMutants(int tM, int kM) {
		int survivedMutants = tM - kM;
		System.out.println("Total Mutants Survived: " +survivedMutants);
		int denominator = kM + survivedMutants;
		float score = (float)kM/(float)denominator;
		System.out.println("Mutation score: " + score);
		
	}
	

}
