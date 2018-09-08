package com.gwg.user.web.controller;

import com.gwg.user.web.common.ErrorCode;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.config.security.RequestContext;
import com.gwg.user.web.config.security.AuthUser;


public class BaseController {
    protected <T> Result<T> Success(){
        return new Result<>(true, ErrorCode.S200.statusCode(), ErrorCode.S200.message(), null);
    }
    protected <T> Result<T> Success(T reslut)throws InstantiationException{
        return new Result<>(true, ErrorCode.S200.statusCode(), ErrorCode.S200.message(), reslut);
    }
    protected <T> Result<T> Error(String statusCode,String message, T reslut){
        return new Result<>(false, statusCode, message,reslut);
    }
    protected <T> Result<T> Error(){
        return new Result<>(false, ErrorCode.S404.statusCode(), ErrorCode.S404.message(),null);
    }
    protected <T> Result<T> Error(ErrorCode errorCode){
        return new Result<>(false, errorCode.statusCode(), errorCode.message(),null);
    }
    protected <T> Result<T> Error(String statusCode,String message){
        return new Result<>(false, statusCode, message,null);
    }
    protected RequestContext getRequestContext() {
        return RequestContext.getOrCreate();
    }

    protected AuthUser getCurrentUser() {
        return getRequestContext().getAuthUser();
    }

    protected String getCurrentUserId(){
        AuthUser authUser = getCurrentUser();
        if(null != authUser){
            return authUser.getUsername();
        }
        return null;
    }

    protected String getCurrentStaffCode() {
        AuthUser authUser = getCurrentUser();
        if (null != authUser) {
            return authUser.getStaffCode();
        }
        return null;
    }

}
