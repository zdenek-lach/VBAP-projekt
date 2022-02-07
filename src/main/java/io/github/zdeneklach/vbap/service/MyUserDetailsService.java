package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetailsByUsername = userService.findUserByUsername_(username);
        return new MyUserDetails(myUserDetailsByUsername.getUsername(), myUserDetailsByUsername.getPassword());
    }
}
