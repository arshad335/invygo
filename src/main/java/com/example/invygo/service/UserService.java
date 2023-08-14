package com.example.invygo.service;

import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.User;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public User saveUser(UserRequest userRequest) {
        User user = User.build(0, userRequest.getName(),userRequest.getPassword(),true , userRequest.getRoles());
        return repository.save(user);
    }
    public List<User> getAllUsers() {
        return repository.findAll();
    }


    public User getUser(int id) throws UserNotFoundException {
        User user= repository.findById(id);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("User not found with id : "+id);
        }
    }

    /*public List<User> getUserByRole(String role) throws UserNotFoundException {
        List<User> user= repository.findByRole(role);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("No user with the role: "+role);
        }
    }*/
}
