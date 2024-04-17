package com.ict.cartegory.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.common.dao.OptionVO;
import com.ict.common.dao.ProductVO;

@Repository
public class CategoryDAO {
	
	@Autowired
	// mapper 와의 통로를 연결한거다.
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<ProductVO> getCategoryList(String p_category) {	
		try {
			return sqlSessionTemplate.selectList("product.selectList",p_category);
		} catch (Exception e) {
			System.out.println("CateDAO에서 에러발생");
		}
		
		return null;
	}

	public List<ProductVO> getProductList(String p_idx) {
		try {
			return sqlSessionTemplate.selectList("product.productlist",p_idx);
		} catch (Exception e) {
			System.out.println("CateDAO에서 에러발생");
		}
		return null;
	}

	public ProductVO getProductDetail(String p_idx) {
		try {
			return sqlSessionTemplate.selectOne("product.producet_detail",p_idx);
		} catch (Exception e) {
			System.out.println("CateDAO에서 에러발생");
		}
		return null;
	}

	public List<ProductVO> getRowList(String p_category) {
		try {
			return sqlSessionTemplate.selectList("product.product_rowlist", p_category);
		} catch (Exception e) {
			System.out.println("CategoryDAO에서 에러 발생함. (p_price row)");
		}
		return null;
	}

	public List<ProductVO> getHighList(String p_category) {
		try {
			return sqlSessionTemplate.selectList("product.product_rowlist", p_category);
		} catch (Exception e) {
			System.out.println("CategoryDAO에서 에러 발생함. (p_price high)");
		}
		return null;
	}
	
	public List<OptionVO> getOptionList(String p_idx) {
		try {
			return sqlSessionTemplate.selectList("product.OptionList" , p_idx);
		} catch (Exception e) {
			System.out.println("optionlist optionDAO에서 에러발생");
		}
		
		return null;
	}

	public int getBasketGo(OrderVO ovo) {
		try {
			return sqlSessionTemplate.insert("product.BasketList", ovo);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("basket dao에서 에러발생");
		}
		return 0;
	}

	public OptionVO getOption(OrderVO ovo) {
		try {
			return sqlSessionTemplate.selectOne("product.option", ovo);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("option dao 에서 에러 발생");
		}
		return null;
	}

}