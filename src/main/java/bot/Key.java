package bot;

import java.io.*;

class Key {
    static String get_token()  {
        String path = new File("").getAbsolutePath();
        String path_to_token = path.substring(0, path.lastIndexOf("\\")) + "\\";

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path_to_token+"\\"+"TOKEN"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String s = null;

        try {
            s = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
}