package br.com.usermanage.usermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = this.userService;
    }

    @GetMapping
    public List<User> getAllUsuarios() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> usuario = userService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUsuario(@RequestBody User usuario) {
        return userService.createUser(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUsuario(@PathVariable Long id, @RequestBody User usuarioDetails) {
        return ResponseEntity.ok(userService.updateUser(id, usuarioDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        userService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
