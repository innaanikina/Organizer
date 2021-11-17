package bot.services;


import bot.entities.Deadlines;
import bot.repository.DeadlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeadlinesService {
    @Autowired
    private final DeadlinesRepository deadlinesRepository;

    public DeadlinesService(DeadlinesRepository deadlinesRepository){
        this.deadlinesRepository = deadlinesRepository;
    }

    public void createDeadlines(Deadlines deadlines){
        deadlinesRepository.save(deadlines);
    }
}
