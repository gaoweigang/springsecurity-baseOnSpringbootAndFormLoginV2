package com.gwg.user.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import com.gwg.user.web.common.ErrorCode;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.dto.RoleDto;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Role;
import com.gwg.user.web.service.RoleService;
import com.gwg.user.web.util.ParamUtil;
import com.gwg.user.web.vo.RoleVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/role" })
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public Result addRole(@RequestBody RoleDto dto) {

		try {
			//参数去除空格
			ParamUtil.trim(dto);
			//参数校验
			if(ParamUtil.isEmpty(dto.getRoleCode(), dto.getRoleName())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
			roleService.addRole(dto);
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), null);
		} catch (BusinessException e) {
			logger.error("异常，请求参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}
	}

	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public Result updateRole(@RequestBody RoleDto dto) {
		try {
			//参数去除空格
			ParamUtil.trim(dto);
			//参数校验
			if(ParamUtil.isEmpty(dto.getRoleCode(), dto.getRoleName())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
			roleService.updateRoleById(dto);
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), null);
		} catch (BusinessException e) {
			logger.error("异常，请求参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}
	}

	/**
	 * 根据条件查询角色信息
	 */
  	@RequestMapping(value = "/queryRoleByLimit", method = RequestMethod.GET)
	public Result queryRoleByLimit(@RequestBody RoleDto dto) {
		try {
			//参数去除空格
			ParamUtil.trim(dto);
			//参数校验
			if(ParamUtil.isEmpty(dto.getRoleCode(), dto.getRoleName())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
			PageInfo<Role> page = roleService.queryRoleByLimit(dto);
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), null);
		} catch (BusinessException e) {
			logger.error("异常，请求参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}

	}

	/**
	 * 删除角色
	 */
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	public Result delRole(@RequestBody RoleDto dto) {
		try {
			//参数校验
			if(ParamUtil.isEmpty(dto.getId())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
			roleService.delRoleById(dto);
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), null);
		} catch (BusinessException e) {
			logger.error("异常，请求参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}

	}

	/**
	 * 查询角色具有哪些资源权限- 用于前端显示用
	 * @return
	 */
	@RequestMapping(value = "/queryRoleRelatedAllResById", method = RequestMethod.POST)
	public Result queryRoleRelatedAllResById(@RequestBody RoleDto dto) {

		try {
			//参数校验
			if(ParamUtil.isEmpty(dto.getId())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);

			}
			RoleVo vo = roleService.queryRoleRelatedAllResById(dto);
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), vo);
		} catch (BusinessException e) {
			logger.error("异常，请求参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}
	}

	/**
	 * 给角色授予资源权限
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "授予权限")
	@RequestMapping(value = "/grantResources", method = RequestMethod.POST)
	public Result<?> grantResources(@RequestBody RoleDto dto) {

		try {
			if(ParamUtil.isEmpty(dto.getId())){
				return new Result(false, ErrorCode.S401.statusCode(), ErrorCode.S401.message(),null);
			}
			roleService.grantResources(dto);
			//shiroService.updatePermission(); 刷新shiro权限
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), null);
		} catch (BusinessException e) {
			logger.error("异常，请求参数：{}，异常信息：{}", JSON.toJSON(dto), e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}
	}
}
