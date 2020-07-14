import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IncrementDecrementOpStorage {
	
	List<String> incdecOpL = new ArrayList<String>();
	List<String> incdecOpP = new ArrayList<String>();
 	
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
			if((line.contains("System.out.println") && line.contains("\"")) || (line.contains("\"") && line.contains("+")))
				continue;
			
			if(line.contains("'++'") || line.contains("'--'"))
				continue;
			
//			if(line.contains("return")) {
//				if((line.contains("++") && !(line.contains("+"))) || (line.contains("--") || !(line.contains("-")))) {
//					incdecOpL.add(line);
//				}
//			}
			
			//if(line.contains("=") && !(line.contains("+=")) && !(line.contains("-=")) && !(line.contains("*=")) && !(line.contains("/=")) && !(line.contains("%=")) && !(line.contains("&=")) && !(line.contains("|=")) && !(line.contains("^=")) && !(line.contains("<<=")) && !(line.contains(">>="))) {
			//if((line.contains("++") && !(line.contains("+"))) || (line.contains("--") && !(line.contains("-")))) {
			if((line.contains("++") && !(line.contains("\"++\""))) || (line.contains("--") && !(line.contains("\"--\"")))) {	
					//String newLine = line.trim();
					incdecOpL.add(line);
					//System.out.println(line);
				}

		}
		br.close();
		fr.close();	
	}
	
	public List<String> returnOpList(){
		return incdecOpL;
	}
	
//	public void printIncDecList() {
//		System.out.println("\nPre List for Inc/Dec Ops");
//		for(int i=0;i<incdecOpL.size();i++) {
//			System.out.println(incdecOpL.get(i));
//		}
//	}
	
	public void processList(List<String> incdecOpL ) {
		//String check = "+"+System.lineSeparator();
		for(String s : incdecOpL) {
			if(s.contains("--")) {
				//s.replace("-", "+");
				incdecOpP.add(s.replace("--", "++"));
			}
			if(s.contains("++")) {
				//s.replace("-", "+");
				incdecOpP.add(s.replace("++", "--"));
			}	
		}
	}
	
	public List<String> retriveProcessList(){
		return incdecOpP;
	}
}
