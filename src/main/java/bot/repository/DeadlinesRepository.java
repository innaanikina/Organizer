package bot.repository;

import bot.entities.Deadlines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeadlinesRepository extends JpaRepository<Deadlines, Long> {
// здесь писать методы доступа к сущностям
}
