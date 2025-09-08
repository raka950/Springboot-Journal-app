package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Repository.UserEntryRepository;
import com.example.MyFirstProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
private UserEntryRepository userEntryRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntryRepository.findByUserName(username);
        if (username != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRole().toArray(new String[0])).build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username: "+ username);
    }
}
