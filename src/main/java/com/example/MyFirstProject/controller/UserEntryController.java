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

    /** Get a user by username **/
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        User user = userEntryService.findByUserName(userName);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** Sign up a new user **/
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userEntryService.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity<>("❌ User already exists!", HttpStatus.BAD_REQUEST);
        }
        userEntryService.saveNewEntry(user); // hashes password + sets role
        return new ResponseEntity<>("✅ User registered successfully!", HttpStatus.CREATED);
    }

    /** Update current logged-in user **/
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userEntryService.findByUserName(userName);

        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("❌ User not found", HttpStatus.NOT_FOUND);
    }

    /** Delete current logged-in user **/
    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** Greeting with weather info **/
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        WeatherResponse weatherResponse = weatherService.getWeather("Patna");

        String greeting;
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting = "Hello " + username + "! The weather in Patna is " +
                    weatherResponse.getCurrent().getFeelslike();
        } else {
            greeting = "Hello " + username + "! Weather information is not available.";
        }

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
