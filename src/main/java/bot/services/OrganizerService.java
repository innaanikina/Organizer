package bot.services;

import bot.entities.Organizers;
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

    public void createOrganizer(Organizers organizer){
        organizerRepository.save(organizer);
    }

    public ArrayList<Organizers> getAll(){return (ArrayList<Organizers>) organizerRepository.findAll();}
}
