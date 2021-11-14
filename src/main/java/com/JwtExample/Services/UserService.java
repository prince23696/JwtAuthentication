package com.JwtExample.Services;

import com.JwtExample.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User saveUser(User user);

    public List<User> getAllUsers();

    public User getUser(int id);

    public String deleteUser(int id);

    public String updateUser(int id, User user);

}
