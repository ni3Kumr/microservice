package com.micro.UserService.controller;

import com.micro.UserService.entities.Rating;
import com.micro.UserService.entities.User;
import com.micro.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
   public ResponseEntity<List<User>> getAllUsers( ){
      List<User> userList = userService.getAllUsers();
      return  ResponseEntity.ok(userList);

    }
    @PostMapping
   public ResponseEntity<User> saveUser(@RequestBody User user){

         User saveduser = userService.saveUser(user);
         return new ResponseEntity<>(saveduser, HttpStatus.CREATED);
     }
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") String id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);


     }
}
