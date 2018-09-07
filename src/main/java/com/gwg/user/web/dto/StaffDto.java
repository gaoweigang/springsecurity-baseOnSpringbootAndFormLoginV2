package com.gwg.user.web.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StaffDto implements Serializable{
    //用户名
    private String userId;

    //密码
    private String password;

    //确认密码
    private String confirmPassword;

    /**
     * 员工工号
     */
    private String StaffCode;

    //姓名
    private String staffName;

    //性别
    private Integer sex;

    //出生日期
    private Date birthday;

    //邮件
    private String email;

    //身份证号
    private String cardNo;

    //手机号码
    private String mobile;

    //入职时间
    private Date entryTime;

    private Date entryStartDate;

    private Date entryEndDate;

    //状态：是否在职
    private String status;

    //角色
    private String roleCode;

    //角色名称
    private String roleName;

    //当前登录用户名
    private String creator;

    //页数
    private int pageIndex;

    //每页显示条数
    private int pageSize;

    //排序
    private String sort;

}
