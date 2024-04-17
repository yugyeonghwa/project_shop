package com.ict.cartegory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.common.dao.UserVO;
import com.ict.mypage.service.MypageService;

@RestController
public class AjaxWishController {

	@Autowired
	private MypageService mypageService;
	
	@RequestMapping(value = "wish_insert.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishInsert(String p_idx, HttpSession session) {
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		String result = mypageService.getWishClick(p_idx,uvo.getU_idx());
		
		return result;
	}
	
	@RequestMapping(value = "wish_delete.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishDelete(String p_idx, HttpSession session) {
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		
		String result = mypageService.getWishDelete(p_idx,uvo.getU_idx());
		
		return result;
	}
	
	
	
}
