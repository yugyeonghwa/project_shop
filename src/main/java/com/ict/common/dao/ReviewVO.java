package com.ict.common.dao;

import org.springframework.web.multipart.MultipartFile;

public class ReviewVO {
	private String review_idx, u_idx, r_idx, review_category, review_subject, review_content, 
							review_f_name, regdate, hit, groups, step, lev, active, old_f_name;
	
	private MultipartFile review_file;

	public String getReview_idx() {
		return review_idx;
	}

	public void setReview_idx(String review_idx) {
		this.review_idx = review_idx;
	}

	public String getU_idx() {
		return u_idx;
	}

	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
	}

	public String getR_idx() {
		return r_idx;
	}

	public void setR_idx(String r_idx) {
		this.r_idx = r_idx;
	}

	public String getReview_category() {
		return review_category;
	}

	public void setReview_category(String review_category) {
		this.review_category = review_category;
	}

	public String getReview_subject() {
		return review_subject;
	}

	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getReview_f_name() {
		return review_f_name;
	}

	public void setReview_f_name(String review_f_name) {
		this.review_f_name = review_f_name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getOld_f_name() {
		return old_f_name;
	}

	public void setOld_f_name(String old_f_name) {
		this.old_f_name = old_f_name;
	}

	public MultipartFile getReview_file() {
		return review_file;
	}

	public void setReview_file(MultipartFile review_file) {
		this.review_file = review_file;
	}
	
}
