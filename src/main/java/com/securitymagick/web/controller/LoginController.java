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

import com.securitymagick.domain.Permissions;
import com.securitymagick.web.cookie.CookieHandler;
import com.securitymagick.domain.LoginForm;
import com.securitymagick.domain.User;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.LogMessage;

@Controller
public class LoginController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	LogDao logDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Model model, @RequestParam(value = "message", required = false) String message, HttpServletRequest request) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		if (message != null) {
			request.setAttribute("message", message);
		}
		return "login";
	}	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showAccount(@ModelAttribute("loginForm") LoginForm loginForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {
		LogMessage lm = new LogMessage(null, null, request.getHeader("user-agent"), "Trying to log in user with credentials: " + loginForm.toString());	
		logDao.addLog(lm);
		
		CookieHandler pCookie = new CookieHandler("permissions");
		CookieHandler userCookie = new CookieHandler("user");
		
		List<User> ulist = userDao.getUser(loginForm.getUsername());
		
		if (ulist.size() != 1) {
			String message = "An unexpected error occurred.  Please contact the admin";
			request.setAttribute("message", message);
			return "login";			
		}
		
		User u = ulist.get(0);
		
		if (loginForm.getPassword().equals(u.getPassword())) {
			Permissions p = new Permissions(u.getIsUser().equals(1), u.getIsAdmin().equals(1));
			pCookie.addCookie(response, p.getCookieValue());
			userCookie.addCookie(response, u.getUsername());
			return "redirect:/myAccount";
		} else {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p.getCookieValue());
			userCookie.addCookie(response, "");
			String message = "Incorrect username or password.  If you forgot your password please use the forgot password link.";
			request.setAttribute("message", message);
			return "login";
		}
	}
	
/*	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;

	}
*/
}