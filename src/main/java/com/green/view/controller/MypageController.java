package com.green.view.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.biz.dto.CartVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.order.CartService;
import com.green.biz.order.OrderService;

@Controller //������ controller�� ���
public class MypageController {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/cart_insert", method=RequestMethod.POST)
	public String cartInsert(CartVO vo, Model model, HttpSession session) {
		
		String url = "member/login";
		
		// ���ǿ� ����� ����� ������ �о�´�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) { // loginUser���� null �̸� ��α���
			return url;
		} else {
			vo.setId(loginUser.getId());
			
			// ��ٱ��� ������ ���� ���� ȣ��
			cartService.insertCart(vo);
			return "redirect:cart_list"; // ��ٱ��� ����� ��ȸ�Ͽ� ��ٱ��ϸ��ȭ�� ǥ��
		}
	}
	
	/* productList������ ��ٱ��� add */
	@ResponseBody
	@RequestMapping(value="/cart_insert_product", method=RequestMethod.POST)
	public Map<String,Object> cartInsertProduct(CartVO vo, Model model, HttpSession session) {
		Map<String,Object> resp = new HashMap<>();
		
		// String url = "member/login";
		
		// ���ǿ� ����� ����� ������ �о�´�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) { // loginUser���� null �̸� ��α���
			resp.put("ok", false);
			resp.put("url", "/login_form");
			return resp;
		} else {
			vo.setId(loginUser.getId());

			// ��ٱ��� ������ ���� ���� ȣ��
			cartService.insertCartProduct(vo);
			resp.put("ok", true);
			resp.put("url", "/cart_list");
			return resp; // ��ٱ��� ����� ��ȸ�Ͽ� ��ٱ��ϸ��ȭ�� ǥ��
		}
	}
	
	// ��ٱ��Ͽ� ��ǰ�� ��� ���� ���� ��ٱ��Ͽ� ��� ��� ���
	@RequestMapping(value="/cart_list")
	public String cartList(HttpSession session, Model model) {
		
		String url = "null";
		
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "member/login";
		} else {			
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			
			// �Ѿ� ���
			int totalAmount = 0;
			for (CartVO vo : cartList) { // �Ѿ� = ���� x ����
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
			
			url = "mypage/cartList";
		}
		
		return url;
	}
	
	@RequestMapping(value="/cart_delete")  // �ϳ��� ���� ����ö�
	//cseq�� �ϳ��� �迭�� ���� �迭���·� �ҷ����°� @RequestParam - ���� �� üũ�� �׸� ����
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) { 
		
		for (int i=0; i<cseq.length; i++) {
			//System.out.println("������ cart seq=" + cseq[i]);
			cartService.deleteCart(cseq[i]);
		}
		
		// ������ ������ �Ѱ� ���� �ٽ� ����Ʈ�� ��ȸ�� �� �ֵ���
		return "redirect:cart_list";
	}
	
	/**/
	@RequestMapping(value="/order_insert")
	public String orderInsert(OrderVO vo, HttpSession session, Model model) {
		
		String url = "redirect:order_list"; // �ֹ��Է��ϰ��� �ֹ�����Ʈ�� �����ִ� ȭ��
		int oseq = 0;
		
		//����� �α��� �Ǿ����� ������ �α��� ȭ������ �̵��Ѵ�.
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "member/login"; //�α����� �ȵǾ����� ���
		} else {
			vo.setId(loginUser.getId());
			
			oseq = orderService.insertOrder(vo);
			
			// �ֹ� ����� ǥ���� ��, �ֹ���ȣ�� �����Ѵ�. (�� �� orderList () ���� ��ȣ ���޹���)
			model.addAttribute("oseq", oseq);
		}
		
		return url; // �α����� �Ǿ����� ���
	}
	
	
	/*
	 * �ֹ����� ó��  
	 * 1. ��ٱ��� ���ȭ�鿡�� �ֹ��ϱ� ��ư Ŭ�� ��
	 * 2. ��α����̸� �α���ȭ���̵�
	 * 3. īƮ��� �ֹ������� orderDAO�� insertOrder() ȣ���Ͽ� �ֹ����̺� ����
	 * 4. �ֹ� ���� ���� ��, �ֹ�Ȯ�θ��(�ֹ���ȣ �Ķ���ͷ� ����)�� ǥ��
	 * 5. ����ȭ������ �̵��Ͽ� ȭ�鳻���� �����ش�
	*/ 
	@RequestMapping(value="/order_list")
	public String orderList(@RequestParam(value="oseq") int oseq, HttpSession session, 
				OrderVO order, Model model) {
		
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");  // ��ó���� ��
			
			// ����ں� ��ó�� �ֹ����� ��ȸ
			List<OrderVO> orderList = orderService.listOrderById(order);
			
			// �ֹ� �Ѿ� ���
			int totalAmount = 0;
			for (OrderVO vo : orderList) {
				// quantity * price2�ؼ� �Ѿ׺����� �־���
				totalAmount += (vo.getQuantity() * vo.getPrice2());
			}
			
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount); // �Ӽ��� totalPrice, ���� totalAmount
			return "mypage/orderList";
		}
	}
	
	
	/**/
	@RequestMapping(value="/mypage")
	public String myPageView(HttpSession session, Model model) {
		
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			// ����� id�� �������� ����ڰ� �ֹ��� ��� �ֹ���ȣ ��� ��ȸ
			OrderVO vo = new OrderVO();  // OrderVOŸ���� �� ��ü ����
			vo.setId(loginUser.getId()); // �α��ο��� ������ ���̵� ����
			vo.setResult("1");			
			List<Integer> oseqList = orderService.selectSeqOrdering(vo); // ������� ��� �ֹ���ȣ�� ���� ��.
			
			// �ֹ���� ���� ���� ����Ʈ ���� ����
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			
			// �� ��� �ֹ���ȣ�� ���� for������ �ݺ��Ͽ� �ֹ�������� ����
			for (int oseq : oseqList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setId(loginUser.getId());
				orderVO.setOseq(oseq);
				orderVO.setResult("1");
				
				// �� �ֹ���ȣ�� ���� �ֹ����� ��ȸ - ������ ����� orderVO��ü�� �����ͼ� �ֹ����� ����
				List<OrderVO> listByOseq = orderService.listOrderById(orderVO);
				
				// ������ ��ȸ�� �ֹ������� ������� ����
				OrderVO order = new OrderVO();
				order.setOseq(listByOseq.get(0).getOseq());
				order.setIndate(listByOseq.get(0).getIndate()); // listbyorder�� 1��°���� indate
				if(listByOseq.size() > 1) { //1���� ũ�� 2�� �̻��̶�� �Ŵϱ�
					order.setPname(listByOseq.get(0).getPname() + "��" + (listByOseq.size()-1) + "��");
				} else { //1���� ��쿡 �׳� ����
					order.setPname(listByOseq.get(0).getPname());
				}
				int amount = 0;
				for(int i=0; i<listByOseq.size(); i++) {
					amount += listByOseq.get(i).getQuantity() * listByOseq.get(i).getPrice2();
					// -1�� ���ִ� ������ �� +1���̴ϱ� 
				}
				order.setPrice2(amount);
				
				orderList.add(order);
			}
			model.addAttribute("title", "�������� �ֹ� ����");
			model.addAttribute("orderList", orderList);
		}
		return "mypage/mypage";
	}
	
	
	// �ֹ� ����� �ֹ���������ȸ 
	@RequestMapping(value="/order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			// "orderDetail"ȭ�鿡�� ��ȸ�� �ֹ���� orderList�� ù��° �����͸� ����
			vo.setId(loginUser.getId());
			vo.setResult("");
			List<OrderVO> orderList = orderService.listOrderById(vo);
			
			// �ֹ��� ���� ����
			OrderVO orderDetail = new OrderVO();
			
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());
			
			// �ֹ� �հ� �ݾ� ��� : ���ؼ� �������
			int amount = 0;
			for (int i=0; i<orderList.size(); i++) {
				amount += orderList.get(i).getQuantity() * orderList.get(i).getPrice2();
			}
			
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("orderList", orderList);
			model.addAttribute("title", "My Page(�ֹ� �� ����)");
			
			return "mypage/orderDetail";
		}
	}
	
	
	// �� �ֹ����� ��ȸó�� ����
	/* ó�� ����� ������� ������� ��� �ֹ��� ��ȸ */
	@RequestMapping(value="/order_all")
	public String orderAllView(OrderVO vo, HttpSession session, Model model) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			// ������� ��ü �ֹ���ȣ ��ȸ
			vo.setId(loginUser.getId());
			vo.setResult(""); // ó������� �������� ����
			
			List<Integer> oseqList = orderService.selectSeqOrdering(vo);
			
			// ��������� ������ List ���� ����
			List<OrderVO> orderList = new ArrayList<>();
			for(int oseq : oseqList) {
				// �ֹ���� ��ȸ�� ���� �Է� ����
				OrderVO orderVo = new OrderVO();
				orderVo.setId(loginUser.getId());
				orderVo.setOseq(oseq);
				orderVo.setResult("");
				
				// �ֹ���ȣ�� �ֹ����� : �ֹ� �ϳ��� ���� ����
				List<OrderVO> orders = orderService.listOrderById(orderVo);
				
				// �ֹ� ������� ����
				OrderVO summary = new OrderVO();
				summary = orders.get(0); // summary�� ù��°�� �ش��ϴ� vo������ ī���ϰ�
				if(orders.size()>1) {
					summary.setPname(summary.getPname() + "��" + (orders.size()-1) + "��");
				} else {
					summary.setPname(summary.getPname());
				}
				
				int amount = 0;
				for (OrderVO order : orders) {
					amount += order.getQuantity() * order.getPrice2();
				}
				summary.setPrice2(amount);
				
				// �ֹ� ��������� ����Ʈ�� �߰�
				orderList.add(summary);
			}
			
			model.addAttribute("title", "�� �ֹ�����");
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";			
		}
	}
	
	
}
