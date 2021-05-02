package com.example.phonebook.controller;

import com.example.phonebook.domain.UserService;
import com.example.phonebook.domain.User;
import com.example.phonebook.domain.Entry;
import com.example.phonebook.exceptions.ApiRequestException;
import com.example.phonebook.exceptions.NotFoundRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userList;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to PhoneBook Services.";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = this.userList.getAllUser();
        if(userList.isEmpty()) {
            throw new NotFoundRequestException("User list is empty");
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        if(id == null) {
            throw new ApiRequestException("User id is null") ;
        }
        User user = userList.getUser(id);
        if(user == null) {
            throw new NotFoundRequestException("User is null");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> addUser(@RequestBody User user) {
        if(user == null) {
            throw new ApiRequestException("User is null") ;
        }
        userList.addUser(user);
        return new ResponseEntity<>(userList.getAllUser(), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        if(id == null || user == null) {
            throw new ApiRequestException("User or user id is null") ;
        }
        userList.updateUser(id, user);
        return new ResponseEntity<>(userList.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        if(id == null) {
            throw new ApiRequestException("User id is null") ;
        }
        userList.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/entries/{id}")
    public ResponseEntity<List<Entry>> getAllEntryOfUser(@PathVariable("id") String id) {
        if(id == null) {
            throw new ApiRequestException("User id is null") ;
        }
        return new ResponseEntity<>(userList.getAllEntryOfUser(id), HttpStatus.OK);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
        if(name == null) {
            throw new ApiRequestException("User name is null") ;
        }
        return new ResponseEntity<>(userList.getUserByName(name), HttpStatus.OK);
    }
}
