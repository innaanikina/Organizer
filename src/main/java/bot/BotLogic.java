package bot;

import commands.Study;
import commands.organizer.OrganizerElement;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class BotLogic {
    public State statusActive = State.START;
    private CopyOnWriteArrayList<String> currentCommands;
    public CopyOnWriteArrayList<OrganizerElement> organizer = new CopyOnWriteArrayList<>();
    public ConcurrentHashMap<String, HashMap<Integer, Boolean>> deadlines = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Integer> edits = new ConcurrentHashMap<>();

    BotLogic(){
        updateCurrentCommands();
    }

    CopyOnWriteArrayList<String> getCurrentCommands() {
        return currentCommands;
    }

    String getAnswer(String line) {
        var allCommands = new DictCommands().getCommands();
        return allCommands.get(statusActive)
                .getOrDefault(line, allCommands.get(statusActive).get("default"))
                .apply(this, line);
    }

    private void updateCurrentCommands() {
        var allCommands = new DictCommands().getCommands();
        var hiddenCommands = new DictCommands().getHiddenCommands();
        var commands = allCommands.get(statusActive).keySet();
        var newCommands = new CopyOnWriteArrayList<String>();

        if (statusActive == State.CLASSES){
            var weekDays = Study.getWeekDays();
            newCommands.addAll(weekDays);
        }
        for (String s : commands){
            if (hiddenCommands.add(s)) {
                newCommands.add(s);
            }
        }
        currentCommands = newCommands;
    }

    void updateStatusActive(BotLogic user) {
        statusActive = user.statusActive;
        updateCurrentCommands();
    }

}
