package com.easymanager.easymanager.security.service;

import com.easymanager.easymanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {
        User use = userService
    }
}
