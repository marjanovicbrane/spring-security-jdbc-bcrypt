package com.brane.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//CONTROLLER CLASS 2
@Controller
public class DemoController {
	
	
	//request mapping for root of our application
	@GetMapping("/")
	public String showHome() {

		return "home";
	}
	
	
	//request mapping /leaders for MENAGER ROLE
	@GetMapping("/leaders")
	public String showLeaders() {

		return "leaders";
	}
	
	
	//request mapping /systems for ADMIN ROLE
	@GetMapping("/systems")
	public String showSystems() {

		return "systems";
	}
	
}
