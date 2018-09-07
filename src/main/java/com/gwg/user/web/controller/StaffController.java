package com.gwg.user.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home redirection to swagger api documentation
 */
@RestController
@Api(value = "staff", tags = "员工管理")
@RequestMapping({ "/staff" })
@Slf4j
public class StaffController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);


}
