package br.com.usermanage.usermanage.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.usermanage.usermanage.dto.LoginRequestDTO;
import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.response.Response;
import br.com.usermanage.usermanage.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author gilberto.lima
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Response<Map<String, Object>>> getAllUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable paging = PageRequest.of(page, size, Sort.by("id").descending());
            Page<User> pageUsers = userService.getAllUsers(paging);

            Map<String, Object> response = new HashMap<>();
            response.put("data", pageUsers.getContent());
            response.put("totalRecords", pageUsers.getTotalElements());

            return Response.successResponse(response);
        } catch (Exception e) {
            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<User>> getUserById(@PathVariable Long id) {
        try {
            Optional<User> usuario = userService.getUserById(id);
            return usuario.map(Response::successResponse)
                    .orElseGet(() -> Response.errorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                            "Usuário não encontrado"));
        } catch (Exception e) {
            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Response<User>> createUsuario(@RequestBody User usuario) {
        try {
            User createdUser = userService.createUser(usuario);
            return Response.successResponse(createdUser);
        } catch (Exception e) {
            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateUsuario(@PathVariable Long id, @RequestBody User usuarioDetails) {
        try {
            String result = userService.updateUser(id, usuarioDetails);
            return Response.successResponse(result);
        } catch (Exception e) {
            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteUsuario(@PathVariable Long id) {
        try {
            String result = userService.deleteUsuario(id);
            return Response.successResponse(result);
        } catch (Exception e) {
            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response<User>> loginUsuario(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Optional<User> user = userService.loginUser(loginRequest);
            return user.map(Response::successResponse)
                    .orElseGet(() -> Response.errorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED,
                            "Credenciais inválidas"));
        } catch (Exception e) {
            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

}
