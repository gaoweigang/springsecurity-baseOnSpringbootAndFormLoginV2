package com.gwg.user.web.dao.impl;


import com.gwg.user.web.dao.LoginLogDao;
import com.gwg.user.web.mapper.LoginLogMapper;
import com.gwg.user.web.model.LoginLog;
import com.gwg.user.web.model.LoginLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class LoginLogDaoImpl implements LoginLogDao{

    @Autowired
    private LoginLogMapper loginLogMapper;

    public LoginLog queryloginLogByUserid(String userid) {
        LoginLogExample example = new LoginLogExample();
        example.createCriteria().andUserIdEqualTo(userid);
        List<LoginLog> logList = loginLogMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(logList)){
            return null;
        }
        return logList.get(0);

    }

    public void insertLoginLog(LoginLog loginLog) {

        loginLogMapper.insertSelective(loginLog);

    }

    public void updateLoginLog(LoginLog loginLog) {
        loginLogMapper.updateByPrimaryKey(loginLog);
    }
}
