package com.jack.rms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jack.rms.common.core.BaseController;
import com.jack.rms.model.User;

@Controller
public class IndexController extends BaseController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		
		User user = this.getCurrUser();
		if (null == user) {
			return "redirect:/";
		}
		
		request.setAttribute("nickName", user.getNickName());
		
		return "index";
	}
}
