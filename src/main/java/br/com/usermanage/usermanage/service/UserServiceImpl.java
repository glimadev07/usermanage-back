package br.com.usermanage.usermanage.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.repository.UserRepository;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        user.setDataCriacao(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setNome(userDetails.getNome());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setSenha(userDetails.getSenha());
        user.setAtivo(userDetails.isAtivo());
        user.setTelefone(userDetails.getTelefone());
        user.setEndereco(userDetails.getEndereco());
        return userRepository.save(user);
    }

    @Override
    public void deleteUsuario(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    // @Override
    // public Optional<User> findByUsername(String username) {
    //     return userRepository.findByUsername(username);
    // }
}
