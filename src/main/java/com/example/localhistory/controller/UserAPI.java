package com.example.localhistory.controller;

import com.example.localhistory.model.User;
import com.example.localhistory.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")  // Allow React app to interact with Spring Boot
public class UserAPI {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("add/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User user1 = userRepo.save(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/User")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> userList = userRepo.findAll();
            if (userList.isEmpty()) {
                return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("Delete/user{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        try {
            userRepo.deleteById(userId);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("Update/user{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User updateUser) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserId(updateUser.getUserId());
            user.setUserName(updateUser.getUserName());
            user.setAddress(updateUser.getAddress());
            user.setEmail(updateUser.getEmail());
            user.setPhonenumber(updateUser.getPhonenumber());
            user.setPassword(updateUser.getPassword());
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getuser{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        try {
            Optional<User> optionalUser = userRepo.findById(userId);
            if (optionalUser.isPresent()) {
                return new ResponseEntity<>(optionalUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
