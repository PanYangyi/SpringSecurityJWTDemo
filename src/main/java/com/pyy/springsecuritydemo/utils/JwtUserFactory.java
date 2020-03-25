package com.pyy.springsecuritydemo.utils;

import com.pyy.springsecuritydemo.domain.User;
import com.pyy.springsecuritydemo.security.JwtUser;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author panyangyi
 * @create 2020/3/24 10:09
 */
@Data
@NoArgsConstructor
public final class JwtUserFactory {

    //创建User
    public static JwtUser create(User user){

        return new JwtUser(user);
    }
}
