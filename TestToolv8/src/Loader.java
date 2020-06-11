import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Loader {
	
//	public static void main(String[] args) {
//		Loader l = new Loader();
//		l.loadClass("F:\\Java Projects\\Mutation-Testing-Tool\\TestToolv8\\bin", "M1");
//	}
    public Object loadClass(String path, String classname) {
        File file = new File(path);
        try {
            URL classUrl = file.toURI().toURL();
            URL[] urls = new URL[]{classUrl};
            ClassLoader ucl = new URLClassLoader(urls, getClass().getClassLoader());
            return ucl.loadClass(classname);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
