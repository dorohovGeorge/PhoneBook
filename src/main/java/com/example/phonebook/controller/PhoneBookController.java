package com.example.phonebook.controller;

import com.example.phonebook.domain.UserService;
import com.example.phonebook.domain.Entry;
import com.example.phonebook.exceptions.ApiRequestException;
import com.example.phonebook.exceptions.NotFoundRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class PhoneBookController {
    @Autowired
    private UserService userList;
    private static Logger logger = Logger.getLogger(PhoneBookController.class.getName());
    @GetMapping("/entries/{phone}")
    public ResponseEntity<Entry> getEntryByPhone(@PathVariable("phone") String phone) {
        if (phone == null) {
            throw new ApiRequestException("User phone is null") ;
        }
        return new ResponseEntity<>(userList.getEntryByPhone(phone), HttpStatus.OK);
    }

    @PostMapping("/entries/users/{id}")
    public ResponseEntity<List<Entry>> updateEntry(@PathVariable("id") String id, @RequestBody Entry entry) {
        if (id == null || entry == null) {
            throw new ApiRequestException("User id or entry is null") ;
        }
        userList.addEntry(id, entry);
        return new ResponseEntity<>(userList.getUser(id).getPhoneBookList(), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<Entry> getEntry(@PathVariable("userId") String userId,
                                          @PathVariable("entryId") String entryId) {
        if (userId == null || entryId == null) {
            throw new ApiRequestException("User id or entry id is null") ;
        }
        Entry entry = userList.getEntryById(userId, entryId);
        if (entry == null) {
            throw new NotFoundRequestException("Entry is null");
        }
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<Entry> deleteEntry(@PathVariable("userId") String userId,
                                             @PathVariable("entryId") String entryId) {
        if (userId == null || entryId == null) {
            throw new ApiRequestException("User id or entry id is null") ;
        }
        userList.deleteEntryById(userId, entryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{userId}/entries/{entryId}")
    public ResponseEntity<List<Entry>> updateUser(@PathVariable("userId") String userId,
                                                  @PathVariable("entryId") String entryId,
                                                  @RequestBody Entry entry) {
        if (userId == null || entryId == null || entry == null) {
            throw new ApiRequestException("User id  or entry id or entry is null") ;
        }
        userList.updateEntry(userId, entryId, entry);
        return new ResponseEntity<>(userList.getAllEntryOfUser(userId), HttpStatus.OK);
    }
}
