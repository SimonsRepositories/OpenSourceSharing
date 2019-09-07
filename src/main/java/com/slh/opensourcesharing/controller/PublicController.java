package com.slh.opensourcesharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller //Original: @RestController
public class PublicController {

    @RequestMapping(value = "/api/public", method = RequestMethod.GET)
    public String getMessage() {
        return "home";
    }
}