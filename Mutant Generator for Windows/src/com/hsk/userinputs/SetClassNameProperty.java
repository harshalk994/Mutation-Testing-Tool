//	This class is created for storage and retrieval of class names

package com.hsk.userinputs;
import com.hsk.*;

public class SetClassNameProperty {

	//----------Getter method for the retrieval of class name-----------------
	public String getCName() {

		String updatedCName = System.getProperty("classname");
		return updatedCName;
	}

	//----------Setter method for storage of class name--------------
	public void setCName(String clName) {
		String cName = clName;
		System.setProperty("classname", cName);
	}

}
