package com.bellamyphan.spring_backend.controller;

import com.bellamyphan.spring_backend.exception.UnauthorizedException;
import com.bellamyphan.spring_backend.model.User;
import com.bellamyphan.spring_backend.repository.UserRepository;
import com.bellamyphan.spring_backend.service.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class); // Logger initialization

    private final JwtUtility jwtUtility; // Inject JwtUtility to generate tokens
    private final UserRepository userRepository; // Inject UserRepository to fetch user details
    private final BCryptPasswordEncoder passwordEncoder; // Inject BCryptPasswordEncoder to validate passwords

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        logger.info("Login attempt for user: {}", user.getUsername());

        // Retrieve user from the database using username
        User userFromDb = userRepository.findByUsernameWithRoles(user.getUsername())
                .orElseThrow(() -> {
                    logger.warn("User not found with username: {}", user.getUsername());
                    return new UsernameNotFoundException("User not found with username: " + user.getUsername());
                });

        // Validate the password using BCrypt password encoder
        if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
            logger.info("User authenticated successfully: {}", user.getUsername());

            // Generate the JWT token with the username
            String token = jwtUtility.generateToken(userFromDb.getUsername(), userFromDb.getRoles());
            logger.debug("Generated JWT Token for user: {}", user.getUsername()); // Logging token generation event

            // Return the token in JSON format
            return Map.of("token", token);
        } else {
            logger.warn("Invalid password for user: {}", user.getUsername());
            throw new UnauthorizedException("Invalid username or password");
        }
    }
}
