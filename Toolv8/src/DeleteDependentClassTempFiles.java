import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeleteDependentClassTempFiles {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
public void deleteTempFile() {

		File directoryPath = new File(mPath);
		String contents[] = directoryPath.list();
		List<String> tempDClasses = new ArrayList<String>();
		for(int i=0;i<contents.length;i++) {
			if(contents[i].contains("FirstTemp")) {
				tempDClasses.add(contents[i]);
			}
		}
		
//		System.out.println("-------got file names------------");
//		for(int i=0;i<tempDClasses.size();i++) {
//			System.out.println(tempDClasses.get(i));
//		}
		//System.out.println(file);
		//System.out.println(file2);
		try  
		{         
		if(tempDClasses.isEmpty() == false) {
			for(int i=0;i<tempDClasses.size();i++) {
				String fileName = mPath+"\\"+tempDClasses.get(i);
				//System.out.println(fileName);
				File f4 = new File(fileName);
				if(f4.delete()) {
					//System.out.println(f3.getName() + " deleted");
				}else {
					System.out.println("failed to deleted temp file");
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
