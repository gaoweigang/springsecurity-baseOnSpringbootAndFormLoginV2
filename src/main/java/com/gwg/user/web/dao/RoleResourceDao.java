package com.gwg.user.web.dao;



import com.gwg.user.web.dto.RoleDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Resource;

import java.util.List;


public interface RoleResourceDao {

    public List<Resource> queryResourceListByRoleId(RoleDto dto) throws BusinessException;

    public boolean batchDelRoleResource(String roleCode, List<String> resCode) throws BusinessException;

    public boolean addRoleResource(String roleCode, String resCode) throws BusinessException;


}
