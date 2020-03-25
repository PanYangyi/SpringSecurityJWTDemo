package com.pyy.springsecuritydemo.security;

import com.pyy.springsecuritydemo.exception.PasswordErroeException;
import com.pyy.springsecuritydemo.exception.PhoneCodeErrorException;
import com.pyy.springsecuritydemo.exception.PhoneErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理
 * @Author panyangyi
 * @create 2020/3/25 14:29
 */
@Component
@Slf4j
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {


        //获取接口访问路径
        String path = httpServletRequest.getServletPath();
        log.error("Responding with unauthorized error. Message - {}, Path - {}", e.getMessage(), path);

        if (e instanceof UsernameNotFoundException){
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"用户名不存在");
            return;
        }

        if (e instanceof BadCredentialsException){
            httpServletResponse.sendError(HttpServletResponse.SC_PAYMENT_REQUIRED,"密码错误");
            return;
        }

        if (e instanceof PhoneCodeErrorException){
            httpServletResponse.sendError(402,"手机验证码错误");
            return;
        }

        if (e instanceof PhoneErrorException){
            httpServletResponse.sendError(403,"手机号不存在哈哈");
            return;
        }

        if (e instanceof PasswordErroeException){
            httpServletResponse.sendError(403,"密码错误哈哈");
            return;
        }

        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());



    }
}
