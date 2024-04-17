package com.ict.common.dao;

import org.springframework.web.multipart.MultipartFile;

public class ReportVO {
	private String report_idx, u_idx, rep_idx, report_category, report_status, report_subject, report_content, report_f_name, hit, groups, step, lev, regdate, active, old_f_name;
	private MultipartFile report_file;
	public String getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(String report_idx) {
		this.report_idx = report_idx;
	}
	public String getU_idx() {
		return u_idx;
	}
	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
	}
	public String getRep_idx() {
		return rep_idx;
	}
	public void setRep_idx(String rep_idx) {
		this.rep_idx = rep_idx;
	}
	public String getReport_category() {
		return report_category;
	}
	public void setReport_category(String report_category) {
		this.report_category = report_category;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public String getReport_subject() {
		return report_subject;
	}
	public void setReport_subject(String report_subject) {
		this.report_subject = report_subject;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public String getReport_f_name() {
		return report_f_name;
	}
	public void setReport_f_name(String report_f_name) {
		this.report_f_name = report_f_name;
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
	public String getOld_f_name() {
		return old_f_name;
	}
	public void setOld_f_name(String old_f_name) {
		this.old_f_name = old_f_name;
	}
	public MultipartFile getReport_file() {
		return report_file;
	}
	public void setReport_file(MultipartFile report_file) {
		this.report_file = report_file;
	}
	
}
