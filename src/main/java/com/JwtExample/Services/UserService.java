package com.JwtExample.Services;

import com.JwtExample.Entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User saveUser(User user);

    public List<User> getAllUsers();

    public ResponseEntity<?> getUser(int id);

    public ResponseEntity<String> deleteUser(int id);

    public String updateUser(int id, User user);

    public boolean isValidUser(int id);
}