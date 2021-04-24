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
        return new ResponseEntity<>(userList.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        return new ResponseEntity<>(userList.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> addUser(@RequestBody User user) {
        userList.addUser(user);
        return new ResponseEntity<>(userList.getAllUser(), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        userList.updateUser(id, user);
        return new ResponseEntity<>(userList.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        userList.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/entries/{id}")
    public ResponseEntity<List<Entry>> getAllEntryOfUser(@PathVariable("id") String id) {
        return new ResponseEntity<>(userList.getAllEntryOfUser(id), HttpStatus.OK);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String phone) {
        return new ResponseEntity<>(userList.getUserByName(phone), HttpStatus.OK);
    }
}
