package bot;

import commands.Study;
import java.util.regex.Pattern;

public class BotLogic {
    public State.state statusActive = State.state.START;
    private String[] currentCommands;

    BotLogic(){
        updateCurrentCommands();
    }

    String[] getCurrentCommands() {
        return currentCommands;
    }

    String getAnswer(String line) {
        var allCommands = new DictsCommands().getCommands();
        String command = line;

        if (!Pattern.matches(" +", line)) {
            command = line.split(" ")[0].toLowerCase();
        }

        return allCommands.get(statusActive)
                .getOrDefault(command, allCommands.get(statusActive).get("default"))
                .apply(this, line);
    }

    private void updateCurrentCommands() {
        var allCommands = new DictsCommands().getCommands();
        var hiddenCommands = new DictsCommands().getHiddenCommands();
        var commands = allCommands.get(statusActive).keySet();
        String[] newCommands = new String[commands.size() + 7];
        int i = 0;

        if (statusActive == State.state.CLASSES){
            var weekDays = Study.getWeekDays();

            for (String s : weekDays){
                newCommands[i] = s;
                i++;
            }
        }
        for (String s : commands){
            if (hiddenCommands.add(s)) {
                newCommands[i] = s;
            }
            i++;
        }

        currentCommands = newCommands;
    }

    void updateStatusActive(BotLogic user) {
        statusActive = user.statusActive;
        updateCurrentCommands();
    }

}
