package com.securitymagick.web.controller;

import java.util.List;
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

import com.securitymagick.domain.User;
import com.securitymagick.web.cookie.CookieHandler;
import com.securitymagick.domain.UpdatePasswordForm;
import com.securitymagick.domain.UpdateZooBabyForm;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.LogMessage;


@Controller
public class UpdateAccountController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	LogDao logDao;

	@RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
	public String showUpdateAccountForm(Model model, HttpServletRequest request) {
		CookieHandler userCookie = new CookieHandler("user");
		if (userCookie.checkForCookie(request)) {
			Cookie c = userCookie.getCookie();
			request.setAttribute("user", c.getValue());
		}

		UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
		model.addAttribute("updatePasswordForm", updatePasswordForm);
		UpdateZooBabyForm updateZooBabyForm = new UpdateZooBabyForm();
		model.addAttribute("updateZooBabyForm", updateZooBabyForm);
		
		return "updateAccount";
	}	
	
	@RequestMapping (value="/updateAccount", method=RequestMethod.POST, params={"updatefavorite"})
	public String updateFavorite(@ModelAttribute("updateZooBabyForm") UpdateZooBabyForm updateZooBabyForm,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {

		String message = "Unexpected error.  Please Try again. or contact an admin";	
		CookieHandler userCookie = new CookieHandler("user");
		if (userCookie.checkForCookie(request)) {
			Cookie c = userCookie.getCookie();
			List<User> ulist = userDao.getUser(c.getValue());
			if (ulist.size() == 1) {
				User u = ulist.get(0);
				u.setFavorite(updateZooBabyForm.getFavorite());
				userDao.updateUser(u);		
				message = "The favorite zoo baby has been updated!";	
				LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Favorite Zoo Baby update successful for user.");	
				logDao.addLog(lm);				
			}
		}
		UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
		model.addAttribute("updatePasswordForm", updatePasswordForm);		
		request.setAttribute("message", message);	
		return "updateAccount";	
	}	
	
	
	@RequestMapping (value="/updateAccount", method=RequestMethod.POST, params={"updatepassword"})
	public String updatePassword(@ModelAttribute("updatePasswordForm") UpdatePasswordForm updatePasswordForm,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		String message = "Unexpected error.  Please Try again. or contact an admin";	
		CookieHandler userCookie = new CookieHandler("user");
		if (userCookie.checkForCookie(request)) {
			Cookie c = userCookie.getCookie();
			if (updatePasswordForm.getPassword().equals(updatePasswordForm.getConfirmPassword())) {
					List<User> ulist = userDao.getUser(c.getValue());
					if (ulist.size() == 1) {
						message = "Password update successful!";
						User u = ulist.get(0);
						LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Password update successful for user.");	
						logDao.addLog(lm);
						userDao.updatePassword(u.getUsername(), updatePasswordForm.getPassword());
					}
			} else {
				message = "Passwords do not match!";
			}
		} 
		UpdateZooBabyForm updateZooBabyForm = new UpdateZooBabyForm();
		model.addAttribute("updateZooBabyForm", updateZooBabyForm);		
		request.setAttribute("message", message);
		return "updateAccount";	
			
	}	
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showAccount(@ModelAttribute("loginForm") LoginForm loginForm,
		BindingResult result, Model model, HttpServletResponse response) {
		CookieHandler pCookie = new CookieHandler("permissions");
		CookieHandler userCookie = new CookieHandler("user");
		
		if (loginForm.getUsername().equals("RickPotter") && loginForm.getPassword().equals("WhiteRhino")) {
			Permissions p = new Permissions(true, true);
			pCookie.addCookie(response, p.getCookieValue());
			userCookie.addCookie(response, loginForm.getUsername());
			return "redirect:/myAccount";
		} else {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p.getCookieValue());
			userCookie.addCookie(response, "");
			return "login";
		}
	} */
	
/*	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;

	}
*/
}