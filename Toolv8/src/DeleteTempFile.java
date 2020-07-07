import java.io.File;

public class DeleteTempFile {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void deleteTempFile() {
		
		String file = mPath+"\\FirstTemp.java";
		String file2 = mPath+"\\SecondTemp.java";
		String file3 = mPath+"\\OriginalTempCopy.java";
		System.out.println(file);
		//System.out.println(file2);
		try  
		{         
		File f= new File(file);           //file to be delete
		File f2= new File(file2);
		File f3 = new File(file3);
		if(f.delete())                      //returns Boolean value  
		{  
		System.out.println(f.getName() + " deleted");   //getting and printing the file name  
		}  
		else  
		{  
		System.out.println("failed");  
		}
		if(f2.delete()) {
			System.out.println(f2.getName() + " deleted");
		}
		else {
			System.out.println("failed");
		}
		if(f3.delete()) {
			System.out.println(f3.getName() + " deleted");
		}
		else {
			System.out.println("failed");
		}
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	}
}
