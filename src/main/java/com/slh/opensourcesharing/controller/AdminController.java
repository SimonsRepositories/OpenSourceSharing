package com.slh.opensourcesharing.controller;

import com.slh.opensourcesharing.model.User;
import com.slh.opensourcesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/rest")
public class AdminController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/admin/add")
    public String addUserBAdmin(@RequestBody User user)
    {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "user added successfully...";
    }
}
