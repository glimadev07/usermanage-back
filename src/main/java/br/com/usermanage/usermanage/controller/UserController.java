package br.com.usermanage.usermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.service.UserService;

import java.util.List;


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

    
}
