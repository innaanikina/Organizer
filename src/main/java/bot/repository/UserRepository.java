package bot.repository;

import bot.entities.Organizer;
import bot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
// здесь писать методы доступа к сущностям
    @Override
    Optional<User> findById(Long user);

   /* @Query("update user set statusActive = :statusActive where userID = :id")
    void updateStatusActiveById(@Param("id") Long id, @Param("statusActive") String statusActive);
*/
    @Transactional
    @Modifying
    @Query("UPDATE User c SET c.statusActive = :statusActive WHERE c.userID = :userID")
    public void updateStatusActiveByUserId(@Param("userID") Long userID, @Param("statusActive") String statusActive);

    @Transactional
    @Modifying
    @Query("UPDATE User c SET c.currentCommands = :currentCommands WHERE c.userID = :userID")
    public void updateCurrentCommandsByUserId(@Param("userID") Long userID, @Param("currentCommands") String currentCommands);

    @Transactional
    @Modifying
    @Query("UPDATE User c SET c.editTask = :editTask WHERE c.userID = :userID")
    public void updateEditTaskByUserId(@Param("userID") Long userID, @Param("editTask") int editTask);

    @Transactional
    @Modifying
    @Query("UPDATE User c SET c.organizer = :organizer WHERE c.userID = :userID")
    public void updateOrganizerByUserId(@Param("userID") Long userID, @Param("organizer") List<Organizer> organizer);
}
