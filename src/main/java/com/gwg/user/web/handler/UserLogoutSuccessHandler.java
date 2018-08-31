package com.gwg.user.web.handler;

import com.gwg.user.web.common.Constant;
import com.gwg.user.web.util.SessionUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户注销后，从session中删除用户信息
 */
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		SessionUtil.removeSessionAttribute(Constant.USER_SESSION, request);
	}
}
