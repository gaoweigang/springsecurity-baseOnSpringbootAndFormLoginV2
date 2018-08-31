package com.gwg.user.web.service;


import com.gwg.user.web.model.Resource;

import java.util.List;

/**
 *
 */
public interface ResourceService {

	/**
	 * 获取全部资源
	 */
	public List<Resource> queryAllResources();


	/**
	 * 根据登陆用户user_id获取菜单
	 */
	public List<Resource> queryCurrentUserMenu(String userId);




}
