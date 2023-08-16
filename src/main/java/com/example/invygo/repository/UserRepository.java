package com.example.invygo.repository;


import com.example.invygo.entity.Role;
import com.example.invygo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(int id);

    User findByUsername(String name);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query( "select u from User u inner join u.roles r where r.name = :role" )
    List<User> findBySpecificRole(@Param("role") String role);
}
