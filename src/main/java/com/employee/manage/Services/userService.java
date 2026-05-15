package com.employee.manage.Services;

import com.employee.manage.DTO.loginDto;
import com.employee.manage.Model.userCredentials;
import com.employee.manage.Repo.userRepo;
import com.employee.manage.Utils.JwtTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private userRepo userrepo;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Boolean createUser(userCredentials data) {
        if (userrepo.findByUsername(data.getUsername()) != null) {
            return false; // username already taken
        }
        if (userrepo.findByEmail(data.getEmail()) != null) {
            return false; // email already registered
        }
        data.setPassword(passwordEncoder.encode(data.getPassword()));
        userrepo.save(data);
        return true;
    }

    public String userLogin(loginDto data) {
        userCredentials user = userrepo.findByUsername(data.username);

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(data.password, user.getPassword())) {
            return "Invalid password";
        }

        return jwtTokenizer.Tokenizer(user.getUsername());
    }
}
