package com.gwg.user.web.dao.impl;


import com.gwg.user.web.dao.RoleResourceDao;
import com.gwg.user.web.dto.RoleDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.RoleResourceMapper;
import com.gwg.user.web.model.Resource;
import com.gwg.user.web.model.RoleResource;
import com.gwg.user.web.model.RoleResourceExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleResourceDaoImpl implements RoleResourceDao{

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<Resource> queryResourceListByRoleId(RoleDto dto) throws BusinessException {

        return roleResourceMapper.queryResourceListByRoleId(dto);

    }

    public boolean batchDelRoleResource(String roleCode, List<String> resCodeList) throws BusinessException{
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andRoleCodeEqualTo(roleCode).andResCodeIn(resCodeList);
        return roleResourceMapper.deleteByExample(example) > 0;
    }

    public boolean addRoleResource(String roleCode, String resCode) throws BusinessException{
        if(StringUtils.isEmpty(roleCode) || StringUtils.isEmpty(resCode)){
            return false;
        }
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleCode(roleCode);
        roleResource.setResCode(resCode);
        return roleResourceMapper.insertSelective(roleResource) > 0;
    }

    public List<String> queryAllowedRolesByUrl(String url) throws BusinessException{
        return roleResourceMapper.queryAllowedRolesByUrl(url);
    }



}
