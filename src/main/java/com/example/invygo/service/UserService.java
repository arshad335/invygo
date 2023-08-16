package com.example.invygo.service;

import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.Schedule;
import com.example.invygo.entity.User;
import com.example.invygo.exception.ScheduleNotFoundException;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User saveUser(UserRequest userRequest) {
        User user = User.build(0, userRequest.getName(),passwordEncoder.encode(userRequest.getPassword()),true , userRequest.getRoles());
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

    public User updateUser(int id, UserRequest userRequest) throws UserNotFoundException {
        deleteUser(id);
        User user = User.build(id, userRequest.getName(),userRequest.getPassword(),true , userRequest.getRoles());
    return repository.save(user);
    }

    public boolean deleteUser(int id) throws UserNotFoundException {
        User user = repository.findById(id);

        if(user != null){
            repository.delete(user);
            return true;
        }
        else {
            throw new UserNotFoundException("User Not Found with id :"+ id);
        }
    }

    public List<User> getUserByRole(String role) throws UserNotFoundException {
        List<User> user= repository.findBySpecificRole(role);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("No user with the role: "+role);
        }
    }
}
