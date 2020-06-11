import java.io.File;
import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CustomCompiler {
	
	String mutantPath;
	public void setMutantPath(String mPath) {
		mutantPath = mPath; 
	}
	
	public void compileFiles() throws IOException{
		//String filePath = "F:\\MSc Computing Science\\Thesis\\New Testing\\";
		File directoryPath = new File(mutantPath);
		String contents[] = directoryPath.list();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(compiler);
        for(int i=0;i<contents.length;i++) {
        	String requiredPath = mutantPath+"\\"+contents[i];
        	int b=compiler.run(null, null, null,requiredPath);
        }
       
		
	}
	
//	public static void main(String[] args) throws IOException {
//		CustomCompiler cc = new CustomCompiler();
//		cc.compileFiles();
//		
//	}

}
