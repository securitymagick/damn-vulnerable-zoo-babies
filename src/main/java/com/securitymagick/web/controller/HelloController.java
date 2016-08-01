package com.securitymagick.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import com.securitymagick.domain.Permissions;
import com.securitymagick.web.cookie.PermissionsCookie;

@Controller
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		PermissionsCookie pCookie = new PermissionsCookie();
		if (!pCookie.checkForCookie(request)) {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p);
		}
		
		model.addAttribute("message", "Zoo Babies");
		return "hello";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) {
		PermissionsCookie pCookie = new PermissionsCookie();
		if (!pCookie.checkForCookie(request)) {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p);
		}
	
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}

}