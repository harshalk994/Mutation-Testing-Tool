import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelOpStorageFinal {
	
	private static List<String> conditionOpL = new ArrayList<String>();
	private static List<String> conditionOpP = new ArrayList<String>();
	
//	public static void main(String[] args) throws IOException {
//		RelOpStorageFinal rel = new RelOpStorageFinal();
//		rel.processOp();
//		rel.printOp();
//		rel.processList(conditionOpL);
//		rel.listMutantCount();
//		
//	}
 	
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
			if(line.contains("System.out.println"))
				continue; 
			
			if(line.contains("if") || line.contains("else if") || line.contains("for") || line.contains("while")) {
				if(line.contains(">") && !(line.contains(">=")) && !(line.contains(">>")) && !(line.contains(">>=")) && !(line.contains(">>>"))){
					conditionOpL.add(line);
				}else if(line.contains("<") && !(line.contains("<=")) && !(line.contains("<<=")) && !(line.contains("<<"))) {
					conditionOpL.add(line);
				}else if(line.contains("==")) {
					conditionOpL.add(line);
				}else if(line.contains("!=")) {
					conditionOpL.add(line);
				}else if(line.contains(">=") && !(line.contains(">>="))) {
					conditionOpL.add(line);
				}else if(line.contains("<=") && !(line.contains("<<="))) {
					conditionOpL.add(line);
				}
			}
			
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
	public List<String> returnRelOpList(){
		return conditionOpL;
	}
	
//	public void printOp() {
//		System.out.println("Original Operators Stored are: \n");
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
//	}
	
	
	public void processList(List<String> conditionOpL ) {
		for(String s : conditionOpL) {
			if(s.contains(">") && !(s.contains(">="))) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace(">", "<"));
				conditionOpP.add(s.replace(">", "=="));
				conditionOpP.add(s.replace(">", "!="));
				conditionOpP.add(s.replace(">", ">="));
				conditionOpP.add(s.replace(">", "<="));
			}
			if(s.contains("<") && !(s.contains("<="))) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace("<", ">"));
				conditionOpP.add(s.replace("<", "=="));
				conditionOpP.add(s.replace("<", "!="));
				conditionOpP.add(s.replace("<", ">="));
				conditionOpP.add(s.replace("<", "<="));
			}
			if(s.contains("==")) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace("==", "<"));
				conditionOpP.add(s.replace("==", ">"));
				conditionOpP.add(s.replace("==", "!="));
				conditionOpP.add(s.replace("==", ">="));
				conditionOpP.add(s.replace("==", "<="));
			}
			if(s.contains("!=")) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace("!=", "<"));
				conditionOpP.add(s.replace("!=", "=="));
				conditionOpP.add(s.replace("!=", ">"));
				conditionOpP.add(s.replace("!=", ">="));
				conditionOpP.add(s.replace("!=", "<="));
			}
			if(s.contains(">=")) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace(">=", "<"));
				conditionOpP.add(s.replace(">=", "=="));
				conditionOpP.add(s.replace(">=", "!="));
				conditionOpP.add(s.replace(">=", ">"));
				conditionOpP.add(s.replace(">=", "<="));
			}
			if(s.contains("<=")) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace("<=", "<"));
				conditionOpP.add(s.replace("<=", "=="));
				conditionOpP.add(s.replace("<=", "!="));
				conditionOpP.add(s.replace("<=", ">"));
				conditionOpP.add(s.replace("<=", ">="));
			}
				
		}
	}
	
	public List<String> retriveRelProcessList(){
		return conditionOpP;
	}
	
//	public void listMutantCount() {
//		for(int i=0;i<conditionOpP.size();i++) {
//			System.out.println(conditionOpP.get(i));
//		}
//	}
}
