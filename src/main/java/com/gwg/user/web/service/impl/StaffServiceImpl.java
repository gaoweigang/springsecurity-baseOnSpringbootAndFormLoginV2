package com.gwg.user.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dao.AccountDao;
import com.gwg.user.web.dao.StaffDao;
import com.gwg.user.web.dao.AccountRoleDao;
import com.gwg.user.web.dto.StaffDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.User;
import com.gwg.user.web.service.StaffService;
import com.gwg.user.web.vo.StaffVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author
 *
 */
@Service
public class StaffServiceImpl implements StaffService {

	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Autowired
	private StaffDao staffDao;
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AccountRoleDao accountRoleDao;

	public User getUserByUserId(String userId) {
		return staffDao.getUserByUserId(userId);
	}


	public PageInfo<StaffVo> queryUserInfoByLimit(StaffDto dto) throws BusinessException {
		return staffDao.queryUserInfoByLimit(dto);
	}

	@Transactional
	public void addUserInfo(StaffDto dto) throws BusinessException{
		//校验
       //1.账户表
		staffDao.addUser(dto);

		//2.用户表
        accountDao.addAccount(dto);

		//3.用户角色关联表
		accountRoleDao.addUserRole(dto);

	}

	@Transactional
	public void updateUserInfo(StaffDto dto) throws BusinessException{
		//0.校验数据的合法性
		//判断该记录是否存在
		//1.账户表
		staffDao.updateUserByUserId(dto);

		//2.用户表
		accountDao.updateAccountByUserId(dto);

		//3.用户角色关联表
		accountRoleDao.updateUserRoleByUserId(dto);

	}


	/**
	 * 根据id查询用户信息
	 */
	public StaffVo queryUserInfoByUserId(StaffDto dto) {
		return staffDao.queryUserInfoByUserId(dto);
	}
}
