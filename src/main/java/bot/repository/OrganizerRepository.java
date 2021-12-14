package bot.repository;

import bot.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
// здесь писать методы доступа к сущностям
}
