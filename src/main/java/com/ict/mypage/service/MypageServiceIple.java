package com.ict.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;
import com.ict.mypage.dao.MypageDAO;
import com.ict.mypage.dao.OrderListVO;
import com.ict.mypage.dao.PurchaseListVO;
import com.ict.mypage.dao.WishListVO;

@Service
public class MypageServiceIple implements MypageService{
	
	@Autowired
	private MypageDAO mypageDAO;
	
	@Override
	public List<OrderListVO> getOrderList(String u_idx) {
		return mypageDAO.getOrderList(u_idx);
	}
	
	@Override
	public int getDeliveryActive(String order_idx) {
		return mypageDAO.getDeliveryActive(order_idx);
	}
	
	@Override
	public List<PurchaseListVO> getPurchaseList(String u_idx) {
		return mypageDAO.getPurchaseList(u_idx);
	}
	
	@Override
	public List<WishListVO> getWishList(String u_idx) {
		return mypageDAO.getWishList(u_idx);
	}
	
	@Override
	public int getWishListDelete(String wish_idx) {
		return mypageDAO.getWishListDelete(wish_idx);
	}
	
	@Override
	public UserVO getUserDetail(String u_idx) {
		return mypageDAO.getUserDetail(u_idx);
	}
	
	@Override
	public int getUserUpdate(UserVO userVO) {
		return mypageDAO.getUserUpdate(userVO);
	}
	
	@Override
	public int getBasicAddrUpdate(String u_idx, String addr_idx) {
		return mypageDAO.getBasicAddrUpdate(u_idx, addr_idx);
	}
	
	@Override
	public int getAddrDelete(String addr_idx) {
		return mypageDAO.getAddrDelete(addr_idx);
	}
	
	@Override
	public int getAddrAdd(AddressVO addrVO) {
		return mypageDAO.getAddrAdd(addrVO);
	}
	
	@Override
	public int getResign(String u_idx) {
		return mypageDAO.getResign(u_idx);
	}
	
	
	
	
	//찜
	@Override
	public String getWishClick(String p_idx, String u_idx) {
		return mypageDAO.getWishClick(p_idx, u_idx);
	}
	@Override
	public String getWishDelete(String p_idx, String u_idx) {
		return mypageDAO.getWishDelete(p_idx, u_idx);
	}
	@Override
	public List<WishVO> getCategoryWishList(String u_idx) {
		return mypageDAO.getCategoryWishList(u_idx);
	}
	
}
 








