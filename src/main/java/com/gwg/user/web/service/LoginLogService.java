package com.gwg.user.web.service;


import com.gwg.user.web.model.User;

public interface LoginLogService {

    /**
     * 记录登陆日志
     * @param user
     */
    public void recordLoginLog(User user);

}
