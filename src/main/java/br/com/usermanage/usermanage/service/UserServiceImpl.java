package br.com.usermanage.usermanage.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.usermanage.usermanage.dto.LoginRequestDTO;
import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.exception.UserNotFoundException;
import br.com.usermanage.usermanage.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author gilberto.lima
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        user.setDataCriacao(LocalDateTime.now());
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    @Override
    public String updateUser(Long id, User userDetails) {
        User user = findUserById(id);
        updateUserData(user, userDetails);
        userRepository.save(user);
        return "Usuário " + id + " atualizado com sucesso";
    }

    @Override
    public String deleteUsuario(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
        return "Usuário " + id + " deletado com sucesso";
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    private void updateUserData(User user, User userDetails) {
        user.setNome(userDetails.getNome());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setSenha(userDetails.getSenha());
        user.setAtivo(userDetails.getAtivo());
        user.setTelefone(userDetails.getTelefone());
        user.setEndereco(userDetails.getEndereco());
        user.setIsMaster(userDetails.getIsMaster());
    }

    @Override
    public Optional<User> loginUser(LoginRequestDTO loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getSenha(), user.get().getSenha())) {
            return user;
        }
        return Optional.empty();
    }
}
