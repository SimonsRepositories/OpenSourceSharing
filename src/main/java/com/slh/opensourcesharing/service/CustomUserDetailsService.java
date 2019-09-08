package com.slh.opensourcesharing.service;

import com.slh.opensourcesharing.model.User;
import com.slh.opensourcesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = repository.findByUsername(username);
        CustomUserDetails userDetails = null;
        if(user != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user); //important: Install Lombok plugin in File -> Settings -> Plugins. Annotations such as @Getter and @Setter will not suffice.
        } else {
            throw new UsernameNotFoundException("User does not exist with name : " + username);
        }
        return null;
    }
}
