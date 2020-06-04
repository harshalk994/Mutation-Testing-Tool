import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileLocation;
		System.out.println("Enter the location and filename you want to load:");
		fileLocation = sc.nextLine();
		JavaSyntaxChecker checker = new JavaSyntaxChecker();
		List<String> logs = checker.check(fileLocation);
		
		FileErrorLogPrintAndBreak fel = new FileErrorLogPrintAndBreak();
		fel.printLogs(logs);
		fel.breakCode(logs);
		
		//FileOpsTest fot = new FileOpsTest();
		//fot.createCopies(fileLocation);
		
		TempFileGenerator temp = new TempFileGenerator();
		temp.createTempFile(fileLocation);
		
		RemoveComments rc = new RemoveComments();
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

	}
	
	

}
