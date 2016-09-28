package com.jack.rms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.rms.common.core.BaseController;
import com.jack.rms.common.utils.MD5Util;
import com.jack.rms.model.PageResponse;
import com.jack.rms.model.User;
import com.jack.rms.model.request.UserQueryParam;
import com.jack.rms.service.IUserService;

import javafx.print.JobSettings;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService userService; 

	@ResponseBody
	@RequestMapping("/signIn")
	public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.getUserByUsername(username);
		
		if (null == user) {
			return "用户不存在";
		}
		
		if (!user.getPassword().equals(MD5Util.MD5(password))) {
			return "密码不正确";
		}
		request.getSession().setAttribute("user", user);
		return "200";
	}
	
	@RequestMapping("userPage")
	public String userPage() {
		return "/user/userList";
	}
	
	@ResponseBody
	@RequestMapping("list")
	public PageResponse list(UserQueryParam userQueryParam) {
		
		if (null == userQueryParam.getPageNumber()) {
			userQueryParam.setPageNumber(0);
		}
		if (null == userQueryParam.getPageSize()) {
			userQueryParam.setPageSize(10);
		}
		userQueryParam.setStartNumber((userQueryParam.getPageNumber() - 1) * userQueryParam.getPageSize());
		
		int total = userService.countUsersByPage(userQueryParam);
		List<User> users = userService.getUsersByPage(userQueryParam);
		
		PageResponse pageResponse = new PageResponse();
		pageResponse.setTotal(total);
		pageResponse.setPageNumber(userQueryParam.getPageNumber());
		pageResponse.setResults(users);
		
		return pageResponse;
	}
}
