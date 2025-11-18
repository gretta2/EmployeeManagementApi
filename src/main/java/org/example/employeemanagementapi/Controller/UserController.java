package org.example.employeemanagementapi.Controller;

import org.example.employeemanagementapi.Entity.User;
import org.example.employeemanagementapi.Security.JwtUtil;
import org.example.employeemanagementapi.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final JwtUtil jwt;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User requestUser){
        User user = service.findByUsername(requestUser.getUsername());
        if(user == null) return "User not found";
        return jwt.generateToken(user.getUsername());
    }
}
