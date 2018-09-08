package com.gwg.user.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.common.Constant;
import com.gwg.user.web.dao.StaffDao;
import com.gwg.user.web.dao.UserDao;
import com.gwg.user.web.dao.UserRoleDao;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.User;
import com.gwg.user.web.service.UserService;
import com.gwg.user.web.util.ParamUtil;
import com.gwg.user.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private StaffDao staffDao;


	@Autowired
	private UserRoleDao userRoleDao;


	@Autowired
	private RedisTemplate redisTemplate;

	public UserVo queryUserDetailByUsrename(String username) throws BusinessException{

		return userDao.queryUserDetailByUsrename(username);

	}

	public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException {
		return userDao.queryUserInfoByLimit(dto);
	}


	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}


	@Transactional
	public void addUserInfo(UserDto dto) throws BusinessException{
		//1.生成员工编号
		dto.setStaffCode(ParamUtil.generateStaffCode());
		//校验
       //1.账户表
		userDao.addUser(dto);

		//2.用户表
        staffDao.addStaff(dto);

		//3.用户角色关联表
		userRoleDao.addUserRole(dto);

	}

	@Transactional
	public void updateUserInfo(UserDto dto) throws BusinessException{
		//0.校验数据的合法性
		//判断该记录是否存在
		//1.账户表
		userDao.updateUserByUsername(dto);

		//2.用户表
		staffDao.updateStaffByStaffCode(dto);

		//3.用户角色关联表
		userRoleDao.updateUserRoleByStaffCode(dto);

	}

}
