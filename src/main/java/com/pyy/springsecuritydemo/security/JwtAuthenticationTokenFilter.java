package com.pyy.springsecuritydemo.security;

import com.pyy.springsecuritydemo.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author panyangyi
 * @create 2020/3/24 11:34
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Qualifier("jwtUserDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        log.info("===JwtAuthenticationTokenFilter.doFilterInternal()=======");
        //获取请求头中AUTHORIZATION的信息
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        //浏览器传过来的token携带“beaer ”,所以要去掉token前缀获取真正的token
        String token = null;
        if (Strings.isNotBlank(header) && header.startsWith(JwtTokenUtil.TOKEN_PREFIX)){
            token = header.substring(JwtTokenUtil.TOKEN_PREFIX.length(),header.length());
        }

        if (Strings.isNotBlank(token) && jwtTokenUtil.validateToken(token)){
            String username = jwtTokenUtil.getUsername(token);
            log.info("checking authentication " + username);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                log.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
