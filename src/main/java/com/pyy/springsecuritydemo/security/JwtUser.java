package com.pyy.springsecuritydemo.security;

import com.pyy.springsecuritydemo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @Author panyangyi
 * @create 2020/3/23 15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUser implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //账号是否未过期 默认是false,记得改成true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否锁定 默认是false
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账号凭证是否未过期，默认是false
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
