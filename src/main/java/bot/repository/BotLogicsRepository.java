package bot.repository;

import bot.entities.BotLogics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BotLogicsRepository extends JpaRepository<BotLogics, Long> {
// здесь писать методы доступа к сущностям


}
