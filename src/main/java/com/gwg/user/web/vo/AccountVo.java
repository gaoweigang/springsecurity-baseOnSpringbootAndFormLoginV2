package com.gwg.user.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountVo implements Serializable {

    /**
     * 用户id
     */
    private String userId;

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
     * 该用户拥有的角色集合
     */
    private List<String> roleCodeList;

}
