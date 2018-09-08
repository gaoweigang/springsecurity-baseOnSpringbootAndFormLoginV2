package com.gwg.user.web.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.alibaba.fastjson.JSON;
import com.gwg.user.web.common.Result;

/**
 * 若匿名访问受限资源，则提示用户没登录
 * 当用户请求了一个受保护的资源，但是用户没通过认证，那么抛出异常
 */
public class HttpForbiddenEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(HttpForbiddenEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException authException) throws IOException, ServletException {
        writeCrosInfo(response);
        logger.info("登录异常：{}" + authException);
        Result<String> result = new Result<String>(false, "610", "用户未登录！", null);
        /*if (authException instanceof UsernameNotFoundException) {
            result = Result.error("610", "用户名或者密码不存在");
        } else if (authException instanceof BadCredentialsException) {
            result = Result.error("610", "用户名或者密码不存在");
        } else if (authException instanceof CredentialsExpiredException) {
            result = Result.error("610", "用户名或者密码不存在");
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = Result.error("610", "用户名或者密码不存在");
        } else {
            result = Result.error("610", "登录失败");
        }*/
        JSON.writeJSONString(response.getWriter(), result);
    }

    public static void writeCrosInfo(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        response.setHeader("Access-Control-Max-Age", "3600");
    }


}