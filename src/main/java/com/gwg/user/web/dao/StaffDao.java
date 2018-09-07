package com.gwg.user.web.dao;

import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;

public interface StaffDao {

    public boolean addStaff(UserDto dto) throws BusinessException;

    public boolean updateStaffByStaffCode(UserDto dto) throws BusinessException;

}
