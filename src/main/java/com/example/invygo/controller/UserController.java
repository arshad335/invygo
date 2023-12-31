package com.example.invygo.controller;

import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.User;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api/v1/user")
@EnableMethodSecurity
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest){
        return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUser(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {

        User user = service.updateUser(id, userRequest);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws UserNotFoundException {
        boolean isDeleted = service.deleteUser(id);
        if(isDeleted){
            String responseContent = "User has been deleted successfully";
            return new ResponseEntity<String>(responseContent,HttpStatus.OK);
        }
        String error = "Error while deleting User from database";
        return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUserByRole(@PathVariable String role) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUserByRole(role));
    }
}
