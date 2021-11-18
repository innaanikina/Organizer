package bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Key {
    static String getToken() throws IOException {
        String path = new File("").getAbsolutePath();
        System.out.println(path);
        String separator = System.getProperty("file.separator");
        String pathToToken = path.substring(0, path.lastIndexOf("\\")) + separator;
        BufferedReader br = new BufferedReader(new FileReader(pathToToken + separator + "TOKEN"));
        String s = br.readLine();
        br.close();
        return s;
    }
}