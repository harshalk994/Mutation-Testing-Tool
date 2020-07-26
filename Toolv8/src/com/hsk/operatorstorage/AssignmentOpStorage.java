package com.hsk.operatorstorage;
import com.hsk.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentOpStorage {
	
	private static List<String> assignmentArithOpL = new ArrayList<String>();
	private static List<String> assignmentArithOpP = new ArrayList<String>();
	
	private static List<String> assignmentBitwiseOpL = new ArrayList<String>();
	private static List<String> assignmentBitwiseOpP = new ArrayList<String>();
	
	private static List<String> assignmentShiftOpL = new ArrayList<String>();
	private static List<String> assignmentShiftOpP = new ArrayList<String>();
	

	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}
	
public void processOp() throws IOException{
		
		String tempFileName = mPath+"\\Temp.java";
		//String mutantFileName = mPath+"\\MuArithOp";
		FileReader fr = new FileReader(tempFileName);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null) {
			if(line.contains("System.out.println") && line.contains("+"))
				continue;
			
			if(line.contains("'+='") || line.contains("'-='") || line.contains("'*='") || line.contains("'/='") || line.contains("'%='") || line.contains("'&='") || line.contains("'|='") || line.contains("'^='") || line.contains("'<<='") || line.contains("'>>='"))
				continue;
//				if(line.contains("=") && !(line.contains("==")) && !(line.contains("+=")) && !(line.contains("-=")) && !(line.contains("*=")) && !(line.contains("/=")) && !(line.contains("%=")) && !(line.contains("&=")) && !(line.contains("|=")) && !(line.contains("^=")) && !(line.contains(">>=")) && !(line.contains("<<=")) && !(line.contains("!=")) && !(line.contains(">=")) && !(line.contains("<="))){
//					assignmentArithOpL.add(line);
//				}
			
			
				if(line.contains("+=") && !(line.contains("\"+=\""))) {
					assignmentArithOpL.add(line);
				}else if(line.contains("-=") && !(line.contains("\"-=\""))) {
					assignmentArithOpL.add(line);
				}else if(line.contains("*=") && !(line.contains("\"*=\""))) {
					assignmentArithOpL.add(line);
				}else if(line.contains("/=") && !(line.contains("\"/=\""))) {
					assignmentArithOpL.add(line);
				}else if(line.contains("%=") && !(line.contains("\"%=\""))) {
					assignmentArithOpL.add(line);
				}else if(line.contains("&=") && !(line.contains("\"&=\""))) {
					assignmentBitwiseOpL.add(line);
				}else if(line.contains("|=") && !(line.contains("\"|=\""))) {
					assignmentBitwiseOpL.add(line);
				}else if(line.contains("^=") && !(line.contains("\"^=\""))) {
					assignmentBitwiseOpL.add(line);
				}else if(line.contains(">>=") && !(line.contains("\">>=\""))) {
					assignmentShiftOpL.add(line);
				}else if(line.contains("<<=") && !(line.contains("\"<<=\""))) {
					assignmentShiftOpL.add(line);
				}
				
			
		}
		
		br.close();
		fr.close();
		
		
	}
	
	public List<String> returnArithAssignOpList(){
		return assignmentArithOpL;
	}
	
//	public void printArithAssignmentList() {
//		for(int i=0;i<assignmentArithOpL.size();i++) {
//			System.out.println(assignmentArithOpL.get(i));
//		}
//	}
	
	public List<String> returnBitwiseAssignOpList(){
		return assignmentBitwiseOpL;
	}
	
	public List<String> returnShiftAssignOpList(){
		return assignmentShiftOpL;
	}
	
//	public void printOp() {
//		
//		for(int i=0;i<conditionOpL.size();i++) {
//			System.out.println(conditionOpL.get(i));
//		}
//	}
	
	
	public void processArithAssignList(List<String> assignmentArithOpL ) {
		for(String s : assignmentArithOpL) {
//			if(s.contains("=")) {
//				//s.replace("-", "+");
//				assignmentArithOpP.add(s.replace("=", "+="));
//				assignmentArithOpP.add(s.replace("=", "-="));
//				assignmentArithOpP.add(s.replace("=", "*="));
//				assignmentArithOpP.add(s.replace("=", "/="));
//				assignmentArithOpP.add(s.replace("=", "%="));
//			}
			if(s.contains("+=")) {
				//s.replace("-", "+");
				assignmentArithOpP.add(s.replace("+=", "="));
				assignmentArithOpP.add(s.replace("+=", "-="));
				assignmentArithOpP.add(s.replace("+=", "*="));
				assignmentArithOpP.add(s.replace("+=", "/="));
				assignmentArithOpP.add(s.replace("+=", "%="));
			}
			if(s.contains("-=")) {
				//s.replace("-", "+");
				assignmentArithOpP.add(s.replace("-=", "="));
				assignmentArithOpP.add(s.replace("-=", "+="));
				assignmentArithOpP.add(s.replace("-=", "*="));
				assignmentArithOpP.add(s.replace("-=", "/="));
				assignmentArithOpP.add(s.replace("-=", "%="));
			}
			if(s.contains("*=")) {
				//s.replace("-", "+");
				assignmentArithOpP.add(s.replace("*=", "="));
				assignmentArithOpP.add(s.replace("*=", "+="));
				assignmentArithOpP.add(s.replace("*=", "-="));
				assignmentArithOpP.add(s.replace("*=", "/="));
				assignmentArithOpP.add(s.replace("*=", "%="));
			}
			if(s.contains("/=")) {
				//s.replace("-", "+");
				assignmentArithOpP.add(s.replace("/=", "="));
				assignmentArithOpP.add(s.replace("/=", "+="));
				assignmentArithOpP.add(s.replace("/=", "-="));
				assignmentArithOpP.add(s.replace("/=", "*="));
				assignmentArithOpP.add(s.replace("/=", "%="));
			}
			if(s.contains("%=")) {
				//s.replace("-", "+");
				assignmentArithOpP.add(s.replace("%=", "="));
				assignmentArithOpP.add(s.replace("%=", "+="));
				assignmentArithOpP.add(s.replace("%=", "-="));
				assignmentArithOpP.add(s.replace("%=", "*="));
				assignmentArithOpP.add(s.replace("%=", "/="));
			}
		}
	}
	
	public void processBitwiseAssignList(List<String> assignmentBitwiseOpL ) {
		for(String s : assignmentBitwiseOpL) {
			if(s.contains("&=")) {
				//s.replace("-", "+");
				assignmentBitwiseOpP.add(s.replace("&=", "|="));
				assignmentBitwiseOpP.add(s.replace("&=", "^="));
			}
			if(s.contains("|=")) {
				//s.replace("-", "+");
				assignmentBitwiseOpP.add(s.replace("|=", "&="));
				assignmentBitwiseOpP.add(s.replace("|=", "^="));
			}
			if(s.contains("^=")) {
				//s.replace("-", "+");
				assignmentBitwiseOpP.add(s.replace("^=", "&="));
				assignmentBitwiseOpP.add(s.replace("^=", "|="));
			}
		}
	}
	
	public void processShiftAssignList(List<String> assignmentShiftOpL ) {
		for(String s : assignmentShiftOpL) {
			if(s.contains(">>=")) {
				//s.replace("-", "+");
				assignmentShiftOpP.add(s.replace(">>=", "<<="));
			}
			if(s.contains("<<=")) {
				//s.replace("-", "+");
				assignmentShiftOpP.add(s.replace("<<=", ">>="));
			}
		}
	}
	
	
	public List<String> retriveArithAssignmentProcessList(){
		return assignmentArithOpP;
	}
	
	public List<String> retriveBitwiseAssignmentProcessList(){
		return assignmentBitwiseOpP;
	}
	
	public List<String> retriveShiftAssignmentProcessList(){
		return assignmentShiftOpP;
	}


}
