package com.JwtExample.Services;

import com.JwtExample.CustomException.BusinessException;
import com.JwtExample.CustomException.EmptyInputException;
import com.JwtExample.Entity.User;
import com.JwtExample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public User saveUser(User user) {

        if (user.getEmail().isEmpty() || user.getName().isEmpty())
            throw new EmptyInputException();
        // try {
        return userRepository.save(user);
       /* } catch (IllegalArgumentException e) {
            throw new BusinessException("404", "User is Null :-" + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("602", "Something not appropriate :-" + e.getMessage());
        }*/

    }

    @Override
    public List<User> getAllUsers() {

        try {
            List<User> list = userRepository.findAll();
            if (list.isEmpty())
                throw new BusinessException("603", "List Of User Empty Nothing to Display");
            return list;
        } catch (Exception e) {
            throw new BusinessException("604", "Something not appropriate while fetching all User :-" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getUser(int id) {
        /*   try {
         */
        if (isValidUser(id))
            return new ResponseEntity<User>(userRepository.findById(id).get(), HttpStatus.OK);
        else if (!isValidUser(id) && userRepository.findById(id).get() != null)
            throw new IllegalArgumentException();
        else
            return new ResponseEntity<String>("You Don't Have Correct Right To Access, Please Send Some Id To Be Searched.", HttpStatus.NOT_FOUND);
        //     else throw new Exception();
     /*   } catch (IllegalArgumentException e) {
            throw new BusinessException("605", "given user id is null, please send some id to be searched :-" + e.getMessage());
        } catch (java.util.NoSuchElementException e) {
            throw new BusinessException("606", "given user id does not exist in DB :-" + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("607", "Something went wrong in Service layer while fetching user :-" + e.getMessage());
        }*/
    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        //   try {
        if (isValidUser(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.OK);
        } else if (!isValidUser(id) && userRepository.findById(id).get() == null)
            return new ResponseEntity<String>("User Does Not Exists In DB", HttpStatus.OK);
        else
            return new ResponseEntity<String>("You Don't Have Correct Right To Access, Please Send Some Id To Be Searched.", HttpStatus.OK);
     /*   } catch (IllegalArgumentException e) {
            throw new BusinessException("608", " User Not Exists Not to delete :-" + e.getMessage());

        } catch (Exception e) {
            throw new BusinessException("609", "Something went wrong in Service layer while deleting :-" + e.getMessage());
        }
*/
    }

    @Override
    public String updateUser(int id, User user) {

        if (isValidUser(id)) {
            User user1 = userRepository.findById(id).get();
            user1.setEmail(user.getEmail());
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            userRepository.save(user1);
            return "User Update Successfully";
        } else
            return "You Don't Have Correct Right To Access, Please Send Some Id To Be Searched.";
    }

    @Override
    public boolean isValidUser(int id) {
        String username;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) auth.getPrincipal()).getUsername();
            if (username != null) {
                User user = userRepository.findByEmail(username);
                if (user != null && user.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}