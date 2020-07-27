package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatePackageStructure {

	private static String mPath;
	private static String pName;
	private static String finalPath;
	
	public void getPath(String testFilePath) {
		mPath = testFilePath;
	}
	
	
	public void getPName(String packageName) {
		pName = packageName;
	}
	
//	public void createPackageDirectory() throws IOException {
//		String packageNameFolder1 = "com";
//		String packageNameFolder2 = "try";
//		String appendFolder1 = mPath+"\\"+packageNameFolder1;
//		String appendFolder2 = appendFolder1+"\\"+packageNameFolder2;
//		
//		Path path = Paths.get(appendFolder2);
//		
//		if(!Files.exists(path)){
//			Files.createDirectories(path);
//			System.out.println("Driectory structure created");
//		}else {
//			System.out.println("Directory already exists");
//		}
//		
//	}
	
	public void createPackageDirectory() throws IOException {
		String appendPath;
		if(pName.contains(".")) {
			appendPath = pName.replace(".", "\\");
			finalPath = mPath+"\\"+appendPath;
		}else {
			finalPath = mPath+"\\"+pName;
		}
		
		Path path = Paths.get(finalPath);
		
		if(!Files.exists(path)){
			Files.createDirectories(path);
			System.out.println("Driectory structure created");
		}else {
			System.out.println("Directory already exists");
		}
		
	}
	
	public String getPackagedPath() {
		return finalPath;
	}
}
