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


package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//This class is created to create a package directory structure in the Generated Test Copies folder
public class CreatePackageStructure {

	private static String mPath;
	private static String pName;
	private static String finalPath;
	
	//----------Method to get the test folder path-----------------
	public void getPath(String testFilePath) {
		mPath = testFilePath;
	}
	
	//----------Method to get the test package name-----------------
	public void getPName(String packageName) {
		pName = packageName;
	}
	
	//----------Method to create the package directory structure in the Generated Test Copies folder-----------
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
			System.out.println("Directory structure created");
		}else {
			System.out.println("Directory already exists");
		}
		
	}
	
	//----------Method to return the package path---------------
	public String getPackagedPath() {
		return finalPath;
	}
}
