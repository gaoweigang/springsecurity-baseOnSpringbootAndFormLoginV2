package com.gwg.user.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HandlerMethodController {
	
	private static Logger logger = LoggerFactory.getLogger(HandlerMethodController.class);
	
	@RequestMapping("/login-success")
	public String success(){
		logger.info("重定向 success start .....");
		//登录成功之后处理
		return "redirect:success.html";
	}

	@RequestMapping("/login-error")
	public String failure(){
		logger.info("重定向  failure start .....");
		//登录失败之后处理
		return "redirect:failure.html";
	}
}
