package com.gwg.user.web.security;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.gwg.user.common.Constant;
import com.gwg.user.dto.UserDto;
import com.gwg.user.util.SessionUtil;
import com.gwg.user.web.authority.AuthorityManager;

/**
 * 自定义投票者
 */
public class CustomAccessDecisionVoter implements AccessDecisionVoter{
	
	private static Logger logger = LoggerFactory.getLogger(CustomAccessDecisionVoter.class);
	
	    @Autowired(required = false)
	    private AuthorityManager authorityManager;//根据uri获取访问该uri所需要的角色

	    @Override
	    public int vote(Authentication authentication, Object object, Collection collection) {

	    	//请求Url
	        FilterInvocation fi = (FilterInvocation) object;
	        logger.info("******************请求路径：{}", fi);
	        if (null == fi) {
	            return ACCESS_GRANTED; //
	        }
	        UserDto userDto = SessionUtil.getSessionAttribute(Constant.SESSION_KEY_USER, fi.getRequest());
	        if (null == userDto) {
	            return ACCESS_DENIED; //访问拒绝
	        }
	        String function = fi.getRequestUrl(); //获取请求路径
	        Set<String> userOwnRoles = userDto.getRoles(); //获取用户所拥有的的角色集合
	        //从数据库中查询访问该URI需要哪些角色
	        Set<String> needRoles = authorityManager.getAllowedRolesByUrl(function);//根据URI获取角色
	        //判断该用户身份有所需要的岗位
	        boolean hasAuthority = needRoles.stream().anyMatch(role -> {return userOwnRoles.contains(role);});
	        return hasAuthority ? ACCESS_GRANTED : ACCESS_DENIED;
	    }
	    

	    @Override
	    public boolean supports(ConfigAttribute attribute) {
	        return true;
	    }

	    /**
	     * 指示AccessDecisionVoter的实现是否能够为指定的安全对象类型提供访问控制投票
	     */
	    @Override
	    public boolean supports(Class clazz) {
	        return true;
	    }
	}
