package com.gwg.user.web.dao.impl;

import com.gwg.user.web.dao.UserRoleDao;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.UserRoleMapper;
import com.gwg.user.web.model.UserRole;
import com.gwg.user.web.model.UserRoleExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<String> queryRoleListByUserid(String userid) throws BusinessException {
        if(StringUtils.isEmpty(userid)){
            return null;
        }
        return userRoleMapper.queryRoleListByUserid(userid);
    }

    public boolean addUserRole(UserDto dto) throws BusinessException{
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        return userRoleMapper.insert(userRole) > 0;
    }

    public boolean updateUserRoleByUserId(UserDto dto) throws BusinessException{
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(dto.getUserId());
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        return userRoleMapper.updateByExampleSelective(userRole, example) > 0;
    }

}
