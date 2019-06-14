package com.buseni.adminpwa.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@GetMapping("/callback")
	public String callback(HttpServletRequest request) {
		return  "Code : " + request.getParameter("code");
	}

}
