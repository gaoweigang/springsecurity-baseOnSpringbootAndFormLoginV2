package com.gwg.user.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gwg.user.web.authority.RequestContext;
import com.gwg.user.web.common.Constant;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.configuration.AuthUser;
import com.gwg.user.web.security.HttpForbiddenEntryPoint;
import com.gwg.user.web.service.StaffService;
import com.gwg.user.web.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * 前后端分离项目，不管认证成功还是失败，返回JSON数据
 * 认证成功后把用户信息放到Session中
 */
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);

	@Autowired
	private StaffService staffService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("用户认证成功后的处理 start .....");
		//1.查询当前登录用户的用户信息
		//2.把用户信息放到Session中
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		SessionUtil.setSessionAttribute(Constant.USER_SESSION, authUser, request);
		//3.把用户信息放到ThreadLocal中
		RequestContext ctxt = RequestContext.getOrCreate();
		ctxt.setAuthUser(authUser);

		//返回用户登录成功
		HttpForbiddenEntryPoint.writeCrosInfo(response);
		Result<String> result = new Result(true, "200", "登录成功！", null);//返回认证失败提示
		JSON.writeJSONString(response.getWriter(), result);

	}
}
