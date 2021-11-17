package bot.services;

import bot.entities.Organizer;
import bot.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
