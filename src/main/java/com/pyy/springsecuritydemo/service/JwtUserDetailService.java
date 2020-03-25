package com.pyy.springsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author panyangyi
 * @create 2020/3/24 9:59
 */
public interface JwtUserDetailService extends UserDetailsService {

    public UserDetails loadUserByPhone(String phone,String password);
}
