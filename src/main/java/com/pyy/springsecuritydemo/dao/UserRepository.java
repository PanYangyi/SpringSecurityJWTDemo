package com.pyy.springsecuritydemo.dao;

import com.pyy.springsecuritydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author panyangyi
 * @create 2020/3/24 15:23
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByPhone(String phone);
}
