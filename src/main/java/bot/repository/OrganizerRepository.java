package bot.repository;

import bot.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
// здесь писать методы доступа к сущностям
}
