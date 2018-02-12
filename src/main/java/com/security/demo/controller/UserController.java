package com.security.demo.controller;

import com.security.demo.model.User;
import com.security.demo.model.UserRegistrationRequest;
import com.security.demo.model.UserUpdateRequest;
import com.security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody UserRegistrationRequest u) {
        return userRepository.register(u);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody UserUpdateRequest u,
                           Principal authenticatedUser) {
        return userRepository.update(
                                    u,
                                    authenticatedUser.getName()
        );
    }
}
