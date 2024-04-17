package com.ict.board.controller;

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

import com.ict.board.service.BoardService;
import com.ict.common.Paging;
import com.ict.common.dao.PostVO;
import com.ict.common.dao.UserVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private Paging paging;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// QNA게시판 이동
	@RequestMapping("qna_list.do")
	public ModelAndView getQnaList(HttpServletRequest request, @ModelAttribute("post_idx")String post_idx,
			@ModelAttribute("u_idx")String u_idx, @ModelAttribute("postOwner") String postOwner) {
		ModelAndView mv = new ModelAndView("qna/qna_list_page");
		int count = boardService.getTotalCount();
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
		
		List<PostVO> qna_list = boardService.getQnaList(paging.getOffset(), paging.getNumPerPage());
		if (qna_list != null) {
			mv.addObject("qna_list", qna_list);
			mv.addObject("paging", paging);
			return mv;
		}
		return new ModelAndView("common/error");
	}
	
	// QNA 작성 페이지 이동
	@GetMapping("qna_write_go.do")
	public ModelAndView getQnaWrite() {
		return new ModelAndView("qna/qna_write_page");
	}
	
	// QNA 작성
	@PostMapping("qna_write_ok.do")
	public ModelAndView getQnaWriteOK(PostVO postVO, HttpServletRequest request,
			@ModelAttribute("post_idx")String post_idx, @ModelAttribute("u_idx")String u_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:qna_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile post_file = postVO.getPost_file();
			
			if (post_file.isEmpty()) {
				postVO.setPost_f_name("");
			} else {
				UUID uuid = UUID.randomUUID();
				String post_f_name = uuid.toString()+"_"+post_file.getOriginalFilename();
				postVO.setPost_f_name(post_f_name);
				
				byte[] in = post_file.getBytes();
				File out = new File(path, post_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int result = boardService.getQnaInsert(postVO);
			if (result >0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println("qna write err : "+e);
		}
		return new ModelAndView("common/error");
	}
	
	// QNA 상세보기
	@GetMapping("qna_detail.do")
	public ModelAndView getQnaDetail(@ModelAttribute("cPage")String cPage, String post_idx, 
			HttpSession session) {
		ModelAndView mv = new ModelAndView("qna/qna_detail_page");
		UserVO userVO = null;
		userVO = (UserVO) session.getAttribute("userVO"); 
		
		int res = boardService.getQnaHit(post_idx);
		PostVO postVO = boardService.getQnaDetail(post_idx);

		
		UserVO userVO = null;
        userVO = (UserVO) session.getAttribute("userVO"); 
        
        if(userVO.getU_idx() == null) {
            mv.setViewName("redirect:qna_list.do");
            return mv;
        } else if (!(userVO != null && userVO.getU_idx().equals(postVO.getU_idx()))) {
            if(userVO.getU_id().equals("admin") ) {
                mv.addObject("postVO", postVO);
                mv.addObject("postOwner", "ok");
                return mv;
            }
            mv.addObject("postOwner", "no");
            mv.setViewName("redirect:qna_list.do");
            return mv;
        } 
        mv.addObject("postOwner", "ok");
        if (res>0 && postVO != null) {
            mv.addObject("postVO", postVO);
            return mv;
        }

		return new ModelAndView("common/error");
	}
	
	// QNA 업데이트 페이지 이동
	@PostMapping("qna_update.do")
	public ModelAndView getQnaUpdate(String post_idx, @ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView("qna/qna_update_page");
		PostVO postVO = boardService.getQnaDetail(post_idx);
		if (postVO != null) {
			mv.addObject("postVO", postVO);
			return mv;
		}
		return new ModelAndView("common/error"); 
	}
	
	// QNA 수정
	@PostMapping("qna_update_ok.do")
	public ModelAndView getQnaUpdateOK(PostVO postVO, HttpServletRequest request, 
			@ModelAttribute("cPage")String cPage) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile post_file = postVO.getPost_file();
			
			if (post_file.isEmpty()) {
				postVO.setPost_f_name(postVO.getOld_f_name());
			} else {
				UUID uuid = UUID.randomUUID();
				String post_f_name = uuid.toString()+"_"+post_file.getOriginalFilename();
				postVO.setPost_f_name(post_f_name);
				
				byte in [] = post_file.getBytes();
				File out = new File(path, post_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int res = boardService.getQnaUpdate(postVO);
			if (res >0) {
				mv.setViewName("redirect:qna_detail.do?post_idx="+postVO.getPost_idx());
				return mv;
			}
			return new ModelAndView("common/error"); 
		} catch (Exception e) {
			System.out.println("qnaupdateok err : "+e);
		}
		return new ModelAndView("common/error"); 
	}
	
	// QNA 답변 페이지 이동
	@PostMapping("qna_answer.do")
	public ModelAndView getQnaAnswer(@ModelAttribute("cPage")String cPage, 
			@ModelAttribute("post_idx")String post_idx, @ModelAttribute("postVO") PostVO postVO) {
		return new ModelAndView("qna/qna_answer_page");
	}
	
	
	
	// QNA 답변 등록
	@PostMapping("qna_answer_ok.do")
	public ModelAndView getQnaAnswerOK(@ModelAttribute("post_idx")String post_idx, HttpServletRequest request, 
			@ModelAttribute("cPage")String cPage) {
		try {
			PostVO postVO = boardService.getQnaDetail(post_idx);
			//PostVO postVO2 = boardService.getQnaDetail(postVO.getPost_idx());

			int groups = Integer.parseInt(postVO.getGroups());
			int step = Integer.parseInt(postVO.getStep());
			int lev = Integer.parseInt(postVO.getLev());

			step++;
			lev++;
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			int res = boardService.getLevUpdate(map);
			postVO.setGroups(String.valueOf(groups));
			postVO.setStep(String.valueOf(step));
			postVO.setLev(String.valueOf(lev));
			
			ModelAndView mv = new ModelAndView("redirect:qna_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");

			MultipartFile post_file = postVO.getPost_file();
			if (post_file == null) {
				postVO.setPost_f_name(" ");
			} else {
				UUID uuid = UUID.randomUUID();
				String post_f_name = uuid.toString()+"_"+post_file.getOriginalFilename();
				postVO.setPost_f_name(post_f_name);
				byte in [] = post_file.getBytes();
				File out = new File(path, post_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int result = boardService.getQnaAnswerInsert(postVO);
			if (result>0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println("qna answer err : "+e);
		}
		return new ModelAndView("common/error"); 
	}
	
	// QNA 삭제 페이지 이동
	@PostMapping("qna_delete.do")
	public ModelAndView getQnaDelete(@ModelAttribute("post_idx") String post_idx,
			PostVO postVO, @ModelAttribute("cPage")String cPage) {
		return new ModelAndView("qna/qna_delete_page");
	}
	
	// QNA 삭제 
	@PostMapping("qna_delete_ok.do")
	public ModelAndView getQnaDeleteOK(UserVO userVO, @ModelAttribute("post_idx") String post_idx,
			@ModelAttribute("cPage")String cPage, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PostVO postVO = boardService.getQnaDetail(post_idx);
		
        UserVO userVO2 = (UserVO) session.getAttribute("userVO");
        String dpwd = userVO2.getU_pwd();
		
		if (! passwordEncoder.matches(userVO.getU_pwd(), dpwd)) {
			mv.setViewName("qna/qna_delete_page");
			mv.addObject("pwdchk", "fail");
			return mv;
		} else {
			int res = boardService.getQnaDelete(postVO);
			if (res >0) {
				mv.setViewName("redirect:qna_list.do");
				return mv;
			}
		}
		return new ModelAndView("common/error");
	}
	
}
