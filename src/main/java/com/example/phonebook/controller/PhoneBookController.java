package com.example.phonebook.controller;

import com.example.phonebook.domain.UserService;
import com.example.phonebook.domain.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneBookController {
    @Autowired
    private UserService userList;

    @GetMapping("/entries/{phone}")
    public ResponseEntity<Entry> getEntryByPhone(@PathVariable("phone") String phone) {
        if (phone == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userList.getEntryByPhone(phone), HttpStatus.OK);
    }

    @PostMapping("/entries/users/{id}")
    public ResponseEntity<List<Entry>> updateEntry(@PathVariable("id") String id, @RequestBody Entry entry) {
        if (id == null || entry == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userList.addEntry(id, entry);
        return new ResponseEntity<>(userList.getUser(id).getPhoneBookList(), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<Entry> getEntry(@PathVariable("userId") String userId,
                                          @PathVariable("entryId") String entryId) {
        if (userId == null || entryId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Entry entry = userList.getEntryById(userId, entryId);
        if (entry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<Entry> deleteEntry(@PathVariable("userId") String userId,
                                             @PathVariable("entryId") String entryId) {
        if (userId == null || entryId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userList.deleteEntryById(userId, entryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<List<Entry>> updateUser(@PathVariable("userId") String userId,
                                                  @PathVariable("entryId") String entryId,
                                                  @RequestBody Entry entry) {
        if (userId == null || entryId == null || entry == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userList.updateEntry(userId, entryId, entry);
        return new ResponseEntity<>(userList.getAllEntryOfUser(userId), HttpStatus.OK);
    }
}
