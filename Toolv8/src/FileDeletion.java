import java.io.File;

public class FileDeletion {
	
	public void deleteFiles() {
		
		String file = "F:\\Java Projects\\Toolv1\\M9.java";
		try  
		{         
		File f= new File(file);           //file to be delete  
		if(f.delete())                      //returns Boolean value  
		{  
		System.out.println(f.getName() + " deleted");   //getting and printing the file name  
		}  
		else  
		{  
		System.out.println("failed");  
		}  
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	}
}
