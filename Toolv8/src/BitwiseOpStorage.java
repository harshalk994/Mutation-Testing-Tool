import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BitwiseOpStorage {
	
	private static List<String> bitwiseOpL = new ArrayList<String>();
	private static List<String> bitwiseOpP = new ArrayList<String>();
	

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
		while((line = br.readLine()) != null) {
				if(line.contains("&") && !(line.contains("&&"))){
					bitwiseOpL.add(line);
				}else if(line.contains("|") && !(line.contains("||"))) {
					bitwiseOpL.add(line);
				}else if(line.contains("^")) {
					bitwiseOpL.add(line);
				}else if(line.contains("~")) {
					bitwiseOpL.add(line);
				}
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
	public List<String> returnBitwiseOpList(){
		return bitwiseOpL;
	}
	
//	public void printOp() {
//		
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
//	}
	
	
	public void processList(List<String> bitwiseOpL ) {
		for(String s : bitwiseOpL) {
			if(s.contains("&")) {
				//s.replace("-", "+");
				bitwiseOpP.add(s.replace("&", "|"));
				bitwiseOpP.add(s.replace("&", "^"));
				bitwiseOpP.add(s.replace("&", "~"));
			}
			if(s.contains("|")) {
				//s.replace("-", "+");
				bitwiseOpP.add(s.replace("|", "&"));
				bitwiseOpP.add(s.replace("|", "^"));
				bitwiseOpP.add(s.replace("|", "~"));
			}
			if(s.contains("^")) {
				//s.replace("-", "+");
				bitwiseOpP.add(s.replace("^", "|"));
				bitwiseOpP.add(s.replace("^", "&"));
				bitwiseOpP.add(s.replace("^", "~"));
			}if(s.contains("~")) {
				//s.replace("-", "+");
				bitwiseOpP.add(s.replace("~", "^"));
				bitwiseOpP.add(s.replace("~", "&"));
				bitwiseOpP.add(s.replace("~", "|"));
			}
				
		}
	}
	
	public List<String> retriveBitwiseProcessList(){
		return bitwiseOpP;
	}

	

}
