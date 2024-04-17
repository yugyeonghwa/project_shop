package com.ict.common.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.ict.common.dao.AddressVO;
import com.ict.common.dao.BasketVO;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.ProductVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;
import com.ict.common.service.CommonService;
import com.ict.mypage.service.MypageService;

@Controller
public class CommonController {
	
	@Autowired
	private CommonService commonService;

	
	// 메인 페이지 이동
	@GetMapping("/")
	public ModelAndView getFirst(HttpServletRequest request, HttpSession session) {
		 Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookie.getName().equals("adclose")) {
		                String value = cookie.getValue();
		                request.setAttribute("adclose", value);
		            }
		        }
		    }
		return new ModelAndView("main_page");
	}

	// 회원가입 페이지 이동
	@GetMapping("signup_page.do")
	public ModelAndView getSignupPage() {
		return new ModelAndView("sign/signup_page");
	}

	// 로그인 페이지 이동
	@GetMapping("login_page.do")
	public ModelAndView getLoginPage() {
		return new ModelAndView("sign/login_page");
	}
	
	// 아이디 찾기 페이지 이동
	@GetMapping("findID_page.do")
	public ModelAndView getFindIDPage() {
		return new ModelAndView("sign/findID_page");
	}
	
	// 비밀번호 찾기 페이지 이동
	@GetMapping("findPWD_page.do")
	public ModelAndView getFindPWDPage() {
		return new ModelAndView("sign/findPWD_page");
	}
	
	// 비밀번호 재설정 페이지 이동
	@GetMapping("repwd_page.do")
	public ModelAndView getRePwdPage() {
		return new ModelAndView("sign/repwd_page");
	}
	
	// 고객센터 이동
	@GetMapping("community_page.do")
	public ModelAndView getCommunityPage() {
		return new ModelAndView("community/community_page");
	}
	
	// 마이페이지 이동
	@Autowired
	private MypageService mypageService;
	@GetMapping("mypage_page.do")
	public ModelAndView geteMypagePage(HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("mypage/myPage_page");
			
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			
			UserVO userVO = mypageService.getUserDetail(uvo.getU_idx());
			
			List<AddressVO> addr_list = userVO.getAddr_list();
			
			mv.addObject("userVO", userVO);
			mv.addObject("addr_list", addr_list);
			
			return mv;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	// 장바구니 이동
	@GetMapping("basket_page.do")
	public ModelAndView getBasketListPage(HttpSession session) {
			ModelAndView mv = new ModelAndView("order/basket_page");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			List<BasketVO> basket_list = commonService.getBasketList(uvo.getU_idx());
			List<OptionVO> option_list = new ArrayList<OptionVO>();
			Set<String> optionIdxSet = new HashSet<String>();
			
			for (BasketVO k : basket_list) {
			// 장바구니에 같은 상품이 있을 경우 옵션 리스트에는 하나만 담음
				List<OptionVO> options = commonService.getOptionList(k.getP_idx()); 
				for (OptionVO option : options) {
					if(!optionIdxSet.contains(option.getOption_idx())) {
						option_list.add(option);
						optionIdxSet.add(option.getOption_idx());
					}
				}
			}
			
			mv.addObject("basket_list", basket_list);
			mv.addObject("option_list", option_list);
		return mv;
	}
	
	@PostMapping("wishDel_go.do")
	public ModelAndView getBasketAdd(HttpSession session, WishVO wishVO) {
		ModelAndView mv = new ModelAndView("redirect:wishlist_page.do");
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		int res = commonService.getWishDel(wishVO);
		
		
		
		mv.addObject("u_idx", uvo.getU_idx());
		return mv;
	}
}
