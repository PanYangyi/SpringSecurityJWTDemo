package com.pyy.springsecuritydemo.service;

import com.pyy.springsecuritydemo.dao.UserRepository;
import com.pyy.springsecuritydemo.domain.User;
import com.pyy.springsecuritydemo.exception.PasswordErroeException;
import com.pyy.springsecuritydemo.exception.PhoneCodeErrorException;
import com.pyy.springsecuritydemo.exception.PhoneErrorException;
import com.pyy.springsecuritydemo.utils.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author panyangyi
 * @create 2020/3/24 9:59
 */
@Service
@Slf4j
public class JwtUserDetailServiceImpl implements JwtUserDetailService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(s);
            return JwtUserFactory.create(user);

        }catch (Exception e){
            log.error("jwt验证过程根据用户名获取用户出错",e);
            throw new UsernameNotFoundException("jwt auth something unexpected happened");
        }
    }


    /**
     * 手机号密码登录
     * @param phone
     * @param password
     * @return
     */
    @Override
    public UserDetails loadUserByPhone(String phone,String password){
        User userByPhone = userRepository.findByPhone(phone);

        if (userByPhone == null){
            throw new PhoneErrorException("当前手机号不存在");
        }

        if(!passwordEncoder.matches(password,userByPhone.getPassword())){
            throw new PasswordErroeException("密码错了你个傻逼");
        }

        return JwtUserFactory.create(userByPhone);
    }
}
