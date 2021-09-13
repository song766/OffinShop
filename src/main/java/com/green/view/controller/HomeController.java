package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.CartVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.ProductVO;
import com.green.biz.order.CartService;
import com.green.biz.product.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		//System.out.println("URL=index received!");
		List<ProductVO> newProList = productService.getNewProductList();
		model.addAttribute("newProList", newProList);
		
		List<ProductVO> bestProList = productService.getBestProductList();
		model.addAttribute("bestProList", bestProList);	

		return "index";  // index.jsp 파일 출력
	}
	
	
}
