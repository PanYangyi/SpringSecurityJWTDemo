package com.pyy.springsecuritydemo.controller;

import com.pyy.springsecuritydemo.dao.UserRepository;
import com.pyy.springsecuritydemo.domain.JwtAuthenticationResponse;
import com.pyy.springsecuritydemo.domain.User;
import com.pyy.springsecuritydemo.security.JwtUser;
import com.pyy.springsecuritydemo.security.PhoneAndPasswordLoginAuthentication;
import com.pyy.springsecuritydemo.utils.JwtTokenUtil;
import com.pyy.springsecuritydemo.utils.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author panyangyi
 * @create 2020/3/24 14:12
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "login")
    public JwtAuthenticationResponse loginByUsernameAndPassword(String username, String password){

        log.info("========LoginController.loginByUsernameAndPassword()==============");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        log.info("#################");
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        log.info("@@@@@@@@@@@@@@@@@");
        JwtUser jwtUser = (JwtUser) authenticate.getPrincipal();
        String token = jwtTokenUtil.createToken(jwtUser.getUsername(), false);
        User user = userRepository.findByUsername(username);
        JwtUser jwtUser1 = JwtUserFactory.create(user);

        return new JwtAuthenticationResponse(token,jwtUser1);
    }

    /**
     * 手机号密码登录
     * @param phone
     * @param password
     * @return
     */
    @PostMapping(value = "loginPhone")
    public JwtAuthenticationResponse loginPhone(String phone,String password){
        log.info("===LoginController.loginPhone()===============");
        PhoneAndPasswordLoginAuthentication authentication = new PhoneAndPasswordLoginAuthentication(phone, password);
        Authentication authenticate = authenticationManager.authenticate(authentication);
        JwtUser user = (JwtUser) authenticate.getPrincipal();
        String token = jwtTokenUtil.createToken(user.getUsername(), false);
        User byUsername = userRepository.findByUsername(user.getUsername());
        JwtUser jwtUser = JwtUserFactory.create(byUsername);
        return new JwtAuthenticationResponse(token,jwtUser);
    }
}
