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


package com.hsk.deletemutants;
import com.hsk.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hsk.userinputs.UserInputs;

//This class is created to delete the tests that successfully killed the corresponding mutants
public class DeleteKilledTests {

	private static String reportPath;

	//-------------Method to delete the tests that killed the mutants----------
	public void deleteTests() {
		String mPath;
		String tPath;
		int deleteListCount = 0;
		int mContentCount = 0;
		int newDLCount = 0;
		UserInputs ui = new UserInputs();
		reportPath = ui.returnReportsPath();
		mPath = ui.returnMutantPath();
		tPath = ui.returnMTestPath();

		//------------Get the files stored in the reports\html folder-------------
		File directoryRPath = new File(reportPath);
		String contents[] = directoryRPath.list();

		//--------------Get the test files stored in the Generated Test Copies folder-------------
		File directoryMPath = new File(tPath);
		String mContents[] = directoryMPath.list();

		List<String> deleteList = new ArrayList<String>();
		List<String> mList = new ArrayList<String>();

		//---------Generate a list of files that need to be deleted (filenames ending with Test-fails will be captured and stored in the arraylist)-------------
		for(int i=0;i<contents.length;i++) {
			if(contents[i].contains("Test-fails")) {
				deleteList.add(contents[i]);
			}
		}

		//---------Get a count of the number of items store in the arraylist - this count will denote the number of mutants that are killed-------------
		for(int i=0;i<deleteList.size();i++) {
			deleteListCount++;
		}

		//------------Get the list of mutants stored in the Generated Mutants Folder--------------
		for(int i=0;i<mContents.length;i++) {			
			mList.add(mContents[i]);			
		}

		//-----------Get the count of the number of files stored in Generated Mutants Folder-------------
		for(int i=0;i<mList.size();i++) {
			mContentCount++;
		}

		//--------Extract the names of the test files captured in the deleteList and trim the file names to store only the names that match the mutant file names----------
		List<String> newDL = new ArrayList<String>();
		for(int i=0;i<deleteList.size();i++) {
			String temp = deleteList.get(i).substring(deleteList.get(i).indexOf("_")+1, deleteList.get(i).indexOf("-"));
			newDL.add(temp);

		}

		//------------Delete the tests that killed the mutants-----------
		for(int i=0;i<mList.size();i++) {
			for(int j=0;j<newDL.size();j++) {

				if(mList.get(i).equalsIgnoreCase(newDL.get(j)+".java")) {
					String fileName = mList.get(i);
					String file = tPath+"/"+fileName;
					try  
					{         
						File f= new File(file);           //file to be delete
						if(f.delete())                      //returns Boolean value  
						{  
							//System.out.println(f.getName() + " deleted");   //getting and printing the file name  
						}  
						else  
						{  
							//System.out.println("failed");  
						}
					}
					catch(Exception e)  
					{  
						e.printStackTrace();  
					}  
				}
			}
		}

		System.out.println("Tests that killed mutants were deleted successfully!!");
	}


}
