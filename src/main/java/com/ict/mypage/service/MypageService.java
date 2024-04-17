package com.ict.mypage.service;

import java.util.List;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;
import com.ict.mypage.dao.OrderListVO;
import com.ict.mypage.dao.PurchaseListVO;
import com.ict.mypage.dao.WishListVO;

public interface MypageService {
	
	//	주문목록
	public List<OrderListVO> getOrderList(String u_idx);
	
	//	3일동안 구매확정 안누르면 delivery_active 값을 1로 업데이트
	public int getDeliveryActive(String order_idx);
	
	//	구매목록
	public List<PurchaseListVO> getPurchaseList(String u_idx);
	
	//	찜목록
	public List<WishListVO> getWishList(String u_idx);
	
	//	찜목록 삭제
	public int getWishListDelete(String wish_idx);
	
	//	회원정보 가져오기
	public UserVO getUserDetail(String u_idx);
	
	//	회원정보 수정
	public int getUserUpdate(UserVO userVO);
	
	//	기본배송지 변경
	public int getBasicAddrUpdate(String u_idx, String addr_idx);

	//	일반배송지 삭제
	public int getAddrDelete(String addr_idx);
	
	//	일반배송지 추가
	public int getAddrAdd(AddressVO addrVO);
	
	//	회원탈퇴
	public int getResign(String u_idx);
	
	
	//	찜기능
	public String getWishClick(String p_idx, String u_idx);
	public String getWishDelete(String p_idx, String u_idx);

	public List<WishVO> getCategoryWishList(String u_idx);
	
	
	
	
}
