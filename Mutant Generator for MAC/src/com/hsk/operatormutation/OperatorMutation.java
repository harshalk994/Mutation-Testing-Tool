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


package com.hsk.operatormutation;
import com.hsk.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hsk.htmlfilegenerator.GenerateHtmlTable;
import com.hsk.operatorstorage.ArithmeticOpStorage;
import com.hsk.operatorstorage.AssignmentOpStorage;
import com.hsk.operatorstorage.BitwiseOpStorage;
import com.hsk.operatorstorage.ConditionalOpStorage;
import com.hsk.operatorstorage.IncrementDecrementOpStorage;
import com.hsk.operatorstorage.RelationalOpStorage;
import com.hsk.operatorstorage.ShiftOpStorage;
import com.hsk.userinputs.UserInputs;

/*
 * This class is created to apply the different types of operator mutation rules based on 
 * operators captured from the original program
 */
public class OperatorMutation {

	//--------------Method to apply required mutation rules to operatores captured in the original program---------------------
	public void opMutationRules() throws IOException {
		List<String> arithOpList = new ArrayList<String>();
		List<String> arithTwoList = new ArrayList<String>();
		List<String> conditionalOpList = new ArrayList<String>();
		List<String> relationalOpList = new ArrayList<String>();
		List<String> relationalOpTwoList = new ArrayList<String>();
		List<String> shiftOpList = new ArrayList<String>();
		List<String> bitwiseOpList = new ArrayList<String>();
		List<String> arithAssignmentOpList = new ArrayList<String>();
		List<String> bitwiseAssignOpList = new ArrayList<String>();
		List<String> shiftAssignOpList = new ArrayList<String>();
		List<String> incDecOpList = new ArrayList<String>();

		boolean allops = false;
		char arithop;
		char assignmentop;
		char bitwiseop;
		char conditionalop;
		char incdecop;
		char relationalop;
		char shiftop;

		//----------getting userinputs regarding which mutation rules user wishes to apply to the program------------
		UserInputs ui = new UserInputs();
		arithop = ui.returnArithOp();
		assignmentop = ui.returnAssignOp();
		bitwiseop = ui.returnBitwiseOp();
		conditionalop = ui.returnConditionalOp();
		incdecop = ui.returnIncDecOp();
		relationalop = ui.returnRelationalOp();
		shiftop = ui.returnShiftOp();


		//-------------------Retrieve the arithmetic operators that are captured from original class----------------------
		ArithmeticOpStorage ops = new ArithmeticOpStorage();
		ops.processOp();
		arithOpList = ops.returnOpList();
		arithTwoList = ops.returnOpTwoList();

		//-------------------Retrieve the conditional operators that are captured from original class----------------------
		ConditionalOpStorage cs = new ConditionalOpStorage();
		cs.processOp();
		conditionalOpList = cs.returnOpList();

		//-------------------Retrieve the relational operators that are captured from original class----------------------
		RelationalOpStorage rs = new RelationalOpStorage();
		rs.processOp();
		relationalOpList = rs.returnRelOpList();
		relationalOpTwoList = rs.returnRelOpListNotEqual();

		//-------------------Retrieve the shift operators that are captured from original class----------------------
		ShiftOpStorage ss = new ShiftOpStorage();
		ss.processOp();
		shiftOpList = ss.returnShiftOpList();

		//-------------------Retrieve the bitwise operators that are captured from original class----------------------
		BitwiseOpStorage bos = new BitwiseOpStorage();
		bos.processOp();
		bitwiseOpList = bos.returnBitwiseOpList();

		//-------------------Retrieve the assignment operators that are captured from original class----------------------
		AssignmentOpStorage as = new AssignmentOpStorage();
		as.processOp();
		arithAssignmentOpList = as.returnArithAssignOpList();
		bitwiseAssignOpList = as.returnBitwiseAssignOpList();
		shiftAssignOpList = as.returnShiftAssignOpList();

		//-------------------Retrieve the inc/dec operators that are captured from original class----------------------
		IncrementDecrementOpStorage ids = new IncrementDecrementOpStorage();
		ids.processOp();
		incDecOpList = ids.returnOpList();

		//-----------if choices in the cofig file are left blank for the mutation rules, apply all rules by default---------------
		if((arithop==' ') && (assignmentop==' ') && (bitwiseop==' ')
				&& (conditionalop==' ') && (incdecop==' ') && (relationalop==' ')
				&& (shiftop==' ')) {
			allops=true;
		}

		if(allops==true) {
			//------------apply arithmetic operator mutation rule if arithmetic operators are present in the original program-----------------
			if(arithOpList.isEmpty() == false || arithTwoList.isEmpty() == false) {
				ArithmeticOperatorMutation aop = new ArithmeticOperatorMutation();
				aop.generateArithOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateArithOpTable();
			}

			//------------apply conditional operator mutation rule if conditional operators are present in the original program-----------------
			if(conditionalOpList.isEmpty() == false) {
				ConditionOperatorMutation cop = new ConditionOperatorMutation();
				cop.generateConditionMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateConditionalOpTable();
			}

			//------------apply relational operator mutation rule if relational operators are present in the original program-----------------
			if(relationalOpList.isEmpty() == false || relationalOpTwoList.isEmpty() == false) {
				RelationalOperatorMutation rop = new RelationalOperatorMutation();
				rop.generateRelOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateRelationalOpTable();
			}

			//------------apply shift operator mutation rule if shift operators are present in the original program-----------------
			if(shiftOpList.isEmpty() == false) {
				ShiftOperatorMutation sot = new ShiftOperatorMutation();
				sot.generateShiftOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateShiftOpTable();
			}

			//------------apply bitwise operator mutation rule if bitwise operators are present in the original program-----------------
			if(bitwiseOpList.isEmpty() == false) {
				BitwiseOperatorMutation bt = new BitwiseOperatorMutation();
				bt.generateBitwiseOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateBitwiseOpTable();
			}

			//------------apply assignment operator mutation rule if assignment operators are present in the original program-----------------
			if(arithAssignmentOpList.isEmpty() == false || bitwiseAssignOpList.isEmpty() == false || shiftAssignOpList.isEmpty() == false) {
				AssignmentOperatorMutation at = new AssignmentOperatorMutation();
				at.generateAssignmentOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateAssignmentOpTable();
			}

			//------------apply inc/dec operator mutation rule if inc/dec operators are present in the original program-----------------
			if(incDecOpList.isEmpty() == false) {
				IncrementDecrementOperatorMutation idt = new IncrementDecrementOperatorMutation();
				idt.generateIncDecOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateIncDecOpTable();
			}
		}

		//------if flag for arithop mutation rule is set to y in config file, apply the rule----------
		if(arithop=='y') {
			if(arithOpList.isEmpty() == false || arithTwoList.isEmpty() == false) {
				ArithmeticOperatorMutation aop = new ArithmeticOperatorMutation();
				aop.generateArithOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateArithOpTable();
			}
		}

		//------if flag for assignment op mutation rule is set to y in config file, apply the rule----------
		if(assignmentop=='y') {
			if(arithAssignmentOpList.isEmpty() == false || bitwiseAssignOpList.isEmpty() == false || shiftAssignOpList.isEmpty() == false) {
				AssignmentOperatorMutation at = new AssignmentOperatorMutation();
				at.generateAssignmentOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateAssignmentOpTable();
			}
		}

		//------if flag for bitwise op mutation rule is set to y in config file, apply the rule----------
		if(bitwiseop=='y') {
			if(bitwiseOpList.isEmpty() == false) {
				BitwiseOperatorMutation bt = new BitwiseOperatorMutation();
				bt.generateBitwiseOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateBitwiseOpTable();
			}
		}

		//------if flag for condition op mutation rule is set to y in config file, apply the rule----------
		if(conditionalop=='y') {
			if(conditionalOpList.isEmpty() == false) {
				ConditionOperatorMutation cop = new ConditionOperatorMutation();
				cop.generateConditionMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateConditionalOpTable();
			}
		}

		//------if flag for incdec op mutation rule is set to y in config file, apply the rule----------
		if(incdecop=='y') {
			if(incDecOpList.isEmpty() == false) {
				IncrementDecrementOperatorMutation idt = new IncrementDecrementOperatorMutation();
				idt.generateIncDecOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateIncDecOpTable();
			}
		}

		//------if flag for relational op mutation rule is set to y in config file, apply the rule----------
		if(relationalop=='y') {
			if(relationalOpList.isEmpty() == false || relationalOpTwoList.isEmpty() == false) {
				RelationalOperatorMutation rop = new RelationalOperatorMutation();
				rop.generateRelOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateRelationalOpTable();
			}
		}

		//------if flag for shift op mutation rule is set to y in config file, apply the rule----------
		if(shiftop=='y') {
			if(shiftOpList.isEmpty() == false) {
				ShiftOperatorMutation sot = new ShiftOperatorMutation();
				sot.generateShiftOpMutantFiles();
				GenerateHtmlTable pht = new GenerateHtmlTable();
				pht.generateShiftOpTable();
			}
		}



	}

}
