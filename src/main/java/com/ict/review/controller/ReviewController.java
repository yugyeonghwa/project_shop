package com.ict.review.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.Paging;
import com.ict.common.dao.ReviewVO;
import com.ict.common.dao.UserVO;
import com.ict.review.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private Paging paging;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// 리뷰게시판 이동
	@RequestMapping("review_list.do")
	public ModelAndView getReviewList(HttpServletRequest request, @ModelAttribute("review_idx")String review_idx,
			@ModelAttribute("u_idx")String u_idx, @ModelAttribute("reviewOwner")String reviewOwner) {
		ModelAndView mv = new ModelAndView("review/review_list_page");
		int count = reviewService.getReviewCnt();
		paging.setTotalRecord(count);
		
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1) );
		paging.setBeginBlock( (int) ((paging.getNowPage()-1) / paging.getPagePerBlock()) * paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
		
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		List<ReviewVO> review_list = reviewService.getReviewList(paging.getOffset(), paging.getNumPerPage());
		if (review_list != null) {
			mv.addObject("review_list", review_list);
			mv.addObject("paging", paging);
			return mv;
		}
		System.out.println("cont rev err : "+review_list);
		return new ModelAndView("common/error");
	}
	
	
	// 리뷰 작성 페이지 이동
	@GetMapping("review_write_go.do")
	public ModelAndView getReviewWrite() {
		return new ModelAndView("review/review_write_page");
	}
	
	
	// 리뷰 작성
	@PostMapping("review_write_ok.do")
	public ModelAndView getReviewWriteOK(ReviewVO reviewVO, HttpServletRequest request,
			@ModelAttribute("review_idx")String review_idx, @ModelAttribute("u_idx")String u_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:review_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile review_file = reviewVO.getReview_file();
			
			if (review_file.isEmpty()) {
				reviewVO.setReview_f_name("");
			} else {
				UUID uuid = UUID.randomUUID();
				String review_f_name = uuid.toString()+"_"+review_file.getOriginalFilename();
				reviewVO.setReview_f_name(review_f_name);
				
				byte[] in = review_file.getBytes();
				File out = new File(path, review_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int result = reviewService.getReviewInsert(reviewVO);
			if (result >0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println("cont rev write err : "+e);
		}
		return new ModelAndView("common/error");
	}
	
	
	// 리뷰 상세보기
	@GetMapping("review_detail.do")
	public ModelAndView getReviewDetail(@ModelAttribute("cPage")String cPage, String review_idx,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("review/review_detail_page");
		int res = reviewService.getReviewHit(review_idx);
		
		ReviewVO reviewVO = reviewService.getReviewDetail(review_idx);
		UserVO userVO = null;
		userVO = (UserVO) session.getAttribute("userVO");
		if (userVO.getU_idx() == null) {
			mv.setViewName("redirect:review_list.do");
			return mv;
		} else if (!(userVO != null && userVO.getU_idx().equals(reviewVO.getU_idx()))) {
			if (userVO.getU_id().equals("admin")) {
				mv.addObject("reviewVO", reviewVO);
				mv.addObject("reviewOwner", "ok");
				return mv;
			}
			mv.addObject("reviewOwner", "no");
			mv.setViewName("redirect:review_list.do");
			return mv;
		}
		mv.addObject("reviewOwner", "ok");
		if (res >0 && reviewVO != null) {
			mv.addObject("reviewVO", reviewVO);
			return mv;
		}
		return new ModelAndView("common/error");
	}
	
	
	// 리뷰 수정 페이지 이동
	@PostMapping("review_update.do")
	public ModelAndView getReviewUpdate(String review_idx, @ModelAttribute("cPage")String cPage) {
		ModelAndView mv = new ModelAndView("review/review_update_page");
		ReviewVO reviewVO = reviewService.getReviewDetail(review_idx);
		if (reviewVO != null) {
			mv.addObject("reviewVO", reviewVO);
			return mv;
		}
		return new ModelAndView("common/error"); 
	}
	
	
	// 리뷰 수정
	@PostMapping("review_update_ok.do")
	public ModelAndView getReviewUpdateOK(ReviewVO reviewVO, HttpServletRequest request,
			@ModelAttribute("cPage")String cPage) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile review_file = reviewVO.getReview_file();

			if (review_file.isEmpty()) {
				reviewVO.setReview_f_name(reviewVO.getOld_f_name());
			} else {
				UUID uuid = UUID.randomUUID();
				String review_f_name = uuid.toString()+"_"+review_file.getOriginalFilename();
				reviewVO.setReview_f_name(review_f_name);
				
				byte in [] = review_file.getBytes();
				File out = new File(path, review_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int res = reviewService.getReviewUpdate(reviewVO);
			if (res>0) {
				mv.setViewName("redirect:review_detail.do?review_idx="+reviewVO.getReview_idx());
				return mv;
			}
			return new ModelAndView("common/error");
			
		} catch (Exception e) {
			System.out.println("cont rev update err : "+e);
		}
		return new ModelAndView("common/error"); 
	}
	
	
	// 리뷰 답변 페이지 이동
	@PostMapping("review_answer.do")
	public ModelAndView getReviewAnswer(@ModelAttribute("cPage")String cPage, 
			@ModelAttribute("review_idx")String review_idx) {
		return new ModelAndView("review/review_answer_page");
	}
	
	
	// 리뷰 답변 등록
	@PostMapping("review_answer_ok.do")
	public ModelAndView getReviewAnswerOK(ReviewVO reviewVO, HttpServletRequest request,
			@ModelAttribute("cPage")String cPage) {
		try {
			ReviewVO reviewVO2 = reviewService.getReviewDetail(reviewVO.getReview_idx());
			
			int groups = Integer.parseInt(reviewVO2.getGroups());
			int step = Integer.parseInt(reviewVO2.getStep());
			int lev = Integer.parseInt(reviewVO2.getLev());
			
			step++;
			lev++;
			
			Map<String, Integer>map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			int res = reviewService.getLevUpdate(map);
			reviewVO.setGroups(String.valueOf(groups));
			reviewVO.setStep(String.valueOf(step));
			reviewVO.setLev(String.valueOf(lev));
			
			ModelAndView mv = new ModelAndView("redirect:review_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile review_file = reviewVO.getReview_file();
			if (review_file.isEmpty()) {
				reviewVO.setReview_f_name("");
			} else {
				UUID uuid = UUID.randomUUID();
				String review_f_name = uuid.toString()+"_"+review_file.getOriginalFilename();
				reviewVO.setReview_f_name(review_f_name);
				byte in [] = review_file.getBytes();
				File out = new File(path, review_f_name);
				FileCopyUtils.copy(in, out);
			}
			int result = reviewService.getReviewAnswerInsert(reviewVO);
			if (result >0) {
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println("cont ans err : "+e);
		}
		return new ModelAndView("common/error"); 
	}
	
	
	// 리뷰 삭제 페이지 이동
	@PostMapping("review_delete.do")
	public ModelAndView getReviewDelete(@ModelAttribute("review_idx") String review_idx,
			ReviewVO reviewVO, @ModelAttribute("cPage")String cPage) {
		return new ModelAndView("review/review_delete_page");
	}
	
	
	// 리뷰 삭제
	@PostMapping("review_delete_ok.do")
	public ModelAndView getReviewDeleteOK(UserVO userVO, @ModelAttribute("review_idx") String review_idx,
			@ModelAttribute("cPage")String cPage, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ReviewVO reviewVO = reviewService.getReviewDetail(review_idx);
		
		UserVO userVO2 = (UserVO) session.getAttribute("userVO");
		String dpwd = userVO2.getU_pwd();
		
		if (! passwordEncoder.matches(userVO.getU_pwd(), dpwd)) {
			mv.setViewName("review/review_delete_page");
			mv.addObject("pwdchk", "fail");
			return mv;
		} else {
			int res = reviewService.getReviewDelete(reviewVO);
			if (res >0) {
				mv.setViewName("redirect:review_list.do");
				return mv;
			}
		}
		return new ModelAndView("common/error");
	}
	
}

