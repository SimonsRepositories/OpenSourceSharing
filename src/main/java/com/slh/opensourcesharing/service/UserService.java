package com.slh.opensourcesharing.service;
import com.slh.opensourcesharing.model.User;
public interface UserService
{
    public void saveUser(User user);
    public boolean isUserAlreadyPresent(User user);
}
