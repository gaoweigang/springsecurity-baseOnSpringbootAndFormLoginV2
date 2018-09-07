package com.gwg.user.web.dao;



import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;

import java.util.List;

public interface UserRoleDao {
    /**
     * 根据用户ID获取用户角色
     */
    public List<String> queryRoleListByStaffCode(String staffCode) throws BusinessException;

    /**
     * 添加用户角色关联信息
     */
    public boolean addUserRole(UserDto dto) throws BusinessException;

    /**
     * 根据userid更新用户角色
     * @param dto
     * @return
     * @throws BusinessException
     */
    public boolean updateUserRoleByStaffCode(UserDto dto) throws BusinessException;

}
