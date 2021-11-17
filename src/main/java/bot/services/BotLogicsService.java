package bot.services;

import bot.entities.BotLogics;
import bot.repository.BotLogicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotLogicsService {
    @Autowired
    private final BotLogicsRepository botLogicRepository;

    public BotLogicsService(BotLogicsRepository botLogicsRepository){
        this.botLogicRepository = botLogicsRepository;
    }

    public void createBotLogics(BotLogics botLogics){
        botLogicRepository.save(botLogics);
    }

    public BotLogics findById(Long botLogicId){
        return botLogicRepository.findById(botLogicId).orElse(null);
    }
}
