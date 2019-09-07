package com.slh.opensourcesharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //Changed from @RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping
    public String getMessage() {
        return "home.jsp";
    }
}