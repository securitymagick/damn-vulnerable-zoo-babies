package com.securitymagick.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyAccountController {


	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public ModelAndView myAccount() {

		ModelAndView model = new ModelAndView();
		model.setViewName("myAccount");

		return model;

	}

}