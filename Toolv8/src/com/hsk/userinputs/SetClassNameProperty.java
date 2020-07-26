package com.hsk.userinputs;
import com.hsk.*;

public class SetClassNameProperty {
	
	//private String cName=null;
	
//	
//
//	public SetClassNameProperty(String cName) {
//		super();
//		this.cName = cName;
//	}

	public String getCName() {
		
		String updatedCName = System.getProperty("classname");
		return updatedCName;
	}

	public void setCName(String clName) {
		String cName = clName;
		//this.cName = clName;
		System.setProperty("classname", cName);
		//System.out.println("Classname was set to: " + cName);
	}
	
	
	

//public class SimpleGetterAndSetter {
//    private int number;
//    public int getNumber() {
//        return this.number;
//    }
//    public void setNumber(int num) {
//        this.number = num;
//    }
//}
	

}
