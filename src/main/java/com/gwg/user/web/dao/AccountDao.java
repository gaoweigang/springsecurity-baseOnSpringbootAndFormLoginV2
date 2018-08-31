package com.gwg.user.web.dao;


import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Account;

public interface AccountDao {

    public Account queryAccountByUserId(String userId) throws BusinessException;

    public boolean addAccount(UserDto dto) throws BusinessException;

    public boolean updateAccountByUserId(UserDto dto) throws BusinessException;


}
