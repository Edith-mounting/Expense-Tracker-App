package com.pairlearning.expensetracker.controller;

import com.pairlearning.expensetracker.request.LoginRequest;
import com.pairlearning.expensetracker.request.UserRequest;
import com.pairlearning.expensetracker.response.UserResponse;
import com.pairlearning.expensetracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody LoginRequest loginRequest){
        return userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public Map<String,String> registerUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        return userService.registerUser(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(),userRequest.getPassword());
    }
}
