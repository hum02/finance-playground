package com.openbank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping()
    public String login() {
        return "login";
    }
}
