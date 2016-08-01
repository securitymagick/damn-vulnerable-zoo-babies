package com.securitymagick.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {


	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView about() {

		ModelAndView model = new ModelAndView();
		model.setViewName("admin");

		return model;

	}

}