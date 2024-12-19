package com.uetravel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartingController {
    @GetMapping("/")
    public String getStartingPage() {
        return "Starting";
    }
}
