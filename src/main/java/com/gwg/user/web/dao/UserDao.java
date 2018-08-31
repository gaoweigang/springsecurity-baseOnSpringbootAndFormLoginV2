package com.gwg.user.web.dao;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.User;
import com.gwg.user.web.vo.UserVo;


public interface UserDao {

    public User getUserByUserId(String userId);

    /**
     * 分页查询-根据条件查询用户信息
     */
    public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException;

    /**
     * 新增-用户
     */
    public boolean addUser(UserDto dto) throws BusinessException;

    /**
     * 根据userid查询用户信息
     */
    public UserVo queryUserInfoById(UserDto dto) throws BusinessException;

    /**
     * 根据userId更新User
     */
    public boolean updateUserByUserId(UserDto dto) throws BusinessException;

}
