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

    public List<String> queryRoleListByStaffCode(String staffCode) throws BusinessException {
        if(StringUtils.isEmpty(staffCode)){
            return null;
        }
        return userRoleMapper.queryRoleListByStaffCode(staffCode);
    }

    public boolean addUserRole(UserDto dto) throws BusinessException{
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        return userRoleMapper.insert(userRole) > 0;
    }

    public boolean updateUserRoleByStaffCode(UserDto dto) throws BusinessException{
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andStaffCodeEqualTo(dto.getStaffCode());
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        return userRoleMapper.updateByExampleSelective(userRole, example) > 0;
    }

}
