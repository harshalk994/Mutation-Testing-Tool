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

//This class is created to delete the unwanted temp files generated during the temp file generation process
public class DeleteTempFile {

	private static String mPath;

	//method to get the mutant folder path
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//method to delete generated temp files	
	public void deleteTempFile() {

		String file = mPath+"\\FirstTemp.java"; 			//path and filename to be deleted
		String file2 = mPath+"\\SecondTemp.java";			//path and filename to be deleted
		String file3 = mPath+"\\OriginalTempCopy.java";		//path and filename to be deleted

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
