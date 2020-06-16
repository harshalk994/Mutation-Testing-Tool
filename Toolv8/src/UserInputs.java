import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class UserInputs {
	
	String originalpath;
	String mutantpath;
	
	public void readProperties() {
		File configFile = new File("config.properties");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String oPath = props.getProperty("originalprogrampath");
		    String mPath = props.getProperty("mutantdestination");
		    setProperties(oPath, mPath);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void setProperties(String oPath, String mPath) {
		originalpath = oPath;
		mutantpath = mPath;
	}
	
	public String returnOPath() {
		return originalpath;
	}
	
	public String returnMPath() {
		return mutantpath;
	}
}
