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
	
	@Autowired // �ڵ���ü ���� ����ϱ� ���� 
	private ProductService productService; //�������̽��� Ÿ������ ��밡��
	
	// index.jsp���� product_detail a��ũ �� ����
	// ��ũ= GET���
	@RequestMapping(value="/product_detail", method=RequestMethod.GET)  
	public String productDetailAction(ProductVO vo, Model model) {
		
		// ��ǰ�� ������ ��ȸ ���� ȣ��
		// product�� ����Ҷ� ������ü , productService��ü�� �Է¹����� ��ü
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		return "product/productDetail"; // presentation-layer���� /WEB-INF/views/ ���� �����̴ϱ�
	}
	
	@RequestMapping(value="/product_detail", method=RequestMethod.POST)  
	public String productDetailReAction(ProductVO vo, Model model) {
		
		// ��ǰ�� ������ ��ȸ ���� ȣ��
		// product�� ����Ҷ� ������ü , productService��ü�� �Է¹����� ��ü
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		return "product/productDetail"; // presentation-layer���� /WEB-INF/views/ ���� �����̴ϱ�
	}
	
	// �з��� ��ǰ��� ȭ�� ���̱�	
	// ȭ���� �����ҰŶ� String���� ����. value���� ȭ�鿡�� ���޹޾ƿ��°�
	// (sub_menu.html/index.jsp�� header���ִ� /category=). 
	// ��ũ������ �޾ƿ��⶧���� GET���
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String productKindAction(ProductVO vo, Model model) {
		
		// productService��ü���� getProductListByKind(vo.getKind()) �޼ҵ� ȣ��
		List<ProductVO> listProduct = productService.getProductListByKind(vo.getKind());
		
		// ��ȸ����� productKindListŰ�� ����
		model.addAttribute("productKindList", listProduct);
		return "product/productKind";
	}
	
	// Product������
	@RequestMapping(value="/productListAll", method=RequestMethod.GET)
	public String productListAction(
			@RequestParam(value="key", defaultValue="") String key,
			ProductVO vo, Model model) {
		
		List<ProductVO> listProductAll = productService.productListAllSearch(key);
		
		model.addAttribute("productListAll", listProductAll);
		return "product/productList";
		
	}
	
	// Product New ������
	@RequestMapping(value="/productListNew", method=RequestMethod.GET)
	public String productListNewAction(ProductVO vo, Model model) {
		
		List<ProductVO> listProductNew = productService.getNewProductListAll();
		
		model.addAttribute("productListNew", listProductNew);
		return "product/productNew";
	}
	
	// Product Best ������
	@RequestMapping(value="/productListBest", method=RequestMethod.GET)
	public String productListBestAction(ProductVO vo, Model model) {
		
		List<ProductVO> listProductBest = productService.getBestProductListAll();
		
		model.addAttribute("productListBest", listProductBest);
		return "product/productBest";
	}	
	
}
