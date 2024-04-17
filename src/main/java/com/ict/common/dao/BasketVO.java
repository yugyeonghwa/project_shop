package com.ict.common.dao;

public class BasketVO {
	
	private String b_idx, u_idx, p_idx, o_idx, p_name, p_size, p_num, regdate, f_name, checked;
	
	
	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	//	장바구니 처리
	private int quant, totalPrice;
	
	// 	장바구니에서 수량이 변경되면 총 금액도 변경되어야 한다.
	public void setQuant(int quant) {
		this.quant = quant;
		
		// 수량 변경시 총 금액 변경
		setTotalPrice(quant * p_price);
	}
	
	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	private int p_price;
	
	public String getP_size() {
		return p_size;
	}

	public void setP_size(String p_size) {
		this.p_size = p_size;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}


	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuant() {
		return quant;
	}
	
	
	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getB_idx() {
		return b_idx;
	}

	public void setB_idx(String b_idx) {
		this.b_idx = b_idx;
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

	public String getO_idx() {
		return o_idx;
	}

	public void setO_idx(String o_idx) {
		this.o_idx = o_idx;
	}

	
}
