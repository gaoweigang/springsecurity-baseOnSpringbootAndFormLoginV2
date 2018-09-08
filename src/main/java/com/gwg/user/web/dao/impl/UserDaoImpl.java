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

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;


    /**
     * 根据用户名获取用户详情
     * @param username
     * @return
     * @throws BusinessException
     */
    public UserVo queryUserDetailByUsrename(String username) throws BusinessException{
        if(StringUtils.isEmpty(username)){
            return null;
        }
        return userMapper.queryUserDetailByUsrename(username);
        //User user = userMapper.selectByPrimaryKey(1L);
        //return null;
    }

    /**
     * 分页查询-用户信息
     * @param dto
     * @return
     * @throws BusinessException
     */
    public PageInfo<UserVo> queryUserInfoByLimit(UserDto dto) throws BusinessException{
        PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
        List<UserVo> userVoList = userMapper.queryUserInfoByLimit(dto);
        return new PageInfo<>(userVoList);
    }

    public User getUserByUsername(String username) {
        if(StringUtils.isEmpty(username)){
            return null;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        return userList == null ? null : userList.get(0);
    }


    public boolean addUser(UserDto dto) throws BusinessException {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return userMapper.insertSelective(user) > 0;
    }


    public boolean updateUserByUsername(UserDto dto) throws BusinessException{
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(dto.getUsername());
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return userMapper.updateByExampleSelective(user, example) > 0;
    }



}
