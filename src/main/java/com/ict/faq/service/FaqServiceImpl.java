package com.ict.faq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.FaqVO;
import com.ict.faq.dao.FaqDAO;

@Service
public class FaqServiceImpl implements FaqService{
	@Autowired
	private FaqDAO faqDAO;
	
	@Override
	public List<FaqVO> getFaqList() {
		return faqDAO.getFaqList();
	}
	
	
}

