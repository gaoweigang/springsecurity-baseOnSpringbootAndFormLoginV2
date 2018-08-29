package com.gwg.user.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class UserDto implements Serializable{
	
	private long id;
	
	//员工姓名
	private String name;
	
	//员工工号
	private String staffCode;
	
	//用户拥有的角色集合
	private Set<String> roles;//使用Set，Set可以去重

}
