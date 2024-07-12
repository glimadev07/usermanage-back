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

    public void deleteUsuario(Long id) {
        userRepository.deleteById(id);
    }
    
}
