package bot.entities;

import javax.persistence.*;

@Entity
@Table(schema="organizer_bot", name="organizer")
public class Organizer {
    @Id
    private Long id;

    @Column(columnDefinition="text")
    private String flag;

    @Column(columnDefinition="text")
    private String task;

    @Column
    private Long date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deadline_id")
    private Deadlines deadlines;

    @ManyToOne
    @JoinColumn(name = "botLogic_id")
    private BotLogics botLogic;

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Deadlines getMy_deadlines() {
        return deadlines;
    }

    public void setMy_deadlines(Deadlines my_deadlines) {
        this.deadlines = my_deadlines;
    }

    public BotLogics getBotLogic() {
        return botLogic;
    }

    public void setBotLogic(BotLogics botLogic) {
        this.botLogic = botLogic;
    }
}
