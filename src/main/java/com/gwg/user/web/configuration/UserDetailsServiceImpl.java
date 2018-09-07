package com.gwg.user.web.configuration;

import com.alibaba.fastjson.JSON;
import com.gwg.user.web.service.AccountService;
import com.gwg.user.web.vo.AccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	/**
	 * 依赖注入密码加密解密工具（PS: 需要在springsecurity的配置文件中配置这个Bean）
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountService accountService;


	/**
	 * 通过用户名查询用户
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.info("前端闯过来的用户名为：{}", username);
		//1.通过用户名获取登录信息
		AccountVo accountVo = accountService.queryAccountDetailByUsreId(username);
		if (accountVo == null) {
			throw new UsernameNotFoundException("该用户不存在！");
		}
		//2.根据username获取用户具有角色权限
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = accountVo.getRoleCodeList().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

		logger.info("登录用户信息：" + JSON.toJSON(accountVo));
		AuthUser authUser = new AuthUser(accountVo.getUserId(), accountVo.getPassword(), accountVo.getStaffCode(), accountVo.getStaffName(), true, true, true, true, simpleGrantedAuthorityList);
		return authUser;
	}

}
