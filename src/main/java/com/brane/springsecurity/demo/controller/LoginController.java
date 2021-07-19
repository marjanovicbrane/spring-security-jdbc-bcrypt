package com.brane.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//CONTROLLER CLASS 1
@Controller
public class LoginController {
	
	//we want to show our custom login form with this mapping /showMyLoginPage which we have defined
	//in our JAVA CONFIG CLASS DemoSecurityConfig.
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "fancy-login";
	}

	
	//we want to show our access denied page, for example when user try to access MANAGER or ADMIN 
	//information, with user ROLE EMPLOYEE.
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
	}
	
}
