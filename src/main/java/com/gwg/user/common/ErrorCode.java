package com.gwg.user.common;

public enum ErrorCode {
	S200(Constant.SUCCESS_CODE,"成功"),
	S400(Constant.REQUEST_DATA_MISSING,"请求参数不能为空"),
	S401(Constant.REQUEST_DATA_ERROR,"请求参数格式有误"),
	S403(Constant.DATA_NOT_FOUND,"请重新登录"),
	S404(Constant.DATA_NOT_FOUND,"没有查询到数据"),
	S500(Constant.SYSTEM_ERROR,"服务内部出现异常"),
	S501(Constant.BUSINESS_PROSS_ERROR,"业务流程异常");

	private String statusCode;
	private String message;
	
	private ErrorCode(String statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public String statusCode() {
		return statusCode;
	}

	public String message() {
		return message;
	}

}
