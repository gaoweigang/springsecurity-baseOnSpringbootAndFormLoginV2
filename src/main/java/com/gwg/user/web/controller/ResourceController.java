package com.gwg.user.web.controller;


import com.gwg.user.web.common.ErrorCode;
import com.gwg.user.web.common.Result;
import com.gwg.user.web.exception.BusinessException;
import com.gwg.user.web.model.Resource;
import com.gwg.user.web.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({ "/resource" })
@Api(value = "resourceController", tags = "资源管理")
public class ResourceController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	/**
	 * 在登陆之后调用 -- 初始化资源
	 * @return
	 */
	@RequestMapping(value = "/queryAllResources", method = RequestMethod.GET)
	public Result queryAllResources() {

		try {
			List<Resource> resourceList = resourceService.queryAllResources();
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), resourceList);
		} catch (BusinessException e) {
			logger.error("异常:{}", e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);

		}
	}


	/**
	 * 登陆成功之后根据用户角色获取菜单
	 */
	@ApiOperation(value = "加载菜单")
	@RequestMapping(value = "/queryCurrentUserMenu", method = RequestMethod.GET)
	public Result queryCurrentUserMenu(){

		try {
			List<Resource> resourceList = resourceService.queryCurrentUserMenu(this.getCurrentUsername());
			return new Result(true,  ErrorCode.S200.statusCode(), ErrorCode.S200.message(), resourceList);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
			return new Result(false, ErrorCode.S501.statusCode(), ErrorCode.S501.message(),null);
		}

	}



}
