import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Menu {
	
//	String rType, mName;
//	int nParams;
	
	public void initiateMenu() throws Exception, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		Scanner sc1, sc2, sc3, sc4, sc5;
		sc1 = new Scanner(System.in);
		sc2 = new Scanner(System.in);
		sc3 = new Scanner(System.in);
		sc4 = new Scanner(System.in);
		sc5 = new Scanner(System.in);
		
		String returnType, methodName, testFilePath, mutantPath;
		int numParameters;
		
		
		
		System.out.println("******** Menu **********");
		System.out.println("1. What is the return type of your method: (Select a,b,c,d,e,f as required)\n"
							+ "	a. Integer\n"
							+  " b. String\n" 
							+  " c. Float\n" 
							+  " d. Double\n" 
							+  " e. Long\n" 
							+  " f. Boolean");
		returnType = sc1.nextLine();
		//System.out.println(returnType);
	//	rType = returnType;
//		System.out.println(rType);
		
		System.out.println("2. Enter the method name:");
		methodName = sc2.nextLine();
//		mName = methodName;
		
		System.out.println("Enter the number of parameters passed in your method: ");
		numParameters = sc3.nextInt();
		
		System.out.println("Enter the location of test inputs file:");
		testFilePath = sc4.nextLine();
		
		System.out.println("Enter the location of the folder where the mutants are stored");
		mutantPath = sc5.nextLine();
		
		
//		nParams = numParameters;
		
		//System.out.println("Here here");
		initiateTest(returnType, numParameters, methodName, testFilePath, mutantPath);
		
		System.out.println("\nDo you want to quit? (Y/N)");
		String choice = sc5.nextLine();
		if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("Y")) {
			System.exit(1);
		}

	}
	
	public void initiateTest(String rType, int nParams, String mName, String testPath, String mutantPath) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
	//	System.out.println("Im here");
	//	System.out.println(rType);
	//	System.out.println(nParams);
		if(rType.equalsIgnoreCase("a") && nParams == 2) {
			//System.out.println("I reached here!!");
			TestInputs ti = new TestInputs();
			ti.setTestPath(testPath);
			CustomCompiler cc = new CustomCompiler();
			cc.setMutantPath(mutantPath);
			cc.compileFiles();
//			ti.printInputs();
//			System.out.println("Test inputs");
			IntMutantTwoParamTest mutest = new IntMutantTwoParamTest();
			mutest.setMethodName(mName);
			mutest.setMutantPath(mutantPath);
			mutest.test();
		}else {
			System.out.println("Invalid input");
			System.exit(1);
		}
		
	}


}
