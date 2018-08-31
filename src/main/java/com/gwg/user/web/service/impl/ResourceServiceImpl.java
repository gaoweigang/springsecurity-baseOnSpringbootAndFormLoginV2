package com.gwg.user.web.service.impl;


import com.gwg.user.web.dao.ResourceDao;
import com.gwg.user.web.dao.RoleDao;
import com.gwg.user.web.dao.RoleResourceDao;
import com.gwg.user.web.model.Resource;
import com.gwg.user.web.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author
 *
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleResourceDao roleResourceDao;


	/**
	 * 1.权限展示有两部分
	 * 1.查询所有的资源， 2.查询该角色有哪些资源的id
	 */
	public List<Resource> queryAllResources() {

		//1.查询所有的资源信息，在内存中处理
		return resourceDao.queryAllResources();

	}

	@Override
	public List<Resource> queryCurrentUserMenu(String userId) {
		return resourceDao.queryCurrentUserMenu(userId);
	}
}
