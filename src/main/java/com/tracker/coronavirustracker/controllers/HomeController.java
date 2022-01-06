package com.tracker.coronavirustracker.controllers;

import com.tracker.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//using @Controller rather than @RestController, because we will be returning an UI, not rest responses
@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronavirusDataService.getAllStats());
        return "home";
    }

}
