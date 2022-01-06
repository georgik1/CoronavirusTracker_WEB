package com.tracker.coronavirustracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//using @Controller rather than @RestController, because we will be returning an UI, not rest responses
@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("testName", "TEST1");
        return "home";
    }

}
