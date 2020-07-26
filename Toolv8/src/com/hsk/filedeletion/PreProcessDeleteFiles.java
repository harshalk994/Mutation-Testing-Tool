package com.hsk.filedeletion;
import com.hsk.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PreProcessDeleteFiles {
	
//	private static String mPath;
//	private static String mTPath;
//	private static String oPName;
//	private static String tPName;
//	
//	public void getPath(String mutantFilePath) {
//		mPath = mutantFilePath;
//	}
//	
//	public void getMTPath(String tPath) {
//		mTPath = tPath;
//	}
//	
//	public void getOPName(String pName) {
//		oPName = pName;
//	}
//	
//	public void getTPName(String testPName) {
//		tPName = testPName;
//	}
	
	public void deleteTempFile() {
		
//		File directoryMPath = new File(mPath);
//		String contents[] = directoryMPath.list();
//		
//		File directoryTPath = new File(mTPath);
//		String mContents[] = directoryTPath.list();

//		if(mPath.contains(oPName)) {
//			String filePath = mPath;
//			File file = new File(mPath);
//			
//		}
		
		String mFilePath = "Generated Mutants";
		File mfile = new File(mFilePath);
		deleteMFolder(mfile);
		System.out.println("Previous files from Generated Mutants folder deleted successfully.");
		
		String tFilePath = "Generated Test Copies";
		File tfile = new File(tFilePath);
		deleteTFolder(tfile);
		System.out.println("Previous files from Generated Test Copies folder deleted successfully.");
		
		String stagingPath = "staging";
		File sFile = new File(stagingPath);
		deleteSFolder(sFile);
		System.out.println("Previous files from staging folder deleted successfully");
		
		String reportsPath = "reports";
		File rFile = new File(reportsPath);
		if(rFile.exists()) {
			deleteRFolder(rFile);
			System.out.println("Reports folder deleted successfully");
		}
		
		
		
		
		
		String htmlFile = "htmlreport.html";
		String mutantpropsfile = "mutant.properties";
		try  
		{         
		File f= new File(htmlFile);           //file to be delete
		File f2= new File(mutantpropsfile);
		
		if(f.exists()==true) {
			if(f.delete())                      //returns Boolean value  
			{  
			//System.out.println(f.getName() + " deleted");   //getting and printing the file name  
			}  
			else  
			{  
			System.out.println("failed");  
			}
		}
		if(f2.exists()==true) {
			if(f2.delete()) {
				//System.out.println(f2.getName() + " deleted");
			}
			else {
				System.out.println("failed");
			}
		}
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
		
		Path genMutants = Paths.get("Generated Mutants");
		if(!Files.exists(genMutants)) {
			try {
				Files.createDirectory(genMutants);
			}catch(IOException e) {
				System.out.println("Failed to create Genereted Mutants Folder");
			}
		}
		
		Path genTCopies = Paths.get("Generated Test Copies");
		if(!Files.exists(genTCopies)) {
			try {
				Files.createDirectory(genTCopies);
			}catch(IOException e) {
				System.out.println("Failed to create Genereted Test Copies Folder");
			}
		}
		
		Path genStaging = Paths.get("staging");
		if(!Files.exists(genStaging)) {
			try {
				Files.createDirectory(genStaging);
			}catch(IOException e) {
				System.out.println("Failed to create staging Folder");
			}
		}
		
		
	}
	
	public void deleteMFolder(File file){
	      for (File subFile : file.listFiles()) {
	         if(subFile.isDirectory()) {
	            deleteMFolder(subFile);
	         } else {
	            subFile.delete();
	         }
	      }
	      file.delete();
	   }
	
	public void deleteTFolder(File file){
	      for (File subFile : file.listFiles()) {
	         if(subFile.isDirectory()) {
	            deleteTFolder(subFile);
	         } else {
	            subFile.delete();
	         }
	      }
	      file.delete();
	   }
	
	public void deleteSFolder(File file){
	      for (File subFile : file.listFiles()) {
	         if(subFile.isDirectory()) {
	            deleteSFolder(subFile);
	         } else {
	            subFile.delete();
	         }
	      }
	      file.delete();
	   }
	
	public void deleteRFolder(File file){
	      for (File subFile : file.listFiles()) {
	         if(subFile.isDirectory()) {
	            deleteRFolder(subFile);
	         } else {
	            subFile.delete();
	         }
	      }
	      file.delete();
	   }

}
