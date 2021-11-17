package bot;

import bot.entities.BotLogics;
import bot.entities.Organizer;
import bot.repository.OrganizerRepository;
import bot.services.BotLogicsService;
import bot.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OrganizerBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizerBotApplication.class, args);
	}
	@Autowired
	private BotLogicsService botLogicsService;

	@Autowired
	private OrganizerService organizerService;
	

}
