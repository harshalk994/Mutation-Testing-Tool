import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserInputs {
	
	private static String originalTestPath;
	private static String testCopyPath;
	private static String mutantPath;
	private static String nameOfClassUnderTest;
	private static String dependentClassName;
	private static char testForDClass;
	private static String originalClassName;
	
	//private static String nameOfTestClass;
	
	public void readProperties() {
		File configFile = new File("config.properties");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String oPath = props.getProperty("originaltestpath");
		    String mPath = props.getProperty("testcopypath");
		    String pName = props.getProperty("testpackagename");
		    String mPName = props.getProperty("originalprogrampackagename");
		    String mutantsPath = props.getProperty("mutantdestination");
		    String originalClassName = props.getProperty("testclassname");
		    String dClassName = props.getProperty("dependentclassname");
		    String testDClass = props.getProperty("testdependentclass(y/n)");
		    String oCName = props.getProperty("originalclassname");
		    
		    //String testClassName = props.getProperty("testclassname");
		    setProperties(oPath, mPath, pName, mutantsPath, originalClassName, mPName, dClassName, testDClass, oCName);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void setProperties(String oPath, String mPath, String pName, String mutantsPath, String orignalCName, String mPName, String dClassName, String testDClass, String oCName) throws IOException {
		originalTestPath = oPath;
		testCopyPath = mPath;
		mutantPath = mutantsPath;
		nameOfClassUnderTest = orignalCName;
		dependentClassName = dClassName;
		originalClassName = oCName;
		
		if(testDClass.isBlank()==false) {
			char dClassChoice = testDClass.charAt(0);
			if(dClassChoice=='y' || dClassChoice=='n') {
				testForDClass = dClassChoice;
				//System.out.println("value of arithop was set to: " + arithop);
			}
		}
		System.out.println("Name of class Under test is: " + nameOfClassUnderTest);
		//nameOfTestClass = originalTName;
		String appendPath;
		String appendMPath;
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "\\");
				testCopyPath = mPath+"\\"+appendPath;
				Path path = Paths.get(testCopyPath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Driectory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}else {
				testCopyPath = mPath+"\\"+pName;
				Path path = Paths.get(testCopyPath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Driectory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}
		}
		if(mPName!=null) {
			if(mPName.contains(".")) {
				appendMPath = mPName.replace(".", "\\");
				mutantPath = mutantsPath+"\\"+appendMPath;
				Path path = Paths.get(mutantPath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Driectory structure created");
				}else {
					System.out.println("Directory already exists");
				}
			}else {
				mutantPath = mutantsPath+"\\"+mPName;
				Path path = Paths.get(mutantPath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Driectory structure created");
				}else {
					System.out.println("Directory already exists");
				}
			}
		}
		
	}
	
	public String returnOPath() {
		return originalTestPath;
	}
	
	public String returnTestCopyPath() {
		return testCopyPath;
	}
	
	public String returnMutantPath() {
		return mutantPath;
	}
	
	public String returnOriginalCName() {
		return nameOfClassUnderTest;
	}
	
	public String returnDClassName() {
		return dependentClassName;
	}
	
	public char returnDClassChoice() {
		return testForDClass;
	}
	
	public String returnOriginalClassName() {
		return originalClassName;
	}
	
//	public String returnOriginalTName() {
//		return nameOfTestClass;
//	}
}
