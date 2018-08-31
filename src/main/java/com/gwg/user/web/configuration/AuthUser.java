package com.gwg.user.web.configuration;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 */
@Data
public class AuthUser implements Serializable{
	private Long id;

	private String userId;

	//密码
	private String password;

	private String username;

	private String sex;

	private Date birthday;

	private String cardNo;

	private String email;

	private String mobile;

	private String position;

	private Boolean status;

	private Boolean validFlag;

	private Date entryTime;

	private Date resignTime;

	private Date createTime;

	private Date modifyTime;

	private String creator;

	private String modifier;

	private String remark;

}
