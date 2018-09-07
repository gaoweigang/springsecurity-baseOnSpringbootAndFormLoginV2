package com.gwg.user.web.dao;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dto.StaffDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Staff;
import com.gwg.user.web.model.User;
import com.gwg.user.web.vo.StaffVo;


public interface StaffDao {

    public User getUserByUserId(String userId);


    /**
     * 新增-用户
     */
    public boolean addUser(StaffDto dto) throws BusinessException;

    /**
     * 根据userid查询用户信息
     */
    public Staff queryUserInfoByUserId(StaffDto dto) throws BusinessException;

    /**
     * 根据userId更新User
     */
    public boolean updateUserByUserId(StaffDto dto) throws BusinessException;

}
