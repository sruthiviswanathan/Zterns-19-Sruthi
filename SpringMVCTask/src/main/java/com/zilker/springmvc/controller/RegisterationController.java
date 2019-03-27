package com.zilker.springmvc.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zilker.springmvc.bean.User;
import com.zilker.springmvc.delegate.UserDelegate;

@Controller
public class RegisterationController {

	@Autowired 
	UserDelegate userDelegate;
	
	 @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		    System.out.println("inShowRegister");
	    ModelAndView mav = new ModelAndView("register");
	    mav.addObject("user", new User());
	
	    return mav;
	  }
	
	
	  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("user") User user) {
	 
		  
	  userDelegate.register(user);
	  return new ModelAndView("welcome", "firstname", user.getName());
	  }
	 
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new User());
	    return mav;
	  }
	
	
	  @RequestMapping(value = "/login", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") User user) {
	    ModelAndView mav = null;
	    User userObj = userDelegate.validateUser(user);
	    if (null != userObj.getName()) {
	    mav = new ModelAndView("welcome");
	    mav.addObject("firstname", userObj.getName());
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }

	 
	  
}
	
	
