package com.ict.cartegory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.cartegory.dao.CategoryDAO;
import com.ict.cartegory.dao.OrderVO;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.ProductVO;

// Service와 연동
@Service
public class CategoryServiceImpl implements CategoryService {
	// DAO랑 연동
	@Autowired
	private CategoryDAO categoryDAO;
		
	@Override
	public List<ProductVO> getCategoryList(String p_category) {
		return categoryDAO.getCategoryList(p_category);
	}

	@Override
	public List<ProductVO> getProductList(String p_idx) {
		return categoryDAO.getProductList(p_idx);
	}

	@Override
	public ProductVO getProductDetail(String p_idx) {
		return categoryDAO.getProductDetail(p_idx);
	}

	@Override
	public List<OptionVO> getOptionList(String p_idx) {
		return categoryDAO.getOptionList(p_idx);
	}

	@Override
	public int getBasketGo(OrderVO ovo) {
		return categoryDAO.getBasketGo(ovo);
	}

	@Override
	public OptionVO getOption(OrderVO ovo) {
		return categoryDAO.getOption(ovo);
	}

}
