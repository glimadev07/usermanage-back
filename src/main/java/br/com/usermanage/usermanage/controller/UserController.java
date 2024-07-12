package br.com.usermanage.usermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.service.UserService;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    private UserService usuarioService;

    public UserController(UserService usuarioService){
        this.usuarioService = this.usuarioService;
    }

    @GetMapping
    public List<User> getAllUsuarios() {
        return usuarioService.getAllUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
}
