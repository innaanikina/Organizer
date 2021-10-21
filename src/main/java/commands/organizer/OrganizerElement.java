package commands.organizer;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class OrganizerElement implements Serializable {

    public Flag flag;
    public String task;
    public GregorianCalendar date;

    public OrganizerElement(String task) {
        this.flag = Flag.NO_CHECK;
        this.task = task;
        date = new GregorianCalendar();
    }

    public void changeTask(String task) {
        this.task = task;
    }

    public void changeDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getDeadlineName() {
        return date.getTimeInMillis() + task;
    }

    public String getDeadlineName(GregorianCalendar date) {
        return date.getTimeInMillis() + task;
    }

    public String getDeadlineName(String newTask) {
        return this.date.getTimeInMillis() + newTask;
    }

    public Boolean needsFlagUpdate() {
        return flag != Flag.NO_CHECK && flag != Flag.COMPLETED;
    }

    public void updateFlag() {
        if (flag == Flag.NO_CHECK || flag == Flag.COMPLETED) {
            return;
        }

        var d = new GregorianCalendar();
        var dPlus3 = new GregorianCalendar();
        dPlus3.add(Calendar.DATE, +3);


        if (date.before(d)) {
            flag = Flag.FAILED;
        }

        else if (date.before(dPlus3)) {
            flag = Flag.DEADLINE_IS_COMING;
        }

        else {
            flag = Flag.IN_PROGRESS;
        }
    }
}
