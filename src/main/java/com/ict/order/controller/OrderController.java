package com.ict.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.BasketVO;
import com.ict.common.dao.OrderVO;
import com.ict.common.dao.Pro_ImgVO;
import com.ict.common.dao.UserVO;
import com.ict.common.service.CommonService;
import com.ict.order.dao.TempOrderVO;
import com.ict.order.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired 
	private CommonService commonService;
	
	@PostMapping("basket_edit.do")
	public ModelAndView getBasketEdit(BasketVO basketVO, HttpServletRequest request) {
		try {
			int res = orderService.getBasketUpdate(basketVO);
			
			return new ModelAndView("redirect:basket_page.do");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return new ModelAndView("common/error");
	}
	
	@PostMapping("basket_delete.do")
	public ModelAndView getBasketDelete(String[] b_idx) {
		ModelAndView mv = new ModelAndView("redirect:basket_page.do");
		for (String k : b_idx) {
			int res = orderService.getBasketProDelete(k);
		}
		
		return mv;
	}
	@RequestMapping("order_page.do")
	public ModelAndView getOrderPage(String[] b_idx, HttpSession session, @ModelAttribute("paychk") String paychk, @ModelAttribute("pointfail") String pointfail) {
		ModelAndView mv = new ModelAndView("order/order_page");
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		List<BasketVO> basket_list = new ArrayList<BasketVO>();
		for (String k : b_idx) {
			BasketVO basketVO = commonService.getTempBasket(k);
			basket_list.add(basketVO);
		}
		AddressVO addr_basic = commonService.getAddrBasic(uvo.getU_idx());
		mv.addObject("addr_basic", addr_basic);
		mv.addObject("basket_list", basket_list);
		return mv;
	}

	
	@PostMapping("paymentCheck.do")
	public ModelAndView getPayCheck(OrderVO orderVO, HttpSession session, TempOrderVO tempOrderVO, @ModelAttribute("b_idx") String[] b_idx) {
		ModelAndView mv = new ModelAndView("redirect:mypage_page.do");
		// 결제 진행 
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		int res = uvo.getU_point() - tempOrderVO.getOrderpriceTotal();
		if(res > 0) {
			uvo.setU_point(res);
			tempOrderVO.setU_idx(uvo.getU_idx());
			List<BasketVO> basket_list = new ArrayList<BasketVO>();
			for (String k : b_idx) {
				BasketVO basketVO = commonService.getTempBasket(k);
				basket_list.add(basketVO);
			}
			AddressVO addrVO = commonService.getAddrSelect(uvo.getU_idx());
			
			tempOrderVO.setAddr_idx(addrVO.getAddr_idx());
			
			// 결제 진행
			res = commonService.getPayCheck(tempOrderVO, basket_list, uvo);
		
			if(res>0) {
				return mv;
			} else {
				mv.addObject("paychk", "fail");
				mv.setViewName("redirect:order_page.do");
				return mv;
			}
			
		}else {
			System.out.println("금액부족");
			mv.addObject("paychk", "fail");
			mv.addObject("pointfail", "ok");
			mv.setViewName("redirect:order_page.do");
			return mv;
		}
	}

}
