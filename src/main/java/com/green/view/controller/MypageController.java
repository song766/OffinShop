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

@Controller //스프링 controller로 등록
public class MypageController {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/cart_insert", method=RequestMethod.POST)
	public String cartInsert(CartVO vo, Model model, HttpSession session) {
		
		String url = "member/login";
		
		// 세션에 저장된 사용자 정보를 읽어온다
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) { // loginUser값이 null 이면 비로그인
			return url;
		} else {
			vo.setId(loginUser.getId());
			
			// 장바구니 저장을 위해 서비스 호출
			cartService.insertCart(vo);
			return "redirect:cart_list"; // 장바구니 목록을 조회하여 장바구니목록화면 표시
		}
	}
	
	/* productList에서의 장바구니 add */
	@ResponseBody
	@RequestMapping(value="/cart_insert_product", method=RequestMethod.POST)
	public Map<String,Object> cartInsertProduct(CartVO vo, Model model, HttpSession session) {
		Map<String,Object> resp = new HashMap<>();
		
		// String url = "member/login";
		
		// 세션에 저장된 사용자 정보를 읽어온다
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) { // loginUser값이 null 이면 비로그인
			resp.put("ok", false);
			resp.put("url", "/login_form");
			return resp;
		} else {
			vo.setId(loginUser.getId());

			// 장바구니 저장을 위해 서비스 호출
			cartService.insertCartProduct(vo);
			resp.put("ok", true);
			resp.put("url", "/cart_list");
			return resp; // 장바구니 목록을 조회하여 장바구니목록화면 표시
		}
	}
	
	// 장바구니에 상품을 담고 나면 현재 장바구니에 담긴 목록 출력
	@RequestMapping(value="/cart_list")
	public String cartList(HttpSession session, Model model) {
		
		String url = "null";
		
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "member/login";
		} else {			
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			
			// 총액 계산
			int totalAmount = 0;
			for (CartVO vo : cartList) { // 총액 = 수량 x 가격
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
			
			url = "mypage/cartList";
		}
		
		return url;
	}
	
	@RequestMapping(value="/cart_delete")  // 하나씩 값을 갖고올때
	//cseq의 하나의 배열의 값을 배열형태로 불러오는게 @RequestParam - 삭제 시 체크된 항목만 저장
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) { 
		
		for (int i=0; i<cseq.length; i++) {
			//System.out.println("삭제할 cart seq=" + cseq[i]);
			cartService.deleteCart(cseq[i]);
		}
		
		// 위에서 삭제를 한거 빼고 다시 리스트를 조회할 수 있도록
		return "redirect:cart_list";
	}
	
	/**/
	@RequestMapping(value="/order_insert")
	public String orderInsert(OrderVO vo, HttpSession session, Model model) {
		
		String url = "redirect:order_list"; // 주문입력하고나서 주문리스트를 보여주는 화면
		int oseq = 0;
		
		//사용자 로그인 되어있지 않으면 로그인 화면으로 이동한다.
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "member/login"; //로그인이 안되어있을 경우
		} else {
			vo.setId(loginUser.getId());
			
			oseq = orderService.insertOrder(vo);
			
			// 주문 목록을 표시할 때, 주문번호를 전달한다. (이 후 orderList () 에서 번호 전달받음)
			model.addAttribute("oseq", oseq);
		}
		
		return url; // 로그인이 되어있을 경우
	}
	
	
	/*
	 * 주문내역 처리  
	 * 1. 장바구니 목록화면에서 주문하기 버튼 클릭 후
	 * 2. 비로그인이면 로그인화면이동
	 * 3. 카트목록 주문내역을 orderDAO의 insertOrder() 호출하여 주문테이블에 저장
	 * 4. 주문 내역 저장 후, 주문확인목록(주문번호 파라미터로 전송)을 표시
	 * 5. 다음화면으로 이동하여 화면내용을 보여준다
	*/ 
	@RequestMapping(value="/order_list")
	public String orderList(@RequestParam(value="oseq") int oseq, HttpSession session, 
				OrderVO order, Model model) {
		
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");  // 미처리된 것
			
			// 사용자별 미처리 주문내역 조회
			List<OrderVO> orderList = orderService.listOrderById(order);
			
			// 주문 총액 계산
			int totalAmount = 0;
			for (OrderVO vo : orderList) {
				// quantity * price2해서 총액변수에 넣어줌
				totalAmount += (vo.getQuantity() * vo.getPrice2());
			}
			
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount); // 속성이 totalPrice, 값은 totalAmount
			return "mypage/orderList";
		}
	}
	
	
	/**/
	@RequestMapping(value="/mypage")
	public String myPageView(HttpSession session, Model model) {
		
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			// 사용자 id를 조건으로 사용자가 주문한 모든 주문번호 목록 조회
			OrderVO vo = new OrderVO();  // OrderVO타입의 새 객체 생성
			vo.setId(loginUser.getId()); // 로그인에서 가져온 아이디 설정
			vo.setResult("1");			
			List<Integer> oseqList = orderService.selectSeqOrdering(vo); // 사용자의 모든 주문번호를 갖고 옴.
			
			// 주문요약 정보 저장 리스트 변수 생성
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			
			// 각 모든 주문번호에 대해 for문으로 반복하여 주문요약정보 생성
			for (int oseq : oseqList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setId(loginUser.getId());
				orderVO.setOseq(oseq);
				orderVO.setResult("1");
				
				// 각 주문번호에 대해 주문내역 조회 - 위에서 담아준 orderVO객체를 가져와서 주문내역 생성
				List<OrderVO> listByOseq = orderService.listOrderById(orderVO);
				
				// 위에서 조회한 주문내역의 요약정보 생성
				OrderVO order = new OrderVO();
				order.setOseq(listByOseq.get(0).getOseq());
				order.setIndate(listByOseq.get(0).getIndate()); // listbyorder의 1번째꺼에 indate
				if(listByOseq.size() > 1) { //1보다 크면 2개 이상이라는 거니까
					order.setPname(listByOseq.get(0).getPname() + "외" + (listByOseq.size()-1) + "건");
				} else { //1건일 경우에 그냥 써줌
					order.setPname(listByOseq.get(0).getPname());
				}
				int amount = 0;
				for(int i=0; i<listByOseq.size(); i++) {
					amount += listByOseq.get(i).getQuantity() * listByOseq.get(i).getPrice2();
					// -1을 해주는 이유는 외 +1건이니까 
				}
				order.setPrice2(amount);
				
				orderList.add(order);
			}
			model.addAttribute("title", "진행중인 주문 내역");
			model.addAttribute("orderList", orderList);
		}
		return "mypage/mypage";
	}
	
	
	// 주문 목록의 주문상세정보조회 
	@RequestMapping(value="/order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			// "orderDetail"화면에서 조회한 주문목록 orderList의 첫번째 데이터를 저장
			vo.setId(loginUser.getId());
			vo.setResult("");
			List<OrderVO> orderList = orderService.listOrderById(vo);
			
			// 주문자 정보 생성
			OrderVO orderDetail = new OrderVO();
			
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());
			
			// 주문 합계 금액 계산 : 곱해서 누적계산
			int amount = 0;
			for (int i=0; i<orderList.size(); i++) {
				amount += orderList.get(i).getQuantity() * orderList.get(i).getPrice2();
			}
			
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("orderList", orderList);
			model.addAttribute("title", "My Page(주문 상세 정보)");
			
			return "mypage/orderDetail";
		}
	}
	
	
	// 총 주문내역 조회처리 보기
	/* 처리 결과에 관계없이 사용자의 모든 주문을 조회 */
	@RequestMapping(value="/order_all")
	public String orderAllView(OrderVO vo, HttpSession session, Model model) {
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			// 사용자의 전체 주문번호 조회
			vo.setId(loginUser.getId());
			vo.setResult(""); // 처리결과를 지정하지 않음
			
			List<Integer> oseqList = orderService.selectSeqOrdering(vo);
			
			// 요악정보를 저장할 List 변수 생성
			List<OrderVO> orderList = new ArrayList<>();
			for(int oseq : oseqList) {
				// 주문목록 조회를 위한 입력 설정
				OrderVO orderVo = new OrderVO();
				orderVo.setId(loginUser.getId());
				orderVo.setOseq(oseq);
				orderVo.setResult("");
				
				// 주문번호별 주문내역 : 주문 하나에 대한 내역
				List<OrderVO> orders = orderService.listOrderById(orderVo);
				
				// 주문 요약정보 생성
				OrderVO summary = new OrderVO();
				summary = orders.get(0); // summary에 첫번째에 해당하는 vo정보를 카피하고
				if(orders.size()>1) {
					summary.setPname(summary.getPname() + "외" + (orders.size()-1) + "건");
				} else {
					summary.setPname(summary.getPname());
				}
				
				int amount = 0;
				for (OrderVO order : orders) {
					amount += order.getQuantity() * order.getPrice2();
				}
				summary.setPrice2(amount);
				
				// 주문 요약정보를 리스트에 추가
				orderList.add(summary);
			}
			
			model.addAttribute("title", "총 주문내역");
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";			
		}
	}
	
	
}
