package com.ict.common.dao;

// 주문한 상품 정보
public class Order_pdVO {
	private String order_pd_idx, p_idx, order_p_quant, order_idx, regdate;
	
	public String getOrder_idx() {
		return order_idx;
	}

	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getOrder_pd_idx() {
		return order_pd_idx;
	}

	public void setOrder_pd_idx(String order_pd_idx) {
		this.order_pd_idx = order_pd_idx;
	}

	public String getP_idx() {
		return p_idx;
	}

	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}

	public String getOrder_p_quant() {
		return order_p_quant;
	}

	public void setOrder_p_quant(String order_p_quant) {
		this.order_p_quant = order_p_quant;
	}
	
}
