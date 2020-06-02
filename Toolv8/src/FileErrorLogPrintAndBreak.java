import java.util.List;

public class FileErrorLogPrintAndBreak {
	
	public void printLogs(List<String> logs){
		
		for(int i=0; i<logs.size(); i++) {
			System.out.println(logs.get(i));			
			}
	}
	
	public void breakCode(List<String> logs) {
		String error = "ERROR";
		for(int i=0; i<logs.size(); i++) {
			if(logs.get(i).contains(error)) {
				System.out.println("Syntax error in file, please correct the error and try again");
				System.exit(1);
			}
		}
	}

}
