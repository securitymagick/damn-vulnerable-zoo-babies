package com.securitymagick.web.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
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

import com.securitymagick.web.cookie.CookieHandler;
import com.securitymagick.domain.UsernameForm;
import com.securitymagick.domain.User;
import com.securitymagick.domain.dao.UserDao;

@Controller
public class ForgotPasswordController {
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String showForgotPassword(Model model, @RequestParam(value = "message", required = false) String message, HttpServletRequest request) {
		UsernameForm usernameForm = new UsernameForm();
		model.addAttribute("usernameForm", usernameForm);
		if (message != null) {
			request.setAttribute("message", message);
		}
		return "forgotpassword";
	}	
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String checkAccount(@ModelAttribute("usernameForm") UsernameForm usernameForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {

		CookieHandler userCookie = new CookieHandler("user");
		List<User> ulist = userDao.getUser(usernameForm.getUsername());
		
		if (ulist.size() != 1) {
			String message = "The username you entered is not registered.";
			request.setAttribute("message", message);
			userCookie.addCookie(response, "");
			return "forgotpassword";	
		}
		
		User u = ulist.get(0);
		userCookie.addCookie(response, usernameForm.getUsername());
		return "redirect:/resetpassword";

	}
	
/*	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;

	}
*/
}