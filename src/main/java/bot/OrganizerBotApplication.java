package bot;

import bot.entities.BotLogics;
import bot.entities.Deadlines;
import bot.entities.Organizer;
import bot.repository.OrganizerRepository;
import bot.services.BotLogicsService;
import bot.services.DeadlinesService;
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
		botLogics.setUserID(1);

		BotLogics botLogics1 = new BotLogics();
		botLogics1.setStatusActive("STOP");
		botLogics1.setEditTask(2);
		botLogics1.setCurrentCommands("fff");
		botLogics1.setUserID(2);

//		ArrayList<Organizer> a = new ArrayList<Organizer>();
//
//		botLogics.setOrganizer(a);


		ArrayList<BotLogics> list = new ArrayList<BotLogics>();
		list.add(botLogics);
		list.add(botLogics1);
		BotLogics d = list.get(0);

		for(int i = 0; i < 2; i++){
			if(list.get(i).getUserID() == 2)
				System.out.println("yes");
		}
		if(botLogics.getOrganizer() == null){
			System.out.println("no");
		}
		BotLogics botLogics2 = new BotLogics();
		if(botLogics2.getUserID() != 1)
			System.out.println("ok");
		botLogics2 = botLogics;
		if(botLogics2.getUserID() != 1){
			System.out.println("magic");
			System.out.println(botLogics2.getUserID());
			System.out.println(botLogics2.getBotLogicID());
			System.out.println(botLogics.getUserID());
			System.out.println(botLogics.getBotLogicID());
		}


		botLogicsService.createBotLogics(botLogics);
		botLogicsService.createBotLogics(botLogics1);

		Organizer organizer = new Organizer();
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
