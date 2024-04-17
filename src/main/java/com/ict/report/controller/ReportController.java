package com.ict.report.controller;

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
import com.ict.common.dao.ReportVO;
import com.ict.common.dao.UserVO;
import com.ict.report.service.ReportService;

@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private Paging paging;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	// 신고 게시판 이동
	@RequestMapping("report_list.do")
	public ModelAndView getReportList(HttpServletRequest request, @ModelAttribute("report_idx")String report_idx,
			@ModelAttribute("u_idx")String u_idx, @ModelAttribute("reportOwner")String reportOwner) {
		ModelAndView mv = new ModelAndView("report/report_list_page");
		int count = reportService.getReportCnt();
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
		
		List<ReportVO> report_list = reportService.getReportList(paging.getOffset(), paging.getNumPerPage());
		if (report_list != null) {
			mv.addObject("report_list", report_list);
			mv.addObject("paging", paging);
			return mv;
		}
		return new ModelAndView("common/error");
	}
	
	// 신고 작성 페이지 이동
	@GetMapping("report_write_go.do")
	public ModelAndView getReportWrite() {
		return new ModelAndView("report/report_write_page");
	}
	
	// 신고 작성
	@PostMapping("report_write_ok.do")
	public ModelAndView getReportWriteOK(ReportVO reportVO, HttpServletRequest request,
			@ModelAttribute("report_idx")String report_idx, @ModelAttribute("u_idx")String u_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:report_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile report_file = reportVO.getReport_file();
			
			if (report_file.isEmpty()) {
				reportVO.setReport_f_name("");
			} else {
				UUID uuid = UUID.randomUUID();
				String report_f_name = uuid.toString()+"_"+report_file.getOriginalFilename();
				reportVO.setReport_f_name(report_f_name);
				
				byte[] in = report_file.getBytes();
				File out = new File(path, report_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int result = reportService.getReportInsert(reportVO);
			if (result >0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println("rep write err : "+e);
		}
		return new ModelAndView("common/error");
	}
	
	
	// 신고 게시글 상세보기
	@GetMapping("report_detail.do")
	public ModelAndView getReportDetail(@ModelAttribute("cPage")String cPage, String report_idx, 
			HttpSession session) {
		ModelAndView mv = new ModelAndView("report/report_detail_page");
		
		int res = reportService.getReportHit(report_idx);
		
		ReportVO reportVO = reportService.getReportDetail(report_idx);
		
		UserVO userVO = null;
		userVO = (UserVO) session.getAttribute("userVO");
		if (userVO.getU_idx() ==null) {
			mv.setViewName("redirect:report_list.do");
			return mv;
		} else if (!(userVO != null && userVO.getU_idx().equals(reportVO.getU_idx()))) {
			if (userVO.getU_id().equals("admin")) {
				mv.addObject("reportVO", reportVO);
				mv.addObject("reportOwner", "ok");
				return mv;
			}
			mv.addObject("reportOwner", "no");
			mv.setViewName("redirect:report_list.do");
			return mv;
		} 
		mv.addObject("reportOwner", "ok");
		if (res>0 && reportVO != null) {
			mv.addObject("reportVO", reportVO);
			return mv;
		}
		return new ModelAndView("common/error");
	}
	
	
	// 신고 게시판 게시물 수정 페이지 이동
	@PostMapping("report_update.do")
	public ModelAndView getReportUpdate(String report_idx, @ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView("report/report_update_page");
		ReportVO reportVO = reportService.getReportDetail(report_idx);
		if (reportVO != null) {
			mv.addObject("reportVO", reportVO);
			return mv;
		}
		return new ModelAndView("common/error"); 
	}
	
	
	// 신고 게시판 게시물 수정
	@PostMapping("report_update_ok.do")
	public ModelAndView getQnaUpdateOK(ReportVO reportVO, HttpServletRequest request, 
			@ModelAttribute("cPage")String cPage) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile report_file = reportVO.getReport_file();
			
			if (report_file.isEmpty()) {
				reportVO.setReport_f_name(reportVO.getOld_f_name());
			} else {
				UUID uuid = UUID.randomUUID();
				String report_f_name = uuid.toString()+"_"+report_file.getOriginalFilename();
				reportVO.setReport_f_name(report_f_name);
				
				byte in [] = report_file.getBytes();
				File out = new File(path, report_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int res = reportService.getReportUpdate(reportVO);
			if (res >0) {
				mv.setViewName("redirect:report_detail.do?report_idx="+reportVO.getReport_idx());
				return mv;
			}
			return new ModelAndView("common/error"); 
		} catch (Exception e) {
			System.out.println("rep update ok err : "+e);
		}
		return new ModelAndView("common/error"); 
	}
	
	
	// 신고 게시판 게시물 답변 등록 페이지 이동
	@PostMapping("report_answer.do")
	public ModelAndView getReportAnswer(@ModelAttribute("cPage")String cPage, 
			@ModelAttribute("report_idx")String report_idx) {
		return new ModelAndView("report/report_answer_page");
	}
	
	
	// 신고 게시판 게시물 답변 등록
	@PostMapping("report_answer_ok.do")
	public ModelAndView getReportAnswerOK(@ModelAttribute("report_idx")String report_idx, 
			HttpServletRequest request, @ModelAttribute("cPage")String cPage) {
		try {
			ReportVO reportVO = reportService.getReportDetail(report_idx);
			ReportVO reportVO2 = reportService.getReportDetail(reportVO.getReport_idx());
			
			int groups = Integer.parseInt(reportVO2.getGroups());
			int step = Integer.parseInt(reportVO2.getStep());
			int lev = Integer.parseInt(reportVO2.getLev());
			
			step++;
			lev++;
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			int res = reportService.getLevUpdate(map);
			reportVO.setGroups(String.valueOf(groups));
			reportVO.setStep(String.valueOf(step));
			reportVO.setLev(String.valueOf(lev));
			
			ModelAndView mv = new ModelAndView("redirect:report_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile report_file = reportVO.getReport_file();
			
			if (report_file == null) {
				reportVO.setReport_f_name(" ");
			} else {
				UUID uuid = UUID.randomUUID();
				String report_f_name = uuid.toString()+"_"+report_file.getOriginalFilename();
				reportVO.setReport_f_name(report_f_name);
				byte in [] = report_file.getBytes();
				File out = new File(path, report_f_name);
				FileCopyUtils.copy(in, out);
			}
			
			int result = reportService.getReportAnswerInsert(reportVO);
			if (result>0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println("report answer err : "+e);
		}
		return new ModelAndView("common/error"); 
	}
	
	
	// 신고 게시물 삭제 페이지 이동
	@PostMapping("report_delete.do")
	public ModelAndView getReportDelete(@ModelAttribute("report_idx") String report_idx,
			ReportVO reportVO, @ModelAttribute("cPage")String cPage) {
		return new ModelAndView("report/report_delete_page");
	}
	
	
	// 신고 게시물 삭제
	@PostMapping("report_delete_ok.do")
	public ModelAndView getReportDeleteOK(UserVO userVO, HttpSession session,
			@ModelAttribute("report_idx") String report_idx, @ModelAttribute("cPage")String cPage ) {
		ModelAndView mv = new ModelAndView();
		ReportVO reportVO = reportService.getReportDetail(report_idx);

		UserVO userVO2 = (UserVO) session.getAttribute("userVO");
		String dpwd = userVO2.getU_pwd();
		
		if (! passwordEncoder.matches(userVO.getU_pwd(), dpwd)) {
			mv.setViewName("report/report_delete_page");
			mv.addObject("pwdchk", "fail");
			return mv;
		} else {
			int res = reportService.getReportDelete(reportVO);
			if (res >0) {
				mv.setViewName("redirect:report_list.do");
				return mv;
			}
		}
		return new ModelAndView("common/error");
	}
	
}
