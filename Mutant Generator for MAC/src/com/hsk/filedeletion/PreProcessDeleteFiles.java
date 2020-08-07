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


package com.hsk.filedeletion;
import com.hsk.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
 * This class is created to delete files that were generated from the previous project that was run using the tool
 * to avoid manual deletion of files everytime a project is run by the tool
 */

public class PreProcessDeleteFiles {

	//method delete files that are generated from previous project
	public void deleteTempFile() {


		String mFilePath = "Generated Mutants";
		File mfile = new File(mFilePath);
		deleteMFolder(mfile); 
		System.out.println("Previous files from Generated Mutants folder deleted successfully.");		//display success message on folder deletion

		String tFilePath = "Generated Test Copies";
		File tfile = new File(tFilePath);
		deleteTFolder(tfile);
		System.out.println("Previous files from Generated Test Copies folder deleted successfully.");		//display success message on folder deletion

		String stagingPath = "staging";
		File sFile = new File(stagingPath);
		deleteSFolder(sFile);
		System.out.println("Previous files from staging folder deleted successfully");		//display success message on folder deletion


		//delete the reports folder generated from previous project
		String reportsPath = "reports";
		File rFile = new File(reportsPath);
		if(rFile.exists()) {
			deleteRFolder(rFile);
			System.out.println("Reports folder deleted successfully");		//display success message on folder deletion
		}




		//delete the html file generated for the previous project
		String htmlFile = "htmlreport.html";
		String mutantpropsfile = "mutant.properties";
		try  
		{         
			File f= new File(htmlFile);           //file to be delete
			File f2= new File(mutantpropsfile);

			if(f.exists()==true) {
				if(f.delete())                      //returns Boolean value  
				{  

				}  
				else  
				{  
					System.out.println("failed");		//display failure message on unsuccessful file deletion  
				}
			}
			if(f2.exists()==true) {
				if(f2.delete()) {

				}
				else {
					System.out.println("failed");		//display failure message on unsuccessful file deletion
				}
			}
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  


		//Create Generated Mutants folder after the entire folder consisting of previous project files is deleted
		Path genMutants = Paths.get("Generated Mutants");
		if(!Files.exists(genMutants)) {
			try {
				Files.createDirectory(genMutants);
			}catch(IOException e) {
				System.out.println("Failed to create Genereted Mutants Folder");		//display failure message on unsuccessful file deletion
			}
		}

		//Create Generated Test Copies folder after the entire folder consisting of previous project files is deleted
		Path genTCopies = Paths.get("Generated Test Copies");
		if(!Files.exists(genTCopies)) {
			try {
				Files.createDirectory(genTCopies);
			}catch(IOException e) {
				System.out.println("Failed to create Genereted Test Copies Folder");		//display failure message on unsuccessful file deletion
			}
		}

		//Create staging folder after the entire folder consisting of previous project files is deleted
		Path genStaging = Paths.get("staging");
		if(!Files.exists(genStaging)) {
			try {
				Files.createDirectory(genStaging);
			}catch(IOException e) {
				System.out.println("Failed to create staging Folder");		//display failure message on unsuccessful file deletion
			}
		}


	}


	//method to delete the Generated Mutants folder
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


	//method to delete the Generated Test Copies folder
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

	//method to delete the staging folder
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

	//method to delete the reports folder
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
