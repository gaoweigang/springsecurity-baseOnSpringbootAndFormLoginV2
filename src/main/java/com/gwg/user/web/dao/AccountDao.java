package com.gwg.user.web.dao;


import com.gwg.user.web.dto.StaffDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Account;
import com.gwg.user.web.vo.AccountVo;

public interface AccountDao {

    public Account queryAccountByUserId(String userId) throws BusinessException;

    public boolean addAccount(StaffDto dto) throws BusinessException;

    public boolean updateAccountByUserId(StaffDto dto) throws BusinessException;

    public AccountVo queryAccountDetailByUsreId(String userId) throws BusinessException;


}
