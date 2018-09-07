package com.gwg.user.web.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwg.user.web.dao.StaffDao;
import com.gwg.user.web.dto.StaffDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.StaffMapper;
import com.gwg.user.web.mapper.UserMapper;
import com.gwg.user.web.model.Staff;
import com.gwg.user.web.model.StaffExample;
import com.gwg.user.web.model.User;
import com.gwg.user.web.model.UserExample;
import com.gwg.user.web.vo.StaffVo;
import net.minidev.json.writer.CollectionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class StaffDaoImpl implements StaffDao {

    @Autowired
    private StaffMapper staffMapper;

    public User getUserByUserId(String userId) {
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        StaffExample example = new StaffExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = staffMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }

        return userList.get(0);
    }


    public boolean addUser(StaffDto dto) throws BusinessException {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return staffMapper.insertSelective(user) > 0;
    }

    public Staff queryUserInfoByUserId(StaffDto dto) throws BusinessException {
        StaffExample example = new StaffExample();
        example.createCriteria().andUserIdEqualTo(dto.getUserId());
        List<Staff> staffList = staffMapper.selectByExample(example);
        return staffList == null ? null : staffList.get(0);
    }

    public boolean updateUserByUserId(StaffDto dto) throws BusinessException{
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(dto.getUserId());
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return userMapper.updateByExampleSelective(user, example) > 0;
    }
}
