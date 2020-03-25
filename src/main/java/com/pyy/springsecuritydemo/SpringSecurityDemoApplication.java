package com.pyy.springsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author panyangyi
 * @create 2020/3/23 10:42
 */
@SpringBootApplication
public class SpringSecurityDemoApplication {

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){ //这里注入了就可以了
//        return new BCryptPasswordEncoder();
//    }


    public static void main(String[] args){
        SpringApplication.run(SpringSecurityDemoApplication.class,args);
    }
}
