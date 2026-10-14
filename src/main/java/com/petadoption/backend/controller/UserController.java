package com.petadoption.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petadoption.backend.entity.User;
import com.petadoption.backend.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    // Login user
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {

        List<User> users = userRepository.findAll();

        for (User existingUser : users) {

            if (existingUser.getEmail().equals(user.getEmail())
                && existingUser.getPassword().equals(user.getPassword())) {

            return existingUser;
        }
    }

    return null;
}

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}