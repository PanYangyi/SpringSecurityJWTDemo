package com.pyy.springsecuritydemo.domain;

import com.pyy.springsecuritydemo.security.JwtUser;

/**
 * @Author panyangyi
 * @create 2020/3/24 16:53
 */
public class JwtAuthenticationResponse {

    private String accessToken;

    private String tokenType = "Bearer";

    private JwtUser jwtUser;

    public JwtAuthenticationResponse(String accessToken, JwtUser jwtUser) {
        this.accessToken = accessToken;
        this.jwtUser = jwtUser;
    }

    public JwtUser getUserInfo() {
        return jwtUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
