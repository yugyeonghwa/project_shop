package com.ict.common.dao;

import org.springframework.web.multipart.MultipartFile;

public class Pro_ImgVO {
	private String image_idx, p_idx, f_name, regdate, main;
	
	private MultipartFile file;

	public String getImage_idx() {
		return image_idx;
	}

	public void setImage_idx(String image_idx) {
		this.image_idx = image_idx;
	}

	public String getP_idx() {
		return p_idx;
	}

	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
