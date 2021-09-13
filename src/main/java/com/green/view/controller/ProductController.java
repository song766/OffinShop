package com.green.view.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.green.biz.dto.ProductVO;
import com.green.biz.order.CartService;
import com.green.biz.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired // 자동객체 생성 사용하기 위해 
	private ProductService productService; //인터페이스를 타입으로 사용가능
	
	// index.jsp에서 product_detail a링크 시 수행
	// 링크= GET방식
	@RequestMapping(value="/product_detail", method=RequestMethod.GET)  
	public String productDetailAction(ProductVO vo, Model model) {
		
		// 상품의 상세정보 조회 서비스 호출
		// product는 출력할때 변수객체 , productService객체는 입력받을때 객체
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		return "product/productDetail"; // presentation-layer에서 /WEB-INF/views/ 까지 매핑이니까
	}
	
	@RequestMapping(value="/product_detail", method=RequestMethod.POST)  
	public String productDetailReAction(ProductVO vo, Model model) {
		
		// 상품의 상세정보 조회 서비스 호출
		// product는 출력할때 변수객체 , productService객체는 입력받을때 객체
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		return "product/productDetail"; // presentation-layer에서 /WEB-INF/views/ 까지 매핑이니까
	}
	
	// 분류별 상품목록 화면 보이기	
	// 화면을 리턴할거라 String으로 리턴. value값은 화면에서 전달받아오는값
	// (sub_menu.html/index.jsp의 header에있는 /category=). 
	// 링크를눌러 받아오기때문에 GET방식
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String productKindAction(ProductVO vo, Model model) {
		
		// productService객체에서 getProductListByKind(vo.getKind()) 메소드 호출
		List<ProductVO> listProduct = productService.getProductListByKind(vo.getKind());
		
		// 조회결과는 productKindList키로 저장
		model.addAttribute("productKindList", listProduct);
		return "product/productKind";
	}
	
	// Product페이지
	@RequestMapping(value="/productListAll", method=RequestMethod.GET)
	public String productListAction(
			@RequestParam(value="key", defaultValue="") String key,
			ProductVO vo, Model model) {
		
		List<ProductVO> listProductAll = productService.productListAllSearch(key);
		
		model.addAttribute("productListAll", listProductAll);
		return "product/productList";
		
	}
	
	// Product New 페이지
	@RequestMapping(value="/productListNew", method=RequestMethod.GET)
	public String productListNewAction(ProductVO vo, Model model) {
		
		List<ProductVO> listProductNew = productService.getNewProductListAll();
		
		model.addAttribute("productListNew", listProductNew);
		return "product/productNew";
	}
	
	// Product Best 페이지
	@RequestMapping(value="/productListBest", method=RequestMethod.GET)
	public String productListBestAction(ProductVO vo, Model model) {
		
		List<ProductVO> listProductBest = productService.getBestProductListAll();
		
		model.addAttribute("productListBest", listProductBest);
		return "product/productBest";
	}	
	
}
