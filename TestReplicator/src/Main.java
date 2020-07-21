import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		String originalTestLocation;
		String testCopyLocation;;
		//String updatedTestFileLocation=null;;
		//String packageName="";
		String nameOfClassUnderTest;
		String mutantPath;
		String dName;
		//String nameOfClassUnderTest;
		char testChoice;
		boolean flag=true;
		//FileDetailsStorage fds = new FileDetailsStorage();
		
		UserInputs ui = new UserInputs();
		ui.readProperties();
		originalTestLocation = ui.returnOPath();
		testCopyLocation = ui.returnTestCopyPath();
		nameOfClassUnderTest = ui.returnOriginalCName();
		mutantPath = ui.returnMutantPath();
		testChoice = ui.returnDClassChoice();
		dName = ui.returnDClassName();
		
//		CreateTempTest ct = new CreateTempTest();
//		ct.getFPath(originalTestLocation);
//		ct.getPath(testCopyLocation);
//		ct.getClassName(nameOfClassUnderTest);
//		ct.createTempCopy();
		
		if(testChoice=='y') {
			//System.out.println("HERE");
			TestReplicatorDependentClass trdc = new TestReplicatorDependentClass();
			trdc.getClassName(nameOfClassUnderTest);
			trdc.getDClName(dName);
			trdc.getFPath(originalTestLocation);
			trdc.getMPath(mutantPath);
			trdc.getPath(testCopyLocation);
			trdc.getTMPath(testCopyLocation);
			trdc.testReplicator();
		}else {
			TestReplicatorTry tr = new TestReplicatorTry();
			tr.getPath(testCopyLocation);
			tr.getFPath(originalTestLocation);
			tr.getTMPath(testCopyLocation);
			tr.getMPath(mutantPath);
			tr.getClassName(nameOfClassUnderTest);
			tr.testReplicator();
		}
	
		
//		System.out.println("Enter the location and filename of the test that needs to be tested:");
//		originalTestLocation = sc.nextLine();
//		
//		
//		System.out.println("Enter the location where you want to store the copy of the test:");
//		testCopyLocation = sc.nextLine();
//		
//		System.out.println("Enter the name of class that is being tested with the test case:");
//		nameOfClassUnderTest = sc.nextLine();
//		
//		System.out.println("Enter the path where the mutants are stored:");
//		mutantPath = sc.nextLine();
		
//		while(flag) {
//			System.out.println("Does the program contain a package structure? (y/n)");
//			String choice = sc.nextLine();
//			if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("Y")) {
//				System.out.println("Please enter the package name: ");
//				packageName = sc.nextLine();
//				CreatePackageStructure cps = new CreatePackageStructure();
//				cps.getPath(testCopyLocation);
//				cps.getPName(packageName);
//				cps.createPackageDirectory();
//				updatedTestFileLocation = cps.getPackagedPath();
//				flag=false;
//			}else if(choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("N")) {
//				flag=false;
//				System.out.println("Stopped!!");
//			}else{
//				flag=true;	
//				System.out.println("Continuing");
//			}
//		}
//		
//		if(updatedTestFileLocation==null) {
//			CreateTempTest ct = new CreateTempTest();
//			ct.getFPath(originalTestLocation);
//			ct.getPath(testCopyLocation);
//			ct.getClassName(nameOfClassUnderTest);
//			ct.createTempCopy();
//			
//			TestReplicatorTry tr = new TestReplicatorTry();
//			tr.getTMPath(testCopyLocation);
//			tr.getMPath(mutantPath);
//			tr.getClassName(nameOfClassUnderTest);
//			tr.testReplicator();
//			
//			
//		}
	}

}
