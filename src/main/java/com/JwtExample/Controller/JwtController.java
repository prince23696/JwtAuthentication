package com.JwtExample.Controller;

import com.JwtExample.Entity.User;
import com.JwtExample.Entity.UserResponse;
import com.JwtExample.Helper.JwtUtil;
import com.JwtExample.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody User user) throws Exception {
        System.out.println(user);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        UserDetails userDetails = this.userService.loadUserByUsername(user.getEmail());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT : "  + token);
        return ResponseEntity.ok(new UserResponse(token));
    }
}