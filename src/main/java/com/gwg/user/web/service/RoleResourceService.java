package com.gwg.user.web.service;

import com.gwg.user.web.exception.BusinessException;

import java.util.List;

public interface RoleResourceService {

    /**
     * 根据URL查询相应的角色
     */
    public List<String> queryAllowedRolesByUrl(String url) throws BusinessException;
}
