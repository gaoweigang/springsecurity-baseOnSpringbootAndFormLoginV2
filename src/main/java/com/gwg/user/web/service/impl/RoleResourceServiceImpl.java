package com.gwg.user.web.service.impl;


import com.gwg.user.web.dao.RoleResourceDao;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleResourceServiceImpl implements RoleResourceService{

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<String> queryAllowedRolesByUrl(String url) throws BusinessException {
        return roleResourceDao.queryAllowedRolesByUrl(url);
    }
}
