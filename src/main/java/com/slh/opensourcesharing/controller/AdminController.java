package com.slh.opensourcesharing.controller;

import com.slh.opensourcesharing.model.User;
import com.slh.opensourcesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/secure/rest")
public class AdminController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //trying to get data from json (working)
    //-----------------------------------------------------------
    //@PreAuthorize("hasAnyRole('ADMIN')")
    /*@PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody User user)
    {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "user added successfully...";
    }*/

    //trying to get data from form
    //------------------------------------------------------------
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/admin/add")
    public String addUserByAdmin(@ModelAttribute User user)
    {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "user added successfully...";
    }
}
