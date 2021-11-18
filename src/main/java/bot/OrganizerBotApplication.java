package bot;

import bot.Main;

import bot.entities.BotLogics;
import bot.entities.Deadlines;
import bot.entities.Organizers;
import bot.services.BotLogicsService;
import bot.services.DeadlinesService;
import bot.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@SpringBootApplication
public class OrganizerBotApplication {

	public static void main(String[] args) throws IOException, TelegramApiException {
		//Main.main(args);
		SpringApplication.run(OrganizerBotApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpaMethods(){
		Deadlines deadlines = new Deadlines();
		deadlines.setDeadlineID((long)1);
		deadlines.setIs_end(false);
		deadlines.setFive_minute(false);
		deadlines.setIs_hour(false);
		deadlines.setTwelve_hour(false);
		deadlinesService.createDeadlines(deadlines);

		BotLogics botLogics = new BotLogics();
		botLogics.setStatusActive("START");
		botLogics.setEditTask(2);
		botLogics.setCurrentCommands("fff");
		botLogics.setBotLogicID((long)1);

		botLogicsService.createBotLogics(botLogics);

		Organizers organizer = new Organizers();
		organizer.setId((long)1);
		organizer.setFlag("END");
		organizer.setDate((long)12333);
		organizer.setMy_deadlines(deadlines);
		organizer.setTask("hi");
		organizer.setBotLogic(botLogics);
		organizerService.createOrganizer(organizer);

	}


	@Autowired
	private BotLogicsService botLogicsService;
	@Autowired
	private OrganizerService organizerService;
	@Autowired
	private DeadlinesService deadlinesService;

}
