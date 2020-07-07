import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TempFileGenerator {
	
	private static String mPath;
	private static String fPath;
	private static String className;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void getFPath(String originalFilePath) {
		fPath = originalFilePath;
	}
	public void createTempFile() throws IOException {
//		List<String> paths = new ArrayList<String>();
//    	FileDetailsStorage fds3 = new FileDetailsStorage();
//    	paths = fds3.returnPaths();
//    	for(int i=0;i<paths.size();i++) {
//    		System.out.println(paths.get(i));
//    	}
//    	String mPath = null;
//    	
//    	for(int i=0;i<paths.size();) {
//    		i++;
//    		mPath = paths.get(i);
//    		break;		
//    	}
//    	
//    	System.out.println(mPath);
		
//		String[] locatewords = fPath.split("\\\\");
//		for(int i=0;i<locatewords.length;i++) {
//			if(locatewords[i].contains(".java")) {
//				String newW = locatewords[i].replace(".java", "");
//				className = newW;
//				
//			}
//		}
		//System.out.println("Classname is : " + className);
		SetClassNameProperty scp = new SetClassNameProperty();
		//scp.setCName("FirstTemp");
		//TempFileGenerator tfg = new TempFileGenerator();
		className = scp.getCName();
		String sourceFileName = mPath+"\\OriginalTempCopy.java";
    	String tempFileName = mPath+"\\FirstTemp.java";
    	
    	//System.out.println("In TempFileGenerator Class name was set to: " + scp.getCName());
		FileReader source = new FileReader(sourceFileName);
		BufferedReader br = new BufferedReader(source);
		FileWriter targetFile = new FileWriter(tempFileName);
		BufferedWriter bw = new BufferedWriter(targetFile);
		List<String> opList = new ArrayList<String>();
		OperatorStorage op = new OperatorStorage();
		
		String line;
		while((line = br.readLine()) != null) {
				
			if(line.contains(className) && line.contains("(")) {
				//System.out.println("I found bracket line");
				String[] brackets = line.split("");
				for(int j=0;j<brackets.length;j++) {
					if(brackets[j].contains("(")) {
						String replaceBrackets = brackets[j-1]+System.lineSeparator();
						String temp = replaceBrackets;
						brackets[j-1] = temp;
						//System.out.println("HERE: " + brackets[j-1]);
					}
			}
				String newLine = String.join("", brackets);
				bw.write(newLine);
				bw.newLine();
				
			}

				else {
				bw.write(line);
				bw.newLine();
				}
			
		}
		
		br.close();
		bw.close();
		source.close();
		targetFile.close();		
	}

}
