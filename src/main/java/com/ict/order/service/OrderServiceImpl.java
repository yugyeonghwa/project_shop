package com.ict.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.BasketVO;
import com.ict.order.dao.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public int getBasketUpdate(BasketVO basketVO) {
		return orderDAO.getBasketUpdate(basketVO);
	}
	
	@Override
	public int getBasketProDelete(String k) {
		return orderDAO.getBasketProDelete(k);
		
	}
}
