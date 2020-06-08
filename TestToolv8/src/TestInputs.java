import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestInputs {
	
	List<String> intInputs = new ArrayList<String>();
	List<Integer> testInputs = new ArrayList<Integer>();
	
//	public static void main(String[] args) throws IOException {
//		TestInputs ti = new TestInputs();
//		ti.getTestInputs();
//		ti.printInputs();
//	}
	
	public void getTestInputs() throws IOException {
		FileReader fr = new FileReader("src/testinputs.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null) {
			String[] words = line.split(" ");
			for(int i=0;i<words.length;i++) {
				intInputs.add(words[i]);
			}
			
			}
		
		br.close();
		fr.close();
		
		convertTestInputs(intInputs);
		
	}
	
	public void convertTestInputs(List<String> stringArray){
		for(String stringVal : stringArray) {
			try {
                //Convert String to Integer, and store it into integer array list.
                testInputs.add(Integer.parseInt(stringVal));
            } catch(NumberFormatException nfe) {
               //System.out.println("Could not parse " + nfe);
               nfe.printStackTrace();
            } 
		}
		
	}
	
	public List<Integer> retrieveTestInputs(){
		return testInputs;
	}
	
	public void printInputs() {
		for(int i=0;i<testInputs.size();i++) {
			System.out.println(testInputs.get(i));
		}
	}


}
