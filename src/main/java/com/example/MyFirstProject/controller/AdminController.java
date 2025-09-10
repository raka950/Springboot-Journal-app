package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.entity.User;
import com.example.MyFirstProject.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> all = userEntryService.getAll();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    @PostMapping("create-admin-user")
    public void createUser(@RequestBody User user){
        userEntryService.saveAdmin(user);
    }


}
