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
	List<String> opTwoL = new ArrayList<String>();
	List<String> opTwoP = new ArrayList<String>();
 	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
//	public void processOp() throws IOException{
//		List<String> removeNull = new ArrayList<String>();
//		String tempFileName = mPath+"\\Temp.java";
//		//String mutantFileName = mPath+"\\MuArithOp";
//		FileReader fr = new FileReader(tempFileName);
//		BufferedReader br = new BufferedReader(fr);
//		String line;
//		String check = "+"+System.lineSeparator();
//		while((line = br.readLine()) != null) {
//			if(line.contains("System.out.println") && line.contains("+"))
//				continue;
//			
//			if(line.contains("return")) {
//				boolean flag=false;
//				String[] words = line.split("");
//				for(int i=0;i<words.length;i++) {
//					if((words[i].contains("r") && words[i+1].contains("e") && words[i+2].contains("t") && words[i+3].contains("u") && words[i+4].contains("r") && words[i+5].contains("n") && words[i+6].contains("-"))) {
//						//System.out.println("Found return");
//						flag=true;
//					}else if((words[i].contains("r") && words[i+1].contains("e") && words[i+2].contains("t") && words[i+3].contains("u") && words[i+4].contains("r") && words[i+5].contains("n") && words[i+7].contains("-"))) {
//						flag=true;
//					}
//					
//				}
//				
//				
//				if(flag==true) {
//					continue;
//				}
////					else if((words[i].contains("r") && words[i+1].contains("e") && words[i+2].contains("t") && words[i+3].contains("u") && words[i+4].contains("r") && words[i+5].contains("n") && words[i+7].contains("-"))){
////						continue;
////					}
////				}
//			}
//			
////			if(line.contains("=") && line.contains("+")) {
////				boolean flag=false;
////				String[] words = line.split("");
////				for(int i=0;i<words.length;i++) {
////					if(words[i].contains("=") && words[i+1].contains("+")) {
////						flag=true;
////					}
////				}
////				
////				if(flag==true) {
////					continue;
////				}
////			} 
//			
////			if((line.contains("+") && !(line.contains("+="))) || (line.contains("-") && !(line.contains("-=")))  || (line.contains("*") && !(line.contains("*="))) || (line.contains("/")) && !(line.contains("/=")) || ((line.contains("%")) && !(line.contains("%=")))) {
////				String[] words = line.split("");
////				for(int i=0;i<words.length;i++) {
////					if(words[i].contains("=") && words[i+2].contains("+")) {
////						continue;
////						
////							
////					}else {
////						
////					}
////				}
//////				String newLine = line.trim();
//////				opList.add(newLine);
////			}
//			
//			if(line.contains("=") && line.contains("-") && !(line.contains("=+"))) {
//				String[] words = line.split("");
//				String[] newWords = new String[words.length-1];
//				for(int i=0, j=0;i<words.length;i++) {
//					if(words[i].contains("=") && !(words[i+2].contains("-"))) 
//						continue;
//						
////					String newLine = String.join("", words);
////					newLine = line.trim();
////					opList.add(newLine);
//						newWords[j++]=words[i];
//					}
//				
//				for(String s : newWords) {
//			    	  if(s!=null && s.length() > 0) {
//			    		  removeNull.add(s);
//			    	  }
//			      }
//				
//			      
//			      newWords = removeNull.toArray(new String[removeNull.size()]);
//			      
//			      System.out.println("Found\n");
//			      for(int i=0;i<newWords.length;i++) {
//			    	  
//			    	  System.out.println(newWords[i]);
//			      }
//			      
//			      String newLine = String.join("", newWords);
//					newLine = line.trim();
//					opList.add(newLine);
////					else {
////						
////					}
//				}
//				
////			}
////			if(line.contains(check)) {
////				opList.add(line);
////			}
//		//}
//	}
//
//			//if(line.contains("=") && !(line.contains("+=")) && !(line.contains("-=")) && !(line.contains("*=")) && !(line.contains("/=")) && !(line.contains("%=")) && !(line.contains("&=")) && !(line.contains("|=")) && !(line.contains("^=")) && !(line.contains("<<=")) && !(line.contains(">>="))) {
//			
//		br.close();
//		fr.close();
//		
//		
//	}
	
	public void processOp() throws IOException{
		String tempFileName = mPath+"\\Temp.java";
		//String mutantFileName = mPath+"\\MuArithOp";
		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String check = "+"+System.lineSeparator();
		while((line = br.readLine()) != null) {
			if((line.contains("System.out.println") && line.contains("\"")) || (line.contains("\"") && line.contains("+")) || (line.contains("\"") && line.contains("*")) || (line.contains("\"") && line.contains("/")) || (line.contains("\"") && line.contains("-")) || (line.contains("\"") && line.contains("%")))
				continue;
			
			if(line.contains("case"))
				continue;
			
			if(line.contains("\""))
				continue;
			
			if(line.contains("switch"))
				continue;
			
			if(line.contains("import"))
				continue;
			
//			if(line.contains("return")) {
//				boolean flag=false;
//				String[] words = line.split("");
//				for(int i=0;i<words.length;i++) {
//					if((words[i].contains("r") && words[i+1].contains("e") && words[i+2].contains("t") && words[i+3].contains("u") && words[i+4].contains("r") && words[i+5].contains("n") && words[i+6].contains("-"))) {
//						//System.out.println("Found return");
//						flag=true;
//					}else if((words[i].contains("r") && words[i+1].contains("e") && words[i+2].contains("t") && words[i+3].contains("u") && words[i+4].contains("r") && words[i+5].contains("n") && words[i+7].contains("-"))) {
//						flag=true;
//					}
//					
//				}
//				
//				for(int j=0;j<words.length;j++) {
//					if((words[j].contains("r") && words[j+1].contains("e") && words[j+2].contains("t") && words[j+3].contains("u") && words[j+4].contains("r") && words[j+5].contains("n") && words[j+6].contains("+"))) {
//						//System.out.println("Found return");
//						flag=true;
//					}else if((words[j].contains("r") && words[j+1].contains("e") && words[j+2].contains("t") && words[j+3].contains("u") && words[j+4].contains("r") && words[j+5].contains("n") && words[j+7].contains("+"))) {
//						flag=true;
//					}
//					
//				}
//				
//				
//				if(flag==true) {
//					continue;
//				}
//					else if((words[i].contains("r") && words[i+1].contains("e") && words[i+2].contains("t") && words[i+3].contains("u") && words[i+4].contains("r") && words[i+5].contains("n") && words[i+7].contains("-"))){
//						continue;
//					}
//				}
//			}
			
			if(line.contains("return")) {
				if((line.contains("return-") || line.contains("return -") || line.contains("return+") || line.contains("return +")) && (!(line.contains("return--")) && !(line.contains("return --")) && !(line.contains("return++")) && !(line.contains("return ++")))) {
					opTwoL.add(line);
				}
			}
			
			if(line.contains("\"+\"") || line.contains("\"-\"") || line.contains("\"*\"") || line.contains("\"/\"") || line.contains("\"%\""))
				continue;
			
			if(line.contains("'+'") || line.contains("'-'") || line.contains("'*'") || line.contains("'/'") || line.contains("'%'"))
				continue;
			
			//if(line.contains("=") && !(line.contains("+=")) && !(line.contains("-=")) && !(line.contains("*=")) && !(line.contains("/=")) && !(line.contains("%=")) && !(line.contains("&=")) && !(line.contains("|=")) && !(line.contains("^=")) && !(line.contains("<<=")) && !(line.contains(">>="))) {
			if((line.contains("+") && !(line.contains("+=")) && !(line.contains("=+")) && !(line.contains("= +")) && !(line.contains("++")) && !(line.contains("--")) && !(line.contains("return+")) && !(line.contains("return +")) && !(line.contains("return++")) && !(line.contains("return ++"))) || (line.contains("-") && !(line.contains("++")) && !(line.contains("-=")) && !(line.contains("=-")) && !(line.contains("= -")) && !(line.contains("--")) && !(line.contains("return-")) && !(line.contains("return -")) && !(line.contains("return--")) && !(line.contains("return --")))  || (line.contains("*") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("*="))) || (line.contains("/")) && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("/=")) || (line.contains("%") && !(line.contains("--")) && !(line.contains("++")) && !(line.contains("%=")))) {
					//String newLine = line.trim();
					opList.add(line);
				}
			
			if(line.contains("=+") || line.contains("= +")) {
				//String newLine = line.trim();
				//System.out.println(line);
				opTwoL.add(line);
			}
			
			if(line.contains("=-") || line.contains("= -")) {
				opTwoL.add(line);
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
	
	public List<String> returnOpTwoList(){
		return opTwoL;
	}
	
//	public void printOp() {		
//		System.out.println("\n -----------Pre List----------------");
//		for(int i=0;i<opList.size();i++) {
//			System.out.println(opList.get(i));
//		}
//	}
	
//	public void printOpTwo() {		
//		System.out.println("\n -----------Pre Two List----------------");
//		for(int i=0;i<opTwoL.size();i++) {
//			System.out.println(opTwoL.get(i));
//		}
//	}
	
	
	public void processList(List<String> opL ) {
		//String check = "+"+System.lineSeparator();
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
//			if(s.contains(check)) {
//				opP.add(s.replace(check, "-"));
//			}
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
	
	public void processTwoList(List<String> opTwoL ) {
		//String check = "+"+System.lineSeparator();
		for(String s : opTwoL) {
			if(s.contains("-")) {
				//s.replace("-", "+");
				opTwoP.add(s.replace("-", "+"));
			}
			if(s.contains("+")) {
				//s.replace("-", "+");
				opTwoP.add(s.replace("+", "-"));
			}	
		}
	}
	
	public List<String> retriveProcessList(){
		return opP;
	}
	
	public List<String> retriveProcessTwoList(){
		return opTwoP;
	}
	
//	public void printPostOp() {		
//		System.out.println("\n -----------Post List----------------");
//		for(int i=0;i<opP.size();i++) {
//			System.out.println(opP.get(i));
//		}
//	}
	
//	public void printPostOpTwo() {		
//		System.out.println("\n -----------Post Two List----------------");
//		for(int i=0;i<opTwoP.size();i++) {
//			System.out.println(opTwoP.get(i));
//		}
//	}
	

}
