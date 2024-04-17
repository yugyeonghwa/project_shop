package com.ict.cartegory.service;

import java.util.List;

import com.ict.cartegory.dao.OrderVO;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.ProductVO;

public interface CategoryService {

	List<ProductVO> getCategoryList(String p_category);

	List<ProductVO> getProductList(String p_idx);

	ProductVO getProductDetail(String p_idx);

	List<OptionVO> getOptionList(String p_idx);

	int getBasketGo(OrderVO ovo);

	OptionVO getOption(OrderVO ovo);
	
}
