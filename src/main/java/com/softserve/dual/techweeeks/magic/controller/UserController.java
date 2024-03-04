package com.softserve.dual.techweeeks.magic.controller;

import com.softserve.dual.techweeeks.magic.User;
import com.softserve.dual.techweeeks.magic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/magic-java-be")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(path="/users")
    public @ResponseBody User addNewUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id).orElseThrow(() -> new RuntimeException("User with id" + id + " is not found"));
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Integer id) {
        return userService.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setDateOfBirth(newUser.getDateOfBirth());
                    user.setZipCode(newUser.getZipCode());
                    return userService.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User with id" + id + " is not found"));
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
