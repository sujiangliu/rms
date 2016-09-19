package com.jack.rms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jack.rms.common.core.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@RequestMapping("/signIn")
	public String login(String username, String password, HttpServletRequest request) {
		
		System.out.println(username + "===" + password);
		
		request.setAttribute("username", username);
		return "index";
	}
}
