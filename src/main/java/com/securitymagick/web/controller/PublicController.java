package com.securitymagick.web.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;

import com.securitymagick.domain.Post;
import com.securitymagick.domain.dao.PostDao;


@Controller
public class PublicController {

	@Autowired
	PostDao postDao;

	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public ModelAndView showPublic(HttpServletRequest request) {
		
		List<Post> posts= postDao.getPosts();
		request.setAttribute("posts", posts);
		ModelAndView model = new ModelAndView();
		model.setViewName("public");

		return model;

	}
	/*public String showLoginForm(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		return "login";
	}	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showAccount(@ModelAttribute("loginForm") LoginForm loginForm,
		BindingResult result, Model model, HttpServletResponse response) {
		PermissionsCookie pCookie = new PermissionsCookie();
		
		if (loginForm.getUsername().equals("RickPotter") && loginForm.getPassword().equals("WhiteRhino")) {
			Permissions p = new Permissions(true, true);
			pCookie.addCookie(response, p);
			return "redirect:/myAccount";
		} else {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p);
			return "login";
		}
	}*/
	
/*	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;

	}
*/
}