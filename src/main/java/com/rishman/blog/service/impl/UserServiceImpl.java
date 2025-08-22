package com.rishman.blog.service.impl;

import com.rishman.blog.config.AppConstants;
import com.rishman.blog.entity.Role;
import com.rishman.blog.entity.User;
import com.rishman.blog.exception.ResourceNotFoundException;
import com.rishman.blog.payload.UserDTO;
import com.rishman.blog.repository.RoleDAO;
import com.rishman.blog.repository.UserDAO;
import com.rishman.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleDAO.findById(AppConstants.ROLE_USER).get();
        user.getRoles().add(role);
        userDAO.save(user);
        return userToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        userDAO.save(user);
        return userToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        userDAO.save(user);

        return userToDTO(user);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userDAO.findAll();

        return users.stream().map(this::userToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Integer userId) {

        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userDAO.delete(user);

    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
