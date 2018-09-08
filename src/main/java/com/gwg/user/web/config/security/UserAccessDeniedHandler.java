package com.gwg.user.web.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSON;
import com.gwg.user.web.common.Constant;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.util.SessionUtil;

/**
 * 当用户认证通过后，访问用户没有权限的资源处理
 */
public class UserAccessDeniedHandler implements AccessDeniedHandler {
	
	private static Logger logger = LoggerFactory.getLogger(UserAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    	logger.info("用户没权限访问 start .........");
    	HttpForbiddenEntryPoint.writeCrosInfo(response);
        Result<String> result = Result.error("600", "没有权限访问");
        AuthUser authUser = SessionUtil.getSessionAttribute(Constant.USER_SESSION, request);
        if (null == authUser) {
            result = Result.error("610", "用户未登录");//会话超时
        }
        JSON.writeJSONString(response.getWriter(), result);
    }
}
