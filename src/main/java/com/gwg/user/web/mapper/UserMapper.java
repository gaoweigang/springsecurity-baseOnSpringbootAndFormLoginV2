package com.gwg.user.web.mapper;

import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.User;
import com.gwg.user.web.model.UserExample;
import java.util.List;

import com.gwg.user.web.vo.UserVo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据条件分页查询用户信息
     * @param dto
     * @return
     */
    public List<UserVo> queryUserInfo(UserDto dto) throws BusinessException;
}