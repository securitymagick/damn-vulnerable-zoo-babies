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
public class CommentsController {
	@Autowired
	PostDao postDao;

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public String showPost(HttpServletRequest request) {
		List<PostComment> comments= postDao.getComments();
		request.setAttribute("comments", comments);
		return "comments";
	}
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET, params={"delete"})
	public String showPost(HttpServletRequest request, @RequestParam("delete") String id) {
		postDao.deleteComment(id);
		List<PostComment> comments= postDao.getComments();
		request.setAttribute("comments", comments);
		return "comments";
	}
	

	/*@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("postComment") PostComment postComment,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		postDao.addComment(postComment);	
		Integer postid = new Integer(postComment.getPostid());
		String message = "Your comment has been added!";
		request.setAttribute("message", message);
		List<Post> posts= postDao.getPostsWithComments();
			
		for (Post p1 : posts) {
			if (p1.getId().equals(postid)) {
				request.setAttribute("post", p1);
			} 
		}
		
		return "post";
	}*/
}