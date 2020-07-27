//	This class is created to delete the mutants that were killed

package com.hsk.deletemutants;
import com.hsk.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hsk.userinputs.UserInputs;

public class DeleteKilledMutants {

	private static String reportPath;

	//-------------Method to delete the mutants that were killed--------------
	public void deleteMutants() {
		String mPath;
		int deleteListCount = 0;
		int mContentCount = 0;
		int newDLCount = 0;
		UserInputs ui = new UserInputs();
		reportPath = ui.returnReportsPath();
		mPath = ui.returnMutantPath();

		//------------Get the files stored in the reports\html folder-------------
		File directoryRPath = new File(reportPath);
		String contents[] = directoryRPath.list();

		//--------------Get the mutants stored in the Generated Mutants folder-------------
		File directoryMPath = new File(mPath);
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

		//------------Get the list of mutants stored in the Generated Mutants Folder (skip the Temp file)--------------
		for(int i=0;i<mContents.length;i++) {
			if(!(mContents[i].contains("Temp"))) {
				mList.add(mContents[i]);
			}
		}

		//-----------Get the count of the number of mutants-------------
		for(int i=0;i<mList.size();i++) {
			mContentCount++;
		}

		//--------Extract the names of the test files captured in the deleteList and trim the file names to store only the names that match the mutant file names----------
		List<String> newDL = new ArrayList<String>();
		for(int i=0;i<deleteList.size();i++) {
			String temp = deleteList.get(i).substring(deleteList.get(i).indexOf("_")+1, deleteList.get(i).indexOf("T"));
			newDL.add(temp);

		}

		//------------Delete the killed mutant files-----------
		for(int i=0;i<mList.size();i++) {
			for(int j=0;j<newDL.size();j++) {

				if(mList.get(i).equalsIgnoreCase(newDL.get(j)+".java")) {
					String fileName = mList.get(i);
					String file = mPath+"/"+fileName;
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

		System.out.println("Killed mutants were deleted successfully!!");
	}

}
