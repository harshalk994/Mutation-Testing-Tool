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


package com.hsk.htmlfilegenerator;
import com.hsk.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.hsk.operatorstorage.ArithmeticOpStorage;
import com.hsk.operatorstorage.AssignmentOpStorage;
import com.hsk.operatorstorage.BitwiseOpStorage;
import com.hsk.operatorstorage.ConditionalOpStorage;
import com.hsk.operatorstorage.IncrementDecrementOpStorage;
import com.hsk.operatorstorage.RelationalOpStorage;
import com.hsk.operatorstorage.ShiftOpStorage;

/* 
 * This class is created to generate an HTML table that will display 
 * each mutation of the original line of code that was generated in the mutant generation process
 */
public class GenerateHtmlTable {

	//------------Generate a html table for the arithmetic operators--------------------
	public void generateArithOpTable() throws IOException {
		List<String> arithOpList = new ArrayList<String>();
		List<String> arithOpTwoList = new ArrayList<String>();

		List<String> processedArithOpList = new ArrayList<String>();
		List<String> processedArithOpTwoList = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants---------------------- 
		ArithmeticOpStorage ops = new ArithmeticOpStorage();
		ops.processOp();
		arithOpList = ops.returnOpList();
		ops.processList(arithOpList);
		processedArithOpList = ops.retriveProcessList();		
		arithOpTwoList = ops.returnOpTwoList();
		ops.processTwoList(arithOpTwoList);
		processedArithOpTwoList = ops.retriveProcessTwoList();

		//---------------------If the one of the retrieved lists are not empty, proceed with generating the html table--------------------------
		if(arithOpList.isEmpty() == false || arithOpTwoList.isEmpty() == false) {
			FileWriter aw = new FileWriter("htmlreport.html", true);		//write data to htmlreport.html file; append flag is set to "true"
			BufferedWriter abw = new BufferedWriter(aw);
			PrintWriter apw = new PrintWriter(abw);
			apw.println("<br>");
			apw.println();

			//---------------------Generating the required html table for arithmetic operators--------------------
			apw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t01 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t01 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t01 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			apw.println("<table id=\"t01\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Arithmetic Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int arithOpPointer =0;
			int arithOpCounter = 1;

			int arithOpTwoPointer =0;
			int arithOpTwoCounter = 1;

			if(arithOpList.isEmpty() == false) {
				for(int i=0;i<processedArithOpList.size();i++) {
					if(i!=0 && i % 4 == 0) {
						//System.out.println(arithOpPointer);
						arithOpPointer++;
					}
					String s = arithOpList.get(arithOpPointer);
					apw.format("<TR ALIGN=CENTER><TD>%d<TD>%s<TD>%s%n",arithOpCounter, s, processedArithOpList.get(i));
					arithOpCounter++;
				}

			}

			if(arithOpTwoList.isEmpty() == false) {
				for(int j=0;j<processedArithOpTwoList.size();j++) {
					if(j!=0 && j % 1 == 0) {
						arithOpTwoPointer++;
					}
					String s2 = arithOpTwoList.get(arithOpTwoPointer);
					apw.format("<TR ALIGN=CENTER><TD>%d<TD>%s<TD>%s%n",arithOpTwoCounter, s2, processedArithOpTwoList.get(j));
					arithOpTwoCounter++;
				}
			}


			apw.println("</TABLE>");
			apw.println("<br>");
			apw.close();
			abw.close();
			aw.close();

		}

	}

	//------------Generate a html table for the shift operators--------------------
	public void generateShiftOpTable() throws IOException {

		List<String> shiftOpList = new ArrayList<String>();
		List<String> processedShiftOpList = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants----------------------
		ShiftOpStorage ss = new ShiftOpStorage();
		shiftOpList = ss.returnShiftOpList();
		processedShiftOpList = ss.retriveShiftProcessList();


		//---------------------Generating the required html table for shift operators--------------------
		if(shiftOpList.isEmpty() == false) {
			FileWriter sw = new FileWriter("htmlreport.html", true);
			BufferedWriter sbw = new BufferedWriter(sw);
			PrintWriter spw = new PrintWriter(sbw);
			spw.println("<br>");
			spw.println();
			spw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			spw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Shift Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int shiftPointer = 0;
			int shiftCounter = 1;
			for(int i=0;i<processedShiftOpList.size();i++) {
				if(i!=0 && i % 2 == 0) {
					shiftPointer++;
				}
				String s = shiftOpList.get(shiftPointer);
				spw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",shiftCounter, s, processedShiftOpList.get(i));
				shiftCounter++;
			}

			spw.println("</TABLE>");
			spw.println("<br>");
			spw.close();
			sbw.close();
			sw.close();
		}

	}

	//------------Generate a html table for the bitwise operators--------------------
	public void generateBitwiseOpTable() throws IOException {
		List<String> bitwiseOpList = new ArrayList<String>();
		List<String> processedBitwiseOpList = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants----------------------
		BitwiseOpStorage bos = new BitwiseOpStorage();
		bitwiseOpList = bos.returnBitwiseOpList();
		processedBitwiseOpList = bos.retriveBitwiseProcessList();

		//---------------------Generating the required html table for bitwise operators--------------------
		if(bitwiseOpList.isEmpty() == false) {
			FileWriter bw = new FileWriter("htmlreport.html", true);
			BufferedWriter brw = new BufferedWriter(bw);
			PrintWriter bpw = new PrintWriter(brw);
			bpw.println("<br>");
			bpw.println();
			bpw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			bpw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Bitwise Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int bitwisePointer = 0;
			int bitwiseCounter = 1;
			for(int i=0;i<processedBitwiseOpList.size();i++) {
				if(i!=0 && i % 2 == 0) {
					bitwisePointer++;
				}
				String s = bitwiseOpList.get(bitwisePointer);
				bpw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",bitwiseCounter, s, processedBitwiseOpList.get(i));
				bitwiseCounter++;
			}

			bpw.println("</TABLE>");
			bpw.println("<br>");
			bpw.close();
			brw.close();
			bw.close();
		}

	}

	//------------Generate a html table for the conditional operators--------------------
	public void generateConditionalOpTable() throws IOException {
		List<String> conditionalOpList = new ArrayList<String>();
		List<String> processedConditionalOpList = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants----------------------
		ConditionalOpStorage cos = new ConditionalOpStorage();
		conditionalOpList = cos.returnOpList();
		processedConditionalOpList = cos.retriveProcessList();


		//---------------------Generating the required html table for conditional operators--------------------
		if(conditionalOpList.isEmpty() == false) {
			FileWriter cw = new FileWriter("htmlreport.html", true);
			BufferedWriter cbw = new BufferedWriter(cw);
			PrintWriter cpw = new PrintWriter(cbw);
			cpw.println("<br>");
			cpw.println();
			cpw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			cpw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Conditional Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int conditionalPointer = 0;
			int conditionalCounter = 1;
			for(int i=0;i<processedConditionalOpList.size();i++) {
				if(i!=0 && i % 1 == 0) {
					conditionalPointer++;
				}
				String s = conditionalOpList.get(conditionalPointer);
				cpw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",conditionalCounter, s, processedConditionalOpList.get(i));
				conditionalCounter++;
			}

			cpw.println("</TABLE>");
			cpw.println("<br>");
			cpw.close();
			cbw.close();
			cw.close();
		}
	}

	//------------Generate a html table for the relational operators--------------------
	public void generateRelationalOpTable() throws IOException {
		List<String> relationalOpList = new ArrayList<String>();
		List<String> processedRelationalOpList = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants----------------------
		RelationalOpStorage rs = new RelationalOpStorage();
		relationalOpList = rs.returnRelOpList();
		processedRelationalOpList = rs.retriveRelProcessList();

		//---------------------Generating the required html table for relational operators--------------------
		if(relationalOpList.isEmpty() == false) {
			FileWriter rw = new FileWriter("htmlreport.html", true);
			BufferedWriter rbw = new BufferedWriter(rw);
			PrintWriter rpw = new PrintWriter(rbw);
			rpw.println("<br>");
			rpw.println();
			rpw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			rpw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Relational Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int relationalPointer = 0;
			int relationalCounter = 1;
			for(int i=0;i<processedRelationalOpList.size();i++) {
				if(i!=0 && i % 5 == 0) {
					relationalPointer++;
				}
				String s = relationalOpList.get(relationalPointer);
				rpw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",relationalCounter, s, processedRelationalOpList.get(i));
				relationalCounter++;
			}

			rpw.println("</TABLE>");
			rpw.println("<br>");
			rpw.close();
			rbw.close();
			rw.close();
		}
	}

	//------------Generate a html table for the increment/decrement operators--------------------
	public void generateIncDecOpTable() throws IOException {
		List<String> incDecOpList = new ArrayList<String>();
		List<String> processedIncDecOpList = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants----------------------
		IncrementDecrementOpStorage ios = new IncrementDecrementOpStorage();
		ios.processOp();
		incDecOpList = ios.returnOpList();
		ios.processList(incDecOpList);
		processedIncDecOpList = ios.retriveProcessList();

		//---------------------Generating the required html table for increment/decrement operators--------------------
		if(incDecOpList.isEmpty() == false) {
			FileWriter idw = new FileWriter("htmlreport.html", true);
			BufferedWriter idbw = new BufferedWriter(idw);
			PrintWriter idpw = new PrintWriter(idbw);
			idpw.println("<br>");
			idpw.println();
			idpw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			idpw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Increment/Decrement Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int incDecPointer = 0;
			int incDecCounter = 1;
			for(int i=0;i<processedIncDecOpList.size();i++) {
				if(i!=0 && i % 1 == 0) {
					incDecPointer++;
				}
				String s = incDecOpList.get(incDecPointer);
				idpw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",incDecCounter, s, processedIncDecOpList.get(i));
				incDecCounter++;
			}

			idpw.println("</TABLE>");
			idpw.println("<br>");
			idpw.close();
			idbw.close();
			idw.close();
		}
	}

	//------------Generate a html table for the assignment operators--------------------
	public void generateAssignmentOpTable() throws IOException {
		List<String> arithOpL = new ArrayList<String>();
		List<String> arithOpP = new ArrayList<String>();

		List<String> bitwiseOpL = new ArrayList<String>();
		List<String> bitwiseOpP = new ArrayList<String>();

		List<String> shiftOpL = new ArrayList<String>();
		List<String> shiftOpP = new ArrayList<String>();

		//-------------------Retrieve operators stored and their respective generated mutants----------------------
		AssignmentOpStorage aos = new AssignmentOpStorage();
		arithOpL = aos.returnArithAssignOpList();
		arithOpP = aos.retriveArithAssignmentProcessList();
		bitwiseOpL = aos.returnBitwiseAssignOpList();
		bitwiseOpP = aos.retriveBitwiseAssignmentProcessList();
		shiftOpL = aos.returnShiftAssignOpList();
		shiftOpP = aos.retriveShiftAssignmentProcessList();

		//---------------------Generating the required html table for arithmeticassignment operators--------------------
		if(arithOpL.isEmpty() == false) {
			FileWriter aaw = new FileWriter("htmlreport.html", true);
			BufferedWriter aabw = new BufferedWriter(aaw);
			PrintWriter aapw = new PrintWriter(aabw);
			aapw.println("<br>");
			aapw.println();
			aapw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			aapw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Arithmetic Assignment Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int arithAssignPointer = 0;
			int arithAssignCounter = 1;
			for(int i=0;i<arithOpP.size();i++) {
				if(i!=0 && i % 5 == 0) {
					arithAssignPointer++;
				}
				String s = arithOpL.get(arithAssignPointer);
				aapw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",arithAssignCounter, s, arithOpP.get(i));
				arithAssignCounter++;
			}

			aapw.println("</TABLE>");
			aapw.println("<br>");
			aapw.close();
			aabw.close();
			aaw.close();
		}

		//---------------------Generating the required html table for bitwiseassignment operators--------------------
		if(bitwiseOpL.isEmpty() == false) {
			FileWriter baw = new FileWriter("htmlreport.html", true);
			BufferedWriter babw = new BufferedWriter(baw);
			PrintWriter bapw = new PrintWriter(babw);
			bapw.println("<br>");
			bapw.println();
			bapw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			bapw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Bitwise Assignment Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int bitwiseAssignPointer = 0;
			int bitwiseAssignCounter = 1;
			for(int i=0;i<bitwiseOpP.size();i++) {
				if(i!=0 && i % 2 == 0) {
					bitwiseAssignPointer++;
				}
				String s = bitwiseOpL.get(bitwiseAssignPointer);
				bapw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",bitwiseAssignCounter, s, bitwiseOpP.get(i));
				bitwiseAssignCounter++;
			}

			bapw.println("</TABLE>");
			bapw.println("<br>");
			bapw.close();
			babw.close();
			baw.close();
		}

		//---------------------Generating the required html table for shiftassignment operators--------------------
		if(shiftOpL.isEmpty() == false) {
			FileWriter ssw = new FileWriter("htmlreport.html", true);
			BufferedWriter ssbw = new BufferedWriter(ssw);
			PrintWriter sspw = new PrintWriter(ssbw);
			sspw.println("<br>");
			sspw.println();
			sspw.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<style>\r\n" + 
					"table {\r\n" + 
					"  width:100%;\r\n" + 
					"}\r\n" + 
					"table, th, td {\r\n" + 
					"  border: 1px solid black;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"}\r\n" + 
					"th, td {\r\n" + 
					"  padding: 15px;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(even) {\r\n" + 
					"  background-color: #eee;\r\n" + 
					"}\r\n" + 
					"#t02 tr:nth-child(odd) {\r\n" + 
					" background-color: #fff;\r\n" + 
					"}\r\n" + 
					"#t02 th {\r\n" + 
					"  background-color: black;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			sspw.println("<table id=\"t02\">"
					+ "<tr><th colspan=3 style=\"text-align:center\">Shift Assignment Operator Mutants</th></tr>"
					+ "<tr>\r\n" + 
					"    <th style=\"text-align:center\">Index</th>\r\n" + 
					"    <th style=\"text-align:center\">Original Stamement</th> \r\n" + 
					"    <th style=\"text-align:center\">Mutation</th>\r\n" + 
					"  </tr>");
			int shiftAssignPointer = 0;
			int shiftAssignCounter = 1;
			for(int i=0;i<shiftOpP.size();i++) {
				if(i!=0 && i % 1 == 0) {
					shiftAssignPointer++;
				}
				String s = shiftOpL.get(shiftAssignPointer);
				sspw.format("<TR ALIGN=RIGHT><TD>%d<TD>%s<TD>%s%n",shiftAssignCounter, s, shiftOpP.get(i));
				shiftAssignCounter++;
			}

			sspw.println("</TABLE>");
			sspw.println("<br>");
			sspw.close();
			ssbw.close();
			ssw.close();
		}

	}

}
