package com.ict.sgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.sgin.dao.SginDAO;

@Service
public class SginServiceImpl implements SginService {
	@Autowired
	private SginDAO sginDAO;
	
	// 회원가입
	@Override
	public int getSignup(UserVO userVO) {
		return sginDAO.getSignup(userVO);
	}
	@Override
	public int getSignup2(AddressVO addrVO) {
		return sginDAO.getSignup2(addrVO);
	}
	@Override
	public UserVO getUserInfo(String u_id) {
		return sginDAO.getUserInfo(u_id);
	}

	// 아이디 중복확인
	@Override
	public String getIdChk(String u_id) {
		return sginDAO.getIdChk(u_id);
	}

	// 로그인
	@Override
	public UserVO getLogin(UserVO userVO) {
		return sginDAO.getLogin(userVO);
	}
	
	// 비밀번호 변경
	@Override
	public int getRePwd(UserVO uvo) {
		return sginDAO.getRePwd(uvo);
	}
	
	// 아이디 찾기
	@Override
	public UserVO getIdFind(String u_email) {
		return sginDAO.getIdFind(u_email);
	}
	

	
	

}
