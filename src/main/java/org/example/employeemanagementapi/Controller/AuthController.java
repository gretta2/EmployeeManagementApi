package org.example.employeemanagementapi.Controller;

import jakarta.validation.Valid;
import org.example.employeemanagementapi.Entity.User;
import org.example.employeemanagementapi.Service.JwtService;
import org.example.employeemanagementapi.Service.UserService;
import org.example.employeemanagementapi.dto.AuthResponse;
import org.example.employeemanagementapi.dto.LoginRequest;
import org.example.employeemanagementapi.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ---------------- USER REGISTRATION ----------------
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // raw password here
        user.setEmail(request.getEmail());
        userService.register(user);
        return ResponseEntity.ok(new AuthResponse(null, "User registered successfully!"));
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String token = jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token, "Login successful!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Invalid credentials!"));
        }
    }
}
