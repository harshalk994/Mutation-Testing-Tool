/* 
	   Copyright 2020 Dr. Klaas-Jan Stol, Harshal Kasle
	
	   Licensed under the Apache License, Version 2.0 (the "License");
	   you may not use this file except in compliance with the License.
	   You may obtain a copy of the License at
	
	       http://www.apache.org/licenses/LICENSE-2.0
	
	   Unless required by applicable law or agreed to in writing, software
	   distributed under the License is distributed on an "AS IS" BASIS,
	   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	   See the License for the specific language governing permissions and
	   limitations under the License.
 */


package com.hsk.filedeletion;
import com.hsk.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//This class is created to delete the temp files that are generated while creating copies of the dependent class
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
