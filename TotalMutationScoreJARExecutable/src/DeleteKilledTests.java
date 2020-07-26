import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeleteKilledTests {
	
	private static String reportPath;
	
	public void deleteTests() {
		//System.out.println("Inside delete mutants method");
		String mPath;
		String tPath;
		int deleteListCount = 0;
		int mContentCount = 0;
		int newDLCount = 0;
		UserInputs ui = new UserInputs();
		reportPath = ui.returnReportsPath();
		mPath = ui.returnMutantPath();
		tPath = ui.returnMTestPath();
		//System.out.println("TPath is: " + tPath);
		
		File directoryRPath = new File(reportPath);
		String contents[] = directoryRPath.list();
		
		File directoryMPath = new File(tPath);
		String mContents[] = directoryMPath.list();
		
		List<String> deleteList = new ArrayList<String>();
		List<String> mList = new ArrayList<String>();
		
		for(int i=0;i<contents.length;i++) {
			if(contents[i].contains("Test-fails")) {
				deleteList.add(contents[i]);
			}
		}
		
		for(int i=0;i<deleteList.size();i++) {
			deleteListCount++;
		}
		
		//System.out.println("Delete List count is: " + deleteListCount);
		
		for(int i=0;i<mContents.length;i++) {
			
				mList.add(mContents[i]);
			
		}
		
		for(int i=0;i<mList.size();i++) {
			mContentCount++;
		}
		
		//System.out.println("MContent Count is: " + mContentCount);
		
		List<String> newDL = new ArrayList<String>();
		for(int i=0;i<deleteList.size();i++) {
			String temp = deleteList.get(i).substring(deleteList.get(i).indexOf("_")+1, deleteList.get(i).indexOf("-"));
			newDL.add(temp);
			
		}
		
//		for(int i=0;i<newDL.size();i++) {
//			System.out.println("name is: " + newDL.get(i));
//			newDLCount++;
//		}
		
		//System.out.println("New Delete List count: " + newDLCount);
		
		
		for(int i=0;i<mList.size();i++) {
			for(int j=0;j<newDL.size();j++) {
				
				if(mList.get(i).equalsIgnoreCase(newDL.get(j)+".java")) {
					String fileName = mList.get(i);
					String file = tPath+"\\"+fileName;
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
		System.out.println("Testing commit");
	}


}
