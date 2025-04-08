package com.taskmanager.api.controller;

import com.taskmanager.api.dto.JwtAuthResponseDTO;
import com.taskmanager.api.dto.UserAuthRequestDTO;
import com.taskmanager.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAuthRequestDTO dto) {
        try {
            userService.registerUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody UserAuthRequestDTO dto) {
        try {
            JwtAuthResponseDTO authResponse = userService.loginUser(dto);
            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtAuthResponseDTO(e.getMessage()));
        }
    }

}
