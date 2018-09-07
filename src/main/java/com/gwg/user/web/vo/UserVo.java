package com.gwg.user.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserVo implements Serializable{

    /**
     * 用户id
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 员工姓名
     */
    private String staffCode;


    private String staffName;

    /**
     * 职位
     */
    private String position;

    /**
     * 该用户拥有的角色集合
     */
    private List<String> roleCodeList;
}
