package com.gwg.user.web.service;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.User;
import com.gwg.user.web.vo.UserVo;

/**
 * Created by
 */
public interface UserService{


    /**
     * 根据userid得到用户信息
     * @return
     */
    public User getUserByUserId(String userId);


    /**
     * 分页查询-根据条件查询用户信息
     */
    public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException;

    /**
     * 新增-用户
     */
    public void addUserInfo(UserDto dto);

    /**
     * 更新用户信息
     * @param dto
     * @throws BusinessException
     */
    public void updateUserInfo(UserDto dto) throws BusinessException;

        /**
         * 根据userid查询用户信息
         */
    public UserVo queryUserInfoById(UserDto dto) throws BusinessException;




}
