package com.journal.Journal.controller;

import com.journal.Journal.Entity.User;
import com.journal.Journal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public void CreateUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User userInDb = userService.findByName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @GetMapping("/id/{id}")
//    public User findById(@PathVariable ObjectId id){
//        return userService.findById(id).orElse(null);
//    }

//    @DeleteMapping("/id/{id}")
//    public boolean deleteById(@PathVariable ObjectId id){
//        userService.deleteById(id);
//        return true;
    // }


}
