package com.ict.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;



@Repository
public class MypageDAO {
	
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<OrderListVO> getOrderList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("mypage.orderlist", u_idx);
		} catch (Exception e) {
			System.out.println("getOrderList : " + e);
		}
		return null;
	}
	
	public int getDeliveryActive(String order_idx) {
		try {
			return sqlSessionTemplate.update("mypage.delivery_active", order_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public List<PurchaseListVO> getPurchaseList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("mypage.purchaselist", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<WishListVO> getWishList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("mypage.wishlist", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getWishListDelete(String wish_idx) {
		try {
			return sqlSessionTemplate.delete("mypage.wishlist_delete", wish_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public UserVO getUserDetail(String u_idx) {
		try {
			return sqlSessionTemplate.selectOne("mypage.detail", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getUserUpdate(UserVO userVO) {
		try {
			return sqlSessionTemplate.update("mypage.update", userVO);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public int getBasicAddrUpdate(String u_idx, String addr_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.update("mypage.basic_delete", u_idx);
			result += sqlSessionTemplate.update("mypage.basic_update", addr_idx);
			transactionManager.commit(status);
			System.out.println("기본배송지 변경 성공");
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("기본배송지 변경 실패");
		}
		return -1;
	}
	
	public int getAddrDelete(String addr_idx) {
		try {
			return sqlSessionTemplate.delete("mypage.addr_delete", addr_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public int getAddrAdd(AddressVO addrVO) {
		try {
			return sqlSessionTemplate.insert("mypage.addr_insert", addrVO);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public int getResign(String u_idx) {
		try {
			return sqlSessionTemplate.update("mypage.resign", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	
	
	
	//찜
	
	public String getWishClick(String p_idx, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("p_idx", p_idx);
			map.put("u_idx", u_idx);
			result += sqlSessionTemplate.insert("mypage.wish_insert",map);
//			result += sqlSessionTemplate.update("mypage.active_update",map);
			transactionManager.commit(status);
			System.out.println("찜목록 추가 성공");
			return "♥";
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("찜목록 추가 실패");
		}
		return null;
	}
	
	public String getWishDelete(String p_idx, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			System.out.println("찜삭제 p"+p_idx);
			System.out.println("찜삭제 u"+u_idx);
			Map<String, String> map = new HashMap<String, String>();
			map.put("p_idx", p_idx);
			map.put("u_idx", u_idx);
			result += sqlSessionTemplate.delete("mypage.wish_delete", map);
//			result += sqlSessionTemplate.update("mypage.active_update2", map);
			transactionManager.commit(status);
			System.out.println("찜목록 삭제 성공");
			return "♡";
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("찜목록 삭제 실패");
		}
		return null;
	}
	
	// 카테고리에 하트 가져올 위시리스트
	public List<WishVO> getCategoryWishList(String u_idx) {
		return sqlSessionTemplate.selectList("mypage.cateWish_select", u_idx);
	}
	
	
}







