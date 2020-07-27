//	Main class/Entry point of Mutant Generator JAR executable

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

		//--------required variable declarations-------------------
		String fileLocation;
		String mutantFileLocation;
		String originalClassName;
		String dPath;
		String dName;
		String testFileLocation;
		String mPName;
		String tPName;

		//--------deleting the files generated from previous project run using the tool
		PreProcessDeleteFiles pdf = new PreProcessDeleteFiles();
		pdf.deleteTempFile();


		//----------reading config file and retrieving the required user inputs-------------
		UserInputs ui = new UserInputs();
		ui.readProperties();
		fileLocation = ui.returnOPath();
		originalClassName = ui.returnCName();
		dPath = ui.returnDPath();
		dName = ui.returnDName();
		testFileLocation = ui.returnTPath();
		mPName = ui.returnOPName();
		tPName = ui.returnTPName();	
		mutantFileLocation = ui.returnMPath();

		//------------passing the mutant file path value to the arithmetic operator mutation class-------------
		ArithmeticOperatorMutation at = new ArithmeticOperatorMutation();
		at.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the conditional operator storage class---------------
		ConditionalOpStorage cs = new ConditionalOpStorage();
		cs.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the conditional operator mutation class---------------
		ConditionOperatorMutation ct = new ConditionOperatorMutation();
		ct.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the arithmetic operator storage class---------------
		ArithmeticOpStorage os = new ArithmeticOpStorage();
		os.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the relational operator storage class---------------
		RelationalOpStorage rs = new RelationalOpStorage();
		rs.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the relational operator mutation class---------------
		RelationalOperatorMutation rt = new RelationalOperatorMutation();
		rt.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the shift operator storage class---------------
		ShiftOpStorage ss = new ShiftOpStorage();
		ss.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the shift operator mutation class---------------
		ShiftOperatorMutation st = new ShiftOperatorMutation();
		st.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the bitwise operator storage class---------------
		BitwiseOpStorage bw = new BitwiseOpStorage();
		bw.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the bitwise operator mutation class---------------
		BitwiseOperatorMutation bt = new BitwiseOperatorMutation();
		bt.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the assignment operator storage class---------------
		AssignmentOpStorage aos = new AssignmentOpStorage();
		aos.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the assignment operator mutation class---------------
		AssignmentOperatorMutation aot = new AssignmentOperatorMutation();
		aot.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the inc/dec operator storage class---------------
		IncrementDecrementOpStorage ios = new IncrementDecrementOpStorage();
		ios.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the inc/dec operator mutation class---------------
		IncrementDecrementOperatorMutation idt = new IncrementDecrementOperatorMutation();
		idt.getPath(mutantFileLocation);

		//------------passing the mutant file path value to the cleaner class---------------
		Cleaner cc = new Cleaner();
		cc.getPath(mutantFileLocation);


		//-----------passing required inputs to the CopyOriginalClassFile class--------------
		CopyOriginalClassFile ocp = new CopyOriginalClassFile();
		ocp.getPath(mutantFileLocation);
		ocp.getFPath(fileLocation);
		ocp.createTempFile(fileLocation);

		//------------passing the mutant file path value to the RemoveComments class and run the removeComments() method---------------
		RemoveComments rc = new RemoveComments();
		rc.getPath(mutantFileLocation);
		rc.removeComments();

		//-----------passing required inputs to the TempFileProcessorFirstPass class and running the processTempFile() method--------------
		TempFileProcessorFirstPass tfp = new TempFileProcessorFirstPass();
		tfp.getCName(originalClassName);
		tfp.getPath(mutantFileLocation);
		tfp.processTempFile();

		//-----------passing required inputs to the TempFileProcessorSecondPass class and running the processTempFile() method--------------
		TempFileProcessorSecondPass stp = new TempFileProcessorSecondPass();
		stp.getCName(originalClassName);
		stp.getPath(mutantFileLocation);
		stp.processTempFile();

		//-----------passing required inputs to the TempFileProcessorThirdPass class and running the processTempFile() method--------------
		TempFileProcessorThirdPass ftp = new TempFileProcessorThirdPass();
		ftp.getPath(mutantFileLocation);
		ftp.processTempFile();

		//-----------passing required inputs to the DeleteTempFile class and running the deleteTempFile() method--------------
		DeleteTempFile dtf = new DeleteTempFile();
		dtf.getPath(mutantFileLocation);
		dtf.deleteTempFile();


		System.out.println("Generating Mutants . . . . . \n");

		//-----------running the opMutationRules() method--------------
		OperatorMutation opm = new OperatorMutation();
		opm.opMutationRules();

		System.out.println("\nMutant Generation Ended\n");
		System.out.println("Please check the html report to view the mutations.");

		//If depenendent class name is not blank, invoke the dependent class copies generator----------------------
		if(dName.isBlank() == false) {

			//------------passing the mutant file path value to the CreateTempCopyDependentClassFirstPass class and run the createTempCopy() method---------------
			CreateTempCopyDependentClassFirstPass ctcd = new CreateTempCopyDependentClassFirstPass();
			ctcd.getCName(dName);
			ctcd.getOCName(originalClassName);
			ctcd.getPath(mutantFileLocation);
			ctcd.getDPath(dPath);
			ctcd.createTempCopy();

			//------------passing the mutant file path value to the CreateTempCopyDependentClassSecondPass class and run the createClassCopy() method---------------
			CreateTempCopyDependentClassSecondPass ctcdv2 = new CreateTempCopyDependentClassSecondPass();
			ctcdv2.getCName(dName);
			ctcdv2.getOCName(originalClassName);
			ctcdv2.getPath(mutantFileLocation);
			ctcdv2.getDPath(dPath);
			ctcdv2.createClassCopy();

			//-----------passing required inputs to the DeleteDependentClassTempFiles class and running the deleteTempFile() method--------------
			DeleteDependentClassTempFiles dt = new DeleteDependentClassTempFiles();
			dt.getPath(mutantFileLocation);
			dt.deleteTempFile();
		}


	}



}
