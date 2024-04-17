package com.ict.cartegory.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.cartegory.dao.OrderVO;
import com.ict.cartegory.service.CategoryService;
import com.ict.common.dao.OptionVO;
import com.ict.common.dao.ProductVO;
import com.ict.common.dao.UserVO;
import com.ict.common.dao.WishVO;
import com.ict.mypage.dao.WishListVO;
import com.ict.mypage.service.MypageService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MypageService mypageService;
	
	// public ModelAndView getBoardDetail(@ModelAttribute("cPage") String cPage
	@GetMapping("category_page.do")
    public ModelAndView getCatagory(@ModelAttribute("p_category") String p_category, HttpSession session) {
		   ModelAndView mv = new ModelAndView("product/category_page");
        // db에서 카테고리에 해당하는 결과 상품정보 뽑아오기
		 System.out.println(p_category);
        List<ProductVO> pro_list = categoryService.getCategoryList(p_category);
        UserVO uvo = (UserVO) session.getAttribute("userVO");
        // 반복문 돌면서 로그인 되어 있을 때 product를 도는데 wishlist에 p_idx가 있다면 active를 1로 설정
        if(uvo != null) {
        	List<WishVO> wish_list = mypageService.getCategoryWishList(uvo.getU_idx());
        	for (ProductVO k : pro_list) {
        		for (WishVO i : wish_list) {
				  if(k.getP_idx().equals(i.getP_idx())) {
					  k.setActive("1");
					  break;
				  }
				}
        	}
        	mv.addObject("heartloginchk", "yes");
        	mv.addObject("pro_list", pro_list);
        	return mv;
        }
        	mv.addObject("heartloginchk", "no");
        
        // 내가 만들건 카테고리 페이지니까 p_category를 인자로 받는다.
        // p_category를 인자로 받는 이유는 해당 카테고리 페이지는 여러가지이기 때문에 p_category001,002 등 p_category를 받아서 구분해야 하기 때문
        mv.addObject("pro_list", pro_list);
        return mv;
    }
	
	@RequestMapping("product_detail_page.do")
	public ModelAndView getProductDetail(String p_idx) {
		try {
			ModelAndView mv = new ModelAndView("product/product_detail_page");
			ProductVO proVO = categoryService.getProductDetail(p_idx);
			List<OptionVO> option_list = categoryService.getOptionList(p_idx);
			if(proVO != null) {
				mv.addObject("proVO", proVO);
				mv.addObject("option_list", option_list);
				return mv;	
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("제품상세페이지 가는 컨트롤러 에러발생");
		}
		
		return null;
	}
	
	@PostMapping("basket_go.do")
	public ModelAndView getBasketGo(HttpSession session , OrderVO ovo, @ModelAttribute("p_idx") String p_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:product_detail_page.do");
			UserVO uvo = (UserVO) session.getAttribute("userVO");
			OptionVO opvo = categoryService.getOption(ovo);
			
			ovo.setU_idx(uvo.getU_idx()); 
			ovo.setO_idx(opvo.getOption_idx());
			int res = categoryService.getBasketGo(ovo);
			
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("basket으로가는 컨트롤러에서 에러발생");
		}
			return null;
	}
	
}
