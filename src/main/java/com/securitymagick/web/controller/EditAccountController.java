package com.securitymagick.web.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.securitymagick.domain.dao.UserDao;

@Controller
public class EditAccountController {
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/editaccount", method = RequestMethod.GET)
	public String showEditAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, Model model) {
		User userToEdit = new User();
		model.addAttribute("userToEdit", userToEdit);
		List<User> users= userDao.getUsers();
		Integer userid = new Integer(id);

		for (User u1 : users) {
			if (u1.getId().equals(userid)) {
				request.setAttribute("user", u1);
			} 
		}
		
		return "editaccount";

	}

	@RequestMapping(value = "/editaccount", method = RequestMethod.POST)
	public String editAccount(@ModelAttribute("userToEdit") User userToEdit,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		userDao.updateUser(userToEdit);	
		String message = "The account has been modified!";
		request.setAttribute("message", message);
		
		return "redirect:/accounts";
	}
}