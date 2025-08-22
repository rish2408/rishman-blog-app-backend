package com.rishman.blog.controller;

import com.rishman.blog.payload.UserDTO;
import com.rishman.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {

        UserDTO registerUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(registerUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        UserDTO updateUserDTO = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(updateUserDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        UserDTO getUserByIdDTO = userService.getUserById(userId);
        return new ResponseEntity<>(getUserByIdDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> getAllUsersDTO = userService.getAllUsers();
        return new ResponseEntity<>(getAllUsersDTO, HttpStatus.OK);
    }

    // Only admin can delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
