package com.example.invygo.controller;

import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.User;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest){
        return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUser(id));
    }

   /* @GetMapping("/{role}")
    public ResponseEntity<List<User>> getUserByRole(@PathVariable String role) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUserByRole(role));
    }*/
}
