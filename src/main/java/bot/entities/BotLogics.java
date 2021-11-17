package bot.entities;

//import bot.DictCommands;
//import bot.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@Table(schema="organizer_bot", name="botLogic")
public class BotLogics {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long botLogicID;

    @Column
    private int userID;

    //public State statusActive = State.START;
    @Column(columnDefinition="text")
    private String statusActive;
    // прописать изначальные?
    @Column(columnDefinition="text")
    private String currentCommands;
    //сделать сущность organizer
    @Column
    private int editTask;

    @OneToMany(mappedBy = "botLogic")
    private List<Organizer> organizer;

    public BotLogics() {
        //updateCurrentCommands()
    }

//    private void updateCurrentCommands() {
//         List<String> weekDays = new ArrayList<String>() {{
//            add("пн");add("вт");add("ср");add("чт");add("пт");add("сб"); }};
//
//         String newcommands = "";
//
//        var allCommands = new DictCommands().getCommands();
//        var hiddenCommands = new DictCommands().getHiddenCommands();
//        var commands = allCommands.get(statusActive).keySet();
//        var newCommands = new CopyOnWriteArrayList<String>();
//
//        if (statusActive == "CLASSES"){
//            newCommands.addAll(weekDays);
//        }
//        for (String s : commands){
//            if (hiddenCommands.add(s)) {
//                newCommands.add(s);
//            }
//        }
//
//        currentCommands = newcommands;
//    }

    //getters and setters

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setOrganizer(List<Organizer> organizer) {
        this.organizer = organizer;
    }

    public Long getBotLogicID() {
        return botLogicID;
    }

    public void setBotLogicID(Long botLogicID) {
        this.botLogicID = botLogicID;
    }

    public String getStatusActive() {
        return statusActive;
    }

    public void setStatusActive(String statusActive) {
        this.statusActive = statusActive;
    }

    public String getCurrentCommands() {
        return currentCommands;
    }

    public void setCurrentCommands(String currentCommands) {
        this.currentCommands = currentCommands;
    }

    public int getEditTask() {
        return editTask;
    }

    public void setEditTask(int editTask) {
        this.editTask = editTask;
    }

    public List<Organizer> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(ArrayList<Organizer>  organizer) {
        this.organizer = organizer;
    }
}
