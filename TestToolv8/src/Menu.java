import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Menu {
	
//	String rType, mName;
//	int nParams;
	
	public void initiateMenu() throws Exception, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		Scanner sc1, sc2, sc3;
		sc1 = new Scanner(System.in);
		sc2 = new Scanner(System.in);
		sc3 = new Scanner(System.in);
		
		String returnType, methodName;
		int numParameters;
		
		
		
		System.out.println("******** Menu **********");
		System.out.println("1. What is the return type of your method: (Select a,b,c,d,e,f as required)\n"
							+ "	a. Integer\n" +
							  " b. String\n" +
							  " c. Float\n" +
							  " d. Double\n" +
							  " e. Long\n" +
							  " f. Boolean\n");
		returnType = sc1.nextLine();
		System.out.println(returnType);
	//	rType = returnType;
//		System.out.println(rType);
		
		System.out.println("\n2. Enter the method name:\n");
		methodName = sc2.next();
//		mName = methodName;
		
		System.out.println("Enter the number of parameters passed in your method:\n ");
		numParameters = sc3.nextInt();
//		nParams = numParameters;
		
		System.out.println("Here here");
		initiateTest(returnType, numParameters);

	}
	
	public void initiateTest(String rType, int nParams) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		System.out.println("Im here");
		System.out.println(rType);
		System.out.println(nParams);
		if(rType.equalsIgnoreCase("a") && nParams == 2) {
			System.out.println("I reached here!!");
//			TestInputs ti = new TestInputs();
//			ti.getTestInputs();
//			ti.printInputs();
//			System.out.println("Test inputs");
			IntMutantTwoParamTest mutest = new IntMutantTwoParamTest();
			mutest.test();
		}
		
	}


}
