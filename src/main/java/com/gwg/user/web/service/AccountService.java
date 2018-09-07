package com.gwg.user.web.service;

import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Account;
import com.gwg.user.web.vo.AccountVo;

public interface AccountService {

    public Account queryAccountByUsreId(String userId) throws BusinessException;

    /**
     * 根据userId获取账号详情
     * @param userId
     * @return
     * @throws BusinessException
     */
    public AccountVo queryAccountDetailByUsreId(String userId) throws BusinessException;


}
