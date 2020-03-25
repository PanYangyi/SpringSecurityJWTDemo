package com.pyy.springsecuritydemo.security;

import com.pyy.springsecuritydemo.domain.User;
import com.pyy.springsecuritydemo.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author panyangyi
 * @create 2020/3/24 17:22
 */
public class JwtAuthenticationTokenFilter2 extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationTokenFilter2(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        logger.info("=====从流中获取到登录信息=====");
        //从流中获取到登录信息
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);

        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    //成功验证后调用的方法
    //如果验证成功就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info("=====验证成功=====");
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        logger.info("jwtUser:"+jwtUser);
        String token = jwtTokenUtil.createToken(jwtUser.getUsername(), false);
        response.setHeader("token",JwtTokenUtil.TOKEN_PREFIX+token);
    }

    //验证失败时调用
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
