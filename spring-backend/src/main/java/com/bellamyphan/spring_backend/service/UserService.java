package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.exception.MissingRoleException;
import com.bellamyphan.spring_backend.model.Role;
import com.bellamyphan.spring_backend.model.User;
import com.bellamyphan.spring_backend.repository.RoleRepository;
import com.bellamyphan.spring_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${default.admin.username}")
    private String defaultAdminUsername;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;
    @Value("${default.admin.firstname}")
    private String defaultAdminFirstname;
    @Value("${default.admin.lastname}")
    private String defaultAdminLastname;

    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
    }

    @Transactional
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already registered by another user");
        }
        // Encode the password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // Assign default role
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Default role not found"));
        user.setRoles(new HashSet<>(List.of(userRole)));
        // Save the user to the database
        return userRepository.save(user);
    }

    @Transactional
    public void createFirstUser() {
        if (userRepository.findByUsername(defaultAdminUsername).isEmpty()) {
            // Encode the admin password
            String encodedPassword = bCryptPasswordEncoder.encode(defaultAdminPassword);
            logger.debug("Encoding password for admin user: {}", defaultAdminUsername);
            // Check if ROLE_ADMIN, ROLE_USER exist, else throw exceptions
            Role adminRole = getRoleOrThrow("ROLE_ADMIN");
            Role userRole = getRoleOrThrow("ROLE_USER");
            // Create the user and assign the role
            User adminUser = new User();
            adminUser.setFirstName(defaultAdminFirstname);
            adminUser.setLastName(defaultAdminLastname);
            adminUser.setUsername(defaultAdminUsername);
            adminUser.setPassword(encodedPassword);
            adminUser.setRoles(new HashSet<>(List.of(adminRole, userRole)));
            // Save the user
            userRepository.save(adminUser);
            logger.info("Admin user created with username: {}", defaultAdminUsername);
        } else {
            logger.info("Admin user with username '{}' already exists. Skipping creation.", defaultAdminUsername);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    // Convert roles to authorities in the format required by Spring Security
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    private Role getRoleOrThrow(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> {
                    logger.error("Missing required role: {}", roleName);
                    return new MissingRoleException(roleName + " does not exist in the database");
                });
    }
}
