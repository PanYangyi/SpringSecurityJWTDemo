package com.pyy.springsecuritydemo.security;

import com.pyy.springsecuritydemo.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author panyangyi
 * @create 2020/3/25 16:02
 */
public class PhoneAndPasswordLoginAuthenticationProvider implements AuthenticationProvider {


    private JwtUserDetailService jwtUserDetailService;

    public PhoneAndPasswordLoginAuthenticationProvider(JwtUserDetailService jwtUserDetailService){
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication instanceof PhoneAndPasswordLoginAuthentication){

            PhoneAndPasswordLoginAuthentication phoneAndPasswordLoginAuthentication = (PhoneAndPasswordLoginAuthentication) authentication;

            String phone = phoneAndPasswordLoginAuthentication.getPhone();
            String password = phoneAndPasswordLoginAuthentication.getPassword();

            UserDetails userDetails = jwtUserDetailService.loadUserByPhone(phone, password);

            return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

        }

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PhoneAndPasswordLoginAuthentication.class.equals(aClass);
    }
}
