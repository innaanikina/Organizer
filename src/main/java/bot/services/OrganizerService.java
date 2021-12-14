package bot.services;

import bot.entities.Organizer;
import bot.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrganizerService {
    @Autowired
    private final OrganizerRepository organizerRepository;

    public OrganizerService(OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
    }

    public void createOrganizer(Organizer organizer){
        organizerRepository.save(organizer);
    }
    public void create(String task){
        Organizer organizer = new Organizer();
        organizer.setTask(task);
        organizer.setFlag("NO_CHECK");
        organizer.setDate();
    }
    public OrganizerElement(String task) {
        this.flag = Flag.NO_CHECK;
        this.task = task;
        date = new GregorianCalendar();
    }

    public ArrayList<Organizer> getAll(){return (ArrayList<Organizer>) organizerRepository.findAll();}
}
