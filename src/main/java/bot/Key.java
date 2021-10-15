package bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Key {
    static String getToken() throws IOException {
        String path = new File("").getAbsolutePath();
        System.out.println(path);
        //String pathToToken = path.substring(0, path.lastIndexOf("\\")) + "\\";
        String pathToToken = path.substring(0, path.lastIndexOf("/")) + "/";
        BufferedReader br = new BufferedReader(new FileReader(pathToToken+"TOKEN"));
        String s = br.readLine();

        br.close();
        return s;
    }
}