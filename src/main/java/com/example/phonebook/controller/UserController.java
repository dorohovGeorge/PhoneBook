package com.example.phonebook.controller;

import com.example.phonebook.domain.UserService;
import com.example.phonebook.domain.User;
import com.example.phonebook.domain.Entry;
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userList.getUser(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> addUser(@RequestBody User user) {
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userList.addUser(user);
        return new ResponseEntity<>(userList.getAllUser(), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        if(id == null || user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userList.updateUser(id, user);
        return new ResponseEntity<>(userList.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userList.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/entries/{id}")
    public ResponseEntity<List<Entry>> getAllEntryOfUser(@PathVariable("id") String id) {
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userList.getAllEntryOfUser(id), HttpStatus.OK);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userList.getUserByName(name), HttpStatus.OK);
    }
}
