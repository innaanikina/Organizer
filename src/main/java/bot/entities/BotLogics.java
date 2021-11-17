package bot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema="organizer_bot", name="botLogic")
public class BotLogics {
    @Id
    private Long botLogicID;

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

    //getters and setters

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

    public void setOrganizer(List<Organizer>  organizer) {
        this.organizer = organizer;
    }
}
