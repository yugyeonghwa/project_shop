package com.ict.order.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.common.dao.AddressVO;
import com.ict.common.dao.UserVO;
import com.ict.common.service.CommonService;
import com.ict.order.dao.TempOrderVO;

@RestController
public class AjaxOrderController {

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "Addr_add.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	private String getAddrAdd(TempOrderVO tempOrderVO) {

		return commonService.getAddrAdd(tempOrderVO);
	}

	@RequestMapping(value = "getAjaxAddrList.do", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getAjaxList(HttpSession session) {

		UserVO uvo = (UserVO) session.getAttribute("userVO");
		// DB 처리 (전체 정보 가져오기)
		List<AddressVO> addr_list = commonService.getAjaxAddrList(uvo.getU_idx());
		if (addr_list != null) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(addr_list);
			return jsonString;
		}

		return "NO DATA";
	}

	@RequestMapping(value = "addr_basicSet.do", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getAjaxAddr_BasicSet(HttpSession session, String addr_idx) {
		UserVO uvo = (UserVO) session.getAttribute("userVO");
		int res = commonService.getSetBasicSet(addr_idx, uvo);
		// DB 처리 (전체 정보 가져오기)
		
		 List<AddressVO> addr_list = commonService.getAjaxAddrList(uvo.getU_idx()); if
		 (addr_list != null) {		 
			 Gson gson = new Gson();
			 String jsonString = gson.toJson(addr_list);
			 return jsonString;
		 }
		 
		return null;

	}
	

}
