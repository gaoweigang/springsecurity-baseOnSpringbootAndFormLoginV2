package com.gwg.user.web.mapper;

import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Resource;
import com.gwg.user.web.model.ResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    int countByExample(ResourceExample example);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    public List<Resource> queryResourceByUserId(@Param("userId") String userId) throws BusinessException;

    public List<Resource> queryCurrentUserMenu(@Param("userId") String userId) throws BusinessException;

}