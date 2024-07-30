package com.micro.UserService.service;

import com.micro.UserService.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    // get single user by id;

    User getUserById(String id);
//    User updateUserById(User user, String id);
//    void deleteUserById(String id);
}
