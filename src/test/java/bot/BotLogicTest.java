package bot;

import commands.Menu;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotLogicTest {
    BotLogic botLogic = new BotLogic();

    @Test
    void getAnswer() {
        Menu menu = new Menu();
        var answer = menu.help(botLogic, "");

        Assert.assertEquals(answer, botLogic.getAnswer("помощь"));
    }

    @Test
    void botLogic(){
        Assert.assertEquals(State.START, botLogic.statusActive);
    }

    @Test
    void testMenu(){
        Assert.assertEquals(botLogic.statusActive, State.START);
        botLogic.getAnswer("помощь");
        Assert.assertEquals(botLogic.statusActive, State.MENU);
    }

    @Test
    void testStudy() {
        botLogic.getAnswer("помощь");
        botLogic.getAnswer("учеба");
        Assert.assertEquals(State.STUDY, botLogic.statusActive);
        botLogic.getAnswer("меню");
        Assert.assertEquals(State.MENU, botLogic.statusActive);
    }

    @Test
    void testClasses() {
        botLogic.getAnswer("помощь");
        botLogic.getAnswer("учеба");
        botLogic.getAnswer("пары");
        Assert.assertEquals(State.CLASSES, botLogic.statusActive);
        botLogic.getAnswer("меню");
        Assert.assertEquals(State.MENU, botLogic.statusActive);
    }

    @Test
    void testBooks() {
        botLogic.getAnswer("помощь");
        botLogic.getAnswer("книги");
        Assert.assertEquals(State.BOOKS, botLogic.statusActive);
        botLogic.getAnswer("меню");
        Assert.assertEquals(State.MENU, botLogic.statusActive);
    }
}