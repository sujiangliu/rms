package com.jack.rms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.rms.common.core.BaseController;
import com.jack.rms.common.utils.MD5Util;
import com.jack.rms.model.PageResponse;
import com.jack.rms.model.User;
import com.jack.rms.model.request.UserQueryParam;
import com.jack.rms.service.IUserService;

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
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(User user) {
		
		if (null == user) {
			return "用户信息不正确";
		}
		User user2 = userService.getUserByUsername(user.getUsername());
		if (null != user2) {
			return "用户名已存在";
		}
		user2 = userService.getUserByMobile(user.getMobile());
		if (null != user2) {
			return "手机号码已存在";
		}
		user.setPassword(MD5Util.MD5(user.getPassword()));
		
		int i = userService.saveUser(user);
		if (i < 1) {
			return "新增失败";
		}
		return "200";
	}
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(User user) {
		
		if (null == user) {
			return "用户信息不正确";
		}
		User user2 = userService.getUserByUsername(user.getUsername());
		if (null != user2 && !user.getId().equals(user2.getId())) {
			return "用户名已存在";
		}
		user2 = userService.getUserByMobile(user.getMobile());
		if (null != user2 && !user.getMobile().equals(user2.getMobile())) {
			return "手机号码已存在";
		}
		if (!StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(MD5Util.MD5(user.getPassword()));
		}
		
		int i = userService.updateUser(user);
		if (i < 1) {
			return "修改失败";
		}
		return "200";
	}
	@RequestMapping("/del")
	@ResponseBody
	public String del(Integer userId) {
		
		if (null == userId) {
			return "请选择需要删除的用户";
		}
		
		int i = userService.delUser(userId);
		if (i < 1) {
			return "删除失败";
		}
		return "200";
	}
}
