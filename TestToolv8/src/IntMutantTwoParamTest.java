import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class IntMutantTwoParamTest {

	@Rule
    public ErrorCollector collector = new ErrorCollector();
	
//	@Rule
//	public Timeout globalTimeout = Timeout.millis(30);


	@Test
	
	public void test() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException {
		//fail("Not yet implemented");
		TestInputs ti = new TestInputs();
		ti.getTestInputs();
		List<Integer> testInputs = new ArrayList<Integer>();
		testInputs = ti.retrieveTestInputs();
		
		ClassLoader classLoader = IntMutantTwoParamTest.class.getClassLoader();
		IntMutantTwoParamTest mu = new IntMutantTwoParamTest();
		String className = mu.getClass().getSimpleName();
		List<String> removeNull = new ArrayList<String>();
		//Creating a File object for directory
	      File directoryPath = new File("F:\\Java Projects\\TestToolv8\\src");
	      //List of all files and directories
	      String contents[] = directoryPath.list();
	      String trimmedStr[] = new String[contents.length-1];
	      //System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	    	
	    	  //String toTrim = contents[i];
	         if(contents[i].contains(".java")) {
	        	contents[i]  = contents[i].replace(".java", "");
	        	
	        	
	         }
	         
	      }
	      
	      for(int l=0, m=0; l<contents.length; l++) {
	    	  if(contents[l].contains(className) || contents[l].contains("Main") || contents[l].contains("Menu") || contents[l].contains("TestInputs") || contents[l].contains("testinputs") ) 
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
	      
	      for(int j=0; j<trimmedStr.length; j++) {
	    	  System.out.println(trimmedStr[j]);
//	    	  
	      }
	      
//	      long start = System.currentTimeMillis();
//    	  long end = start + 60*1000; // 60 seconds * 1000 ms/sec
	      
    	 
	      for(int k=0;k<trimmedStr.length;k++) {
	    	 
	    	  //collector.addError(new Throwable("failed"));
	    	  String source = trimmedStr[k];
	    	  int count = 1;
	    	  Object objName = source.toLowerCase()+count;
	    	  
	    	  try {
			        Class aClass = classLoader.loadClass(source);
			        //System.out.println("aClass.getName() = " + aClass.getName());
			        objName = aClass.newInstance();
			        //System.out.println(objName.getClass());
			        int pointer=0;
			        int listSize = testInputs.size();
			        int numInputs = listSize%3+1;
			        //numInputs = numInputs%3;
			    //    System.out.println(divideList);
			    //    for(int l=0;l<4;l++) {
			        	for(int m=0;m<testInputs.size();) {
			        		int a = testInputs.get(m);
			        		int b = testInputs.get(m+1);
			        		int result = testInputs.get(m+2);
			        		m=m+3;
			        		Method sumInstance = objName.getClass().getDeclaredMethod("add", int.class, int.class);
			        		int newResult = (int) sumInstance.invoke(objName, a, b);
			        		if(result!=newResult) {
			        			System.out.println("Failure in method of " + objName.getClass());
			        			System.out.println("Mutant " + objName.getClass() + " killed");
			        			break;
			        		}else {
			        			
						    	System.out.println("Mutant " + objName.getClass() + "survived for test " + numInputs);
						    	numInputs = numInputs+1;

						    }
			        	}
			       // }
//			        for(int t=pointer;t<testInputs.size();) {
//			        	pointer = pointer + 3;
//			        	System.out.println(pointer);
//			        	System.out.println(testInputs.size());
//			        	break;
//			        	int a = testInputs.get(t);
//			        	int b = testInputs.get(t+1);
//			        	int result = testInputs.get(t+2);
//			        	System.out.println(a);
//			        	System.out.println(b);
//			        	System.out.println(result);
//			        	pointer=pointer+3;
////			        	if(t!=0 && t%3==0) {
////			        		t=t+3;
////			        	}
//			        	Method sumInstance = objName.getClass().getDeclaredMethod("add", int.class, int.class);
//			        	int newResult = (int) sumInstance.invoke(objName, a, b);
//			        	if(result!=newResult) {
//					    	System.out.println("Failure in method of " + objName.getClass());
//					    	System.out.println("Mutant " + objName.getClass() + "killed");
//					    	
//					    	//assertEquals(newResult, result);
//					    	
//					    }else {
//					    	System.out.println("Mutant " + objName.getClass() + "survived");
//					    	
//					    	
//					    	
//					    }
//			        	
//			        	   
//			        	
//			        }
			        
				    
				    
				    
				    //long startTime = System.nanoTime();
				    
				  //  long endTime = System.nanoTime();
				    
				    
				   
				  
				 
				  
				    
				    //assertEquals(newResult, result);
				    //collector.checkThat(newResult, equalsTo(result));
				    
				   
			    } catch (Throwable t) {
			        collector.addError(t);
			    }  
	      }
    	  
	      

	
	}


}
