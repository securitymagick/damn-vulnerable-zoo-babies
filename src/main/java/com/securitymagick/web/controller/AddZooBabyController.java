package com.securitymagick.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.securitymagick.domain.Post;
import com.securitymagick.domain.ZooBabyForm;
import com.securitymagick.web.cookie.CookieHandler;
import com.securitymagick.domain.dao.PostDao;

@Controller
public class AddZooBabyController {
	public static final String ROOT = "C:\\Users\\NTISNS01\\Desktop\\Demo\\Tools\\apache-tomcat-8.0.33\\webapps\\zoo-babies-1.0-SNAPSHOT\\resources\\core\\images";
	
	@Autowired
	PostDao postDao;

	@RequestMapping(value = "/addZooBaby", method = RequestMethod.GET)
	public ModelAndView addZooBaby(HttpServletRequest request) {
		request.setAttribute("part1", "part1");
		ModelAndView model = new ModelAndView();
		model.setViewName("addZooBaby");

		return model;

	}
	
	@RequestMapping(value = "/addZooBaby", method = RequestMethod.POST, params={"uploadfile"})
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
								    Model model, HttpServletRequest request) {
		CookieHandler userCookie = new CookieHandler("user");
		if (userCookie.checkForCookie(request)) {
			Cookie c = userCookie.getCookie();
			request.setAttribute("user", c.getValue());
		}								
		String message = "Unable to upload file.";
		if (!file.isEmpty()) {
			try {
				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				request.setAttribute("imageName", file.getOriginalFilename());
				request.setAttribute("part1", "");
				request.setAttribute("part2", "part2");
				ZooBabyForm zooBabyForm = new ZooBabyForm();
				model.addAttribute("zooBabyForm", zooBabyForm);
				message = "File uploaded successfully";
			}  catch (IOException|RuntimeException e) {
				message = "Exception:" + e.toString();
			}
		}
		request.setAttribute("message", message);
		return "addZooBaby";
	}
	
	@RequestMapping(value = "/addZooBaby", method = RequestMethod.POST, params={"createpost"}) 
	public String addPost(@ModelAttribute("zooBabyForm") ZooBabyForm zooBabyForm,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {

		Post p = new Post(null, zooBabyForm.getTitle(), zooBabyForm.getImageName(), zooBabyForm.getAuthor(), null);
		postDao.addPost(p);

		return "redirect:/public";		
	}
	
/*	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;

	}
*/
}