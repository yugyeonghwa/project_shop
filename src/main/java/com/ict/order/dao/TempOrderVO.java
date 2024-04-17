package com.ict.order.dao;

public class TempOrderVO {
	private String u_idx, receiver_name, receiver_phone, addrDetail, jibunAddr, roadAddr, zip_code, addr_idx, option_idx, p_idx, quant, order_pd_idx;
	public String getP_idx() {
		return p_idx;
	}

	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}

	public String getQuant() {
		return quant;
	}

	public void setQuant(String quant) {
		this.quant = quant;
	}

	public String getOrder_pd_idx() {
		return order_pd_idx;
	}

	public void setOrder_pd_idx(String order_pd_idx) {
		this.order_pd_idx = order_pd_idx;
	}

	private int orderpriceTotal;
	
	public String getOption_idx() {
		return option_idx;
	}

	public void setOption_idx(String option_idx) {
		this.option_idx = option_idx;
	}

	public int getOrderpriceTotal() {
		return orderpriceTotal;
	}

	public void setOrderpriceTotal(int orderpriceTotal) {
		this.orderpriceTotal = orderpriceTotal;
	}

	public String getAddr_idx() {
		return addr_idx;
	}

	public void setAddr_idx(String addr_idx) {
		this.addr_idx = addr_idx;
	}

	public String getU_idx() {
		return u_idx;
	}

	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
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

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getJibunAddr() {
		return jibunAddr;
	}

	public void setJibunAddr(String jibunAddr) {
		this.jibunAddr = jibunAddr;
	}

	public String getRoadAddr() {
		return roadAddr;
	}

	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	
	
}
