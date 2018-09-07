package com.gwg.user.web.dao;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Staff;
import com.gwg.user.web.model.User;
import com.gwg.user.web.vo.UserVo;


public interface UserDao {


    public UserVo queryUserDetailByUsrename(String username) throws BusinessException;

    /**
     * 分页查询-用户信息
     * @param dto
     * @return
     * @throws BusinessException
     */
    public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException;


    public User getUserByUsername(String userId);


    /**
     * 新增-用户
     */
    public boolean addUser(UserDto dto) throws BusinessException;


    /**
     * 根据userId更新User
     */
    public boolean updateUserByUsername(UserDto dto) throws BusinessException;



}
