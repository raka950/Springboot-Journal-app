package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.entity.User;
import com.example.MyFirstProject.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "ok";
    }
    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userEntryService.saveEntry(user);
    }
}
