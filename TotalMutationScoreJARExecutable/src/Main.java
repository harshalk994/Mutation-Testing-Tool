import java.io.IOException;

public class Main {
	
	private static String className;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		UserInputs ui = new UserInputs();
		ui.readProperties();
		className = ui.returnOriginalCName();
		
		System.out.println("Mutation Testing Result for Class " + className + " is given below:");
		
		TotalMutants tm = new TotalMutants();
		tm.totalMutantsCount();
		
		MutantsKilledCount mk = new MutantsKilledCount();
		mk.countKilledMutants();
		
		TotalSurvived ts = new TotalSurvived();
		ts.totalSurvivedMutants();
		
		System.out.println("-----------DONE-----------");
		
		Thread.sleep(10000);
	}

}
