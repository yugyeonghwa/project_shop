package com.ict.faq.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.common.dao.FaqVO;

@Repository
public class FaqDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<FaqVO> getFaqList() {
		try {
			return sqlSessionTemplate.selectList("faq.faq_list");
		} catch (Exception e) {
			System.out.println("faq_list err : "+e);
		}
		return null;
	}
	
	
}
