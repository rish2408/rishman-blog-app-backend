package com.rishman.blog.security;

import com.rishman.blog.entity.User;
import com.rishman.blog.exception.ResourceNotFoundException;
import com.rishman.blog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.findByEmail(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email : " + username, 0));
        return user;
    }
}
