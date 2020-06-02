import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RemoveComments {
	
	public void removeComments() throws IOException {
		
		
		Cleaner cleaner = new Cleaner();
		String line = cleaner.readFile();
		String newLine = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
		//System.out.println(newLine);
		FileWriter fw = new FileWriter("src/Temp.java");
		fw.write(newLine);
		fw.close();
		
		
		
		
		
		
//		FileReader source = new FileReader("src/Temp.java");
//		BufferedReader br = new BufferedReader(source);
//		FileWriter targetFile = new FileWriter("src/TempC.java");
//		BufferedWriter bw = new BufferedWriter(targetFile);
//		
//		String line;
//		line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
//		//String multiLine = "/\*.*\n.*\*\/";
//		
//		while((line = br.readLine()) != null) {
//			line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
//			
//			
//			bw.write(line);
//			bw.newLine();
//			
//		}
//		
//		bw.close();
//		br.close();
//		targetFile.close();
//		source.close();
//	}
	}

}
