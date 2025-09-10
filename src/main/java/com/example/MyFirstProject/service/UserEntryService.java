package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Repository.UserEntryRepository;
import com.example.MyFirstProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {
    @Autowired
private UserEntryRepository userEntryRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveNewEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }
    public void saveEntry(User user){
        userEntryRepository.save(user);
    }


    public List<User> getAll() {
        return userEntryRepository.findAll();
    }
    public Optional<User> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }
    public User findByUserName(String userName){
        return userEntryRepository.findByUserName(userName);
    }
}
