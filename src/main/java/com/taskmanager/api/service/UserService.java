package com.taskmanager.api.service;

import com.taskmanager.api.dto.UserAuthRequestDTO;
import com.taskmanager.api.entity.User;
import com.taskmanager.api.repository.UserRepository;
import com.taskmanager.api.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired  // Inject BcryptPasswordEncoder bean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public void registerUser(UserAuthRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DataIntegrityViolationException("Username already exists.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    public String loginUser(UserAuthRequestDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtTokenUtil.generateToken(user);
    }
}
