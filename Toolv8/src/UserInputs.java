import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserInputs {
	
	private static String originalpath;
	private static String mutantpath;
	private static String finalPath;
	private static String originalClassName; 
	
	public void readProperties() {
		File configFile = new File("config.properties");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String oPath = props.getProperty("originalprogrampath");
		    String mPath = props.getProperty("mutantdestination");
		    String pName = props.getProperty("originalprogrampackagename");
		    String cName = props.getProperty("originalclassname");
		    System.out.println("pName is: " + pName);
		    setProperties(oPath, mPath, pName, cName);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void setProperties(String oPath, String mPath, String pName, String oCName) throws IOException {
		originalpath = oPath;
		mutantpath = mPath;
		originalClassName = oCName;
		
		String appendPath;
		if(pName!=null) {
			if(pName.contains(".")) {
				appendPath = pName.replace(".", "\\");
				mutantpath = mPath+"\\"+appendPath;
				Path path = Paths.get(mutantpath);
				
				if(!Files.exists(path)){
					Files.createDirectories(path);
					System.out.println("Driectory structure created");
				}else {
					System.out.println("Directory already exists");
				}
			}else {
				mutantpath = mPath+"\\"+pName;
				Path path = Paths.get(mutantpath);
				
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
		return originalpath;
	}
	
	public String returnMPath() {
		return mutantpath;
	}
	
	public String returnCName() {
		return originalClassName;
	}
}
