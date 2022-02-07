package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.MyUserDetails;
import io.github.zdeneklach.vbap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public MyUserDetails findUserByUsername_(String userName){
        if(Objects.equals(userName, "")) throw new UsernameNotFoundException("Please enter a username! Currently it is empty!");
        List<MyUserDetails> MyUserDetails = userRepository.findAll();
        MyUserDetails searchedMyUserDetails = null;
        for (MyUserDetails u: MyUserDetails) {
            if (u.getUsername().equals(userName))
                searchedMyUserDetails = u;
        }
        return Objects.requireNonNullElseGet(searchedMyUserDetails, MyUserDetails::new);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUserDetails encryptNewUserPassword(MyUserDetails newUser) throws Exception {
        MyUserDetails user = new MyUserDetails();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(user);
    }
}
