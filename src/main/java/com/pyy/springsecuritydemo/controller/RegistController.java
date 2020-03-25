package com.pyy.springsecuritydemo.controller;

import com.pyy.springsecuritydemo.dao.UserRepository;
import com.pyy.springsecuritydemo.domain.RegistUser;
import com.pyy.springsecuritydemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author panyangyi
 * @create 2020/3/24 16:24
 */
@RestController
@RequestMapping("/auth")
public class RegistController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/regist")
    public String registUser(@RequestBody RegistUser registUser){
        User user = new User();
        user.setUsername(registUser.getUsername());
//        user.setPassword(bCryptPasswordEncoder.encode(registUser.getPassword()));
        user.setPassword(passwordEncoder.encode(registUser.getPassword()));
        user.setRole("ROLE_USER");
        User save = userRepository.save(user);
        return save.toString();
    }
}
