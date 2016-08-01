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

import com.securitymagick.domain.Post;
import com.securitymagick.domain.PostComment;
import com.securitymagick.web.cookie.CookieHandler;
import com.securitymagick.domain.dao.PostDao;

@Controller
public class DeletePostController {
	@Autowired
	PostDao postDao;
	
	private List<Post> posts = null;

	@RequestMapping(value = "/deletepost", method = RequestMethod.GET)
	public String showPost(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, Model model) {
		Post postToDelete = new Post();
		model.addAttribute("postToDelete", postToDelete);
		List<Post> posts= postDao.getPosts();
		Integer postid = new Integer(id);

		for (Post p1 : posts) {
			if (p1.getId().equals(postid)) {
				request.setAttribute("post", p1);
			} 
		}
		
		return "deletepost";

	}

	@RequestMapping(value = "/deletepost", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("post") Post postToDelete,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		postDao.deletePost(postToDelete.getId());	
		String message = "The post has been deleted!";
		request.setAttribute("message", message);
		
		return "redirect:/posts";
	}
}