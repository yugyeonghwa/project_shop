package com.ict.order.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.BasketVO;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.Order_pdVO;
import com.ict.common.dao.Pro_ImgVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;

@Repository
public class OrderDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	 private DataSourceTransactionManager transactionManager;
	
	// 장바구니 리스트 가져오기
	public List<BasketVO> getBasketList(String u_idx) {
		try {
			List<BasketVO> list = sqlSessionTemplate.selectList("common.basket_list", u_idx);
			return list;
		} catch (Exception e) {
			System.out.println("장바구니 리스트 가져오던 도중 err"+e);
		}
		return null;
	}
	
	// 상품 옵션 가져오기
	public List<OptionVO> getOptionList(String p_idx) {
		try {
			return sqlSessionTemplate.selectList("common.option_list", p_idx);
		} catch (Exception e) {
			System.out.println("상품 옵션 리스트 가져오던 도중 err"+e);
		}
		return null;
	}

	public int getBasketUpdate(BasketVO basketVO) {
		try {
			sqlSessionTemplate.update("common.basket_QuantEdit", basketVO);
			
			return 1;
		} catch (Exception e) {
			System.out.println("장바구니 수량 변경 DB err"+e);
		}
		return 0;
	}

	public int getBasketProDelete(String k) {
		try {
			return sqlSessionTemplate.delete("common.basket_delete", k);
		} catch (Exception e) {
			System.out.println("장바구니 삭제 DB err"+e);
		}
		return 0;
	}

	public List<AddressVO> getAddrList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("common.addr_list", u_idx);
		} catch (Exception e) {
			System.out.println("유저 주소리스트 가져오는 DB err"+e);
		}
		return null;
	}
	public AddressVO getAddrBasic(String u_idx) {
		try {
			return sqlSessionTemplate.selectOne("common.addr_basic", u_idx);
		} catch (Exception e) {
			System.out.println("유저 기본 주소 가져오는 DB err"+e);
		}
		return null;
	}

	public List<Pro_ImgVO> getProImg(String p_idx) {
		try {
			return sqlSessionTemplate.selectList("common.proImg_list", p_idx);
		} catch (Exception e) {
			System.out.println("상품 대표 이미지 가져오는 DB err"+e);
		}
		return null;
	}

	public BasketVO getTempBasket(String k) {
		try {
			return sqlSessionTemplate.selectOne("common.tempBasket_list", k);
		} catch (Exception e) {
			System.out.println("구매용 장바구니 DB err"+e);
		}
		return null;
	}

	public String Addr_add(TempOrderVO tempOrderVO) {
		try {
			return String.valueOf(sqlSessionTemplate.insert("common.Ajaxaddr_add", tempOrderVO));
		} catch (Exception e) {
			System.out.println("주문도중 AJAX배송지 추가 DB err"+e);
		}
		return null;
	}

	public List<AddressVO> getAjaxAddrList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("common.Ajaxaddr_list", u_idx);
		}catch (Exception e) {
			System.out.println("주문도중 AJAX 배송지 리스트가져오기 DB err"+e);
		}
		return null;
	}

	public int getSetBasicSet(String addr_idx, UserVO uvo) {
		 TransactionDefinition def = new DefaultTransactionDefinition();
	     TransactionStatus status = transactionManager.getTransaction(def);
	     try {
	    	 int res = 0;
	    	 res += sqlSessionTemplate.update("common.selected_delete", uvo.getU_idx());
	    	 res += sqlSessionTemplate.update("common.selected_update", addr_idx);
	    	 
	    	 transactionManager.commit(status);
	    	 
	    	 return res;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("배송지 변경 DB 에러" + e);
		}
	     
	     
		return 0;
	}

	public int getPayCheck(TempOrderVO tempOrderVO, List<BasketVO> basket_list, UserVO userVO) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			int res = 0;
			// 각각의 제품들 마다 주문 테이블 삽입
			for (BasketVO k : basket_list) {
				res += sqlSessionTemplate.insert("common.orderPro_insert", k);
				// 방금 삽입한 행의 idx 가져오기
			    String lastInsertedIdx = sqlSessionTemplate.selectOne("common.getLastInsertedIdx");
				
				tempOrderVO.setOption_idx(k.getO_idx());
				tempOrderVO.setOrder_pd_idx(lastInsertedIdx);
				
				res += sqlSessionTemplate.insert("common.order_insert", tempOrderVO);
				res += sqlSessionTemplate.delete("common.OrderPro_delete", k);
			}
	
				res += sqlSessionTemplate.update("common.userPointChange", userVO);
			// 결제 성공
			transactionManager.commit(status);
			return res;
		} catch (Exception e) {
			// 결제 실패
			transactionManager.rollback(status);
			System.out.println("결제 중 DB 에러" + e);
		}

		return 0;
	}

	public AddressVO getAddrSelect(String u_idx) {
		try {
			return sqlSessionTemplate.selectOne("common.getSelectedAddr", u_idx);
		} catch (Exception e) {
			System.out.println("선택 배송지 가져오기 db 에러"+e);
		}
		return null;
	}

	public int getWishDel(WishVO wishVO) {
		try {
			
			return sqlSessionTemplate.delete("common.wish_delete", wishVO);
			
		} catch (Exception e) {
			
			System.out.println("위시 리스트 삭제 db 에러"+e);
		}
		return 0;
	}
}
