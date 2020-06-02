import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConditionalOpStorage {
	private static List<String> conditionOpL = new ArrayList<String>();
	private static List<String> conditionOpP = new ArrayList<String>();
	
//	private static Set<String> duplicates = new HashSet<>();
//	private static Set<String> set = new HashSet<>();
	
//	public static void main(String[] args) throws IOException {
//		ConditionalOpStorage cp = new ConditionalOpStorage();
//		cp.processOp();
//		cp.printOp();
//		cp.processList(conditionOpL);
//		cp.printProcessedOp();
//	}
	
	
	public void processOp() throws IOException{
		FileReader fr = new FileReader("src/Temp.java");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println"))
				continue;
			
			if(line.contains("&&") ) {
				conditionOpL.add(line);
//				String[] words = line.split("");
//				for(int i=0;i<words.length;i++) {
//					if((words[i].contains("&")) && (words[i+1].contains("&"))){
//						conditionOpL.add(words[i-6]+words[i-5]+words[i-4]+words[i-3]+words[i-2]+words[i-1]+words[i]+words[i+1]+words[i+2]+words[i+3]);
//					}
//					
//					if((words[i].contains("|")) && (words[i+1].contains("|"))){
//						conditionOpL.add(words[i-6]+words[i-5]+words[i-4]+words[i-3]+words[i-2]+words[i-1]+words[i]+words[i+1]+words[i+2]+words[i+3]);
//					}
//				}
			}else if(line.contains("||")) {
				conditionOpL.add(line);
			}
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
//	public void printOp() {
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
//	}
	
	public List<String> returnOpList(){
		return conditionOpL;
	}
	
	public void processList(List<String> conditionOpL ) {
		for(String s : conditionOpL) {
			if(s.contains("&&")) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace("&&", "||"));
				
			}
			if(s.contains("||")) {
				//s.replace("-", "+");
				conditionOpP.add(s.replace("||", "&&"));
				
			}
			
		}
	}
	
	public List<String> retriveProcessList(){
		return conditionOpP;
	}
	
//	public void printProcessedOp() {
//		for(int i=0;i<conditionOpP.size();i++) {
//			System.out.println(conditionOpP.get(i));
//		}
//	}
	
	

}
