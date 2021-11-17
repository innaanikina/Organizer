package bot.entities;

import javax.persistence.*;

@Entity
@Table(schema="organizer_bot", name = "deadlines")
public class Deadlines {
    @Id
    private Long deadlineID;

    @Column
    private Boolean is_end;

    @Column
    private Boolean five_minute;

    @Column
    private Boolean is_hour;

    @Column
    private Boolean twelve_hour;

    @OneToOne(mappedBy = "deadlines")
    private Organizer organizer;

    public Deadlines() {
    }

    public Long getDeadlineID() {
        return deadlineID;
    }

    public void setDeadlineID(Long deadlineID) {
        this.deadlineID = deadlineID;
    }

    public Boolean getIs_end() {
        return is_end;
    }

    public void setIs_end(Boolean is_end) {
        this.is_end = is_end;
    }

    public Boolean getFive_minute() {
        return five_minute;
    }

    public void setFive_minute(Boolean five_minute) {
        this.five_minute = five_minute;
    }

    public Boolean getIs_hour() {
        return is_hour;
    }

    public void setIs_hour(Boolean is_hour) {
        this.is_hour = is_hour;
    }

    public Boolean getTwelve_hour() {
        return twelve_hour;
    }

    public void setTwelve_hour(Boolean twelve_hour) {
        this.twelve_hour = twelve_hour;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}
