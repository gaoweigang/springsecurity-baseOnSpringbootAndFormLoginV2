package com.gwg.user.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.security.HttpForbiddenEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * 前后端分离项目，不管认证成功还是失败，都返回JSON数据
 * 认证失败我们需要进行的处理
 */
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.info("用户认证失败后的处理 start .....");
		HttpForbiddenEntryPoint.writeCrosInfo(response);
		Result<String> result = Result.error("600", exception.getMessage());//返回认证失败提示
		JSON.writeJSONString(response.getWriter(), result);

	}
}
