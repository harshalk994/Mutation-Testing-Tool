import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
//			if(line.contains("System.out.println") && line.contains("+"))
//				continue;
			
				if(line.contains(">>") && !(line.contains(">>=")) && !(line.contains(">>>")) && !(line.contains(">>>="))){
					shiftOpL.add(line);
				}else if(line.contains("<<") && !(line.contains("<<="))) {
					shiftOpL.add(line);
				}else if(line.contains(">>>") && !(line.contains(">>>="))) {
					shiftOpL.add(line);
				}
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
	public List<String> returnShiftOpList(){
		return shiftOpL;
	}
	
//	public void printShiftOps() {
//		for(int i=0;i<shiftOpL.size();i++) {
//			System.out.println("Found: " + shiftOpL.get(i));
//		}
//	}
	
//	public void printOp() {
//		
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
//	}
	
	
	public void processList(List<String> shiftOpL ) throws IOException {
		for(String s : shiftOpL) {
			if(s.contains(">>") && !(s.contains(">>>"))) {
				//s.replace("-", "+");
				shiftOpP.add(s.replace(">>", "<<"));
				shiftOpP.add(s.replace(">>", ">>>"));
			}
			if(s.contains("<<")) {
				//s.replace("-", "+");
				shiftOpP.add(s.replace("<<", ">>"));
				shiftOpP.add(s.replace("<<", ">>>"));
			}
			if(s.contains(">>>") ) {
//				String toReplace = ">>>"
//				String replaceRight = ">>";
//				String replaceLeft = "<<";
//				shiftOpP.add(s.replaceFirst(toReplace, replaceRight));
//				shiftOpP.add(s.replaceFirst(toReplace, replaceLeft));
				
//				System.out.println("HERE");
//				String tempFileName = mPath+"\\Temp.java";
//				//String mutantFileName = mPath+"\\MuArithOp";
//				FileReader source = new FileReader(tempFileName);
//				BufferedReader readme = new BufferedReader(source);
//				FileWriter target = new FileWriter(tempFileName);
//				BufferedWriter writeme = new BufferedWriter(target);
//				String line;
//				while((line = readme.readLine()) != null) {
//					System.out.println("CHECK1");
//					if(line.contains(">>>") && !(line.contains(">>>="))) {
//						System.out.println("CHECK");
//						String words[] = s.split("");
//						for(int i=0;i<words.length;i++) {
//							if(words[i].contains(">") && words[i+1].contains(">") && words[i+2].contains(">")) {
//								words[i+2].replace(">", "");
//							}
//						}
//						String newLine = String.join("", words);
//						writeme.write(newLine);
//						System.out.println("NOW HERE");
//						writeme.newLine();
//					}
//					else {
//						writeme.write(line);
//						writeme.newLine();
//					}
//				}
//				readme.close();
//				writeme.close();
//				source.close();
//				target.close();
//				
////				String replaceW = "";
////				String words[] = s.split("");
////				for(int i=0;i<words.length;i++) {
////					if(words[i].contains(">") && words[i+1].contains(">") && words[i+2].contains(">")) {
////						String temp = replaceW;
////						words[i+2]=temp;
////					}
////				}
////				String.join("", words);
////				System.out.println("Here " + s);
				shiftOpP.add(s.replace(">>>", ">>"));
				shiftOpP.add(s.replace(">>>", "<<"));
		}
	}
	}
	
	public List<String> retriveShiftProcessList(){
		return shiftOpP;
	}

	
}
