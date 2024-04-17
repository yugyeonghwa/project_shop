package com.ict.common.dao;

public class OrderVO {
	private String order_idx, u_idx, p_idx, addr_idx, option_idx, receiver_name, receiver_phone, 
	order_price, review_active, order_date, order_quant;

	public String getOrder_idx() {
		return order_idx;
	}

	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}

	public String getU_idx() {
		return u_idx;
	}

	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
	}

	public String getP_idx() {
		return p_idx;
	}

	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}

	public String getAddr_idx() {
		return addr_idx;
	}

	public void setAddr_idx(String addr_idx) {
		this.addr_idx = addr_idx;
	}

	public String getOption_idx() {
		return option_idx;
	}

	public void setOption_idx(String option_idx) {
		this.option_idx = option_idx;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}

	public String getReview_active() {
		return review_active;
	}

	public void setReview_active(String review_active) {
		this.review_active = review_active;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_quant() {
		return order_quant;
	}

	public void setOrder_quant(String order_quant) {
		this.order_quant = order_quant;
	}

	
}
