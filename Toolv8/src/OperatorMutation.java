import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperatorMutation {
	
	
//	public static void main(String[] args) throws IOException {
//		List<String> arithOpList = new ArrayList<String>();
//		List<String> conditionalOpList = new ArrayList<String>();
//		List<String> relationalOpList = new ArrayList<String>();
//		
//		OperatorStorage ops = new OperatorStorage();
//		ops.processOp();
//		
//		ConditionalOpStorage cs = new ConditionalOpStorage();
//		cs.processOp();
//		
//		RelOpStorageFinal rs = new RelOpStorageFinal();
//		rs.processOp();
//		
//		ArithOpTry3 aop = new ArithOpTry3();
//		ConditionOpTry2 cop = new ConditionOpTry2();
//		RelOpTryFinal rop = new RelOpTryFinal();
//		
//		arithOpList = ops.returnOpList();
//		System.out.println("ArithList");
//		for(int i=0;i<arithOpList.size();i++) {
//			System.out.println(arithOpList.get(i));
//		}
////		if(arithOpList.isEmpty() == false) {
////			aop.generateArithOpMutantFiles();
////		}
//		
//		conditionalOpList = cs.returnOpList();
//		System.out.println("ConditionList");
//		for(int i=0;i<conditionalOpList.size();i++) {
//			System.out.println(conditionalOpList.get(i));
//		}
////		if(conditionalOpList.isEmpty() == false) {
////			cop.generateConditionMutantFiles();
////		}
//		
//		relationalOpList = rs.returnRelOpList();
//		System.out.println("RelList");
//		for(int i=0;i<relationalOpList.size();i++) {
//			System.out.println(relationalOpList.get(i));
//		}
////		if(relationalOpList.isEmpty() == false) {
////			rop.generateRelOpMutantFiles();
////		}
//	
//
//	}
	
	public void opMutationRules() throws IOException {
		List<String> arithOpList = new ArrayList<String>();
		List<String> arithTwoList = new ArrayList<String>();
		List<String> conditionalOpList = new ArrayList<String>();
		List<String> relationalOpList = new ArrayList<String>();
		List<String> relationalOpTwoList = new ArrayList<String>();
		List<String> shiftOpList = new ArrayList<String>();
		List<String> bitwiseOpList = new ArrayList<String>();
		List<String> arithAssignmentOpList = new ArrayList<String>();
		List<String> bitwiseAssignOpList = new ArrayList<String>();
		List<String> shiftAssignOpList = new ArrayList<String>();
		List<String> incDecOpList = new ArrayList<String>();
		
		boolean allops = false;
		char arithop;
		char assignmentop;
		char bitwiseop;
		char conditionalop;
		char incdecop;
		char relationalop;
		char shiftop;
		
		UserInputs ui = new UserInputs();
		arithop = ui.returnArithOp();
		assignmentop = ui.returnAssignOp();
		bitwiseop = ui.returnBitwiseOp();
		conditionalop = ui.returnConditionalOp();
		incdecop = ui.returnIncDecOp();
		relationalop = ui.returnRelationalOp();
		shiftop = ui.returnShiftOp();
		
		//System.out.println("received arithop value as: " + arithop);
//		char convertedarith = arithop.charAt(0);
//		System.out.println("Converted to char for arith, value is : " + convertedarith);
		
		OperatorStorage ops = new OperatorStorage();
		ops.processOp();
		arithOpList = ops.returnOpList();
		arithTwoList = ops.returnOpTwoList();
		
		ConditionalOpStorage cs = new ConditionalOpStorage();
		cs.processOp();
		conditionalOpList = cs.returnOpList();
		
		RelOpStorageFinal rs = new RelOpStorageFinal();
		rs.processOp();
		relationalOpList = rs.returnRelOpList();
		relationalOpTwoList = rs.returnRelOpListNotEqual();
		
		ShiftOpStorage ss = new ShiftOpStorage();
		ss.processOp();
		shiftOpList = ss.returnShiftOpList();
		//ss.printShiftOps();
		
		BitwiseOpStorage bos = new BitwiseOpStorage();
		bos.processOp();
		bitwiseOpList = bos.returnBitwiseOpList();
		
		AssignmentOpStorage as = new AssignmentOpStorage();
		as.processOp();
		arithAssignmentOpList = as.returnArithAssignOpList();
		bitwiseAssignOpList = as.returnBitwiseAssignOpList();
		shiftAssignOpList = as.returnShiftAssignOpList();
		
		
		IncrementDecrementOpStorage ids = new IncrementDecrementOpStorage();
		ids.processOp();
		incDecOpList = ids.returnOpList();
		
	
		if((arithop==' ') && (assignmentop==' ') && (bitwiseop==' ')
				&& (conditionalop==' ') && (incdecop==' ') && (relationalop==' ')
				&& (shiftop==' ')) {
			allops=true;
		}
		
		if(allops==true) {
			if(arithOpList.isEmpty() == false || arithTwoList.isEmpty() == false) {
				ArithOpTry3 aop = new ArithOpTry3();
				aop.generateArithOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateArithOpTable();
			}
			

			if(conditionalOpList.isEmpty() == false) {
				ConditionOpTry2 cop = new ConditionOpTry2();
				cop.generateConditionMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateConditionalOpTable();
			}
			
			
			if(relationalOpList.isEmpty() == false || relationalOpTwoList.isEmpty() == false) {
				RelOpTryFinal rop = new RelOpTryFinal();
				rop.generateRelOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateRelationalOpTable();
			}
			
			if(shiftOpList.isEmpty() == false) {
				ShiftOpTry sot = new ShiftOpTry();
				sot.generateShiftOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateShiftOpTable();
			}
			
			if(bitwiseOpList.isEmpty() == false) {
				BitwiseOpTry bt = new BitwiseOpTry();
				bt.generateBitwiseOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateBitwiseOpTable();
			}
			
			if(arithAssignmentOpList.isEmpty() == false || bitwiseAssignOpList.isEmpty() == false || shiftAssignOpList.isEmpty() == false) {
				AssignmentOpTry at = new AssignmentOpTry();
				at.generateAssignmentOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateAssignmentOpTable();
			}
			
			if(incDecOpList.isEmpty() == false) {
				IncrementDecrementOpTry idt = new IncrementDecrementOpTry();
				idt.generateIncDecOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateIncDecOpTable();
			}
		}
		
		if(arithop=='y') {
			if(arithOpList.isEmpty() == false || arithTwoList.isEmpty() == false) {
				ArithOpTry3 aop = new ArithOpTry3();
				aop.generateArithOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateArithOpTable();
			}
		}
		
		if(assignmentop=='y') {
			if(arithAssignmentOpList.isEmpty() == false || bitwiseAssignOpList.isEmpty() == false || shiftAssignOpList.isEmpty() == false) {
				AssignmentOpTry at = new AssignmentOpTry();
				at.generateAssignmentOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateAssignmentOpTable();
			}
		}
		
		if(bitwiseop=='y') {
			if(bitwiseOpList.isEmpty() == false) {
				BitwiseOpTry bt = new BitwiseOpTry();
				bt.generateBitwiseOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateBitwiseOpTable();
			}
		}
		
		if(conditionalop=='y') {
			if(conditionalOpList.isEmpty() == false) {
				ConditionOpTry2 cop = new ConditionOpTry2();
				cop.generateConditionMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateConditionalOpTable();
			}
		}
		
		if(incdecop=='y') {
			if(incDecOpList.isEmpty() == false) {
				IncrementDecrementOpTry idt = new IncrementDecrementOpTry();
				idt.generateIncDecOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateIncDecOpTable();
			}
		}
		
		if(relationalop=='y') {
			if(relationalOpList.isEmpty() == false || relationalOpTwoList.isEmpty() == false) {
				RelOpTryFinal rop = new RelOpTryFinal();
				rop.generateRelOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateRelationalOpTable();
			}
		}
		
		if(shiftop=='y') {
			if(shiftOpList.isEmpty() == false) {
				ShiftOpTry sot = new ShiftOpTry();
				sot.generateShiftOpMutantFiles();
				PrintHtmlTable pht = new PrintHtmlTable();
				pht.generateShiftOpTable();
			}
		}
		
		
		
	}

}
