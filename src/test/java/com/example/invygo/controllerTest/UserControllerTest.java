package com.example.invygo.controllerTest;

import com.example.invygo.controller.UserController;
import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.User;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        UserRequest userRequest = new UserRequest(/* Initialize user request data */);
        User savedUser = new User(/* Initialize saved user data */);

        when(userService.saveUser(userRequest)).thenReturn(savedUser);

        ResponseEntity<User> response = userController.saveUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());

        verify(userService, times(1)).saveUser(userRequest);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>(); /* Initialize user list */

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUser() throws UserNotFoundException {
        int userId = 1;
        User user = new User(/* Initialize user data */);

        when(userService.getUser(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).getUser(userId);
    }

    @Test
    void testUpdateUser() throws UserNotFoundException {
        int userId = 1;
        UserRequest userRequest = new UserRequest(/* Initialize updated user data */);
        User updatedUser = new User(/* Initialize updated user data */);

        when(userService.updateUser(userId, userRequest)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(userId, userRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());

        verify(userService, times(1)).updateUser(userId, userRequest);
    }

    @Test
    void testDeleteUser() throws UserNotFoundException {
        int userId = 1;

        when(userService.deleteUser(userId)).thenReturn(true);

        ResponseEntity<String> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User has been deleted successfully", response.getBody());

        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testGetUserByRole() throws UserNotFoundException {
        String role = "ROLE_ADMIN";
        List<User> userList = new ArrayList<>(); /* Initialize user list by role */

        when(userService.getUserByRole(role)).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getUserByRole(role);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());

        verify(userService, times(1)).getUserByRole(role);
    }
}
