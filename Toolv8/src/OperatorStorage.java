import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperatorStorage {
	
	List<String> opList = new ArrayList<String>();
	List<String> opP = new ArrayList<String>();
	private static String mPath;
	
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
 	
	public void processOp() throws IOException{
		String tempFileName = mPath+"\\Temp.java";
		//String mutantFileName = mPath+"\\MuArithOp";
		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String check = "+"+System.lineSeparator();
		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println") && line.contains("+"))
				continue;
			//if(line.contains("=") && !(line.contains("+=")) && !(line.contains("-=")) && !(line.contains("*=")) && !(line.contains("/=")) && !(line.contains("%=")) && !(line.contains("&=")) && !(line.contains("|=")) && !(line.contains("^=")) && !(line.contains("<<=")) && !(line.contains(">>="))) {
				if((line.contains("+") && !(line.contains("+="))) || (line.contains("-") && !(line.contains("-=")))  || (line.contains("*") && !(line.contains("*="))) || (line.contains("/")) && !(line.contains("/=")) || ((line.contains("%")) && !(line.contains("%=")))) {
					String newLine = line.trim();
					opList.add(newLine);
				}
//				if(line.contains(check)) {
//					opList.add(line);
//				}
			//}
		}
		br.close();
		fr.close();
		
	
		
	}
	
	public List<String> returnOpList(){
		return opList;
	}
	
//	public void printOp() {
//		
//		for(int i=0;i<opList.size();i++) {
//			System.out.println(opList.get(i));
//		}
//	}
	
	
	public void processList(List<String> opL ) {
		for(String s : opL) {
			if(s.contains("-")) {
				//s.replace("-", "+");
				opP.add(s.replace("-", "+"));
				opP.add(s.replace("-", "*"));
				opP.add(s.replace("-", "/"));
				opP.add(s.replace("-", "%"));
			}
			if(s.contains("+")) {
				//s.replace("-", "+");
				opP.add(s.replace("+", "-"));
				opP.add(s.replace("+", "*"));
				opP.add(s.replace("+", "/"));
				opP.add(s.replace("+", "%"));
			}
			if(s.contains("*")) {
				//s.replace("-", "+");
				opP.add(s.replace("*", "+"));
				opP.add(s.replace("*", "-"));
				opP.add(s.replace("*", "/"));
				opP.add(s.replace("*", "%"));
			}
			if(s.contains("/")) {
				//s.replace("-", "+");
				opP.add(s.replace("/", "+"));
				opP.add(s.replace("/", "-"));
				opP.add(s.replace("/", "*"));
				opP.add(s.replace("/", "%"));
			}
			if(s.contains("%")) {
				//s.replace("-", "+");
				opP.add(s.replace("%", "+"));
				opP.add(s.replace("%", "-"));
				opP.add(s.replace("%", "*"));
				opP.add(s.replace("%", "/"));
			}
				
		}
	}
	
	public List<String> retriveProcessList(){
		return opP;
	}
	

}
