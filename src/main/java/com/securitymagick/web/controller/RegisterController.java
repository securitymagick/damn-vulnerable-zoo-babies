package com.securitymagick.web.controller;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.domain.RegistrationForm;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.LogMessage;

@Controller
public class RegisterController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	LogDao logDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		RegistrationForm registrationForm = new RegistrationForm();
		model.addAttribute("registerForm", registrationForm);
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String showAccount(@ModelAttribute("registerForm") RegistrationForm registrationForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {
		
		if (registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
			userDao.addUser(registrationForm);	
			LogMessage lm = new LogMessage(null, registrationForm.getUsername(), request.getHeader("user-agent"), "Registered new user");	
			logDao.addLog(lm);
			return "redirect:/login?message=Registration successful.  Please log in.";
		} else {
			String message = "Passwords do not match!";
			request.setAttribute("message", message);
			return "register";
		}
	}
	
	/*public ModelAndView register() {

		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		
		RegistrationForm registrationForm = new RegistrationForm();
		model.addAttribute("registerForm", registrationForm);
		
		return model;

	}*/

}