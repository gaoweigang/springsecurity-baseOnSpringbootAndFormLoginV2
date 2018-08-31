package com.gwg.user.web.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dao.UserDao;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.UserMapper;
import com.gwg.user.web.model.User;
import com.gwg.user.web.model.UserExample;
import com.gwg.user.web.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserMapper userMapper;

    public User getUserByUserId(String userId) {
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }

        return userList.get(0);
    }


    public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException {
        PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
        List<UserVo> userVoList = userMapper.queryUserInfo(dto);
        PageInfo<UserVo> page = new PageInfo<UserVo>(userVoList);
        return page;

    }

    public boolean addUser(UserDto dto) throws BusinessException {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return userMapper.insertSelective(user) > 0;
    }

    public UserVo queryUserInfoById(UserDto dto) throws BusinessException {
        List<UserVo> userVoList = userMapper.queryUserInfo(dto);
        if(CollectionUtils.isEmpty(userVoList)){
            return null;
        }
        return userVoList.get(0);
    }

    public boolean updateUserByUserId(UserDto dto) throws BusinessException{
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(dto.getUserId());
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return userMapper.updateByExampleSelective(user, example) > 0;
    }
}
