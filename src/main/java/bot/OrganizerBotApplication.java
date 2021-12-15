package bot;

import bot.entities.User;
import bot.entities.Deadlines;
import bot.entities.Organizer;
import bot.services.UserService;
import bot.services.DeadlinesService;
import bot.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class OrganizerBotApplication {

	public static void main(String[] args) throws IOException, TelegramApiException {
		SpringApplication.run(OrganizerBotApplication.class, args);
		Main main = new Main();
		main.main(args);
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

		User user = new User();
		user.setStatusActive("START");
		user.setEditTask(2);
		user.setCurrentCommands("fff");
		user.setUserID((long)751261508);
		userService.createUser(user);

		User user1 = new User();
		user1.setStatusActive("STOP");
		user1.setEditTask(2);
		user1.setCurrentCommands("abc");
		user1.setUserID((long)1726186793);
		userService.createUser(user1);

		User user2 = new User();
		user2.setStatusActive("STOP");
		user2.setEditTask(2);
		user2.setCurrentCommands("abc");
		user2.setUserID((long)232273443);
		userService.createUser(user2);


		ArrayList<Organizer> a = new ArrayList<Organizer>();


		Organizer organizer = new Organizer();
		organizer.setFlag("END");
		organizer.setDate("13.12");
		organizer.setDeadlines(deadlines);
		organizer.setTask("hi");
		organizer.setOrganizeID((long)1);

		a.add(organizer);
		user1.setOrganizer(a);
		organizerService.createOrganizer(organizer);
		userService.createUser(user1);

		userService.updateStatusActiveByUserId( (long)232273443, "izmenen");
		System.out.println("ok");
	}

	@Autowired
	private UserService userService;
	@Autowired
	private OrganizerService organizerService;
	@Autowired
	private DeadlinesService deadlinesService;
}
