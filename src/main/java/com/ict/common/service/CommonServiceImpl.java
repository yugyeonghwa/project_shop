package com.ict.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.BasketVO;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.Pro_ImgVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;
import com.ict.order.dao.OrderDAO;
import com.ict.order.dao.TempOrderVO;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private OrderDAO commonDAO;

	@Override
	public List<BasketVO> getBasketList(String u_idx) {
		return commonDAO.getBasketList(u_idx);
	}

	@Override
	public List<OptionVO> getOptionList(String p_idx) {
		return commonDAO.getOptionList(p_idx);
	}
	
	@Override
	public List<AddressVO> getAddrList(String u_idx) {
		return commonDAO.getAddrList(u_idx);
	}
	
	@Override
	public AddressVO getAddrBasic(String u_idx) {
		return commonDAO.getAddrBasic(u_idx);
	}
	
	@Override
	public List<Pro_ImgVO> getProImg(String p_idx) {
		return commonDAO.getProImg(p_idx);
	}
	@Override
	public BasketVO getTempBasket(String k) {
		return commonDAO.getTempBasket(k);
	}
	
	@Override
	public String getAddrAdd(TempOrderVO tempOrderVO) {
		return commonDAO.Addr_add(tempOrderVO);
	}
	
	// ajax 배송지 리스트
	@Override
	public List<AddressVO> getAjaxAddrList(String u_idx) {
		return commonDAO.getAjaxAddrList(u_idx);
	}
	
	// 기본 주소 변경
	@Override
	public int getSetBasicSet(String addr_idx, UserVO uvo) {
		return commonDAO.getSetBasicSet(addr_idx,uvo);
	}
	
	// 결제 진행
	@Override
	public int getPayCheck(TempOrderVO tempOrderVO, List<BasketVO> basket_list, UserVO userVO) {
		return commonDAO.getPayCheck(tempOrderVO, basket_list, userVO);
	}
	
	// 선택 주소 가져오기
	@Override
	public AddressVO getAddrSelect(String u_idx) {
		return commonDAO.getAddrSelect(u_idx);
	}
	
	@Override
	public int getWishDel(WishVO wishVO) {
		return commonDAO.getWishDel(wishVO);
	}
	
}