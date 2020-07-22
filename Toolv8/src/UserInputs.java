import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserInputs {
	
	private static String originalpath;
	private static String dependentpath;
	private static String originalpathWOCname;
	private static String mutantpath;
	private static String finalPath;
	//private static String depenedentClassCopyPath;
	private static String originalClassName; 
	private static String dependentClassName;
	private static char arithop=' ';
	private static char assignmentop=' ';
	private static char bitwiseop=' ';
	private static char conditionalop=' ';
	private static char incdecop=' ';
	private static char relationalop=' ';
	private static char shiftop=' ';
	
	public void readProperties() {
		File configFile = new File("config.properties");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String oPath = props.getProperty("originalprogrampath");
		    String oPathWOCName = props.getProperty("originalprogrampath");
		    String mPath = props.getProperty("mutantdestination");
		    String pName = props.getProperty("originalprogrampackagename");
		    String cName = props.getProperty("originalclassname");
		    String dCName = props.getProperty("dependentclassname");
		   // String dPath = props.getProperty("dependentclasscopypath");
		    String arith = props.getProperty("arithop(y/n)");
		    //System.out.println("In config file value of arithop was set to: " + arith);
		    String assign = props.getProperty("assignmentop(y/n)");
		    String bitwise = props.getProperty("bitwiseop(y/n)");
		    String conditional = props.getProperty("conditionalop(y/n)");
		    String incdec = props.getProperty("incrementdecrementop(y/n)");
		    String relational = props.getProperty("relationalop(y/n)");
		    String shift = props.getProperty("shiftop(y/n)");
		    
		    
		    String newOPath=oPath+"\\"+cName+".java";
		    String dPath = oPath+"\\"+dCName+".java";
		    //System.out.println("pName is: " + pName);
		    
		    setProperties(newOPath, dPath, oPathWOCName, mPath, pName, cName, dCName, arith, assign, bitwise, conditional, incdec, relational, shift);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void setProperties(String newOPath, String dPath, String oPathWOCName, String mPath, String pName, String oCName, String dCName, String arith, String assign, String bitwise, String conditional, String incdec, String relational, String shift) throws IOException {
		originalpath = newOPath;
		dependentpath = dPath;
		originalpathWOCname = oPathWOCName;
		mutantpath = mPath;
		//depenedentClassCopyPath = dPath;
		originalClassName = oCName;
		dependentClassName = dCName;
//		System.out.println("Got value of arithop inside setProperties as : " + arith);
//		System.out.println("value of assignment op was: " + assign);
		if(arith.isBlank()==false) {
			char convertedarith = arith.charAt(0);
			if(convertedarith=='y' || convertedarith=='n') {
				arithop = convertedarith;
				//System.out.println("value of arithop was set to: " + arithop);
			}
		}
		
//		char oparith=' ';
//		oparith = convertedarith;
//		System.out.println("In set props converted value of arith in char is: " + convertedarith);
//		System.out.println("In set props converted value of oparith in char is: " + oparith);
		if(assign.isBlank()==false) {
			char convertedassign = assign.charAt(0);
			if(convertedassign=='y' || convertedassign=='n') {
				assignmentop = convertedassign;
			}
		}
		
		if(bitwise.isBlank()==false) {
			char convertedbitwise = bitwise.charAt(0);
			if(convertedbitwise=='y' || convertedbitwise=='n') {
				bitwiseop = convertedbitwise;
			}
		}
		
		if(conditional.isBlank()==false) {
			char convertedconditional = conditional.charAt(0);
			if(convertedconditional=='y' || convertedconditional=='n') {
				conditionalop = convertedconditional;
			}
		}
		
		if(incdec.isBlank()==false) {
			char convertedincdec = incdec.charAt(0);
			if(convertedincdec=='y' || convertedincdec=='n') {
				incdecop = convertedincdec;
			}
		}
		
		if(relational.isBlank()==false) {
			char convertedrelational = relational.charAt(0);
			if(convertedrelational=='y' || convertedrelational=='n') {
				relationalop = convertedrelational;
			}
		}
		
		if(shift.isBlank()==false) {
			char convertedshift = shift.charAt(0);
			if(convertedshift=='y' || convertedshift=='n') {
				shiftop = convertedshift;
			}
		}
		
		
		String appendPath;
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "\\");
				mutantpath = mPath+"\\"+appendPath;
				Path path = Paths.get(mutantpath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Driectory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}else {
				mutantpath = mPath+"\\"+pName;
				Path path = Paths.get(mutantpath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Package Driectory structure created");
				}else {
					System.out.println("Package Directory structure not required, proceeding...");
				}
			}
		}
		
//		String appendDPath;
//		if(pName!=null) {
//			if(pName.contains(".")) {
//				appendDPath = pName.replace(".", "\\");
//				depenedentClassCopyPath = dPath+"\\"+appendDPath;
//				Path path = Paths.get(depenedentClassCopyPath);
//				
//				if(!Files.exists(path)){
//					Files.createDirectories(path);
//					System.out.println("Driectory structure created");
//				}else {
//					System.out.println("Directory already exists");
//				}
//			}else {
//				depenedentClassCopyPath = dPath+"\\"+pName;
//				Path path = Paths.get(depenedentClassCopyPath);
//				
//				if(!Files.exists(path)){
//					Files.createDirectories(path);
//					System.out.println("Driectory structure created");
//				}else {
//					System.out.println("Directory already exists");
//				}
//			}
//		}
		
	}
	
	public String returnOPath() {
		return originalpath;
	}
	
	public String returnOPathWoCName() {
		return originalpathWOCname;
	}
	
	public String returnDPath() {
		return dependentpath;
	}
	
	public String returnMPath() {
		return mutantpath;
	}
	
	public String returnCName() {
		return originalClassName;
	}
	
	public String returnDName() {
		return dependentClassName;
	}
	
//	public String returnDPath() {
//		return depenedentClassCopyPath;
//	}
	
	public char returnArithOp() {
		return arithop;
	}
	
	public char returnAssignOp() {
		return assignmentop;
	}
	
	public char returnBitwiseOp() {
		return bitwiseop;
	}
	
	public char returnConditionalOp() {
		return conditionalop;
	}
	
	public char returnIncDecOp() {
		return incdecop;
	}
	
	public char returnRelationalOp() {
		return relationalop;
	}
	
	public char returnShiftOp() {
		return shiftop;
	}
}
