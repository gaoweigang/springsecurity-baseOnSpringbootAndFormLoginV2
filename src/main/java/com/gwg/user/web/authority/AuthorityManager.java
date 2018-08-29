package com.gwg.user.web.authority;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 权限缓存：根据uri获取访问该uri所需要的角色
 */
@Component
public class AuthorityManager {
	
	
	//通过uri获取访问该uri所需要的角色
   public Set<String> getAllowedRolesByUrl(String url) {
       Set<String> hashSet = new HashSet<String>();
       return hashSet;
   }

}
