package com.slh.opensourcesharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController
{
    @RequestMapping("/create-account")
    public String showCreateAccountPage() {
        return "home";
    }
}
