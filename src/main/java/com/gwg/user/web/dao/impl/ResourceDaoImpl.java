package com.gwg.user.web.dao.impl;

import com.gwg.user.web.common.Constant;
import com.gwg.user.web.dao.ResourceDao;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.ResourceMapper;
import com.gwg.user.web.model.Resource;
import com.gwg.user.web.model.ResourceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class ResourceDaoImpl implements ResourceDao {

    @Autowired
    private ResourceMapper resouceMapper;

    public List<Resource> queryAllResources() throws BusinessException {
        ResourceExample example = new ResourceExample();
        example.createCriteria().andValidFlagEqualTo(Constant.VALID_RECORD);
        return resouceMapper.selectByExample(example);

    }

    public List<Resource> queryResourceListByIds(List<Long> idList) throws BusinessException{
        ResourceExample example = new ResourceExample();
        example.createCriteria().andIdIn( idList);
        return resouceMapper.selectByExample(example);
    }


    public List<Resource> queryCurrentUserMenu(String userId) throws BusinessException {
        return resouceMapper.queryCurrentUserMenu(userId);
    }
}
