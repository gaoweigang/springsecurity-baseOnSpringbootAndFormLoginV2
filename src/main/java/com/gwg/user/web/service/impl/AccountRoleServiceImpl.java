package com.gwg.user.web.service.impl;

import com.gwg.user.web.dao.AccountRoleDao;
import com.gwg.user.web.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author
 *
 */
@Service
public class AccountRoleServiceImpl implements AccountRoleService {


	@Autowired
	private AccountRoleDao accountRoleDao;

	/**
	 * 判断用户是不是坐席人员
	 * @param userId
	 * @return
	 */
	public boolean isSaleStuff(String userId) {

		return true;
	}

	/**
	 *根据userid获取用户角色
	 */
	public List<String> queryRoleListByUserId(String userId){
		return accountRoleDao.queryRoleListByUserId(userId);
	}
}
