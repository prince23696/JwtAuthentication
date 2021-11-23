package com.JwtExample.Controller;

import com.JwtExample.Entity.User;
import com.JwtExample.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        //  try {
        User user1 = userService.saveUser(user);
        return new ResponseEntity<User>(user1, HttpStatus.OK);
      /*  } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("611", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        }*/
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {

       /* if (!userService.isValidUser(id)) {
            System.out.println("User Have Not Valid Credentials");
            String s = ("User Have Not Valid Credentials");
            return new ResponseEntity<String>(s, HttpStatus.OK);

        }*/
        return userService.getUser(id);
        /*   try {
                ResponseEntity<?> user = userService.getUser(id);
                return user;
           } catch (BusinessException e) {
                ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
                return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                ControllerException ce = new ControllerException("612", "Something went wrong in controller");
                return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
            }
*/
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id) {
     /*   if (!userService.isValidUser(id)) {
            System.out.println("User Have Not Valid Credentials");
        }*/
        return userService.deleteUser(id);
    }

    @PutMapping("/updatePassword/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        if (!userService.isValidUser(id)) {
            System.out.println("User Have Not Valid Credentials ");
        }
        boolean validUser = userService.isValidUser(id);
        System.out.println("User Have Valid Credentials :- " + validUser);
        return userService.updateUser(id, user);
    }


}