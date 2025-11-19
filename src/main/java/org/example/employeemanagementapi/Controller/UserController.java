package org.example.employeemanagementapi.Controller;

import org.example.employeemanagementapi.Entity.User;
import org.example.employeemanagementapi.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---------------- Update password ----------------
    @PutMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.updatePassword(user, newPassword);
        return ResponseEntity.ok("Password updated successfully!");
    }

    // ---------------- Delete user ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) return ResponseEntity.ok("User deleted successfully!");
        return ResponseEntity.notFound().build();
    }

    // ---------------- Get all users ----------------
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
