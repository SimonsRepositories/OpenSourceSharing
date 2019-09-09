package com.slh.opensourcesharing.service;

import com.slh.opensourcesharing.model.Role;
import com.slh.opensourcesharing.model.User;
import com.slh.opensourcesharing.repository.RoleRepository;
import com.slh.opensourcesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
    public BCryptPasswordEncoder encoder;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        //try to implement this method, as assignment
        return false;
    }
}
