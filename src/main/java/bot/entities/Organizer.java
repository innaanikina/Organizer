package bot.entities;

import javax.persistence.*;

@Entity
@Table(schema="organizer_bot", name="organizer")
public class Organizer {
    @Id
    @GeneratedValue
    private Long organizeID;

    @Column(columnDefinition="text")
    private String flag;

    @Column(columnDefinition="text")
    private String task;

    @Column
    private String date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deadlineID")
    private Deadlines deadlines;

    public Organizer() { }
//getters and setters

    public Long getOrganizeID() {
        return organizeID;
    }

    public void setOrganizeID(Long organizeID) {
        this.organizeID = organizeID;
    }

    public Deadlines getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(Deadlines deadlines) {
        this.deadlines = deadlines;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
