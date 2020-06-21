import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShiftOpStorage {
	
	private static List<String> shiftOpL = new ArrayList<String>();
	private static List<String> shiftOpP = new ArrayList<String>();
	

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
			if(line.contains("System.out.println") && line.contains("+"))
				continue;
			
				if(line.contains(">>")){
					shiftOpL.add(line);
				}else if(line.contains("<<")) {
					shiftOpL.add(line);
				}else if(line.contains(">>>")) {
					shiftOpL.add(line);
				}
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
	public List<String> returnShiftOpList(){
		return shiftOpL;
	}
	
//	public void printOp() {
//		
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
//	}
	
	
	public void processList(List<String> shiftOpL ) {
		for(String s : shiftOpL) {
			if(s.contains(">>")) {
				//s.replace("-", "+");
				shiftOpP.add(s.replace(">>", "<<"));
				shiftOpP.add(s.replace(">>", ">>>"));
			}
			if(s.contains("<<")) {
				//s.replace("-", "+");
				shiftOpP.add(s.replace("<<", ">>"));
				shiftOpP.add(s.replace("<<", ">>>"));
			}
			if(s.contains(">>>")) {
				//s.replace("-", "+");
				shiftOpP.add(s.replace(">>>", ">>"));
				shiftOpP.add(s.replace(">>>", "<<"));
			}
				
		}
	}
	
	public List<String> retriveShiftProcessList(){
		return shiftOpP;
	}

	
}
