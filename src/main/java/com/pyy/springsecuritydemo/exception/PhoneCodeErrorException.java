package com.pyy.springsecuritydemo.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author panyangyi
 * @create 2020/3/25 15:34
 */
public class PhoneCodeErrorException extends AuthenticationException {


    public PhoneCodeErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public PhoneCodeErrorException(String msg) {
        super(msg);
    }
}
