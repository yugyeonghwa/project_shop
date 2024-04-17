package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

public class LoginInterceptor implements AsyncHandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 로그인 체크해서 만약 로그인이 안된 상태이면 value에 false 저장
		
		
		// 만약 session이 삭제된 상태라면 새로운 session을 생성한다.
		// 세션이 삭제가 안된 상태라면 사용하고 있는 그대로 전달한다. 
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("loginChk");
		if(obj == null) {
			// 로그인을 하지 않은 상태일 때
			request.getRequestDispatcher("/WEB-INF/views/sign/login_page.jsp").forward(request, response);
		return false;
		}
		
		return true;
	}
	
}
