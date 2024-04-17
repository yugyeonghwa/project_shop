package com.ict.mypage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.mypage.dao.OrderListVO;
import com.ict.mypage.dao.PurchaseListVO;
import com.ict.mypage.dao.WishListVO;
import com.ict.mypage.service.MypageService;

@Controller
public class MypageController {

	@Autowired
	private MypageService mypageService;
	
	//	주문목록 페이지로
	@GetMapping("orderlist_page.do")
	public ModelAndView getOrderList(HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("mypage/orderList_page");
			
			UserVO uvo = (UserVO) session.getAttribute("userVO");

			// DB 가서 u_idx 에 해당하는 주문리스트를 가져오자
			List<OrderListVO> order_list = mypageService.getOrderList(uvo.getU_idx());

			if (order_list != null) {

				List<OrderListVO> remove = new ArrayList<OrderListVO>();
				String order_date = "";

				for (OrderListVO k : order_list) {
					// 주문 날짜 가져오기
					order_date = k.getOrder_date();
					// 포맷으로 바꾸기
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					// Date 로 날짜형 변환
					Date order = sdf.parse(order_date);

					// 현재날짜시간 구하기
					Calendar now = Calendar.getInstance();

					// 주문날짜시간 과 현재날짜시간 비교하기 (시간단위)
					long diff = (now.getTimeInMillis() - order.getTime()) / (1000 * 60 * 60);
					// OrderListVO 에 있는 order_state 에 주문상태 값 넣어주기
					// 1시간보다 작으면 = 상품준비중
					// 24시간보다 작으면 = 배송중
					// 72시간보다 작으면 = 배송완료
					if (diff <= 1) {
						k.setOrder_state("상품준비중");
					} else if (diff <= 24) {
						k.setOrder_state("배송중");
					} else if (diff <= 72) {
						k.setOrder_state("배송완료");
					} else {
						remove.add(k);
						// 72시간보다 크면 자동 구매확정 => 구매내역으로 이동
						// DB 에 가서 delivery_acive 값을 1로 업데이트
						int result = mypageService.getDeliveryActive(k.getOrder_idx());
					}
				}
				order_list.removeAll(remove);
				mv.addObject("u_idx", uvo.getU_idx());
				mv.addObject("order_list", order_list);
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}

	// 구매목록 보기
	@GetMapping("purchaselist_page.do")
	public ModelAndView getPurchaseList(HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("mypage/purchaseList_page");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			
			List<PurchaseListVO> purchase_list = mypageService.getPurchaseList(uvo.getU_idx());
			if (purchase_list != null) {
				mv.addObject("purchase_list", purchase_list);
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}

	// 구매확정 버튼 누르면 구매목록으로 이동
	@GetMapping("purchase_ok.do")
	public ModelAndView purchaseOK(String order_idx, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:purchaselist_page.do");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
				
			int result = mypageService.getDeliveryActive(order_idx);
			if (result > 0) {
				return mv;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}

	// 찜목록
	@GetMapping("wishlist_page.do")
	public ModelAndView getWishList(HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("mypage/wishList_page");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			List<WishListVO> wish_list = mypageService.getWishList(uvo.getU_idx());
			if (wish_list != null) {
				mv.addObject("wish_list", wish_list);
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	//	찜목록 삭제
	@PostMapping("wishlist_delete.do")
	public ModelAndView getWishDelete(String wish_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:wishlist_page.do");
			int result = mypageService.getWishListDelete(wish_idx);
			if (result > 0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}

	
	// 	회원정보수정 화면으로
	@GetMapping("update_page.do")
	public ModelAndView getUpdate(HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("mypage/update_page");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			UserVO userVO = mypageService.getUserDetail(uvo.getU_idx());
			List<AddressVO> addr_list = userVO.getAddr_list();
			
			if (userVO != null) {
				mv.addObject("userVO", userVO);
				mv.addObject("addr_list", addr_list);
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	
	//	수정완료
	@PostMapping("update_ok.do")
	public ModelAndView getUpdateOK(UserVO userVO, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:mypage_page.do");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			UserVO userVO2 = mypageService.getUserDetail(uvo.getU_idx());
			List<AddressVO> addr_list = userVO2.getAddr_list();
			if (! passwordEncoder.matches(userVO.getU_pwd(), userVO2.getU_pwd())) {
				mv.setViewName("mypage/update_page");
				mv.addObject("pwdchk", "fail");
				mv.addObject("userVO", uvo);
				mv.addObject("addr_list", addr_list);
				return mv;
			}else {
				uvo.setU_email(userVO.getU_email());
				uvo.setU_phone(userVO.getU_phone());
				int result = mypageService.getUserUpdate(uvo);
				if (result > 0) {
					return mv;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	
	//	기본배송지 변경
	@GetMapping("basic_update.do")
	public ModelAndView getBasicUpdate(String addr_idx, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:update_page.do");
			
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			
			int result = mypageService.getBasicAddrUpdate(uvo.getU_idx(), addr_idx);
			if (result >= 2) {
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	//	배송지 삭제
	@GetMapping("addr_delete.do")
	public ModelAndView getAddrDelete(String addr_idx, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:update_page.do");
			
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			
			int result = mypageService.getAddrDelete(addr_idx);
			
			if (result > 0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	//	배송지 추가 화면으로
	@GetMapping("addr_add.do")
	public ModelAndView getAddrAdd() {
		return new ModelAndView("mypage/addr_add_page");
	}
	
	//	배송지 추가하기
	@PostMapping("addr_add_ok.do")
	public ModelAndView getAddrAddOK(AddressVO addrVO, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:update_page.do");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			addrVO.setU_idx(uvo.getU_idx());
			int result = mypageService.getAddrAdd(addrVO);
			
			if (result > 0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
	
	//	회원탈퇴 화면으로
	@GetMapping("resign_page.do")
	public ModelAndView getResign() {
		return new ModelAndView("mypage/resign_page");
	}
	
	//	회원탈퇴 비밀번호 확인 화면으로
	@GetMapping("resign_pwd.do")
	public ModelAndView getResignPwd() {
		return new ModelAndView("mypage/resign_pwd_page");
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//	회원탈퇴 완료
	@PostMapping("resign_ok.do")
	public ModelAndView getResignOK(HttpSession session, String cpwd) {
		try {
			ModelAndView mv = new ModelAndView();
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			
			UserVO userVO = mypageService.getUserDetail(uvo.getU_idx());
			String dpwd = userVO.getU_pwd();
			if (! passwordEncoder.matches(cpwd, dpwd)) {
				mv.setViewName("mypage/resign_pwd_page");
				mv.addObject("pwdchk", "fail");
				return mv;
			}else {
				int result = mypageService.getResign(uvo.getU_idx());
				if (result > 0) {
					mv.setViewName("redirect:/");
					session.removeAttribute("userVO");
					session.setAttribute("loginChk", "fail");
					return mv;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
}















