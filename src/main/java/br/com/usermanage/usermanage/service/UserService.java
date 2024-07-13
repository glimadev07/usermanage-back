package br.com.usermanage.usermanage.service;

import java.util.List;
import java.util.Optional;

import br.com.usermanage.usermanage.entity.User;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUsuario(Long id);
    Optional<User> findByUsername(String username);
}
