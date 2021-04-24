package com.example.phonebook.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    public List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1, "12234", "Thomas")
    ));

    public List<User> getAllUser() {
        return userList;
    }

    public User getUser(String id) {
        return userList.stream().filter(user -> user.getId() == Integer.parseInt(id)).findFirst().get();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public Entry getEntryByPhone(String phone) {
        for (int i = 0; i < userList.size(); i++) {
            List<Entry> tempList = userList.get(i).getPhoneBookList();
            for (int j = 0; j < tempList.size(); j++) {
                if (tempList.get(j).getNumber().equals(phone)) {
                    return tempList.get(j);
                }
            }
        }
        return null;
    }

    public List<Entry> getAllEntryOfUser(String id) {
        User tempUser = userList.stream()
                .filter(user -> user.getId() == Integer.parseInt(id))
                .findFirst()
                .get();
        return tempUser.getPhoneBookList();
    }

    public void updateUser(String id, User user) {
        int counter = 0;
        for (User u : userList) {
            if (u.getId() == Integer.parseInt(id)) {
                userList.set(counter, user);
            }
            counter++;
        }
    }

    public void deleteUser(String id) {
        userList.removeIf(user -> user.getId() == Integer.parseInt(id));
    }

    public void addEntry(String id, Entry entry) {
        for (User u : userList) {
            if (u.getId() == Integer.parseInt(id)) {
                List<Entry> tempList = u.getPhoneBookList();
                tempList.add(entry);
                u.setPhoneBookList(tempList);
            }
        }
    }

    public Entry getEntryById(String userId, String entryId) {
        return userList.stream()
                .filter(user -> user.getId() == Integer.parseInt((userId)))
                .findFirst()
                .get()
                .getPhoneBookList()
                .stream()
                .filter(elemOfPhoneBook -> elemOfPhoneBook.getIdOfElem() == Integer.parseInt(entryId))
                .findFirst()
                .get();
    }

    public void deleteEntryById(String userId, String entryId) {
        userList.stream()
                .filter(user -> user.getId() == Integer.parseInt(userId))
                .findFirst()
                .get()
                .getPhoneBookList()
                .removeIf(elemOfPhoneBook -> elemOfPhoneBook.getIdOfElem() == Integer.parseInt(entryId));
    }

    public void updateEntry(String userId, String entryId, Entry entry) {
        for (User u : userList) {
            if (u.getId() == Integer.parseInt(userId)) {
                int entryCounter = 0;
                for (Entry elem : u.getPhoneBookList()) {
                    if (elem.getIdOfElem() == Integer.parseInt(entryId)) {
                        u.getPhoneBookList().set(entryCounter, entry);
                    }
                    entryCounter++;
                }
            }
        }
    }

    public List<User> getUserByName(String name) {
        List<User> searchableUsers = new ArrayList<>();
        for (User user : userList) {
            if (user.getUserName().contains(name)) {
                searchableUsers.add(user);
            }
        }
        return searchableUsers;
    }
}
