package com.gwg.user.web.dao.impl;

import com.gwg.user.web.dao.AccountRoleDao;
import com.gwg.user.web.dto.StaffDto;
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
public class AccountRoleDaoImpl implements AccountRoleDao {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<String> queryRoleListByUserId(String userId) throws BusinessException {
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        return userRoleMapper.queryRoleListByUserid(userId);
    }

    public boolean addUserRole(StaffDto dto) throws BusinessException{
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        return userRoleMapper.insert(userRole) > 0;
    }

    public boolean updateUserRoleByUserId(StaffDto dto) throws BusinessException{
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(dto.getUserId());
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        return userRoleMapper.updateByExampleSelective(userRole, example) > 0;
    }

}
