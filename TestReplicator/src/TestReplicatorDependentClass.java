import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestReplicatorDependentClass {
	
	private static String mutantsPath;
	private static String tmPath;
	private static String nameOfTestClass;
	private static String nameOfClassUnderTest;
	private static String mPath;
	private static String fPath;
	
	private static String className;
	private static String dependentClassName;
	
	public void getTMPath(String testFilePath) {
		tmPath = testFilePath;
	}
	
	public void getMPath(String mutantPath) {
		mutantsPath = mutantPath;
	}
	
	public void getClassName(String cName) {
		nameOfClassUnderTest = cName;
	}
	
	public void getPath(String testFilePath) {
		mPath = testFilePath;
	}
	
	public void getFPath(String originalTestPath) {
		fPath = originalTestPath;
	}
	
	public void getDClName(String dName) {
		dependentClassName = dName;
	}
	
//	public void getTCName(String tCName) {
//		nameOfTestClass = tCName;
//	}
	
	public void testReplicator() throws IOException {
		SetClassNameProperty scp = new SetClassNameProperty();
		String mLocation=mPath;
		String[] locatewords = fPath.split("\\\\");
		for(int i=0;i<locatewords.length;i++) {
			if(locatewords[i].contains(".java")) {
				String newW = locatewords[i].replace(".java", "");
				className = newW;
//				scp.setCName(className);
//				System.out.println("ClassName was set to: " + className);
			}
		}
		
		
		nameOfTestClass = className;
		System.out.println("Name of test class is: " + nameOfTestClass);
			File directoryPath = new File(mutantsPath);
			List<String> removeNull = new ArrayList<String>();
			List<String> removeDNull = new ArrayList<String>();
	      //List of all files and directories
	      String contents[] = directoryPath.list();
	     
	      String trimmedStr[] = new String[contents.length-1];
	      String className[] = new String[trimmedStr.length];
	      //System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	    	
	    	  //String toTrim = contents[i];
	         if(contents[i].contains(".java")) {
	        	contents[i]  = contents[i].replace(".java", "");
	        	
	        	//System.out.println(contents[i]);
	         }
	         
	      }
	      
	      
	      for(int l=0, m=0; l<contents.length; l++) {
	    	  if(contents[l].matches("Temp") || (contents[l].contains("Mu") && contents[l].contains("Op"))) 
	    		  continue;
	    	  
	    	 
	    	  
	    		  trimmedStr[m++] = contents[l];
//	    		  System.out.println(contents[l].length());
//	    		  System.out.println(contents.length);
	    		 
	    	  
	      }
	      
	     
	      
	      for(String s : trimmedStr) {
	    	  if(s!=null && s.length() > 0) {
	    		  removeNull.add(s);
	    	  }
	      }
	      
	      trimmedStr = removeNull.toArray(new String[removeNull.size()]);
//	      
	      
//	      System.out.println("----------trimmed str-----------");
//	      for(int j=0;j<trimmedStr.length;j++) {
//	    	  
//	    	  System.out.println(trimmedStr[j]);
//	      }	
	      
	      for(int v=0, a=0;v<trimmedStr.length;v++,a++) {
	    	  className[a] = trimmedStr[v];
	    	  className[a]=className[a]+"Test";
	    	//  System.out.println(className[a]);
	      }
	      
	      for(String s : className) {
	    	  if(s!=null && s.length() > 0) {
	    		  removeDNull.add(s);
	    	  }
	      }
	      
	      className = removeDNull.toArray(new String[removeDNull.size()]);
	      
//	      System.out.println("---------Testclass names are-------------");
//	      for(int i=0;i<className.length;i++) {
//	    	  System.out.println(className[i]);
//	      }

		FileReader source = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		String location=tmPath;
		//String nameOfClassUnderTest = "OriginalProgram";
		
		for(int k=0, p=0;k<trimmedStr.length;k++,p++) {
			source = new FileReader(fPath);
			br = new BufferedReader(source);
			fw = new FileWriter(location+"\\"+className[p]+".java");
			bw = new BufferedWriter(fw);
			
			String line;
			
			while((line = br.readLine()) != null) {
				//	System.out.println("Here1");
				
				if(line.contains(nameOfTestClass)) {
					String[] words = line.split(" ");
					//String[] brackets = line.split("");
					for(int i=0;i<words.length;i++) {
						if(words[i].contains(nameOfTestClass)) {
							String temp = className[p];
							words[i] = temp;
//							System.out.println(words[s]);
						}
					}

					String newLine = String.join(" ", words);
					//String secondLine = String.join("", brackets);
					
					bw.write(newLine);
					//bw.write(secondLine);
					bw.newLine();
				}
//					if(line.contains("class")) {
//						String[] words = line.split(" ");
//						for(int s=0; s<words.length; s++) {
//							
//							String replaceW = line.substring(line.indexOf("s ")+1, line.indexOf('{'));
//							String newW = replaceW.trim();
//							System.out.println(newW);
//							
//							if(words[s].contains(newW)) {
//																
//								String temp = className[p];
//								words[s] = temp;
//								System.out.println(words[s]);
//							}
//						}
//						String newLine = String.join(" ", words);
//						
//						
//						
//						bw.write(newLine);
//						bw.newLine();
//					
//					}
					else if(line.contains(dependentClassName)) {
						
//						String[] classLineWords = line.split(" ");
//						for(int c=0; c<classLineWords.length;c++) {
//							if(classLineWords[c].contains(nameOfClassUnderTest)) {
//								String temp = trimmedStr[k];
//								classLineWords[c] = temp;
//								System.out.println(classLineWords[c]);
//							}
//						}
//						String newLine = String.join(" ", classLineWords);
//						
//						
//						
//						bw.write(newLine);
//						bw.newLine();
						
						String newLine = line.replaceAll(dependentClassName, trimmedStr[k]);
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
		
	}
		source.close();
		fw.close();
		System.out.println("Test Copies generated in the specified folder.");

}


}
