package com.gwg.user.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dao.AccountDao;
import com.gwg.user.web.dao.UserDao;
import com.gwg.user.web.dao.UserRoleDao;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Account;
import com.gwg.user.web.model.User;
import com.gwg.user.web.service.UserService;
import com.gwg.user.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private UserRoleDao userRoleDao;

	public User getUserByUserId(String userId) {
		return userDao.getUserByUserId(userId);
	}


	public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException {
		return userDao.queryUserInfoByLimit(dto);
	}

	@Transactional
	public void addUserInfo(UserDto dto) throws BusinessException{
		//校验
       //1.账户表
		userDao.addUser(dto);

		//2.用户表
        accountDao.addAccount(dto);

		//3.用户角色关联表
		userRoleDao.addUserRole(dto);

	}

	@Transactional
	public void updateUserInfo(UserDto dto) throws BusinessException{
		//0.校验数据的合法性
		//判断该记录是否存在
		//1.账户表
		userDao.updateUserByUserId(dto);

		//2.用户表
		accountDao.updateAccountByUserId(dto);

		//3.用户角色关联表
		userRoleDao.updateUserRoleByUserId(dto);

	}


	/**
	 * 根据id查询用户信息
	 */
	public UserVo queryUserInfoById(UserDto dto) {
		return userDao.queryUserInfoById(dto);
	}
}
