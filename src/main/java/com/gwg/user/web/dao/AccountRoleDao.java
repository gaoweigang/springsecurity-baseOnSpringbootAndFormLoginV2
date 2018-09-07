package com.gwg.user.web.dao;



import com.gwg.user.web.dto.StaffDto;
import com.gwg.user.web.exception.BusinessException;

import java.util.List;

public interface AccountRoleDao {
    /**
     * 根据用户ID获取用户角色
     */
    public List<String> queryRoleListByUserId(String userId) throws BusinessException;

    /**
     * 添加用户角色关联信息
     */
    public boolean addUserRole(StaffDto dto) throws BusinessException;

    /**
     * 根据userid更新用户角色
     * @param dto
     * @return
     * @throws BusinessException
     */
    public boolean updateUserRoleByUserId(StaffDto dto) throws BusinessException;

}
