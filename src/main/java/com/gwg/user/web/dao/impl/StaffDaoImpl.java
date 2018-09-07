package com.gwg.user.web.dao.impl;

import com.gwg.user.web.dao.StaffDao;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.mapper.StaffMapper;
import com.gwg.user.web.model.Staff;
import com.gwg.user.web.model.StaffExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffDaoImpl implements StaffDao{

    @Autowired
    private StaffMapper staffMapper;

    public boolean addStaff(UserDto dto) throws BusinessException{
        Staff staff = new Staff();
        BeanUtils.copyProperties(dto, staff);
        return staffMapper.insertSelective(staff) > 0;
    }

    @Override
    public boolean updateStaffByStaffCode(UserDto dto) throws BusinessException {

        StaffExample example = new StaffExample();
        example.createCriteria().andStaffCodeEqualTo(dto.getStaffCode());
        Staff staff = new Staff();
        BeanUtils.copyProperties(dto, staff);
        return staffMapper.updateByExample(staff, example) > 0;
    }
}
