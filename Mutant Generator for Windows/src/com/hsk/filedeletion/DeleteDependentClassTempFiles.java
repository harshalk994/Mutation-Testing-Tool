//	This class is created to delete the temp files that are generated while creating copies of the dependent class

package com.hsk.filedeletion;
import com.hsk.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeleteDependentClassTempFiles {

	private static String mPath;

	//method to get the mutant folder path
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//method to delete generated temp files	
	public void deleteTempFile() {

		File directoryPath = new File(mPath);									//get the files from the mutant folder
		String contents[] = directoryPath.list();								//store the files from the mutant folder into contents array  
		List<String> tempDClasses = new ArrayList<String>();					//arraylist to store the generated temp files
		for(int i=0;i<contents.length;i++) {
			if(contents[i].contains("FirstTemp")) {
				tempDClasses.add(contents[i]);									//adding files from content array into the arraylist: condition being that the filename should contain "FirstTemp"
			}
		}

		try  
		{         
			if(tempDClasses.isEmpty() == false) {								//check if arraylist is empty or not, if not then proceed with deletion
				for(int i=0;i<tempDClasses.size();i++) {
					String fileName = mPath+"\\"+tempDClasses.get(i);			//append the filename to the path to locate the exact file that needs to be deleted
					File f4 = new File(fileName);
					if(f4.delete()) {
					}else {
						System.out.println("failed to deleted temp file");		//display failure message on unsuccessful file deletion
					}

				}
			}
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

}
