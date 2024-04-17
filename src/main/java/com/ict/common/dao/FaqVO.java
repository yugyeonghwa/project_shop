package com.ict.common.dao;

public class FaqVO {
	private String faq_idx, q_content, a_content, regdate;

	public String getFaq_idx() {
		return faq_idx;
	}

	public void setFaq_idx(String faq_idx) {
		this.faq_idx = faq_idx;
	}


	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
