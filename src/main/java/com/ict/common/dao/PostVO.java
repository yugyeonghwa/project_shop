package com.ict.common.dao;

import org.springframework.web.multipart.MultipartFile;

public class PostVO {
	// 게시판
	private String post_idx, u_idx, p_idx, post_category, post_status, post_subject, post_content, 
	post_f_name, regdate, old_f_name, hit, groups, step, lev, active;
	
	private MultipartFile post_file;
	
	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	public String getPost_category() {
		return post_category;
	}
	public void setPost_category(String post_category) {
		this.post_category = post_category;
	}
	public String getPost_subject() {
		return post_subject;
	}
	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getPost_f_name() {
		return post_f_name;
	}
	public void setPost_f_name(String post_f_name) {
		this.post_f_name = post_f_name;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getPost_file() {
		return post_file;
	}
	public void setPost_file(MultipartFile post_file) {
		this.post_file = post_file;
	}

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	public String getPost_category() {
		return post_category;
	}
	public void setPost_category(String post_category) {
		this.post_category = post_category;
	}
	public String getPost_subject() {
		return post_subject;
	}
	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getPost_f_name() {
		return post_f_name;
	}
	public void setPost_f_name(String post_f_name) {
		this.post_f_name = post_f_name;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getPost_file() {
		return post_file;
	}
	public void setPost_file(MultipartFile post_file) {
		this.post_file = post_file;
	}
	public String getOld_f_name() {
		return old_f_name;
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
	public void setOld_f_name(String old_f_name) {
		this.old_f_name = old_f_name;
	}
	public String getPost_idx() {
		return post_idx;
	}
	public String getPost_status() {
		return post_status;
	}
	public void setPost_status(String post_status) {
		this.post_status = post_status;
	}
	public void setPost_idx(String post_idx) {
		this.post_idx = post_idx;
	}

	public String getU_idx() {
		return u_idx;
	}

	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
	}
	
	
}
