package bot.services;

import bot.entities.Organizer;
import bot.entities.User;
import bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public User findById(Long botLogicId){
        return userRepository.findById(botLogicId).orElse(null);
    }

    public ArrayList<User> getAll(){return (ArrayList<User>) userRepository.findAll();}



    @Transactional
    public void updateStatusActiveByUserId(Long userID, String statusActive){
        userRepository.updateStatusActiveByUserId(userID, statusActive);
        //менять команды одновременно?
    }

    @Transactional
    public void updateCurrentCommandsByUserId(Long userID, String currentCommands){
        userRepository.updateCurrentCommandsByUserId(userID, currentCommands);
    }

    @Transactional
    public void updateEditTaskByUserId(Long userID, int editTask){
        userRepository.updateEditTaskByUserId(userID, editTask);
    }

    @Transactional
    public void updateOrganizerByUserId(Long userID, List<Organizer> organizers){
        userRepository.updateOrganizerByUserId(userID, organizers);
    }
}
