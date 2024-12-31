package com.example.anchieta_system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.anchieta_system.Entity.User;
import com.example.anchieta_system.service.UserService;
import com.example.anchieta_system.dto.LoginResponse;

import java.util.List;

/**
 * Controlador responsável por operações de autenticação e gerenciamento de usuários.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // URL do frontend
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Autentica um usuário com base nas credenciais fornecidas.
     *
     * @param loginRequest Objeto contendo email e senha do usuário.
     * @return ResponseEntity com token de autenticação ou mensagem de erro.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            
            if (user != null) {
                return ResponseEntity.ok(new LoginResponse("token-exemplo", user.getEmail()));
            }
            
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Verifica se um usuário existe com base no email fornecido.
     *
     * @param email Email do usuário a ser verificado.
     * @return ResponseEntity com true se o usuário existir, false caso contrário.
     */
    @GetMapping("/check/{email}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable String email) {
        try {
            boolean exists = userService.userExists(email);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Registra um novo usuário no sistema.
     *
     * @param user Objeto User contendo as informações do novo usuário.
     * @return ResponseEntity com o usuário criado ou mensagem de erro.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Retorna uma lista de todos os usuários registrados.
     *
     * @return ResponseEntity com a lista de usuários.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param id ID do usuário a ser atualizado.
     * @param user Objeto User contendo as novas informações do usuário.
     * @return ResponseEntity com o usuário atualizado ou mensagem de erro.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Exclui um usuário com base no ID fornecido.
     *
     * @param id ID do usuário a ser excluído.
     * @return ResponseEntity com status de sucesso ou mensagem de erro.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuário excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

class LoginRequest {
    private String email;
    private String password;

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
