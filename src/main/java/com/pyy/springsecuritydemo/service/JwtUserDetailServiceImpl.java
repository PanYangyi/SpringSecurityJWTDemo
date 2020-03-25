package com.pyy.springsecuritydemo.service;

import com.pyy.springsecuritydemo.dao.UserRepository;
import com.pyy.springsecuritydemo.domain.User;
import com.pyy.springsecuritydemo.utils.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(s);
            return JwtUserFactory.create(user);

        }catch (Exception e){
            log.error("jwt验证过程根据用户名获取用户出错",e);
        }

        throw new UsernameNotFoundException("jwt auth something unexpected happened");
    }
}
