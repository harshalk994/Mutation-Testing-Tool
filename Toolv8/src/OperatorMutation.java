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
		List<String> shiftOpList = new ArrayList<String>();
		List<String> bitwiseOpList = new ArrayList<String>();
		
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
		
		ShiftOpStorage ss = new ShiftOpStorage();
		ss.processOp();
		shiftOpList = ss.returnShiftOpList();
		
		BitwiseOpStorage bos = new BitwiseOpStorage();
		bos.processOp();
		bitwiseOpList = bos.returnBitwiseOpList();
		
	
		
		if(arithOpList.isEmpty() == false || arithTwoList.isEmpty() == false) {
			ArithOpTry3 aop = new ArithOpTry3();
			aop.generateArithOpMutantFiles();
		}
		

		if(conditionalOpList.isEmpty() == false) {
			ConditionOpTry2 cop = new ConditionOpTry2();
			cop.generateConditionMutantFiles();
		}
		
		
		if(relationalOpList.isEmpty() == false) {
			RelOpTryFinal rop = new RelOpTryFinal();
			rop.generateRelOpMutantFiles();
		}
		
		if(shiftOpList.isEmpty() == false) {
			ShiftOpTry sot = new ShiftOpTry();
			sot.generateShiftOpMutantFiles();
		}
		
		if(bitwiseOpList.isEmpty() == false) {
			BitwiseOpTry bt = new BitwiseOpTry();
			bt.generateBitwiseOpMutantFiles();
		}
	}

}
