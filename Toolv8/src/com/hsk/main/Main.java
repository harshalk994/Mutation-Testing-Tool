package com.hsk.main;
import com.hsk.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hsk.filedeletion.DeleteDependentClassTempFiles;
import com.hsk.filedeletion.DeleteTempFile;
import com.hsk.filedeletion.PreProcessDeleteFiles;
import com.hsk.operatormutation.ArithmeticOperatorMutation;
import com.hsk.operatormutation.AssignmentOperatorMutation;
import com.hsk.operatormutation.BitwiseOperatorMutation;
import com.hsk.operatormutation.ConditionOperatorMutation;
import com.hsk.operatormutation.IncrementDecrementOperatorMutation;
import com.hsk.operatormutation.OperatorMutation;
import com.hsk.operatormutation.RelationalOperatorMutation;
import com.hsk.operatormutation.ShiftOperatorMutation;
import com.hsk.operatorstorage.ArithmeticOpStorage;
import com.hsk.operatorstorage.AssignmentOpStorage;
import com.hsk.operatorstorage.BitwiseOpStorage;
import com.hsk.operatorstorage.ConditionalOpStorage;
import com.hsk.operatorstorage.IncrementDecrementOpStorage;
import com.hsk.operatorstorage.RelationalOpStorage;
import com.hsk.operatorstorage.ShiftOpStorage;
import com.hsk.tempfileprocessing.Cleaner;
import com.hsk.tempfileprocessing.CreateTempCopyDependentClassFirstPass;
import com.hsk.tempfileprocessing.CreateTempCopyDependentClassSecondPass;
import com.hsk.tempfileprocessing.TempFileProcessorThirdPass;
import com.hsk.tempfileprocessing.CopyOriginalClassFile;
import com.hsk.tempfileprocessing.RemoveComments;
import com.hsk.tempfileprocessing.TempFileProcessorSecondPass;
import com.hsk.tempfileprocessing.TempFileProcessorFirstPass;
import com.hsk.userinputs.UserInputs;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String fileLocation;
		String mutantFileLocation;
		String originalClassName;
		String dPath;
		String dName;
		String testFileLocation;
		String mPName;
		String tPName;
		//String premutantFilelocation;
		//String dependentClassPath;
		//String oPathWoCName;
		
//		System.out.println("Enter the original file's location and filename whose mutants need to be generated:");
//		fileLocation = sc.nextLine();
//		
//		System.out.println("Enter the location where you want to store the generated mutants:");
//		mutantFileLocation = sc.nextLine();
		
		PreProcessDeleteFiles pdf = new PreProcessDeleteFiles();
//		pdf.getPath(mutantFileLocation);
//		pdf.getMTPath(testFileLocation);
//		pdf.getOPName(mPName);
//		pdf.getTPName(tPName);
		pdf.deleteTempFile();
		
		UserInputs ui = new UserInputs();
		ui.readProperties();
		fileLocation = ui.returnOPath();
		//premutantFilelocation = ui.returnMPath();
		//mutantFileLocation = ui.returnMPath();
		//System.out.println("Pre path: " + mutantFileLocation);
		originalClassName = ui.returnCName();
		dPath = ui.returnDPath();
		dName = ui.returnDName();
		testFileLocation = ui.returnTPath();
		mPName = ui.returnOPName();
		tPName = ui.returnTPName();
		//dependentClassPath = ui.returnDPath();
	//	oPathWoCName = ui.returnOPathWoCName();
		
		//System.out.println("MPath is: " + mutantFileLocation);
		
//		JavaSyntaxChecker checker = new JavaSyntaxChecker();
//		List<String> logs = checker.check(fileLocation);
//		
//		FileErrorLogPrintAndBreak fel = new FileErrorLogPrintAndBreak();
//		fel.printLogs(logs);
//		fel.breakCode(logs);
		
		//FileOpsTest fot = new FileOpsTest();
		//fot.createCopies(fileLocation);
		
		
//		
		mutantFileLocation = ui.returnMPath();
		//System.out.println("Got the mutant file location as: " + mutantFileLocation);
		
		ArithmeticOperatorMutation at = new ArithmeticOperatorMutation();
		at.getPath(mutantFileLocation);
	
		ConditionalOpStorage cs = new ConditionalOpStorage();
		cs.getPath(mutantFileLocation);
		
		ConditionOperatorMutation ct = new ConditionOperatorMutation();
		ct.getPath(mutantFileLocation);
		
		ArithmeticOpStorage os = new ArithmeticOpStorage();
		os.getPath(mutantFileLocation);
		
		RelationalOpStorage rs = new RelationalOpStorage();
		rs.getPath(mutantFileLocation);
		
		RelationalOperatorMutation rt = new RelationalOperatorMutation();
		rt.getPath(mutantFileLocation);
		
		ShiftOpStorage ss = new ShiftOpStorage();
		ss.getPath(mutantFileLocation);
		
		ShiftOperatorMutation st = new ShiftOperatorMutation();
		st.getPath(mutantFileLocation);
		
		BitwiseOpStorage bw = new BitwiseOpStorage();
		bw.getPath(mutantFileLocation);
		
		BitwiseOperatorMutation bt = new BitwiseOperatorMutation();
		bt.getPath(mutantFileLocation);
		
		AssignmentOpStorage aos = new AssignmentOpStorage();
		aos.getPath(mutantFileLocation);
		
		AssignmentOperatorMutation aot = new AssignmentOperatorMutation();
		aot.getPath(mutantFileLocation);
		
		IncrementDecrementOpStorage ios = new IncrementDecrementOpStorage();
		ios.getPath(mutantFileLocation);
		
		IncrementDecrementOperatorMutation idt = new IncrementDecrementOperatorMutation();
		idt.getPath(mutantFileLocation);
		
		Cleaner cc = new Cleaner();
		cc.getPath(mutantFileLocation);
		
		CopyOriginalClassFile ocp = new CopyOriginalClassFile();
		ocp.getPath(mutantFileLocation);
		ocp.getFPath(fileLocation);
		ocp.createTempFile(fileLocation);

		RemoveComments rc = new RemoveComments();
		rc.getPath(mutantFileLocation);
		rc.removeComments();
		
//		TempFileGenerator temp = new TempFileGenerator();
//		temp.getPath(mutantFileLocation);
//		temp.createTempFile();
		
		TempFileProcessorFirstPass tfp = new TempFileProcessorFirstPass();
		tfp.getCName(originalClassName);
		tfp.getPath(mutantFileLocation);
		//tfp.getFPath(fileLocation);
		tfp.processTempFile();
		
		TempFileProcessorSecondPass stp = new TempFileProcessorSecondPass();
		stp.getCName(originalClassName);
		stp.getPath(mutantFileLocation);
		stp.processTempFile();
		
		
//		TempFileProcessor tfp = new TempFileProcessor();
//		tfp.getPath(mutantFileLocation);
//		tfp.processTempFile();
		
		TempFileProcessorThirdPass ftp = new TempFileProcessorThirdPass();
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
		
		if(dName.isBlank() == false) {
			CreateTempCopyDependentClassFirstPass ctcd = new CreateTempCopyDependentClassFirstPass();
			ctcd.getCName(dName);
			ctcd.getOCName(originalClassName);
			ctcd.getPath(mutantFileLocation);
			ctcd.getDPath(dPath);
			ctcd.createTempCopy();
			
			CreateTempCopyDependentClassSecondPass ctcdv2 = new CreateTempCopyDependentClassSecondPass();
			ctcdv2.getCName(dName);
			ctcdv2.getOCName(originalClassName);
			ctcdv2.getPath(mutantFileLocation);
			ctcdv2.getDPath(dPath);
			ctcdv2.createClassCopy();
			
			DeleteDependentClassTempFiles dt = new DeleteDependentClassTempFiles();
			dt.getPath(mutantFileLocation);
			dt.deleteTempFile();
		}
		
		
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
