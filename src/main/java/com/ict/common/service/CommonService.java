package com.ict.common.service;

import java.util.List;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.BasketVO;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.Pro_ImgVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;
import com.ict.order.dao.TempOrderVO;

public interface CommonService {
	
	public List<BasketVO> getBasketList(String u_idx);
	public List<OptionVO> getOptionList(String p_idx);
	public List<AddressVO> getAddrList(String u_idx);
	public AddressVO getAddrBasic(String u_idx);
	public List<Pro_ImgVO> getProImg(String p_idx);
	public BasketVO getTempBasket(String k);
	public String getAddrAdd(TempOrderVO tempOrderVO);
	public List<AddressVO> getAjaxAddrList(String u_idx);
	public int getSetBasicSet(String addr_idx, UserVO uvo);
	public int getPayCheck(TempOrderVO tempOrderVO, List<BasketVO> basket_list, UserVO userVO);
	public AddressVO getAddrSelect(String u_idx);
	public int getWishDel(WishVO wishVO);
}
