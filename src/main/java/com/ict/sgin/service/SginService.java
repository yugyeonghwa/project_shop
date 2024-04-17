package com.ict.sgin.service;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;

public interface SginService {
	// 회원가입
	public int getSignup(UserVO userVO);
	public int getSignup2(AddressVO addrVO);
	public UserVO getUserInfo(String u_id);
	
	// 아이디 중복확인
	public String getIdChk(String u_id);
	
	// 로그인
	public UserVO getLogin(UserVO userVO);
	
	// 비밀번호 변경
	public int getRePwd(UserVO uvo);
	
	// 아이디 찾기
	public UserVO getIdFind(String u_email);
}
