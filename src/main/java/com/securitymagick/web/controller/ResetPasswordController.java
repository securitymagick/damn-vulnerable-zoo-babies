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
import javax.servlet.http.Cookie;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.securitymagick.web.cookie.CookieHandler;
import com.securitymagick.domain.ResetPasswordForm;
import com.securitymagick.domain.User;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.LogMessage;

@Controller
public class ResetPasswordController {
	@Autowired
	UserDao userDao;

	@Autowired
	LogDao logDao;
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String showResetPasswordForm(Model model) {
		ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
		model.addAttribute("resetPasswordForm", resetPasswordForm);
		return "resetpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String checkAccount(@ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {
	
		CookieHandler userCookie = new CookieHandler("user");
		if (userCookie.checkForCookie(request)) {
			Cookie c = userCookie.getCookie();
			if (resetPasswordForm.getPassword().equals(resetPasswordForm.getConfirmPassword())) {
					List<User> ulist = userDao.getUser(c.getValue());
					if (ulist.size() != 1) {
						return "redirect:/forgotpassword?message=Unexpected error.  Please Try again.";		
					}
					User u = ulist.get(0);
					if (u.getFavorite().equals(resetPasswordForm.getFavorite())) {
						LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Password reset successful for user.");	
						logDao.addLog(lm);
						userDao.updatePassword(u.getUsername(), resetPasswordForm.getPassword());
						return "redirect:/login?message=Password updated.  Please login.";
					}
					else {
						LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Password reset failed for user.");	
						logDao.addLog(lm);
						String message = "Incorrect information!";
						request.setAttribute("message", message);
						return "resetpassword";	
					}
			} else {
				String message = "Passwords do not match!";
				request.setAttribute("message", message);
				return "resetpassword";	
			}
		} else {
			return "redirect:/forgotpassword?message=Unexpected error.  Please Try again.";
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