import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String fileLocation;
		String mutantFileLocation;
		String originalClassName;
		//String dependentClassPath;
		//String oPathWoCName;
		
//		System.out.println("Enter the original file's location and filename whose mutants need to be generated:");
//		fileLocation = sc.nextLine();
//		
//		System.out.println("Enter the location where you want to store the generated mutants:");
//		mutantFileLocation = sc.nextLine();
		
		UserInputs ui = new UserInputs();
		ui.readProperties();
		fileLocation = ui.returnOPath();
		mutantFileLocation = ui.returnMPath();
		originalClassName = ui.returnCName();
		//dependentClassPath = ui.returnDPath();
	//	oPathWoCName = ui.returnOPathWoCName();
		
		System.out.println("MPath is: " + mutantFileLocation);
		
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
		
		BitwiseOpStorage bw = new BitwiseOpStorage();
		bw.getPath(mutantFileLocation);
		
		BitwiseOpTry bt = new BitwiseOpTry();
		bt.getPath(mutantFileLocation);
		
		AssignmentOpStorage aos = new AssignmentOpStorage();
		aos.getPath(mutantFileLocation);
		
		AssignmentOpTry aot = new AssignmentOpTry();
		aot.getPath(mutantFileLocation);
		
		IncrementDecrementOpStorage ios = new IncrementDecrementOpStorage();
		ios.getPath(mutantFileLocation);
		
		IncrementDecrementOpTry idt = new IncrementDecrementOpTry();
		idt.getPath(mutantFileLocation);
		
		Cleaner cc = new Cleaner();
		cc.getPath(mutantFileLocation);
		
		OriginalClassCopyFile ocp = new OriginalClassCopyFile();
		ocp.getPath(mutantFileLocation);
		ocp.getFPath(fileLocation);
		ocp.createTempFile(fileLocation);

		RemoveComments rc = new RemoveComments();
		rc.getPath(mutantFileLocation);
		rc.removeComments();
		
//		TempFileGenerator temp = new TempFileGenerator();
//		temp.getPath(mutantFileLocation);
//		temp.createTempFile();
		
		TempFileProcessor tfp = new TempFileProcessor();
		tfp.getCName(originalClassName);
		tfp.getPath(mutantFileLocation);
		//tfp.getFPath(fileLocation);
		tfp.processTempFile();
		
		SecondTempProcessor stp = new SecondTempProcessor();
		stp.getCName(originalClassName);
		stp.getPath(mutantFileLocation);
		stp.processTempFile();
		
		
//		TempFileProcessor tfp = new TempFileProcessor();
//		tfp.getPath(mutantFileLocation);
//		tfp.processTempFile();
		
		FinalTempProcessor ftp = new FinalTempProcessor();
		ftp.getPath(mutantFileLocation);
		ftp.processTempFile();
		
		DeleteTempFile dtf = new DeleteTempFile();
		dtf.getPath(mutantFileLocation);
		dtf.deleteTempFile();
		
		
		System.out.println("Generating Mutants . . . . . \n");
		//Thread.sleep(2000);
		
		OperatorMutation opm = new OperatorMutation();
		opm.opMutationRules();
		
		System.out.println("\nMutant Generation Ended\n");
		System.out.println("Please check the html report to view the mutations.");
		
//		CopyDependentClasses cdc = new CopyDependentClasses();
//		cdc.getCName(originalClassName);
//		cdc.getFPath(oPathWoCName);
//		cdc.getPath(mutantFileLocation);
//		cdc.copyDependentClasses();
//		
//		CopyDependentClassV2 cdcv = new CopyDependentClassV2();
//		cdcv.getCName(originalClassName);
//		cdcv.getFPath(oPathWoCName);
//		cdcv.getPath(mutantFileLocation);
//		cdcv.copyDependentClasses();
//		
//		CopyDependentClassesV3 cdcv3 = new CopyDependentClassesV3();
//		cdcv3.getCName(originalClassName);
//		cdcv3.getFPath(oPathWoCName);
//		cdcv3.getPath(mutantFileLocation);
//		cdcv3.copyDependentClasses();
		
//		CreateTempCopyDependentClass ctcd = new CreateTempCopyDependentClass();
//		ctcd.getCName(originalClassName);
//		ctcd.getFPath(oPathWoCName);
//		ctcd.getPath(mutantFileLocation);
//		ctcd.createTempCopy();
//		
//		CreateTempCopyDependentClassV2 ctcdv2 = new CreateTempCopyDependentClassV2();
//		ctcdv2.getCName(originalClassName);
//		ctcdv2.getFPath(oPathWoCName);
//		ctcdv2.getPath(mutantFileLocation);
//		ctcdv2.createClassCopy();
//		
//		CreateTempCopyDependentClassV3 ctcdv3 = new CreateTempCopyDependentClassV3();
//		ctcdv3.getCName(originalClassName);
//		ctcdv3.getFPath(oPathWoCName);
//		ctcdv3.getPath(mutantFileLocation);
//		ctcdv3.createFinalCopy();
		
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
	//	System.out.println("All mutants were generated successfully!!");
		
//		System.out.println("Do you want to quit? (Y/N)");
//		String qChoice = sc.nextLine();
//		if(qChoice.equalsIgnoreCase("Y") || qChoice.equalsIgnoreCase("y")) {
//			System.exit(1);
//		}

	}
	
	

}
