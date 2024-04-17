package com.ict.sgin.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;

@Repository
public class SginDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 회원가입
	public int getSignup(UserVO userVO) {
		return sqlSessionTemplate.insert("users.signup", userVO);
	}
	public int getSignup2(AddressVO addrVO) {
		return sqlSessionTemplate.insert("users.signup_addr", addrVO);
	}
	public UserVO getUserInfo(String u_id) {
		return sqlSessionTemplate.selectOne("users.user_info", u_id);
	}
	
	// 아이디 중복확인
	public String getIdChk(String u_id) {
		try {
			int result = sqlSessionTemplate.selectOne("users.idchk", u_id);
			// u_id가 존재하면
			if (result > 0) {
				return "0";
			}
			// u_id가 존재하지 않으면
			return "1";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 로그인
	public UserVO getLogin(UserVO userVO) {
		return sqlSessionTemplate.selectOne("users.login", userVO);
	}
	
	// 비밀번호 변경
	public int getRePwd(UserVO uvo) {
		return sqlSessionTemplate.update("users.repwd", uvo);
	}
	
	// 아이디 찾기
	public UserVO getIdFind(String u_email) {
		return sqlSessionTemplate.selectOne("users.id_find", u_email);
	}
}
