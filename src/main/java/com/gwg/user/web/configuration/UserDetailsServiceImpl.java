package com.gwg.user.web.configuration;

import com.alibaba.fastjson.JSON;
import com.gwg.user.web.model.Account;
import com.gwg.user.web.service.AccountService;
import com.gwg.user.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private AccountService accountService;

	/**
	 * 通过用户名查询用户
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.info("用户名：{}", username);
		Account account = accountService.queryAccountByUsreId(username);
		if (account == null) {
			throw new UsernameNotFoundException("该用户不存在！");
		}
		logger.info("登录用户信息：" + JSON.toJSON(account));
		AuthUser user = new AuthUser(account.getUserId(), account.getPassword(), true, true, true, false, null);
		return user;
	}

}
