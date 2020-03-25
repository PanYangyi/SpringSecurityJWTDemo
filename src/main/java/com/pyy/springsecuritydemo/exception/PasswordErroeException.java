package com.pyy.springsecuritydemo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author panyangyi
 * @create 2020/3/25 16:26
 */
public class PasswordErroeException extends AuthenticationException {
    public PasswordErroeException(String msg, Throwable t) {
        super(msg, t);
    }

    public PasswordErroeException(String msg) {
        super(msg);
    }
}
