//package com.pyy.springsecuritydemo.interceptor;
//
//import com.nimbusds.jwt.JWT;
//import com.pyy.springsecuritydemo.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * @Author panyangyi
// * @create 2020/3/24 10:46
// */
//public class JwtInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String token = request.getHeader("token");//从 http 请求头取出 token
//        //如果不是映射到方法直接通过
//        if(!(handler instanceof HandlerMethod)){
//            return true;
//        }
//
//        //获取方法
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        //检查是否有跳过token验证的注释，有则跳过验证
//        if(method.isAnnotationPresent(IgnoreToken.class)){
//            //获取方法注释
//            IgnoreToken ignoreToken = method.getAnnotation(IgnoreToken.class);
//            if (ignoreToken.required()){
//                return true;
//            }
//        }
//
//        //认证
//        if (token == null){
//            throw new RuntimeException("未检测到token，请重新登录");
//        }
//
//        //获取token中的username
//        String username;
//        try {
//            JWT.decode(token).getAudience().get(0)
//        }
//
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
