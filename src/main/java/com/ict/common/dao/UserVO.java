package com.ict.common.dao;

import java.util.List;

public class UserVO {
	private String u_idx, u_id, u_pwd, u_name, u_phone, u_email, u_birth, u_gender, 
	 				regdate, active, addr_idx;
	private int u_point;
	private List<AddressVO> addr_list;
	
	//	getter setter
	public List<AddressVO> getAddr_list() {
		return addr_list;
	}

	public void setAddr_list(List<AddressVO> addr_list) {
		this.addr_list = addr_list;
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

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_birth() {
		return u_birth;
	}

	public void setU_birth(String u_birth) {
		this.u_birth = u_birth;
	}

	public String getU_gender() {
		return u_gender;
	}

	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public int getU_point() {
		return u_point;
	}

	public void setU_point(int u_point) {
		this.u_point = u_point;
	}
	
	
}
