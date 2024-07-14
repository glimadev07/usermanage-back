package br.com.usermanage.usermanage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.usermanage.usermanage.dto.LoginRequestDTO;
import br.com.usermanage.usermanage.entity.User;
import java.util.Optional;

/**
 * @author gilberto.lima
 */
@Service
public interface UserService {

    public Page<User> getAllUsers(Pageable pageable);

    public Optional<User> getUserById(Long id);

    public String updateUser(Long id, User userDetails);

    public String deleteUsuario(Long id);

    public User createUser(User user);

    public Optional<User> loginUser(LoginRequestDTO loginRequest);
}
