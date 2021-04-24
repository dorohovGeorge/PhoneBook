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
        return new ResponseEntity<>(userList.getEntryByPhone(phone), HttpStatus.OK);
    }

    @PostMapping("/entries/users/{id}")
    public ResponseEntity<List<Entry>> updateEntry(@PathVariable("id") String id, @RequestBody Entry entry) {
        userList.addEntry(id, entry);
        return new ResponseEntity<>(userList.getUser(id).getPhoneBookList(), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<Entry> getEntry(@PathVariable("userId") String userId,
                                          @PathVariable("entryId") String entryId) {
        return new ResponseEntity<>(userList.getEntryById(userId, entryId), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<Entry> deleteEntry(@PathVariable("userId") String userId,
                                             @PathVariable("entryId") String entryId) {
        userList.deleteEntryById(userId, entryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<List<Entry>> updateUser(@PathVariable("userId") String userId,
                                                  @PathVariable("entryId") String entryId,
                                                  @RequestBody Entry entry) {
        userList.updateEntry(userId, entryId, entry);
        return new ResponseEntity<>(userList.getAllEntryOfUser(userId), HttpStatus.OK);
    }
}
