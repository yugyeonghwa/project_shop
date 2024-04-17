package com.ict.order.service;

import com.ict.common.dao.BasketVO;

public interface OrderService {

	public int getBasketUpdate(BasketVO basketVO);

	public int getBasketProDelete(String k);

}
