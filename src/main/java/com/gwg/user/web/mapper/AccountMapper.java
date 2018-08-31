package com.gwg.user.web.mapper;

import com.gwg.user.web.model.Account;
import com.gwg.user.web.model.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int countByExample(AccountExample example);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}