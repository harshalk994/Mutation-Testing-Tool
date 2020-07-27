//	This class is created to delete the unwanted temp files generated during the temp file generation process

package com.hsk.filedeletion;
import com.hsk.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeleteTempFile {

	private static String mPath;

	//method to get the mutant folder path
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//method to delete generated temp files	
	public void deleteTempFile() {

		String file = mPath+"/FirstTemp.java"; 			//path and filename to be deleted
		String file2 = mPath+"/SecondTemp.java";			//path and filename to be deleted
		String file3 = mPath+"/OriginalTempCopy.java";		//path and filename to be deleted

		try  
		{         
			File f= new File(file);			//file to be delete
			File f2= new File(file2);		//file to be delete			
			File f3 = new File(file3);		//file to be delete
			if(f.delete())                      //returns Boolean value  
			{  

			}  
			else  
			{  
				System.out.println("failed");  		//display failure message on unsuccessful file deletion
			}
			if(f2.delete()) {

			}
			else {
				System.out.println("failed");		//display failure message on unsuccessful file deletion
			}
			if(f3.delete()) {

			}
			else {
				System.out.println("failed");		//display failure message on unsuccessful file deletion
			}
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}
}
