package com.gwg.user.web.dao.impl;

import com.gwg.user.web.dao.AccountDao;
import com.gwg.user.web.dto.StaffDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.AccountMapper;
import com.gwg.user.web.model.Account;
import com.gwg.user.web.model.AccountExample;
import com.gwg.user.web.vo.AccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.Date;
import java.util.List;

@Component
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountMapper accountMapper;

    public Account queryAccountByUserId(String userid) throws BusinessException {
        if(StringUtils.isEmpty(userid)){
            return null;
        }
        AccountExample example = new AccountExample();
        example.createCriteria().andUserIdEqualTo(userid);
        List<Account> userList = accountMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }

        return userList.get(0);
    }

    public boolean addAccount(StaffDto dto) throws BusinessException{
        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setCreateTime(new Date());
        account.setModifyTime(new Date());
        return accountMapper.insertSelective(account) > 0;

    }

    public boolean updateAccountByUserId(StaffDto dto) throws BusinessException{
        AccountExample example = new AccountExample();
        example.createCriteria().andUserIdEqualTo(dto.getUserId());
        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        return accountMapper.updateByExampleSelective(account, example) > 0;
    }

    public AccountVo queryAccountDetailByUsreId(String userId) throws BusinessException{
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        return accountMapper.queryAccountDetailByUsreId(userId);
    }



}
