package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.Repository.UserEntryRepository;
import com.example.MyFirstProject.api.response.WeatherResponse;
import com.example.MyFirstProject.entity.User;
import com.example.MyFirstProject.service.UserEntryService;
import com.example.MyFirstProject.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        User user = userEntryService.findByUserName(userName);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userEntryService.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity<>("❌ User already exists!", HttpStatus.BAD_REQUEST);
        }
        userEntryService.saveNewEntry(user); // will hash password + set role
        return new ResponseEntity<>("✅ User registered successfully!", HttpStatus.CREATED);
    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb  = userEntryService.findByUserName(userName);

            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewEntry(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteJournalEntryById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("patna");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = "The weather in Patna is " + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("hello " + authentication.getName()+greeting ,HttpStatus.OK);
    }





}
