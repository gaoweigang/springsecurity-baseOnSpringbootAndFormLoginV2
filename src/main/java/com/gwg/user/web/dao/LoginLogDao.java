package com.gwg.user.web.dao;


import com.gwg.user.web.model.LoginLog;

public interface LoginLogDao {

    /**
     * 根据用户ID查询该用户登陆日志
     */
    public LoginLog queryloginLogByUsername(String username);

    /**
     * 插入登陆日志
     * @param loginLog
     */
    public void insertLoginLog(LoginLog loginLog);

    /**
     * 更新登陆日志
     */
    public void updateLoginLog(LoginLog loginLog);


}
