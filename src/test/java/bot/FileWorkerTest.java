package bot;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FileWorkerTest {

    @Test
    void readFileArray() {
        var fileWorker = new FileWorker();
        var hiddenCommands = fileWorker.readFileArray("src/main/resources/hiddenCommand.txt");

        var answer = new HashSet<String>();
        answer.add("help");
        answer.add("default");
        answer.add("авторы");

        Assert.assertEquals(answer, hiddenCommands);
    }

    @Test
    void readFile() {
        var fileWorker = new FileWorker();
        var text = fileWorker.readFile("src/main/resources/testReadFile.txt");

        String answer = "qwerty\n" + "asdfg;\n" + "zxcvb\n";

        Assert.assertEquals(answer, text);

    }
}