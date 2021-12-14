package bot;

import bot.commands.elem.Study;
import bot.commands.organizer.OrganizerElement;
import bot.entities.User;
import bot.services.DeadlinesService;
import bot.services.OrganizerService;
import bot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;

public class LogicBot {
    public String statusActive = "START";
    private CopyOnWriteArrayList<String> currentCommands;
    public CopyOnWriteArrayList<OrganizerElement> organizer = new CopyOnWriteArrayList<>();
    public ConcurrentHashMap<String, HashMap<Integer, Boolean>> deadlines = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Integer> edits = new ConcurrentHashMap<>();
    public Long id;

    CopyOnWriteArrayList<String> getCurrentCommands() {
        return currentCommands;
    }

    @Autowired
    public UserService userService;
    @Autowired
    public OrganizerService organizerService;
    @Autowired
    public DeadlinesService deadlinesService;

    public LogicBot(Long id) {
        this.id = id;
        User user = new User();
        // to do?
        userService.createUser(user);
    }

    String getAnswer(String line) {
        HashMap<String, HashMap<String, BiFunction<LogicBot, String, String>>> allCommands = new DictCommands().getCommands();
        return allCommands.get(statusActive)
                .getOrDefault(line, allCommands.get(statusActive).get("default"))
                .apply(this, line);
    }

    private void updateCurrentCommands() {
        HashMap<String, HashMap<String, BiFunction<LogicBot, String, String>>> allCommands = new DictCommands().getCommands();
        HashSet<String> hiddenCommands = new DictCommands().getHiddenCommands();
        Set<String> commands = allCommands.get(statusActive).keySet();
        CopyOnWriteArrayList<String> newCommands = new CopyOnWriteArrayList<String>();

        if (statusActive.equals("CLASSES")){
            List<String> weekDays = Study.getWeekDays();
            newCommands.addAll(weekDays);
        }
        for (String s : commands){
            if (hiddenCommands.add(s)) {
                newCommands.add(s);
            }
        }
        currentCommands = newCommands;
    }

    void updateStatusActive(LogicBot user) {
        statusActive = user.statusActive;
        userService.updateStatusActiveByUserId(user.id, user.statusActive);
        updateCurrentCommands();
    }
}
