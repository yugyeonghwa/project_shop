package com.ict.main.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@GetMapping("main_adclose.do")
	public ModelAndView getAdClose(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("adclose", "close");
		cookie.setMaxAge(Integer.parseInt(request.getParameter("adcookie")));
		response.addCookie(cookie);
		return new ModelAndView("redirect:/");
	}
}
