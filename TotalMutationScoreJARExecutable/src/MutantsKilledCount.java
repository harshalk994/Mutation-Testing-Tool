import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MutantsKilledCount {
	
	private static String reportPath;
	
	public void countKilledMutants() throws IOException {
		UserInputs ui = new UserInputs();
		reportPath = ui.returnReportsPath();
		FileWriter fw = new FileWriter("mutant.properties", true);
		
		File directoryPath = new File(reportPath);
		String contents[] = directoryPath.list();
		//for(int i=0;i<contents.length)
		int count = 0;
		String failedTests[];
		for(int i=0;i<contents.length;i++) {
			if(contents[i].contains("Test-fails")) {
				count++;
			}
		}
		
		
		System.out.println("Number of mutants killed: " + count);
		String mutantKilled = "mutantskilled=";
		fw.write("\n"+mutantKilled+count);
		fw.flush();
		fw.close();
	}


}
