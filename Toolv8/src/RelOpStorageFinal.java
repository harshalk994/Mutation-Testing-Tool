import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelOpStorageFinal {
	
	private static List<String> conditionOpL = new ArrayList<String>();
	private static List<String> conditionOpP = new ArrayList<String>();
	private static List<String> conditionOpLNotEqual = new ArrayList<String>();
	private static List<String> conditionOpPNotEqual = new ArrayList<String>();
	
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
			
			if(line.contains("List") || line.contains("ArrayList") || line.contains("LinkedList") || line.contains("Vector") || line.contains("Stack"))
				continue;
			
			if(line.contains("Set") || line.contains("HashSet") || line.contains("TreeSet") || line.contains("LinkedHashSet") || line.contains("EnumSet") || line.contains("CopyOnWriteArraySet"))
				continue;
			
			if(line.contains("Queue") || line.contains("PriorityQueue") || line.contains("Collection ") || line.contains("Iterator") || line.contains("Comparator") || line.contains("LinkedBlockingQueue") || line.contains("ArrayBlockingQueue") || line.contains("PriorityBlockingQueue") || line.contains("DelayQueue") || line.contains("SynchronousQueue") || line.contains("BlockingQueue") || line.contains("TransferQueue") || line.contains("LinkedTransferQueue") || line.contains("LinkedQueue"))
				continue;
			
			if(line.contains("<String>") || line.contains("<Integer>") || line.contains("<Character>") || line.contains("<Boolean>") || line.contains("<Byte>") || line.contains("<Float>") || line.contains("<Long>") || line.contains("<Short>") || line.contains("<Double>"))
				continue;
			
			if(line.contains("'>'") || line.contains("'<'") || line.contains("'=='") || line.contains("'!='") || line.contains("'>='") || line.contains("'<='"))
				continue;
			
			//if(line.contains("if") || line.contains("else if") || line.contains("for") || line.contains("while")) {
			if(line.contains(">") && !(line.contains("\">\"")) && !(line.contains(">=")) && !(line.contains("\">=\"")) && !(line.contains(">>")) && !(line.contains("\">>\"")) && !(line.contains(">>=")) && !(line.contains("\">>=\"")) && !(line.contains(">>>")) && !(line.contains("\">>>\""))){
				conditionOpL.add(line);
			}else if(line.contains("<") && !(line.contains("\"<\"")) && !(line.contains("<=")) && !(line.contains("\"<=\"")) && !(line.contains("<<=")) && !(line.contains("\"<<=\"")) && !(line.contains("<<")) && !(line.contains("\"<<\""))) {
				conditionOpL.add(line);
			}else if(line.contains("==") && !(line.contains("\"==\"")) && line.contains("null")) {
				conditionOpLNotEqual.add(line);
			}else if(line.contains("==") && !(line.contains("\"==\"")) && !(line.contains("null"))) {
				conditionOpL.add(line);
			}else if(line.contains("!=") && !(line.contains("\"!=\"")) && line.contains("null")) {
				conditionOpLNotEqual.add(line);
			}else if(line.contains("!=") && !(line.contains("\"!=\"")) && !(line.contains("null"))) {
				conditionOpL.add(line);
			}else if(line.contains(">=") && !(line.contains("\">=\"")) && !(line.contains(">>=")) && !(line.contains("\">>=\""))) {
				conditionOpL.add(line);
			}else if(line.contains("<=") && !(line.contains("\"<=\"")) && !(line.contains("<<=")) && !(line.contains("\"<<=\""))) {
				conditionOpL.add(line);
			}
			//}
			
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
	public List<String> returnRelOpList(){
		return conditionOpL;
	}
	
	public List<String> returnRelOpListNotEqual(){
		return conditionOpLNotEqual;
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
	
	public void processListNotEqual(List<String> conditionOpLNotEqual ) {
		for(String s : conditionOpLNotEqual) {
			if(s.contains("==")) {
				conditionOpPNotEqual.add(s.replace("==", "!="));
			}
			if(s.contains("!=")) {
				//s.replace("-", "+");
				conditionOpPNotEqual.add(s.replace("!=", "=="));
			}

		}
	}

	
	
	public List<String> retriveRelProcessList(){
		return conditionOpP;
	}
	
	public List<String> retriveRelProcessListNotEq(){
		return conditionOpPNotEqual;
	}
	
//	public void listMutantCount() {
//		for(int i=0;i<conditionOpP.size();i++) {
//			System.out.println(conditionOpP.get(i));
//		}
//	}
}
