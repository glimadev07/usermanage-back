package br.com.usermanage.usermanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;




@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setDataCriacao(LocalDateTime.now());
        return userRepository.save(user);
    }
    
}
