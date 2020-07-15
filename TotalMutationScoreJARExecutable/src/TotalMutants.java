import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TotalMutants {
	public void totalMutantsCount() throws IOException {
		String mPath;
		String tPath;
		String rPath;
		UserInputs ui = new UserInputs();
		mPath = ui.returnMutantPath();
		tPath = ui.returnMTestPath();
		rPath = ui.returnReportsPath();
		
		 File directoryPath = new File(mPath);
		 FileWriter fw = new FileWriter("mutant.properties");
	      //List of all files and directories
		 
	      String contents[] = directoryPath.list();
//	      System.out.println("Here");
//	      for(int i=0;i<contents.length;i++) {
//	    	  System.out.println("In the loop");
//	    	  System.out.println(contents[i]);
//	      }
	      String mutants[] = new String[contents.length-1];
	      
	      List<String> removeNull = new ArrayList<String>();
	      for(int l=0, m=0; l<contents.length; l++) {
	    	  if(contents[l].contains("Temp")) 
	    		  continue;
	    	  
	    	 
	    	  
	    		  mutants[m++] = contents[l];
//	    		  System.out.println(contents[l].length());
//	    		  System.out.println(contents.length);
	    		 
	    	  
	      }
	      
	      for(String s : mutants) {
	    	  if(s!=null && s.length() > 0) {
	    		  removeNull.add(s);
	    	  }
	      }
	      
	      mutants = removeNull.toArray(new String[removeNull.size()]);
	      String totalMutants = "totalmutants=";
	      int count = mutants.length;
	      System.out.println("Total Mutants Generated: " + count);
	      fw.write(totalMutants+count);
	      fw.flush();
	      fw.close();
	      
	}

}
