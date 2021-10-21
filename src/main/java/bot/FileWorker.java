package bot;

import java.io.*;
import java.util.HashSet;

class FileWorker {
    private bot.AbsPath absPath = new bot.AbsPath(); //получает путь до части /src

    String readFile(String nameFile) {
        try {
            File file = new File(absPath.getAbsPath(nameFile));
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            StringBuilder text = new StringBuilder();

            while (line != null) {
                text.append(line).append("\n");
                line = reader.readLine();
            }
            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Произошла ошибка чтения";
        }
    }

    HashSet<String> readFileArray(String nameFile) {
        try {
            File file = new File(absPath.getAbsPath(nameFile));
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            HashSet<String> text = new HashSet<>();

            while (line != null) {
                text.add(line);
                line = reader.readLine();
            }
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    FileWriter getFileWriter(String nameFile){
        try {
            return new FileWriter(absPath.getAbsPath(nameFile));
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла");
            e.printStackTrace();
        }
        return null;
    }
}
