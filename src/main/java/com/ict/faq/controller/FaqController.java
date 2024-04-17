package com.ict.faq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.dao.FaqVO;
import com.ict.faq.service.FaqService;

@Controller
public class FaqController {
	@Autowired
	private FaqService faqService;
	
	// FAQ 게시판 이동
	@RequestMapping("faq_list.do")
	public ModelAndView getFaqPage() {
		ModelAndView mv = new ModelAndView("community/faq_page");
		
		List<FaqVO> faq_list = faqService.getFaqList();
		for (FaqVO k : faq_list) {
			k.getFaq_idx();
		}
		if (faq_list != null) {
			mv.addObject("faq_list", faq_list);
			return mv;
		}
		return new ModelAndView("common/error");
	}
	
	
}
