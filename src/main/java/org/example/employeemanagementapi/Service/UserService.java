package org.example.employeemanagementapi.Service;

import org.example.employeemanagementapi.Entity.User;
import org.example.employeemanagementapi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return repo.save(user);
    }

    public User findByUsername(String username){
        return repo.findByUsername(username).orElse(null);
    }
}
