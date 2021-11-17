package bot.repository;

import bot.entities.BotLogics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotLogicsRepository extends JpaRepository<BotLogics, Long> {
// здесь писать методы доступа к сущностям
    @Override
    Optional<BotLogics> findById(Long botLogicId);
}
