package bot.repository;

import bot.entities.Organizers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizers, Long> {
// здесь писать методы доступа к сущностям
}
