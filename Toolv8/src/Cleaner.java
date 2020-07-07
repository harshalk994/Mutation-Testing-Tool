import java.io.File;    
import java.io.FileReader;    
import java.io.IOException;    
import java.io.BufferedReader;    

public class Cleaner{    

//    public static void main( String a[] )    
//    {    
//        String source = readFile();    
//
//        System.out.println(source.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)",""));    
//
//    }    
	
	private static String mPath;
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}


    static String readFile() {    
    	String tempFileName = mPath+"\\OriginalTempCopy.java";
        File file = new File(tempFileName);    

        char[] buffer = null;    

        try {    
                BufferedReader bufferedReader = new BufferedReader( new FileReader(file));    

                buffer = new char[(int)file.length()];    

                int i = 0;    
                int c = bufferedReader.read();    

                while (c != -1) {    
                    buffer[i++] = (char)c;    
                    c = bufferedReader.read();    
                }
                
                bufferedReader.close();

        } catch (IOException e) {    
            e.printStackTrace();    
        }    

        return new String(buffer);    
    }    

}    