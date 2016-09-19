package com.jack.rms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jack.rms.common.core.BaseController;
import com.jack.rms.model.User;
import com.jack.rms.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService UserService; 

	@RequestMapping("/signIn")
	public String login(String username, String password, HttpServletRequest request) {
		
		System.out.println(username + "===" + password);
		
		User user = UserService.getUserByUsername(username);
		
		if (null == user) {
			System.out.println("用户不存在");
		}
		
		request.setAttribute("username", username);
		return "index";
	}
}
