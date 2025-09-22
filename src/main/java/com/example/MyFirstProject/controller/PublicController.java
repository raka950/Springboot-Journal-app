package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.Utils.JwtUtil;
import com.example.MyFirstProject.entity.User;
import com.example.MyFirstProject.service.UserEntryService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "ok";
    }
    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userEntryService.saveNewEntry(user);
    }
    @PostMapping("/login")
    public void login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userEntryService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("❌ Login failed!");
        }
    }
}
