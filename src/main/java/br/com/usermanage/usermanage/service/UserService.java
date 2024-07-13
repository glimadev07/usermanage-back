package br.com.usermanage.usermanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;




/**
 * @author gilberto.lima
 */
@Service
public interface UserService {


    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public User updateUser(Long id, User userDetails);

    public void deleteUsuario(Long id);

    public User createUser(User user) ;
    
}
