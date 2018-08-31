package com.gwg.user.web.configuration;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * SpringSecuity提供的User只能记录用户名和密码
 * 如果满足不了需求，可以进行扩展
 */
@Data
public class UserInfo extends User{

	public UserInfo(String username, String password, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

}
