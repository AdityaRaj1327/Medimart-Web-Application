package com.softpro.MediMart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customerpanel")
public class CustomerController {

	@GetMapping("/home")
	public String ShowCustomerDashboard()
	{
	return "/customerpanel/customerdashboard";	
	}
	
	@GetMapping("/response")
	public String ShowResponse()
	{
		return "/customerpanel/giveresponse";
	}
	
}
