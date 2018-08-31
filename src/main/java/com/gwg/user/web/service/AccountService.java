package com.gwg.user.web.service;

import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Account;

public interface AccountService {

    public Account queryAccountByUsreId(String userId) throws BusinessException;


}
