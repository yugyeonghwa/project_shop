package com.ict.mypage.dao;

public class PurchaseListVO {
	private String order_idx, f_name, p_name, p_size, purchase_date, order_price, 
			order_p_quant, review_active, p_category;

	public String getP_category() {
		return p_category;
	}

	public void setP_category(String p_category) {
		this.p_category = p_category;
	}

	public String getReview_active() {
		return review_active;
	}

	public void setReview_active(String review_active) {
		this.review_active = review_active;
	}

	public String getOrder_idx() {
		return order_idx;
	}

	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_size() {
		return p_size;
	}

	public void setP_size(String p_size) {
		this.p_size = p_size;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}

	public String getOrder_p_quant() {
		return order_p_quant;
	}

	public void setOrder_p_quant(String order_p_quant) {
		this.order_p_quant = order_p_quant;
	}


	
}
