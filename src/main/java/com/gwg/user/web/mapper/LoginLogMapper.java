package com.gwg.user.web.mapper;

import com.gwg.user.web.model.LoginLog;
import com.gwg.user.web.model.LoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper {
    int countByExample(LoginLogExample example);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    List<LoginLog> selectByExample(LoginLogExample example);

    LoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByExample(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}