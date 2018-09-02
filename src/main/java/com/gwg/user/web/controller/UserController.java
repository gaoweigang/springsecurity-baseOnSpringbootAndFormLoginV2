package com.gwg.user.web.controller;

import com.alibaba.fastjson.JSON;

import com.github.pagehelper.PageInfo;
import com.gwg.user.web.common.ErrorCode;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.dto.UserDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.service.LoginLogService;
import com.gwg.user.web.service.UserRoleService;
import com.gwg.user.web.service.UserService;
import com.gwg.user.web.util.ParamUtil;
import com.gwg.user.web.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home redirection to swagger api documentation
 */
@RestController
@Api(value = "user", tags = "用户管理")
@RequestMapping({ "/user" })
@Slf4j
public class UserController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Autowired
	private LoginLogService loginLogService;

	/**
	 * 分页查询-用户信息
	 * @param
	 * @return
	 */
	@ApiOperation(value = "根据条件查询用户信息")
	@RequestMapping(value = "/queryUserInfoByLimit", method = RequestMethod.POST)
	public Result queryUserInfoByLimit(@RequestBody UserDto dto) {
		logger.info("根据条件查询用户信息...");
		try {
			ParamUtil.trim(dto);//去出请求参数前后空格
			PageInfo<UserVo> page =  userService.queryUserInfoByLimit(dto);
			return new Result(true, ErrorCode.S200.statusCode(), ErrorCode.S200.message(),page);
		} catch (BusinessException e) {
			logger.error("参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}

	}

	/**
	 * 根据条件查询用户信息 --这个是否可以省略，分页查询已给
	 * @param
	 * @return
	 */
	@ApiOperation(value = "根据条件查询用户信息")
	@RequestMapping(value = "/queryUserInfoById", method = RequestMethod.POST)
	public Result<?> queryUserInfoById(@RequestBody UserDto dto) {
		logger.info("根据条件查询用户信息...");

		try {
			UserVo userVo = userService.queryUserInfoById(dto);
			return new Result(true, ErrorCode.S200.statusCode(), ErrorCode.S200.message(),userVo);
		} catch (BusinessException e) {
			logger.error("参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);
		}
	}

	/**
	 * 新增用户
	 * @param
	 * @return
	 */
	@ApiOperation(value = "新增用户")
	@RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	public Result<?> addUserInfo(@RequestBody UserDto dto) {
		logger.info("根据条件查询用户信息...");
		try {
			//参数校验
			if(ParamUtil.isEmpty(dto.getUserId(),dto.getUsername(), dto.getCardNo(), dto.getMobile(), dto.getEntryTime(), dto.getRoleCode())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
        	//1.去除空格
			ParamUtil.trim(dto);
        	//设置当前登录用户
            dto.setCreator(this.getCurrentUsername());
			userService.addUserInfo(dto);
			return new Result(true, ErrorCode.S200.statusCode(), ErrorCode.S200.message(),null);
		} catch (Exception e) {
			logger.error("参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);
		}
	}

	/**
	 * 更新用户信息
	 * @param
	 * @return
	 */
	@ApiOperation(value = "更新用户")
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public Result<?> updateUserInfo(@RequestBody UserDto dto) {
		logger.info("根据条件查询用户信息...");
		try {
			//参数校验
			if(ParamUtil.isEmpty(dto.getUserId(),dto.getUsername(), dto.getCardNo(), dto.getMobile(), dto.getEntryTime(), dto.getRoleCode())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
			//1.去除空格
			ParamUtil.trim(dto);
			//设置当前登录用户
			dto.setCreator(this.getCurrentUsername());
			userService.updateUserInfo(dto);
			return new Result(true, ErrorCode.S200.statusCode(), ErrorCode.S200.message(),null);
		} catch (Exception e) {
			logger.error("参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);
		}
	}


}
