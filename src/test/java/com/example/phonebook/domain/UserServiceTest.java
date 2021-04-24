package com.example.phonebook.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @MockBean
    UserService userService = new UserService();

    @Test
    void testGetAllUser() {
        User user = new User(4, "54653", "Knight");
        List<User> userList = userService.getAllUser();
        userList.add(user);
        userService.addUser(user);
        assertEquals(userList, userService.getAllUser());
    }

    @Test
    void testGetUser() {
        User user = new User(1, "44444", "Thomas");

        userService.userList = new ArrayList<>();
        userService.addUser(user);

        assertEquals(userService.getUser("1"), new User(1, "44444", "Thomas"));
    }

    @Test
    void testAddUser() {
        User user = new User(1, "44444", "Tim");


        userService.addUser(user);

        assertEquals(1, user.getId());
        assertEquals("44444", user.getUserPhone());
        assertEquals("Tim", user.getUserName());
    }

    @Test
    void testGetEntryByPhone() {
        Entry entry = new Entry(4, "George", "99999");

        userService.addEntry("1", entry);

        assertEquals(new Entry(4, "George", "99999"),
                userService.getEntryByPhone(entry.getNumber()));
    }

    @Test
    void testGetAllEntryOfUser() {
        Entry entry = new Entry(4, "George", "99999");
        List<Entry> entryList = userService.getAllEntryOfUser("1");

        userService.addEntry("1", entry);
        entryList.add(entry);

        assertEquals(entryList, userService.getAllEntryOfUser("1"));
    }

    @Test
    void testUpdateUser() {
        User user = userService.getUser("1");
        user.setUserName("Ralf");
        user.setUserPhone("31231");

        userService.updateUser("1", user);

        assertEquals(user, userService.getUser("1"));
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser("1");
        assertTrue(userService.getAllUser().isEmpty());
    }

    @Test
    void testAddEntry() {
        Entry entry = new Entry(4, "Ralf", "90900");
        int sizeBeforeAdd = userService.userList.get(0).getPhoneBookList().size();
        userService.addEntry("1", entry);
        assertEquals(++sizeBeforeAdd, userService.userList.get(0).getPhoneBookList().size());
    }

    @Test
    void testGetEntryById() {
        Entry entry = new Entry(4, "Ralf", "90900");
        userService.addEntry("1", entry);
        assertEquals(entry, userService.getEntryById("1", "4"));
    }

    @Test
    void testDeleteEntryById() {
        Entry entry = new Entry(4, "Ralf", "90900");
        userService.addEntry("1", entry);
        int sizeBeforeDelete = userService.userList.get(0).getPhoneBookList().size();
        userService.deleteEntryById("1", "4");
        assertEquals(--sizeBeforeDelete, userService.userList.get(0).getPhoneBookList().size());
    }

    @Test
    void testUpdateEntry() {
        Entry entry = userService.getEntryById("1", "3");
        entry.setName("Floppa");
        entry.setNumber("98765");
        userService.updateEntry("1", "3", entry);
        assertEquals(entry, userService.getEntryById("1", "3"));
    }

    @Test
    void testGetUserByName() {
        User user = new User(1, "98765", "Floppa");
        List<User> userList = new ArrayList<>(Arrays.asList(user));
        userService.addUser(user);
        assertEquals(userList, userService.getUserByName("Fl"));
    }
}