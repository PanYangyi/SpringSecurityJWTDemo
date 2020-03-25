package com.pyy.springsecuritydemo.controller;

import com.pyy.springsecuritydemo.dao.UserRepository;
import com.pyy.springsecuritydemo.domain.User;
import com.pyy.springsecuritydemo.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author panyangyi
 * @create 2020/3/25 11:12
 */
@RestController
@RequestMapping("user")
public class NeedTokenController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "update")
    public String update(String username){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByUsername(jwtUser.getUsername());

        user.setUsername(username);

        User save = userRepository.save(user);

        return save.toString();
    }


}
