import java.io.File;

public class DeleteTempFile {
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
	public void deleteTempFile() {
		
		String file = mPath+"\\FirstTemp.java";
		//System.out.println(file);
		try  
		{         
		File f= new File(file);           //file to be delete
		if(f.delete())                      //returns Boolean value  
		{  
		//System.out.println(f.getName() + " deleted");   //getting and printing the file name  
		}  
		else  
		{  
		System.out.println("FirstTemp.java file deletion failed");  
		}  
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	}
}
