import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileLocation;
		String mutantFileLocation;
		
//		System.out.println("Enter the original file's location and filename whose mutants need to be generated:");
//		fileLocation = sc.nextLine();
//		
//		System.out.println("Enter the location where you want to store the generated mutants:");
//		mutantFileLocation = sc.nextLine();
		
		UserInputs ui = new UserInputs();
		ui.readProperties();
		fileLocation = ui.returnOPath();
		mutantFileLocation = ui.returnMPath();
		
//		JavaSyntaxChecker checker = new JavaSyntaxChecker();
//		List<String> logs = checker.check(fileLocation);
//		
//		FileErrorLogPrintAndBreak fel = new FileErrorLogPrintAndBreak();
//		fel.printLogs(logs);
//		fel.breakCode(logs);
		
		//FileOpsTest fot = new FileOpsTest();
		//fot.createCopies(fileLocation);
		
		ArithOpTry3 at = new ArithOpTry3();
		at.getPath(mutantFileLocation);
	
		ConditionalOpStorage cs = new ConditionalOpStorage();
		cs.getPath(mutantFileLocation);
		
		ConditionOpTry2 ct = new ConditionOpTry2();
		ct.getPath(mutantFileLocation);
		
		OperatorStorage os = new OperatorStorage();
		os.getPath(mutantFileLocation);
		
		RelOpStorageFinal rs = new RelOpStorageFinal();
		rs.getPath(mutantFileLocation);
		
		RelOpTryFinal rt = new RelOpTryFinal();
		rt.getPath(mutantFileLocation);
		
		ShiftOpStorage ss = new ShiftOpStorage();
		ss.getPath(mutantFileLocation);
		
		ShiftOpTry st = new ShiftOpTry();
		st.getPath(mutantFileLocation);
		
		Cleaner cc = new Cleaner();
		cc.getPath(mutantFileLocation);
		
		TempFileGenerator temp = new TempFileGenerator();
		temp.getPath(mutantFileLocation);
		temp.createTempFile(fileLocation);
		
		RemoveComments rc = new RemoveComments();
		rc.getPath(mutantFileLocation);
		rc.removeComments();
		
		OperatorMutation opm = new OperatorMutation();
		opm.opMutationRules();
		
//		ArithOpTry3 aot = new ArithOpTry3();
//		aot.generateArithOpMutantFiles();
//		
//		ConditionOpTry2 ct = new ConditionOpTry2();
//		ct.generateConditionMutantFiles();
//		
//		RelOpTryFinal rt = new RelOpTryFinal();
//		rt.generateRelOpMutantFiles();
				

		//MutantAOP muaop = new MutantAOP();
		//muaop.processMutants();
		
//		List<String> opL = new ArrayList<String>();
//		
//		OperatorStorage os = new OperatorStorage();
//		os.processOp();
//		opL = os.returnOpList();
//		os.processList(opL);
//		
			
		//FileBufferTry fbt = new FileBufferTry();
		//fbt.createCopies();
		
//		BuffOpTry bot = new BuffOpTry();
//		bot.createCopies(fileLocation);
		System.out.println("All mutants were generated successfully!!");
		
//		System.out.println("Do you want to quit? (Y/N)");
//		String qChoice = sc.nextLine();
//		if(qChoice.equalsIgnoreCase("Y") || qChoice.equalsIgnoreCase("y")) {
//			System.exit(1);
//		}

	}
	
	

}
