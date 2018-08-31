package com.gwg.user.web.service.impl;

import com.gwg.user.web.dao.AccountDao;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Account;
import com.gwg.user.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account queryAccountByUsreId(String userId) throws BusinessException {
        return accountDao.queryAccountByUserId(userId);
    }
}
