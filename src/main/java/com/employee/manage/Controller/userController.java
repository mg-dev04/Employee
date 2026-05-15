package com.employee.manage.Controller;

import com.employee.manage.DTO.loginDto;
import com.employee.manage.Model.userCredentials;
import com.employee.manage.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody userCredentials data) {
        Boolean created = userService.createUser(data);
        if (!created) {
            return new ResponseEntity<>(Map.of("message", "Username or email already exists"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Map.of("message", "Account created successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginDto loginData) {
        String result = userService.userLogin(loginData);

        if (result.equals("User not found")) {
            return new ResponseEntity<>(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
        }
        if (result.equals("Invalid password")) {
            return new ResponseEntity<>(Map.of("message", "Invalid password"), HttpStatus.UNAUTHORIZED);
        }

        // result is the JWT token
        return new ResponseEntity<>(Map.of("token", result), HttpStatus.OK);
    }
}