package org.example.employeemanagementapi.Service;

import org.example.employeemanagementapi.Entity.User;
import org.example.employeemanagementapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // encode here
        user.setRole("ROLE_USER");
        return repo.save(user);
    }

    public User registerAdmin(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // encode here
        user.setRole("ROLE_ADMIN");
        return repo.save(user);
    }

    public User findByUsername(String username){
        return repo.findByUsername(username).orElse(null);
    }

    public User findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    public void updatePassword(User user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        repo.save(user); // save the user after updating password
    }
    public void updateUser(User user) {
        repo.save(user);
    }
    public java.util.List<User> getAllUsers() {
        java.util.List<User> users = repo.findAll();
        System.out.println("Found " + users.size() + " users");
        users.forEach(user -> {
            System.out.println("User: " + user.getUsername());
            user.setPassword(null); // Don't expose passwords
        });
        return users;
    }

    public boolean deleteUser(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }


}
