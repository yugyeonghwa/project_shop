package com.ict.sgin.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.common.service.CommonService;
import com.ict.sgin.service.MailService;
import com.ict.sgin.service.SginService;

@Controller
public class SginController {

	@Autowired
	private SginService sginService;
	@Autowired
	private MailService mailService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// 회원가입
	@PostMapping("user_signup.do")
	public ModelAndView getSignup(UserVO userVO, AddressVO addrVO) {
		// 암호화
		String u_pwd = passwordEncoder.encode(userVO.getU_pwd());
		userVO.setU_pwd(u_pwd);
		
		int result = sginService.getSignup(userVO);

		// DB에서 회원가입된 유저 ==> 입력받은 id를 키값 으로 유저를 하나 꺼내와서 uvo에 넣어
		UserVO uvo = sginService.getUserInfo(userVO.getU_id());
		addrVO.setU_idx(uvo.getU_idx());

		int result2 = sginService.getSignup2(addrVO);

		if (result > 0 && result2 > 0) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("common/error");
	}

	// 아이디 중복확인
	@RequestMapping(value = "sign_idchk.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxIdChk(@RequestParam("u_id") String u_id) {
		String result = sginService.getIdChk(u_id);
		return result;
	}

	// 로그인
	@PostMapping("user_login.do")
	public ModelAndView getLogin(HttpSession session, UserVO userVO) {
		ModelAndView mv = new ModelAndView();
		// select 
		UserVO uvo = sginService.getLogin(userVO);
		
		if (uvo == null) {
			session.setAttribute("loginChk", "fail");
			mv.addObject("msg", "해당 ID는 가입 이력이 존재하지 않습니다.");
			mv.setViewName("sign/login_page");
			return mv;
		} 

		// DB에 있는 u_pwd
		String dpwd = uvo.getU_pwd();
		if (!passwordEncoder.matches(userVO.getU_pwd(), dpwd)) {
			session.setAttribute("loginChk", "fail");
			mv.addObject("msg", "로그인 정보가 일치하지 않습니다.");
			mv.setViewName("sign/login_page");
			return mv;
		} else {
			session.setAttribute("loginChk", "ok");
			session.setAttribute("userVO", uvo);
			System.out.println(uvo.getU_idx());

			mv.setViewName("redirect:/");
			return mv;
		}
	}

	// 로그아웃
	@GetMapping("logout.do")
	public ModelAndView getLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("main_page");
	}

	// 비밀번호 찾기
	@PostMapping("pwd_find.do")
	public ModelAndView getPwdFind(UserVO userVO) {
		try {
			ModelAndView mv = new ModelAndView();
			// 입력받은 u_id로 select
			UserVO uvo = sginService.getLogin(userVO);

			if (uvo != null && uvo.getU_id().equals(userVO.getU_id()) && 
					uvo.getU_email().equals(userVO.getU_email())) {
				// 임시번호 만들기
				Random random = new Random();
				String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
				if (randomNumber.length() < 6) {
					int substract = 6 - randomNumber.length();
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < substract; i++) {
						sb.append("0");
					}
					sb.append(randomNumber);
					randomNumber = sb.toString();
				}
				// 임시번호 서버에 출력
				System.out.println("임시 비밀번호 : " + randomNumber);

				// 임시번호 암호화해서 update
				String pwd = passwordEncoder.encode(randomNumber);
				uvo.setU_pwd(pwd);
				int result = sginService.getRePwd(uvo);
				if (result > 0) {
					// 사용자 메일에 임시번호 보내기
					mailService.pwdEmail(randomNumber, uvo.getU_email());
					mv.addObject("msg", "임시 비밀번호가 발송되었습니다.");
					mv.setViewName("sign/login_page");
					return mv;
				}
			}
			mv.addObject("msg", "입력하신 정보가 일치하지 않습니다.");
			mv.setViewName("sign/findPWD_page");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}

	// 비밀번호 변경
	@PostMapping("repwd_go.do")
	public ModelAndView getRePwd(HttpSession session, String re_pwd, 
			String re_pwd2, UserVO userVO) {
		ModelAndView mv = new ModelAndView();

		// 세션에 저장된 user 정보
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		// DB u_pwd
		String dpwd = uvo.getU_pwd();

		if (!passwordEncoder.matches(userVO.getU_pwd(), dpwd) || !re_pwd.equals(re_pwd2)) {
			mv.addObject("userVO", uvo);
			mv.addObject("msg", "비밀번호가 일치하지 않습니다.");
			mv.setViewName("sign/repwd_page");
			return mv;
		} else {
			// 새 비밀번호 암호화
			String repwd = passwordEncoder.encode(re_pwd);
			uvo.setU_pwd(repwd);

			// 새 비밀번호 update
			int result = sginService.getRePwd(uvo);
			if (result > 0) {
				mv.addObject("userVO", uvo);
				mv.addObject("msg", "비밀번호가 변경되었습니다.");
				mv.setViewName("mypage/update_page");
				return mv;
			}
			return new ModelAndView("common/error");
		}
	}

	// 아이디 찾기 인증번호
	@PostMapping("id_find.do")
	public ModelAndView getIdFind(String u_email, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView();
			UserVO uvo = sginService.getIdFind(u_email);

			if (uvo != null && uvo.getU_email().equals(u_email)) {
				// 임시번호 만들기
				Random random = new Random();
				String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
				if (randomNumber.length() < 6) {
					int substract = 6 - randomNumber.length();
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < substract; i++) {
						sb.append("0");
					}
					sb.append(randomNumber);
					randomNumber = sb.toString();
				}
				// 임시번호 서버에 출력
				System.out.println("임시번호 : " + randomNumber);

				// 세션에 임시번호 저장
				session.setAttribute("randomNumber", randomNumber);

				mailService.idEmail(randomNumber, u_email);
				mv.addObject("u_email", u_email);
				mv.addObject("msg", "인증번호가 발송되었습니다.");
				mv.setViewName("sign/findID_page");
				return mv;
			}
			mv.addObject("msg", "입력하신 정보가 일치하지 않습니다.");
			mv.setViewName("sign/findID_page");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}

	// 아이디 찾기
	@PostMapping("id_find_ok.do")
	public ModelAndView idFindOk(String ver_code, HttpSession session, String u_email) {
		try {
			ModelAndView mv = new ModelAndView();
			// 입력받은 u_email로 select
			UserVO uvo = sginService.getIdFind(u_email);
			String u_id = uvo.getU_id();
			
			// 세션에 저장한 임시번호 가져오기
			String randomNumber = (String) session.getAttribute("randomNumber");
			if (randomNumber != null && randomNumber.equals(ver_code)) {
				mv.addObject("u_id", u_id);
				mv.addObject("msg", "인증번호가 확인되었습니다.");
				mv.setViewName("sign/findID_ok_page");
				return mv;
			} else {
				mv.addObject("u_email", u_email);
				mv.addObject("msg", "인증번호가 일치하지 않습니다.");
				mv.setViewName("sign/findID_page");
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
}
