package com.pyy.springsecuritydemo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author panyangyi
 * @create 2020/3/25 16:24
 */
public class PhoneErrorException extends AuthenticationException {
    public PhoneErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public PhoneErrorException(String msg) {
        super(msg);
    }
}
